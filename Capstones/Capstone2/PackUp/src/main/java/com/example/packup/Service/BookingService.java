package com.example.packup.Service;

import com.example.packup.Model.Booking;
import com.example.packup.Model.Space;
import com.example.packup.Model.User;
import com.example.packup.Repository.BookingRepository;
import com.example.packup.Repository.SpaceRepository;
import com.example.packup.Repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service

@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    private final SpaceRepository spaceRepository;
    private final UserRepository userRepository;

    private final SpaceService spaceService;
    private final UserService userService;

    private final NotificationService notificationService;

    //CRUD

    //READ
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    //CREATE
    public int createBooking(Booking booking){
        Space space = spaceRepository.findSpaceById(booking.getSpaceId());
        if(space == null){
            return -1;
        }
        if(userRepository.findUserById(booking.getRenterId()) == null){
            return -2;
        }
        if(userService.isBlocked(space.getOwnerId(), booking.getRenterId())){
            return -3; //renter is blocked by this space's owner
        }
        if(space.getStatus().equalsIgnoreCase("unavailable")){
            return -4;
        }
        List<Booking> existingBookings = bookingRepository.findBookingBySpaceIdAndStatus(booking.getSpaceId(),"BOOKED");
        for(Booking booking1:existingBookings){
            if(booking.getStartDate().compareTo(booking1.getEndDate()) <= 0 && booking.getEndDate().compareTo(booking1.getStartDate()) >= 0){
                return -5; //if there's conflicts in dates
            }
        }
        long numOfDays = ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
        double total = numOfDays * space.getPricePerDay();

        boolean eligibleForDiscount = false;
        List<Space> ownerSpaces = spaceRepository.findSpaceByOwnerId(space.getOwnerId());
        for(Space s : ownerSpaces){
            List<Booking> completed = bookingRepository.findBookingBySpaceIdAndStatus(s.getId(),"COMPLETED");
            for(Booking b : completed){
                if(b.getRenterId().equals(booking.getRenterId())){
                    eligibleForDiscount = true;
                    break;
                }
            }
            if(eligibleForDiscount) break;
        }

        if(eligibleForDiscount){
            total = total * 0.8;
        }
        booking.setTotalPrice(total);

        if(booking.getPaidAmount() > (booking.getTotalPrice()/2) && booking.getPaidAmount() <= booking.getTotalPrice() ){
            booking.setStatus("BOOKED");
            bookingRepository.save(booking);

            User renter = userRepository.findUserById(booking.getRenterId());
            notificationService.sendWhatsApp(renter.getPhone(),
                    "✅ *تم تأكيد حجزك*\n" +
                            "رقم الحجز: #" + booking.getId() + "\n" +
                            "💰 المبلغ الإجمالي: " + booking.getTotalPrice() + " ريال\n" +
                            "—————————\n" +
                            "✅ *Booking Confirmed*\n" +
                            "Booking #" + booking.getId() + "\n" +
                            "💰 Total Amount: " + booking.getTotalPrice() + " SAR");
            return 0;
        }
        return -6;
    }

    //UPDATE
    public int updateBooking(Integer id, Booking booking) {
        Booking booking1 = bookingRepository.findBookingById(id);
        if (booking1 == null) {
            return -1; //no booking with this id
        }

        if (!booking1.getStatus().equalsIgnoreCase("BOOKED")) {
            return -2; //can only update a booking that's BOOKED
        }

        if (!LocalDate.now().isBefore(booking1.getStartDate())) {
            return -3; //too late, booking already started
        }

        LocalDate newEndDate = booking.getEndDate();
        if (newEndDate == null || !newEndDate.isAfter(booking1.getStartDate())) {
            return -4; //end date must be after start date
        }

        List<Booking> existingBookings = bookingRepository.findBookingBySpaceIdAndStatus(booking1.getSpaceId(), "BOOKED");
        for (Booking existing : existingBookings) {
            if (existing.getId().equals(booking1.getId())) {
                continue; //skip comparing the booking with itself
            }
            if (booking1.getStartDate().compareTo(existing.getEndDate()) <= 0 &&
                    newEndDate.compareTo(existing.getStartDate()) >= 0) {
                return -5; //new end date conflicts with another booking
            }
        }

        Space space = spaceRepository.findSpaceById(booking1.getSpaceId());
        long numOfDays = ChronoUnit.DAYS.between(booking1.getStartDate(), newEndDate);
        double newTotal = numOfDays * space.getPricePerDay();

        booking1.setEndDate(newEndDate);
        booking1.setTotalPrice(newTotal);
        bookingRepository.save(booking1);
        return 0;
    }

    //DELETE
    public boolean deleteBooking(Integer id){
        Booking booking= bookingRepository.findBookingById(id);
        if(booking == null){
            return false;
        }
        bookingRepository.delete(booking);
        return true;
    }

    //get All bookings for the user as a RENTER (1/15)
    public List<Booking> renterBookings(Integer renterId){
        if(userRepository.findUserById(renterId)==null){
            return null; //no user with this id
        }
        List<Booking> bookings = bookingRepository.findBookingByRenterId(renterId);
        return bookings;
    }


    //calculate all revenue for a user (7/15)
    public double ownerProfits(Integer ownerId){
        if(userRepository.findUserById(ownerId) == null){
            return -1;
        }
        List<Space> ownerSpaces= spaceService.ownerSpaces(ownerId);
        double profits=0.0;

        List<Booking> spaceBookings;
        for(Space space : ownerSpaces){
            List<Booking> completedBookings = bookingRepository.findBookingBySpaceIdAndStatus(space.getId(), "COMPLETED");
            for (Booking booking : completedBookings) {
                profits += booking.getTotalPrice();
            }
        }
        return profits;
    }

    //cancel booking (8/15)
    public int cancelBooking(Integer bookingId, Integer renterId){
        Booking booking = bookingRepository.findBookingById(bookingId);
        if(booking == null){
            return -1; //no booking
        }
        if(userRepository.findUserById(renterId) == null){
            return -2;//no user
        }
        List<Booking> bookings= bookingRepository.findBookingByRenterIdAndStatus(renterId,"BOOKED");
        if(! (bookings.contains(booking)) ){
            return -3; //status of booking should be booked, not completed or onGoing booking
        }
        if(! (LocalDate.now().isBefore(booking.getStartDate())) ){
            return -4;
        }
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        User renter = userRepository.findUserById(booking.getRenterId());
        notificationService.sendWhatsApp(renter.getPhone(),
                "❌ *تم إلغاء حجزك*\n" +
                        "رقم الحجز: #" + booking.getId() + "\n" +
                        "—————————\n" +
                        "❌ *Booking Cancelled*\n" +
                        "Booking #" + booking.getId());
        return 0;
    }

    //check if available (9/15)
    public int checkAvailability(Integer spaceId, LocalDate startDate, LocalDate endDate) {
        Space space = spaceRepository.findSpaceById(spaceId);
        if(space == null){
            return -1;
        }
        if(startDate.isAfter(endDate)){
            return -3;
        }
        if(space.getStatus().equalsIgnoreCase("UNAVAILABLE")){
            return -4; //space is UNAVAILABLE
        }
        List<Booking> existingBookings = bookingRepository.findBookingBySpaceIdAndStatus(spaceId, "BOOKED");
        for (Booking existing : existingBookings) {
            if (startDate.compareTo(existing.getEndDate()) <= 0 && endDate.compareTo(existing.getStartDate()) >= 0) {
                return -2;
            }
        }
        return 0;
    }

    //get all bookings for specific space (10/15)
    public List<Booking> spaceBookings( Integer spaceId){
        if(spaceRepository.findSpaceById(spaceId) == null){
            return null;
        }
        List<Booking> bookings = bookingRepository.findBookingBySpaceId(spaceId);
        return bookings;
    }

    //extend duration of rent (11/15)
    public int extendBooking(Integer bookingId, Integer renterId, LocalDate newEndDate) {
        Booking booking1 = bookingRepository.findBookingById(bookingId);
        if (booking1 == null) {
            return -1; //no booking with this id
        }
        if (!booking1.getRenterId().equals(renterId)) {
            return -2; //this is not your booking
        }
        if (!(booking1.getStatus().equalsIgnoreCase("BOOKED") || booking1.getStatus().equalsIgnoreCase("ONGOING"))) {
            return -3; //can only extend a booking BOOKED or ONGOING
        }
        if (newEndDate == null || !newEndDate.isAfter(booking1.getEndDate())) {
            return -4; //new end date must be after the current end date
        }

        List<Booking> existingBookings = bookingRepository.findBookingBySpaceIdAndStatus(booking1.getSpaceId(), "BOOKED");
        existingBookings.addAll(bookingRepository.findBookingBySpaceIdAndStatus(booking1.getSpaceId(), "ONGOING"));
        for (Booking existing : existingBookings) {
            if (existing.getId().equals(booking1.getId())) {
                continue;
            }
            if (booking1.getStartDate().compareTo(existing.getEndDate()) <= 0 && newEndDate.compareTo(existing.getStartDate()) >= 0) {
                return -5; //new end date conflicts with another booking
            }
        }

        Space space = spaceRepository.findSpaceById(booking1.getSpaceId());
        long numOfDays = ChronoUnit.DAYS.between(booking1.getStartDate(), newEndDate);
        double newTotal = numOfDays * space.getPricePerDay();

        booking1.setEndDate(newEndDate);
        booking1.setTotalPrice(newTotal);
        bookingRepository.save(booking1);

        User renter = userRepository.findUserById(renterId);
        User owner = userRepository.findUserById(space.getOwnerId());

        notificationService.sendWhatsApp(renter.getPhone(),
                "📅 *تم تمديد حجزك بناءً على طلبك*\n" +
                        "رقم الحجز: #" + booking1.getId() + "\n" +
                        "🏠 المساحة: " + space.getTitle() + "\n" +
                        "👤 المالك: " + owner.getFullName() + "\n" +
                        "—————————\n" +
                        "📅 *Booking Extended as Requested*\n" +
                        "Booking #" + booking1.getId() + "\n" +
                        "🏠 Space: " + space.getTitle() + "\n" +
                        "👤 Owner: " + owner.getFullName());

        return 0;
    }

    //activate booking (change status into ongoing) (12/15)
    public int updateBookingStatuses(){
        List<Booking> bookedBookings = bookingRepository.findBookingByStatus("BOOKED");
        int updatedCount = 0;

        for(Booking booking : bookedBookings){
            if(!LocalDate.now().isBefore(booking.getStartDate())){
                booking.setStatus("ONGOING");
                bookingRepository.save(booking);
                updatedCount++;
            }
        }
        return updatedCount;
    }

    //change status into complete  (13/15)
    public int completeBooking(Integer bookingId, Integer renterId, Double newAmount){
        Booking booking = bookingRepository.findBookingById(bookingId);
        if (booking == null) {
            return -1; //no booking with this id
        }
        if (!booking.getRenterId().equals(renterId)) {
            return -2; //this is not your booking
        }
        if (userRepository.findUserById(renterId) == null) {
            return -3; //no user with this id
        }
        if (!(booking.getStatus().equalsIgnoreCase("BOOKED") || booking.getStatus().equalsIgnoreCase("ONGOING"))) {
            return -4; //already completed or cancelled
        }

        double alreadyPaid = booking.getPaidAmount();
        double newPaid = alreadyPaid + newAmount;

        if (newPaid < booking.getTotalPrice()) {
            return -5; //still remaining amount, must pay the total to complete
        }

        double remaining = booking.getTotalPrice() - alreadyPaid;
        if (newAmount > remaining) {
            return -6; //amount exceeds what's actually remaining
        }

        booking.setPaidAmount(booking.getTotalPrice());
        booking.setStatus("COMPLETED");
        bookingRepository.save(booking);

        Space space = spaceRepository.findSpaceById(booking.getSpaceId());
        User owner = userRepository.findUserById(space.getOwnerId());
        User renter = userRepository.findUserById(booking.getRenterId());

        notificationService.sendWhatsApp(renter.getPhone(),
                "*تم إكمال حجزك بنجاح!*\n" +
                        "رقم الحجز: #" + booking.getId() + "\n" +
                        "شكرًا لاستخدامك *PackUp* \n" +
                        "🎁 أنت الآن مؤهل لخصم 20% على حجوزاتك القادمة مع " + owner.getFullName() + "\n" +
                        "⭐ لا تنسَ تقيّم تجربتك من خلال التطبيق\n" +
                        "—————————\n" +
                        "🎉 *Your booking has been completed!*\n" +
                        "Booking #" + booking.getId() + "\n" +
                        "Thank you for using *PackUp* \n" +
                        "🎁 You're now eligible for a 20% discount on future bookings with " + owner.getFullName() + "\n" +
                        "⭐ Don't forget to rate your experience through the app");

        return 0;
    }

    //get all discount users (14/15)
    public List<Integer> loyaltyDiscountUsers(Integer ownerId){
        if(userRepository.findUserById(ownerId) == null){
            return null; // no owner with this id
        }
        List<Space> ownerSpaces = spaceService.ownerSpaces(ownerId);
        List<Integer> loyaltyRenters = new ArrayList<>();

        for(Space space : ownerSpaces){
            List<Booking> completedBookings = bookingRepository.findBookingBySpaceIdAndStatus(space.getId(), "COMPLETED");
            for(Booking booking : completedBookings){
                if(!loyaltyRenters.contains(booking.getRenterId())){
                    loyaltyRenters.add(booking.getRenterId());
                }
            }
        }
        return loyaltyRenters;
    }



}

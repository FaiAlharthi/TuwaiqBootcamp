package com.example.packup.Controller;


import com.example.packup.Api.ApiResponse;
import com.example.packup.Model.Booking;
import com.example.packup.Service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor

public class BookingController {
    final private BookingService bookingService;

    //CRUD
    @GetMapping("/allBookings")
    public ResponseEntity<?> getAllBookings (){
        if(bookingService.getAllBookings().isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("No Bookings yet"));
        }
        return ResponseEntity.status(200).body(bookingService.getAllBookings());
    }

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(@RequestBody @Valid Booking booking, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int created= bookingService.createBooking(booking);

        if(created == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No Space with this ID "));
        }
        if(created == -2){
            return ResponseEntity.status(400).body(new ApiResponse("No renter with this ID "));
        }
        if(created == -3){
            return ResponseEntity.status(400).body(new ApiResponse("You are blocked from booking this owner's spaces "));
        }
        if(created == -4){
            return ResponseEntity.status(400).body(new ApiResponse("This Space is Unavailable "));
        }
        if(created == -5){
            return ResponseEntity.status(400).body(new ApiResponse("This space is unavailable in this date "));
        }
        if(created == -6){
            return ResponseEntity.status(400).body(new ApiResponse("You have to pay at least half of the total price and not exceeding total price "));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Booking created successfully"));
    }

    @PutMapping("/updateBooking/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Integer id, @RequestBody @Valid Booking booking, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int updated= bookingService.updateBooking(id,booking);
        if(updated == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No Booking with this ID "));
        }
        if(updated == -2){
            return ResponseEntity.status(400).body(new ApiResponse("You can only update a BOOKED booking "));
        }
        if(updated == -3){
            return ResponseEntity.status(400).body(new ApiResponse("Too late to update, booking already started"));
        }
        if(updated == -4){
            return ResponseEntity.status(400).body(new ApiResponse("End date must be after the start date "));
        }
        if(updated == -5){
            return ResponseEntity.status(400).body(new ApiResponse("This end date conflicts with another booking "));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Booking updated successfully"));
    }

    @DeleteMapping("/deleteBooking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Integer id){
        if(bookingService.deleteBooking(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Booking deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No Booking with this Id"));
    }

    @GetMapping("/renterBookings/{renterId}")
    public ResponseEntity<?> renterBookings(@PathVariable Integer renterId){
        List<Booking> bookings = bookingService.renterBookings(renterId);
        if(bookings==null){
            return ResponseEntity.status(400).body(new ApiResponse("no user with this Id"));
        }
        if(bookings.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No Booking with this Id"));
        }
        return ResponseEntity.status(200).body(bookings);
    }

    @GetMapping("/calculateProfits/{ownerId}")
    public ResponseEntity<?> ownerProfits(@PathVariable Integer ownerId){
        double profits = bookingService.ownerProfits(ownerId);
        if(profits == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No user with this id"));
        }
        return ResponseEntity.status(200).body(profits);
    }


    @PutMapping("/cancelBooking/{bookingId}/{renterId}")
    public ResponseEntity<?> cancelBooking ( @PathVariable Integer bookingId,@PathVariable Integer renterId){
        int cancelled = bookingService.cancelBooking(bookingId,renterId);
        if(cancelled == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No booking with this id"));
        }
        if(cancelled == -2){
            return ResponseEntity.status(400).body(new ApiResponse("No user with this id"));
        }
        if(cancelled == -3){
            return ResponseEntity.status(400).body(new ApiResponse("status of booking should be booked, not completed or onGoing booking"));
        }
        if(cancelled == -4){
            return ResponseEntity.status(400).body(new ApiResponse("too late to cancel, booking already started"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Booking cancelled successfully"));

    }

    @GetMapping("/checkAvailability/{spaceId}/{startDate}/{endDate}")
    public ResponseEntity<?> checkAvailability(@PathVariable Integer spaceId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        int available = bookingService.checkAvailability(spaceId,startDate,endDate);
        if(available == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No space with this id"));
        }
        if(available == -2){
            return ResponseEntity.status(400).body(new ApiResponse("space is booked on this date"));
        }
        if(available == -3){
            return ResponseEntity.status(400).body(new ApiResponse("start date should be before end date"));
        }
        if(available == -4){
            return ResponseEntity.status(400).body(new ApiResponse("This space is currently unavailable "));
        }
        return ResponseEntity.status(200).body(new ApiResponse("space is available on this date"));

    }

    @GetMapping("/getSpaceBookings/{spaceId}")
    public ResponseEntity<?> spaceBookings(@PathVariable Integer spaceId ){
        List<Booking>bookings= bookingService.spaceBookings(spaceId);
        if(bookings == null){
            return ResponseEntity.status(400).body(new ApiResponse("No space with this id"));
        }
        if(bookings.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No bookings for this space"));
        }
        return ResponseEntity.status(200).body(bookings);

    }

    @PutMapping("/extendBooking/{bookingId}/{renterId}/{newEndDate}")
    public ResponseEntity<?> extendBooking(@PathVariable Integer bookingId, @PathVariable Integer renterId, @PathVariable LocalDate newEndDate){
        int result = bookingService.extendBooking(bookingId, renterId, newEndDate);

        if(result == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No Booking with this ID"));
        }
        if(result == -2){
            return ResponseEntity.status(400).body(new ApiResponse("This is not your booking"));
        }
        if(result == -3){
            return ResponseEntity.status(400).body(new ApiResponse("You can only extend a booking that is still BOOKED"));
        }
        if(result == -4){
            return ResponseEntity.status(400).body(new ApiResponse("New end date must be after the current end date"));
        }
        if(result == -5){
            return ResponseEntity.status(400).body(new ApiResponse("New end date must be after the start date"));
        }
        if(result == -6){
            return ResponseEntity.status(400).body(new ApiResponse("This new end date conflicts with another booking"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Booking extended successfully"));
    }


    @PutMapping("/updateBookingStatuses")
    public ResponseEntity<?> updateBookingStatuses(){
        int updatedCount = bookingService.updateBookingStatuses();
        return ResponseEntity.status(200).body(new ApiResponse(updatedCount + " booking updated to ONGOING"));
    }

    @PutMapping("/completeBooking/{bookingId}/{renterId}/{newAmount}")
    public ResponseEntity<?> completeBooking(@PathVariable Integer bookingId, @PathVariable Integer renterId, @PathVariable Double newAmount){
        int result = bookingService.completeBooking(bookingId, renterId, newAmount);
        if(result == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No Booking with this ID"));
        }
        if(result == -2){
            return ResponseEntity.status(400).body(new ApiResponse("This is not your booking"));
        }
        if(result == -3){
            return ResponseEntity.status(400).body(new ApiResponse("No user with this ID"));
        }
        if(result == -4){
            return ResponseEntity.status(400).body(new ApiResponse("Booking is already completed or cancelled"));
        }
        if(result == -5){
            return ResponseEntity.status(400).body(new ApiResponse("You still have a remaining amount to pay"));
        }
        if(result == -6){
            return ResponseEntity.status(400).body(new ApiResponse("Amount exceeds the remaining balance"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Booking completed successfully"));
    }

    @GetMapping("/loyaltyDiscountUsers/{ownerId}")
    public ResponseEntity<?> loyaltyDiscountUsers(@PathVariable Integer ownerId){
        List<Integer> renters = bookingService.loyaltyDiscountUsers(ownerId);
        if(renters == null){
            return ResponseEntity.status(400).body(new ApiResponse("No owner with this id"));
        }
        if(renters.isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("No renters eligible for discount yet"));
        }
        return ResponseEntity.status(200).body(renters);
    }

}

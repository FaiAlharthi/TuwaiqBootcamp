package com.example.packup.Controller;


import com.example.packup.Api.ApiResponse;
import com.example.packup.Model.Booking;
import com.example.packup.Service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createBooking(@RequestBody @Valid Booking booking){
        bookingService.createBooking(booking);
        return ResponseEntity.status(200).body(new ApiResponse("Booking created successfully"));
    }

    @PutMapping("/updateBooking/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Integer id, @RequestBody @Valid Booking booking){
        bookingService.updateBooking(id,booking);
        return ResponseEntity.status(200).body(new ApiResponse("Booking updated successfully"));
    }

    @DeleteMapping("/deleteBooking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Integer id){
        bookingService.deleteBooking(id);
        return ResponseEntity.status(200).body(new ApiResponse("Booking deleted successfully"));
    }

    @GetMapping("/renterBookings/{renterId}")
    public ResponseEntity<?> renterBookings(@PathVariable Integer renterId){
        List<Booking> bookings = bookingService.renterBookings(renterId);
        return ResponseEntity.status(200).body(bookings);
    }

    @GetMapping("/calculateProfits/{ownerId}")
    public ResponseEntity<?> ownerProfits(@PathVariable Integer ownerId){
        Double profits = bookingService.ownerProfits(ownerId);
        return ResponseEntity.status(200).body(profits);
    }


    @PutMapping("/cancelBooking/{bookingId}/{renterId}")
    public ResponseEntity<?> cancelBooking ( @PathVariable Integer bookingId,@PathVariable Integer renterId){
        bookingService.cancelBooking(bookingId,renterId);
        return ResponseEntity.status(200).body(new ApiResponse("Booking cancelled successfully"));

    }

    @GetMapping("/checkAvailability/{spaceId}/{startDate}/{endDate}")
    public ResponseEntity<?> checkAvailability(@PathVariable Integer spaceId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        bookingService.checkAvailability(spaceId,startDate,endDate);
        return ResponseEntity.status(200).body(new ApiResponse("space is available on this date"));

    }

    @GetMapping("/getSpaceBookings/{spaceId}")
    public ResponseEntity<?> spaceBookings(@PathVariable Integer spaceId ){
        List<Booking>bookings= bookingService.spaceBookings(spaceId);
        return ResponseEntity.status(200).body(bookings);

    }

    @PutMapping("/extendBooking/{bookingId}/{renterId}/{newEndDate}")
    public ResponseEntity<?> extendBooking(@PathVariable Integer bookingId, @PathVariable Integer renterId, @PathVariable LocalDate newEndDate){
        bookingService.extendBooking(bookingId, renterId, newEndDate);
        return ResponseEntity.status(200).body(new ApiResponse("Booking extended successfully"));
    }


    @PutMapping("/updateBookingStatuses")
    public ResponseEntity<?> updateBookingStatuses(){
        Integer updatedCount = bookingService.updateBookingStatuses();
        return ResponseEntity.status(200).body(new ApiResponse(updatedCount + " booking updated to ONGOING"));
    }

    @PutMapping("/completeBooking/{bookingId}/{renterId}/{newAmount}")
    public ResponseEntity<?> completeBooking(@PathVariable Integer bookingId, @PathVariable Integer renterId, @PathVariable Double newAmount){
        bookingService.completeBooking(bookingId, renterId, newAmount);
        return ResponseEntity.status(200).body(new ApiResponse("Booking completed successfully"));
    }

    @GetMapping("/loyaltyDiscountUsers/{ownerId}")
    public ResponseEntity<?> loyaltyDiscountUsers(@PathVariable Integer ownerId){
        List<Integer> renters = bookingService.loyaltyDiscountUsers(ownerId);
        if(renters.isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("No renters eligible for discount yet"));
        }
        return ResponseEntity.status(200).body(renters);
    }

}

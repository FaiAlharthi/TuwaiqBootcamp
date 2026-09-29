package com.example.packup.Repository;

import com.example.packup.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBookingById(Integer Id);
    List<Booking> findBookingBySpaceIdAndStatus(Integer spaceId, String Status);
    List<Booking> findBookingBySpaceId(Integer spaceId);
    List<Booking> findBookingByRenterId(Integer renterId);
    List<Booking> findBookingByRenterIdAndStatus(Integer renterId, String status);
    List<Booking> findBookingByStatus(String status);

}

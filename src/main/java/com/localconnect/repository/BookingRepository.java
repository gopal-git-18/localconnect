package com.localconnect.repository;

import com.localconnect.entity.Booking;
import com.localconnect.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByCustomerId(Integer customerId);
    List<Booking> findByProviderId(Integer providerId);
    List<Booking> findByStatus(BookingStatus status);
}
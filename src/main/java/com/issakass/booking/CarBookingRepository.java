package com.issakass.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public interface CarBookingRepository extends JpaRepository<CarBooking, UUID> {

	List<CarBooking> findByUserId(UUID userId);
}

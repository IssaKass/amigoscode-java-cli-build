package com.issakass.booking;

import com.issakass.car.Car;

import java.util.List;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
public interface CarBookingDAO {
	List<CarBooking> getCarBookings();

	void cancelCarBooking(UUID id);

	List<Car> getUserBookedCars(UUID userId);

	UUID save(CarBooking carBooking);
}

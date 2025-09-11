package com.issakass.car;

import com.issakass.booking.CarBooking;

import java.util.List;
import java.util.Optional;

/**
 * Author: abdallah-issakass
 */
public interface CarDAO {
	List<Car> getAllCars();

	List<Car> getAllElectricalCars();

	Optional<Car> getCar(String regNumber);

	List<Car> getAvailableCars(List<CarBooking> carBookings);

	List<Car> getAvailableElectricCars(List<CarBooking> carBookings);

	List<Car> getBookedCars(List<CarBooking> carBookings);
}

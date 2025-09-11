package com.issakass.car;

import com.issakass.booking.CarBooking;
import com.issakass.booking.CarBookingDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: abdallah-issakass
 */
@Service
public class CarService {

	private final CarDAO carDAO;
	private final CarBookingDAO carBookingDAO;

	public CarService(@Qualifier("carJpaDAO") CarDAO carDAO,
	                  @Qualifier("carBookingJpaDAO") CarBookingDAO carBookingDAO) {
		this.carDAO = carDAO;
		this.carBookingDAO = carBookingDAO;
	}

	public List<Car> getAllCars() {
		return carDAO.getAllCars();
	}

	public List<Car> getAllElectricCars() {
		return carDAO.getAllElectricalCars();
	}

	public List<Car> getAvailableCars(Boolean electric) {
		List<CarBooking> bookings = carBookingDAO.getCarBookings();

		if (electric != null) {
			return carDAO.getAvailableElectricCars(bookings);
		}

		return carDAO.getAvailableCars(bookings);
	}

	public Car getCar(String regNumber) {
		return carDAO.getCar(regNumber)
				.orElseThrow(() -> new CarNotAvailableException(String.format("Car with reg %s not found", regNumber)));
	}

}

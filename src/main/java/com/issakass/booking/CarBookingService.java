package com.issakass.booking;

import com.issakass.car.Car;
import com.issakass.car.CarNotAvailableException;
import com.issakass.car.CarService;
import com.issakass.user.User;
import com.issakass.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Author:abdallah-issakass
 */
@Service
public class CarBookingService {

	private final CarBookingDAO carBookingDAO;
	private final UserService userService;
	private final CarService carService;

	public CarBookingService(
			@Qualifier("carBookingJpaDAO") CarBookingDAO carBookingDAO,
			UserService userService,
			CarService carService
	) {
		this.carBookingDAO = carBookingDAO;
		this.userService = userService;
		this.carService = carService;
	}

	public UUID bookCar(CarBookingRequest request) {
		User user = userService.getUserById(UUID.fromString(request.userId()));
		Car carToBook = getAvailableCars().stream()
				.filter(car -> car.getRegNumber().equals(request.regNumber()))
				.findFirst()
				.orElseThrow(() -> new CarNotAvailableException(
						"Car not available or already booked: " + request.regNumber()
				));

		return carBookingDAO.save(new CarBooking(user, carToBook));
	}


	public List<Car> getUserBookedCars(UUID userId) {
		return carBookingDAO.getUserBookedCars(userId);
	}

	public List<Car> getAvailableCars() {
		return carService.getAvailableCars(false);
	}


	public List<Car> getAvailableElectricCars() {
		return carService.getAvailableCars(false);
	}

	public List<CarBooking> getBookings() {
		return carBookingDAO.getCarBookings();
	}
}
package com.issakass.car;

import com.issakass.booking.CarBooking;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author: abdallah-issakass
 */
@Service("carListDAO")
public class CarListDataAccessService implements CarDAO {


	private static final List<Car> CARS = Arrays.asList(
			new Car("1234", new BigDecimal("89.0"), Brand.TESLA, true),
			new Car("5678", new BigDecimal("50.0"), Brand.AUDI, false),
			new Car("4321", new BigDecimal("77.0"), Brand.MERCEDES, false)
	);

	@Override
	public List<Car> getAllCars() {
		return CARS;
	}

	@Override
	public List<Car> getAllElectricalCars() {
		return CARS.stream()
				.filter(Car::isElectric)
				.toList();
	}

	@Override
	public Optional<Car> getCar(String regNumber) {
		return CARS.stream()
				.filter(car -> car.getRegNumber().equals(regNumber))
				.findFirst();
	}

	@Override
	public List<Car> getAvailableCars(List<CarBooking> carBookings) {
		Set<Car> bookedCars = carBookings.stream()
				.map(CarBooking::getCar)
				.collect(Collectors.toSet());

		return CARS.stream()
				.filter(car -> !bookedCars.contains(car))
				.toList();
	}

	@Override
	public List<Car> getAvailableElectricCars(List<CarBooking> carBookings) {
		Set<Car> bookedCars = carBookings.stream()
				.map(CarBooking::getCar)
				.collect(Collectors.toSet());

		return CARS.stream()
				.filter(car -> !bookedCars.contains(car))
				.filter(Car::isElectric)
				.toList();
	}

	@Override
	public List<Car> getBookedCars(List<CarBooking> carBookings) {
		return carBookings
				.stream()
				.map(CarBooking::getCar)
				.toList();
	}

}

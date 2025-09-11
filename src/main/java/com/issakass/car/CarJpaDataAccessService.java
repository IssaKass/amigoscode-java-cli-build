package com.issakass.car;

import com.issakass.booking.CarBooking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Author: abdallah-issakass
 */
@Service("carJpaDAO")
public class CarJpaDataAccessService implements CarDAO {

	private final CarRepository carRepository;

	public CarJpaDataAccessService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}


	@Override
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@Override
	public List<Car> getAllElectricalCars() {
		return carRepository.findByElectricTrue();
	}

	@Override
	public Optional<Car> getCar(String regNumber) {
		return carRepository.findById(regNumber);
	}

	@Override
	public List<Car> getAvailableCars(List<CarBooking> carBookings) {
		return carRepository.findAllAvailableCars();
	}

	@Override
	public List<Car> getAvailableElectricCars(List<CarBooking> carBookings) {
		return carRepository.findAllAvailableElectricCars();
	}

	@Override
	public List<Car> getBookedCars(List<CarBooking> carBookings) {
		return carRepository.findAllByBookingsIsNotEmpty();
	}

}

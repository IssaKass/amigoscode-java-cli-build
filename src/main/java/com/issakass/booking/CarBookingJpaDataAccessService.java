package com.issakass.booking;

import com.issakass.car.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
@Service("carBookingJpaDAO")
public class CarBookingJpaDataAccessService implements CarBookingDAO {

	private final CarBookingRepository carBookingRepository;

	public CarBookingJpaDataAccessService(CarBookingRepository carBookingRepository) {
		this.carBookingRepository = carBookingRepository;
	}

	@Override
	public List<CarBooking> getCarBookings() {
		return carBookingRepository.findAll();
	}

	@Override
	public void cancelCarBooking(UUID id) {

	}

	@Override
	public List<Car> getUserBookedCars(UUID userId) {
		return carBookingRepository.findByUserId(userId)
				.stream()
				.map(CarBooking::getCar)
				.toList();
	}

	@Override
	public UUID save(CarBooking carBooking) {
		CarBooking saved = carBookingRepository.save(carBooking);
		return saved.getBookingId();
	}

}

package com.issakass.booking;

import com.issakass.car.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
@Service("carBookingListDAO")
public class CarBookingListDataAccessService implements CarBookingDAO {

	private static final List<CarBooking> carBookings;

	static {
		carBookings = new ArrayList<>();
	}

	@Override
	public List<CarBooking> getCarBookings() {
		return carBookings;
	}

	@Override
	public void cancelCarBooking(UUID id) {
	}

	@Override
	public List<Car> getUserBookedCars(UUID userId) {
		return carBookings.stream()
				.filter(Objects::nonNull)
				.filter(carBooking -> carBooking.getUser().getId().equals(userId))
				.map(CarBooking::getCar)
				.toList();
	}

	@Override
	public UUID save(CarBooking carBooking) {
		UUID bookingId = UUID.randomUUID();
		carBooking.setBookingId(bookingId);
		carBookings.add(carBooking);
		return bookingId;
	}

}

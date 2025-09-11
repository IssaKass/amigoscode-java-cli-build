package com.issakass.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: abdallah-issakass
 */
class CarBookingListDataAccessServiceTest {

	private CarBookingListDataAccessService underTest;

	@BeforeEach
	void setUp() {
		underTest = new CarBookingListDataAccessService();
		underTest.getCarBookings().clear();
	}

	@Test
	void getCarBookings_shouldReturnAnEmptyList_whenNoBookingsExist() {
		// When
		List<CarBooking> bookings = underTest.getCarBookings();

		// Then
		assertThat(bookings).isNotNull()
				.isEmpty();
	}

	@Test
	void getCarBookings_shouldReturnAllBookings_whenBookingsExist() {
		// Given
		CarBooking booking1 = new CarBooking(null, null);
		CarBooking booking2 = new CarBooking(null, null);
		underTest.save(booking1);
		underTest.save(booking2);

		// When
		List<CarBooking> bookings = underTest.getCarBookings();

		// Then
		assertThat(bookings)
				.hasSize(2)
				.containsExactly(booking1, booking2);
	}

	@Test
	void save_shouldAddCarBookingToList() {
		// Given
		CarBooking booking = new CarBooking(null, null, false);

		// When
		underTest.save(booking);

		// Then
		List<CarBooking> bookings = underTest.getCarBookings();

		assertThat(bookings).hasSize(1);
		assertThat(bookings.getFirst()).isEqualTo(booking);
	}
}
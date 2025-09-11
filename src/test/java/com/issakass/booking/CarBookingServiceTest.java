package com.issakass.booking;

import com.issakass.car.Brand;
import com.issakass.car.Car;
import com.issakass.car.CarService;
import com.issakass.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * Author: abdallah-issakass
 */
@ExtendWith(MockitoExtension.class)
class CarBookingServiceTest {

	@Mock
	private CarBookingListDataAccessService carBookingListDataAccessService;

	@Mock
	private CarService carService;

	@InjectMocks
	private CarBookingService underTest;

	private final User user = new User(UUID.randomUUID(), "Alice");
	private final Car car1 = new Car("1234", new BigDecimal("50.0"), Brand.AUDI, false);
	private final Car car2 = new Car("5678", new BigDecimal("80.0"), Brand.TESLA, true);
	private final Car car3 = new Car("9101", new BigDecimal("75.0"), Brand.MERCEDES, false);
	private final List<Car> allCars = List.of(car1, car2, car3);

	@Test
	void bookCar_shouldBookCarSuccessfully_whenCarIsAvailable() {
		// Given
		when(carService.getAllCars()).thenReturn(allCars);
		when(carBookingListDataAccessService.getCarBookings()).thenReturn(Collections.emptyList());

		// Then
		UUID bookingId = underTest.bookCar(new CarBookingRequest(user.getId().toString(), "1234"));

		// Then
		assertThat(bookingId).isNotNull();

		verify(carBookingListDataAccessService, times(1)).save(any(CarBooking.class));
	}

	@Test
	void bookCar_shouldThrowException_whenCarIsAlreadyBooked() {
		// Given
		CarBooking existingBooking = new CarBooking(user, car1);
		when(carService.getAllCars()).thenReturn(allCars);
		when(carBookingListDataAccessService.getCarBookings()).thenReturn(List.of(existingBooking));

		// When + Then
		assertThatThrownBy(() -> underTest.bookCar(new CarBookingRequest(user.getId().toString(), "1234")))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("Already booked. car with regNumber 1234");

		verify(carBookingListDataAccessService, never()).save(any());
	}

	@Test
	void bookCar_shouldThrowException_whenNoCarsAvailable() {
		// Given
		when(carService.getAllCars()).thenReturn(Collections.emptyList());

		// When + Then
		assertThatThrownBy(() -> underTest.bookCar(new CarBookingRequest(user.getId().toString(), "1234")))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("No car available for renting");

		verify(carBookingListDataAccessService, never()).save(any());
	}

	@Test
	void getUserBookedCars_shouldReturnUsersBookedCars() {
		// Given
		User user1 = new User(UUID.randomUUID(), "Alice");
		User user2 = new User(UUID.randomUUID(), "Bob");
		Car car1 = new Car("1234", new BigDecimal("50.0"), Brand.AUDI, false);
		Car car2 = new Car("5678", new BigDecimal("80.0"), Brand.TESLA, true);

		CarBooking booking1 = new CarBooking(user1, car1);
		CarBooking booking2 = new CarBooking(user2, car2);

		when(carBookingListDataAccessService.getCarBookings()).thenReturn(List.of(booking1, booking2));

		// When
		List<Car> bookedCars = underTest.getUserBookedCars(user1.getId());

		// Then
		assertThat(bookedCars)
				.hasSize(1)
				.containsExactly(car1);
	}

	@Test
	void getAvailableCars_shouldReturnAllCars_whenNoBookingsExist() {
		// Given
		when(carService.getAllCars()).thenReturn(allCars);
		when(carBookingListDataAccessService.getCarBookings()).thenReturn(Collections.emptyList());

		// When
		List<Car> availableCars = underTest.getAvailableCars();

		// Then
		assertThat(availableCars).isEqualTo(allCars);
	}

	@Test
	void getAvailableCars_shouldReturnOnlyUnbookedCars() {
		// Given
		CarBooking bookedCar = new CarBooking(user, car1);
		when(carService.getAllCars()).thenReturn(allCars);
		when(carBookingListDataAccessService.getCarBookings()).thenReturn(List.of(bookedCar));

		// When
		List<Car> availableCars = underTest.getAvailableCars();

		// Then
		assertThat(availableCars)
				.hasSize(2)
				.containsExactlyInAnyOrder(car2, car3);
	}

	@Test
	void getAvailableElectricCars_shouldReturnOnlyAvailableElectricCars() {
		// Given
		CarBooking bookedElectricCar = new CarBooking(user, car2);
		when(carService.getAllElectricCars()).thenReturn(List.of(car2));
		when(carBookingListDataAccessService.getCarBookings()).thenReturn(List.of(bookedElectricCar));

		// When
		List<Car> availableCars = underTest.getAvailableElectricCars();

		// Then
		assertThat(availableCars).isEmpty();
	}

	@Test
	void getBookings_shouldReturnAllBookingsFromDAO() {
		// Given
		List<CarBooking> expected = List.of(
				new CarBooking(user, car1),
				new CarBooking(user, car2)
		);

		when(carBookingListDataAccessService.getCarBookings()).thenReturn(expected);

		// When
		var actual = underTest.getBookings();

		// Then
		assertThat(actual).isEqualTo(expected);
	}
}
package com.issakass.booking;

import com.issakass.car.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Author: abdallah-issakass
 */
@RestController
@RequestMapping("/api/v1/bookings")
public class CarBookingController {

	private final CarBookingService carBookingService;

	public CarBookingController(CarBookingService carBookingService) {
		this.carBookingService = carBookingService;
	}

	@GetMapping
	public ResponseEntity<List<CarBooking>> getAllBookings() {
		List<CarBooking> bookings = carBookingService.getBookings();
		return ResponseEntity.ok(bookings);
	}

	@PostMapping
	public ResponseEntity<UUID> bookCar(
			@RequestBody CarBookingRequest request
	) {
		UUID bookingId = carBookingService.bookCar(request);
		return new ResponseEntity<>(bookingId, HttpStatus.CREATED);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Car>> getUserBookedCars(@PathVariable UUID userId) {
		List<Car> userBookedCars = carBookingService.getUserBookedCars(userId);
		return ResponseEntity.ok(userBookedCars);
	}
}

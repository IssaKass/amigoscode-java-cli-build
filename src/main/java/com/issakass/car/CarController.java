package com.issakass.car;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: abdallah-issakass
 */
@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

	private final CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/available")
	public ResponseEntity<List<Car>> getAvailableCars(
			@RequestParam(required = false) Boolean electric
	) {
		List<Car> cars = carService.getAvailableCars(electric);
		return ResponseEntity.ok(cars);
	}
}

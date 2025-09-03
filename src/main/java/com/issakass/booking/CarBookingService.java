package com.issakass.booking;

import com.issakass.car.Car;
import com.issakass.car.CarService;
import com.issakass.user.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author:abdallah-issakass
 */
public class CarBookingService {

    private final CarBookingDAO carBookingDao;
    private final CarService carService;

    public CarBookingService(CarBookingDAO carBookingDao, CarService carService) {
        this.carBookingDao = carBookingDao;
        this.carService = carService;
    }

    public UUID bookCar(User user, String regNumber) {
        List<Car> availableCars = getAvailableCars();

        if (availableCars.isEmpty()) {
            throw new IllegalStateException("No car available for renting");
        }

        return availableCars.stream()
                .filter(car -> car.getRegNumber().equals(regNumber))
                .findFirst()
                .map(car -> {
                    UUID bookingId = UUID.randomUUID();
                    carBookingDao.book(
                            new CarBooking(bookingId, user, car, LocalDateTime.now())
                    );
                    return bookingId;
                })
                .orElseThrow(() -> new IllegalStateException("Already booked. car with regNumber " + regNumber));
    }


    public List<Car> getUserBookedCars(UUID userId) {
        return carBookingDao.getCarBookings()
                .stream()
                .filter(Objects::nonNull)
                .filter(carBooking -> carBooking.getUser().getId().equals(userId))
                .map(CarBooking::getCar)
                .toList();
    }


    public List<Car> getAvailableCars() {
        return getCars(carService.getAllCars());
    }


    public List<Car> getAvailableElectricCars() {
        return getCars(carService.getAllElectricCars());
    }


    private List<Car> getCars(List<Car> cars) {
        // no cars in the system yet
        if (cars.isEmpty()) {
            return Collections.emptyList();
        }

        List<CarBooking> carBookings = carBookingDao.getCarBookings();

        // no bookings yet therefore all cars are available
        if (carBookings.isEmpty()) {
            return cars;
        }

        Set<Car> bookedCars = carBookings.stream()
                .filter(Objects::nonNull)
                .map(CarBooking::getCar)
                .collect(Collectors.toSet());

        return cars.stream()
                .filter(car -> !bookedCars.contains(car))
                .toList();
    }


    public List<CarBooking> getBookings() {
        return carBookingDao.getCarBookings();
    }
}
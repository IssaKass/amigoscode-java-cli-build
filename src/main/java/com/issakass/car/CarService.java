package com.issakass.car;

import java.util.List;

/**
 * Author: abdallah-issakass
 */
public class CarService {

    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getCar(String regNumber) {
        return getAllCars().stream()
                .filter(car -> car.regNumber().equals(regNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Car with reg %s not found", regNumber)));
    }

    public List<Car> getAllElectricCars() {
        return getAllCars().stream()
                .filter(Car::isElectric)
                .toList();
    }

}

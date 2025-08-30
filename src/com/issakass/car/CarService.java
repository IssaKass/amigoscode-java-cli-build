package com.issakass.car;

/**
 * Author: abdallah-issakass
 */
public class CarService {

    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }


    public Car[] getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getCar(String regNumber) {
        for (Car car : getAllCars()) {
            if (regNumber.equals(car.getRegNumber())) {
                return car;
            }
        }
        throw new IllegalStateException(String.format("Car with reg %s not found", regNumber));
    }

    public Car[] getAllElectricCars() {
        int electricalCarsCount = 0;

        Car[] cars = getAllCars();

        if (cars.length == 0) {
            return new Car[0];
        }

        for (Car car : cars) {
            if (car.isElectric()) {
                electricalCarsCount++;
            }
        }

        if (electricalCarsCount == 0) {
            return new Car[0];
        }

        Car[] electricalCars = new Car[electricalCarsCount];
        int index = 0;

        for (Car car : cars) {
            if (car.isElectric()) {
                electricalCars[index++] = car;
            }
        }

        return electricalCars;
    }

}

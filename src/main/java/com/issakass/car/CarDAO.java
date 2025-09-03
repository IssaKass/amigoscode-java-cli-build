package com.issakass.car;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Author: abdallah-issakass
 */
public class CarDAO {

    private static final List<Car> CARS = Arrays.asList(
            new Car("1234", new BigDecimal("89.0"), Brand.TESLA, true),
            new Car("5678", new BigDecimal("50.0"), Brand.AUDI, false),
            new Car("4321", new BigDecimal("77.0"), Brand.MERCEDES, false)
    );

    public List<Car> getAllCars() {
        return CARS;
    }

}

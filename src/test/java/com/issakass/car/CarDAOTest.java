package com.issakass.car;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: abdallah-issakass
 */
class CarDAOTest {

    private final CarDAO underTest = new CarDAO();

    @Test
    void getAllCars_shouldReturnAllHardcodedCars() {
        // When
        var cars = underTest.getAllCars();

        // Then
        assertThat(cars)
                .hasSize(3)
                .containsExactly(
                        new Car("1234", new BigDecimal("89.0"), Brand.TESLA, true),
                        new Car("5678", new BigDecimal("50.0"), Brand.AUDI, false),
                        new Car("4321", new BigDecimal("77.0"), Brand.MERCEDES, false)
                );
    }
}
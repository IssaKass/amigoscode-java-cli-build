package com.issakass.car;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

/**
 * Author: abdallah-issakass
 */
@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarDAO carDAO;

    @InjectMocks
    private CarService underTest;

    @Test
    void getAllCars_shouldReturnAllCarsFromDAO() {
        // Given
        var expected = List.of(
                new Car("1234", new BigDecimal("89.0"), Brand.TESLA, true),
                new Car("5678", new BigDecimal("50.0"), Brand.AUDI, false)
        );

        when(carDAO.getAllCars()).thenReturn(expected);

        // When
        var actual = underTest.getAllCars();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getCar_shouldReturnCar_whenRegNumberExists() {
        // Given
        String regNumber = "1234";
        Car expected = new Car(regNumber, new BigDecimal("89.0"), Brand.TESLA, true);
        List<Car> cars = List.of(
                new Car("5678", new BigDecimal("50.0"), Brand.AUDI, false),
                expected
        );

        when(carDAO.getAllCars()).thenReturn(cars);

        // When
        var actual = underTest.getCar(regNumber);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getCar_shouldThrowException_whenRegNumberDoesNotExist() {
        // Given
        String nonExistentRegNumber = "9999";
        List<Car> cars = List.of(
                new Car("1234", new BigDecimal("89.0"), Brand.TESLA, true),
                new Car("5678", new BigDecimal("50.0"), Brand.AUDI, false)
        );

        when(carDAO.getAllCars()).thenReturn(cars);

        // When + Then
        assertThatThrownBy(() -> underTest.getCar(nonExistentRegNumber))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Car with reg %s not found", nonExistentRegNumber);
    }

    @Test
    void getAllElectricCars_shouldReturnOnlyElectricCars() {
        // Given
        Car electricCar = new Car("1234", new BigDecimal("89.0"), Brand.TESLA, true);
        Car gasCar1 = new Car("5678", new BigDecimal("50.0"), Brand.AUDI, false);
        Car electricCar2 = new Car("9101", new BigDecimal("100.0"), Brand.TESLA, true);
        Car gasCar2 = new Car("4321", new BigDecimal("77.0"), Brand.MERCEDES, false);
        List<Car> allCars = List.of(electricCar, gasCar1, electricCar2, gasCar2);

        when(carDAO.getAllCars()).thenReturn(allCars);

        // When
        var electricCars = underTest.getAllElectricCars();

        // Then
        assertThat(electricCars)
                .hasSize(2)
                .containsExactlyInAnyOrder(electricCar, electricCar2);
    }
}
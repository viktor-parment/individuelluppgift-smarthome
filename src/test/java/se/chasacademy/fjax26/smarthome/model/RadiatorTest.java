package se.chasacademy.fjax26.smarthome.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.*;

public class RadiatorTest {

    private Radiator radiator;

    @BeforeEach
    void setUp() {
        radiator = new Radiator(1, "Living room", true, Clock.systemDefaultZone(), 20);
    }

    @Test
    void settingValidTemperatureShouldUpdateValue() {
        //Arrange with BeforeEach

        //Act
        radiator.setTargetTemperature(25);

        //Assert
        assertEquals(25, radiator.getTargetTemperature());
    }

    @Test
    void settingTemperatureToZeroShouldSucceed() {
        //Arrange with BeforeEach

        //Act
        radiator.setTargetTemperature(0);

        //Assert
        assertEquals(0, radiator.getTargetTemperature());
    }

    @Test
    void settingTemperatureToThirtyShouldSucceed() {
        //Arrange with BeforeEach

        //Act
        radiator.setTargetTemperature(30);

        //Assert
        assertEquals(30, radiator.getTargetTemperature());
    }

    @Test
    void settingTemperatureBelowZeroShouldThrowException() {
        //Arrange with BeforeEach

        //Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> radiator.setTargetTemperature(-1));
    }

    @Test
    void settingTemperatureAboveThirtyShouldThrowException() {
        //Arrange with BeforeEach

        //Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> radiator.setTargetTemperature(31));
    }
}
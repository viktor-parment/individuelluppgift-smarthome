package se.chasacademy.fjax26.smarthome.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.*;

public class LampTest {

    private Lamp lamp;

    @BeforeEach
    void setUp() {
        lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
    }

    @Test
    void settingValidBrightnessShouldUpdateValue() {
        //Arrange with BeforeEach

        //Act
        lamp.setBrightness(50);

        //Assert
        assertEquals(50, lamp.getBrightness());
    }

    @Test
    void settingBrightnessToZeroShouldSucceed() {
        //Arrange with BeforeEach

        //Act
        lamp.setBrightness(0);

        //Assert
        assertEquals(0, lamp.getBrightness());
    }

    @Test
    void settingBrightnessToOneHundredShouldSucceed() {
        //Arrange with BeforeEach

        //Act
        lamp.setBrightness(100);

        //Assert
        assertEquals(100, lamp.getBrightness());
    }

    @Test
    void settingBrightnessBelowZeroShouldThrowException() {
        //Arrange with BeforeEach

        //Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> lamp.setBrightness(-1));
    }

    @Test
    void settingBrightnessAboveOneHundredShouldThrowException() {
        //Arrange with BeforeEach

        //Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> lamp.setBrightness(101));
    }
}
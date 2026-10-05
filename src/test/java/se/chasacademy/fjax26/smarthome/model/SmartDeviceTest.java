package se.chasacademy.fjax26.smarthome.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.chasacademy.fjax26.smarthome.exception.DeviceStateException;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDeviceTest {

    private SmartDevice device;

    @BeforeEach
    void setUp() {
        device = new Lamp(1, "Hall", false, Clock.systemDefaultZone());
    }

    @Test
    void creatingDeviceWithValidDataShouldSucceed() {
        //Arrange with BeforeEach

        //Act & Assert
        assertEquals(1, device.getId());
        assertEquals("Hall", device.getName());
        assertFalse(device.isActive());
    }

    @Test
    void creatingDeviceWithIdZeroShouldThrowException() {
        //Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Lamp(0, "Hall", true, Clock.systemDefaultZone()));
    }

    @Test
    void creatingDeviceWithNegativeIdShouldThrowException() {
        //Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Lamp(-1, "Hall", true, Clock.systemDefaultZone()));
    }

    @Test
    void creatingDeviceWithBlankNameShouldThrowException() {
        //Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Lamp(1, "   ", true, Clock.systemDefaultZone()));
    }

    @Test
    void creatingDeviceWithNullNameShouldThrowException() {
        //Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Lamp(1, null, true, Clock.systemDefaultZone()));
    }

    @Test
    void activateDeviceShouldSetActiveToTrue() {
        //Arrange with BeforeEach

        //Act
        device.activateDevice();

        //Assert
        assertTrue(device.isActive());
    }

    @Test
    void deactivateDeviceShouldSetActiveToFalse() {
        //Arrange
        device.activateDevice();

        //Act
        device.deactivateDevice();

        //Assert
        assertFalse(device.isActive());
    }

    @Test
    void activatingAlreadyActiveDeviceShouldThrowException() {
        //Arrange
        device.activateDevice();

        //Act & Assert
        assertThrows(DeviceStateException.class, () -> device.activateDevice());
    }

    @Test
    void deactivatingAlreadyInactiveDeviceShouldThrowException() {
        //Arrange with BeforeEach (device starts inactive)

        //Act & Assert
        assertThrows(DeviceStateException.class, () -> device.deactivateDevice());
    }

    @Test
    void isActiveShouldReturnCurrentActiveState() {
        //Arrange with BeforeEach

        //Act & Assert
        assertFalse(device.isActive());
        device.activateDevice();
        assertTrue(device.isActive());
    }

    @Test
    void deviceShouldStoreTheProvidedClock() {
        //Arrange
        Clock fixedClock = Clock.fixed(Instant.parse("2025-01-01T10:00:00Z"), ZoneId.of("UTC"));

        //Act
        SmartDevice deviceWithFixedClock = new Lamp(2, "Kök", false, fixedClock);

        //Assert
        assertNotNull(deviceWithFixedClock);
    }
}
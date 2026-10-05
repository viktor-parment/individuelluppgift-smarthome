package se.chasacademy.fjax26.smarthome.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.chasacademy.fjax26.smarthome.exception.DeviceStateException;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
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
    void creatingDeviceShouldSetInstallationDateUsingProvidedClock() {
        //Arrange
        Clock fixedClock = Clock.fixed(Instant.parse("2025-03-10T09:00:00Z"), ZoneId.of("UTC"));

        //Act
        SmartDevice deviceWithFixedClock = new Lamp(2, "Kök", false, fixedClock);

        //Assert
        assertEquals(LocalDateTime.of(2025, 3, 10, 9, 0), deviceWithFixedClock.getInstallationDate());
    }

    @Test
    void activatingDeviceShouldSetLastActivationUsingProvidedClock() {
        //Arrange
        Clock fixedClock = Clock.fixed(Instant.parse("2025-01-01T10:00:00Z"), ZoneId.of("UTC"));
        SmartDevice deviceWithFixedClock = new Lamp(1, "Hall", false, fixedClock);

        //Act
        deviceWithFixedClock.activateDevice();

        //Assert
        assertEquals(LocalDateTime.of(2025, 1, 1, 10, 0), deviceWithFixedClock.getLastActivation());
    }

    @Test
    void deactivatingDeviceShouldSetLastDeactivationUsingProvidedClock() {
        //Arrange
        Clock fixedClock = Clock.fixed(Instant.parse("2025-06-15T14:30:00Z"), ZoneId.of("UTC"));
        SmartDevice deviceWithFixedClock = new Lamp(1, "Hall", true, fixedClock);

        //Act
        deviceWithFixedClock.deactivateDevice();

        //Assert
        assertEquals(LocalDateTime.of(2025, 6, 15, 14, 30), deviceWithFixedClock.getLastDeactivation());
    }

    @Test
    void lastActivationShouldBeNullBeforeFirstActivation() {
        //Arrange with BeforeEach (device starts inactive, never activated)

        //Act & Assert
        assertNull(device.getLastActivation());
    }

    @Test
    void lastDeactivationShouldBeNullBeforeFirstDeactivation() {
        //Arrange with BeforeEach (device starts inactive, never deactivated)

        //Act & Assert
        assertNull(device.getLastDeactivation());
    }

    @Test
    void deactivatingAfterActivatingShouldUpdateBothTimestampsIndependently() {
        //Arrange
        Clock fixedClock = Clock.fixed(Instant.parse("2025-01-01T10:00:00Z"), ZoneId.of("UTC"));
        SmartDevice deviceWithFixedClock = new Lamp(1, "Hall", false, fixedClock);

        //Act
        deviceWithFixedClock.activateDevice();
        deviceWithFixedClock.deactivateDevice();

        //Assert
        assertEquals(LocalDateTime.of(2025, 1, 1, 10, 0), deviceWithFixedClock.getLastActivation());
        assertEquals(LocalDateTime.of(2025, 1, 1, 10, 0), deviceWithFixedClock.getLastDeactivation());
    }
}
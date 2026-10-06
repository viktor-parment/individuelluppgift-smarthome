package se.chasacademy.fjax26.smarthome.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.chasacademy.fjax26.smarthome.exception.DeviceNotFoundException;
import se.chasacademy.fjax26.smarthome.model.Camera;
import se.chasacademy.fjax26.smarthome.model.Lamp;
import se.chasacademy.fjax26.smarthome.model.SmartDevice;
import se.chasacademy.fjax26.smarthome.repository.DeviceRepository;

import java.time.Clock;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    private DeviceRepository repository;

    @InjectMocks
    private ReportService reportService;

    @Test
    void listAllDevicesShouldReturnListFromRepository() {
        //Arrange
        SmartDevice lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        Mockito.when(repository.getDeviceList()).thenReturn(List.of(lamp));

        //Act
        List<SmartDevice> result = reportService.listAllDevices();

        //Assert
        assertEquals(1, result.size());
        assertEquals(lamp, result.get(0));
    }

    @Test
    void listAllDevicesShouldReturnEmptyListWhenRepositoryIsEmpty() {
        //Arrange
        Mockito.when(repository.getDeviceList()).thenReturn(List.of());

        //Act
        List<SmartDevice> result = reportService.listAllDevices();

        //Assert
        assertEquals(0, result.size());
    }

    @Test
    void isDeviceActiveShouldReturnTrueWhenDeviceIsActive() {
        //Arrange
        SmartDevice lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        Mockito.when(repository.findDeviceById(1)).thenReturn(lamp);

        //Act
        boolean result = reportService.isDeviceActive(1);

        //Assert
        assertTrue(result);
    }

    @Test
    void isDeviceActiveShouldReturnFalseWhenDeviceIsInactive() {
        //Arrange
        SmartDevice lamp = new Lamp(1, "Hall", false, Clock.systemDefaultZone());
        Mockito.when(repository.findDeviceById(1)).thenReturn(lamp);

        //Act
        boolean result = reportService.isDeviceActive(1);

        //Assert
        assertFalse(result);
    }

    @Test
    void isDeviceActiveWithNonExistentIdShouldPropagateException() {
        //Arrange
        Mockito.when(repository.findDeviceById(99)).thenThrow(new DeviceNotFoundException("Device not found"));

        //Act & Assert
        assertThrows(DeviceNotFoundException.class, () -> reportService.isDeviceActive(99));
    }
    @Test
    void isDeviceActiveByNameShouldReturnTrueWhenDeviceIsActive() {
        //Arrange
        SmartDevice lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        Mockito.when(repository.findDeviceByName("Hall")).thenReturn(lamp);

        //Act
        boolean result = reportService.isDeviceActive("Hall");

        //Assert
        assertTrue(result);
    }

    @Test
    void isDeviceActiveByNameWithNonExistentNameShouldPropagateException() {
        //Arrange
        Mockito.when(repository.findDeviceByName("Unknown")).thenThrow(new DeviceNotFoundException("..."));

        //Act & Assert
        assertThrows(DeviceNotFoundException.class, () -> reportService.isDeviceActive("Unknown"));
    }

    @Test
    void countActiveDevicesShouldReturnCorrectCount() {
        //Arrange
        SmartDevice activeLamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        SmartDevice inactiveCamera = new Camera(2, "Kitchen", false, Clock.systemDefaultZone());
        Mockito.when(repository.getDeviceList()).thenReturn(List.of(activeLamp, inactiveCamera));

        //Act
        long result = reportService.countActiveDevices();

        //Assert
        assertEquals(1, result);
    }

    @Test
    void countInactiveDevicesShouldReturnCorrectCount() {
        //Arrange
        SmartDevice activeLamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        SmartDevice inactiveCamera = new Camera(2, "Kitchen", false, Clock.systemDefaultZone());
        Mockito.when(repository.getDeviceList()).thenReturn(List.of(activeLamp, inactiveCamera));

        //Act
        long result = reportService.countInactiveDevices();

        //Assert
        assertEquals(1, result);
    }

    @Test
    void countActiveDevicesShouldReturnZeroWhenRepositoryIsEmpty() {
        //Arrange
        Mockito.when(repository.getDeviceList()).thenReturn(List.of());

        //Act
        long result = reportService.countActiveDevices();

        //Assert
        assertEquals(0, result);
    }
}
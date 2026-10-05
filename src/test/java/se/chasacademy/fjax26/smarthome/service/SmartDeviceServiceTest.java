package se.chasacademy.fjax26.smarthome.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.chasacademy.fjax26.smarthome.model.Camera;
import se.chasacademy.fjax26.smarthome.model.Lamp;
import se.chasacademy.fjax26.smarthome.model.Radiator;
import se.chasacademy.fjax26.smarthome.model.SmartDevice;
import se.chasacademy.fjax26.smarthome.repository.DeviceRepository;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SmartDeviceServiceTest {
    @Mock
    private DeviceRepository repository;

    @InjectMocks
    private SmartDeviceService service;

    @Test
    void addDeviceShouldCallRepositoryAddDevice() {
        //Arrange
        SmartDevice lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());

        //Act
        service.addDevice(lamp);

        //Assert
        verify(repository).addDevice(lamp);
    }

    @Test
    void addDeviceWithNullShouldThrowExceptionWithoutCallingRepository() {
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.addDevice(null));
        verify(repository, never()).addDevice(any());
    }

    @Test
    void addDeviceShouldWorkForAllDeviceTypes() {
        //Arrange
        SmartDevice lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        SmartDevice camera = new Camera(2, "Living Room", true, Clock.systemDefaultZone());
        SmartDevice radiator = new Radiator(3, "Bedroom", true, Clock.systemDefaultZone(), 22.5);

        //Act
        service.addDevice(lamp);
        service.addDevice(camera);
        service.addDevice(radiator);

        //Assert
        verify(repository).addDevice(lamp);
        verify(repository).addDevice(camera);
        verify(repository).addDevice(radiator);
    }

    @Test
    void removeDeviceShouldCallRepositoryRemoveDevice() {
        //Arrange
        SmartDevice lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        int deviceId = lamp.getId();

        //Act
        service.removeDevice(deviceId);

        //Assert
        verify(repository).removeDevice(deviceId);
    }

    @Test
    void removeDeviceWithIdZeroShouldThrowException() {
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.removeDevice(0));
    }

    @Test
    void removeDeviceWithNegativeIdShouldThrowExceptionWithoutCallingRepository(){
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.removeDevice(-1));
        verify(repository, never()).removeDevice(anyInt());
    }

    @Test
    void findDeviceByIdShouldReturnDeviceFromRepository(){
        //Arrange
        SmartDevice lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        int deviceId = lamp.getId();
        Mockito.when(repository.findDeviceById(deviceId)).thenReturn(lamp);

        //Act
        SmartDevice result = service.findDeviceById(deviceId);

        //Assert
        assertEquals(lamp, result);
    }

    @Test
    void findDeviceByIdWithIdZeroShouldThrowException(){
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.findDeviceById(0));
    }

    @Test
    void findDeviceByIdWithNegativeIdShouldThrowExceptionWithoutCallingRepository(){
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.findDeviceById(-1));
        verify(repository, never()).findDeviceById(anyInt());
    }

    @Test
    void findDeviceByNameShouldReturnDeviceFromRepository(){
        //Arrange
        SmartDevice lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        String deviceName = lamp.getName();
        Mockito.when(repository.findDeviceByName(deviceName)).thenReturn(lamp);

        //Act
        SmartDevice result = service.findDeviceByName(deviceName);

        //Assert
        assertEquals(lamp, result);
    }

    @Test
    void findDeviceByNameWithWhitespaceNameShouldThrowExceptionWithoutCallingRepository(){
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.findDeviceByName("   "));
        verify(repository, never()).findDeviceByName(any());
    }

    @Test
    void findDeviceByNameWithNullShouldThrowExceptionWithoutCallingRepository(){
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.findDeviceByName(null));
        verify(repository, never()).findDeviceByName(any());
    }

    @Test
    void getDevicesListShouldReturnListFromRepository(){
        //Arrange
        SmartDevice lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        Mockito.when(repository.getDeviceList()).thenReturn(java.util.List.of(lamp));

        //Act
        java.util.List<SmartDevice> result = service.getDeviceList();

        //Assert
        assertEquals(1, result.size());
        assertEquals(lamp, result.get(0));
    }

    @Test
    void getDevicesListShouldReturnEmptyListWhenRepositoryIsEmpty(){
        //Arrange
        Mockito.when(repository.getDeviceList()).thenReturn(java.util.List.of());

        //Act
        java.util.List<SmartDevice> result = service.getDeviceList();

        //Assert
        assertEquals(0, result.size());
    }
}

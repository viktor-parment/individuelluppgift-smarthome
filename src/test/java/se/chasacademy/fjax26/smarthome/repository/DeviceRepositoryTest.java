package se.chasacademy.fjax26.smarthome.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.chasacademy.fjax26.smarthome.exception.DeviceNotFoundException;
import se.chasacademy.fjax26.smarthome.model.Camera;
import se.chasacademy.fjax26.smarthome.model.Lamp;
import se.chasacademy.fjax26.smarthome.model.SmartDevice;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeviceRepositoryTest {

    @Test
    public void addingADeviceShouldStoreItInList(){
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();

        //Act
        deviceRepository.add(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));

        //Assert
        assertEquals(1, deviceRepository.getDevicesList().size());
    }

    @Test
    public void getDeviceListShouldReturnTheWholeList(){
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        //Act
        deviceRepository.add(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));
        deviceRepository.add(new Camera(2, "Hall", true, Clock.systemDefaultZone()));
        //Assert
        assertEquals(2, deviceRepository.getDevicesList().size());
    }

    @Test
    public void getDeviceListShouldBeEmptyWhenNothingIsAdded(){
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        //Act & Assert
        assertEquals(0, deviceRepository.getDevicesList().size());
    }

    @Test
    public void findDeviceByIdShouldReturnDeviceWithSameIdAsInput(){
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        deviceRepository.add(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));
        deviceRepository.add(new Camera(2, "Kök", true, Clock.systemDefaultZone()));
        deviceRepository.add(new Lamp(3, "Toa", true, Clock.systemDefaultZone()));
        //Act
        SmartDevice found = deviceRepository.findDeviceById(3);

        //Assert
        assertEquals(3, found.getId());

    }

    @Test
    public void findDeviceByIdShouldThrowExceptionWhenNoMatchIsFound(){
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        deviceRepository.add(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));
        deviceRepository.add(new Camera(2, "Kök", true, Clock.systemDefaultZone()));
        deviceRepository.add(new Lamp(3, "Toa", true, Clock.systemDefaultZone()));
        //Act & Assert
        assertThrows(DeviceNotFoundException.class, () ->  deviceRepository.findDeviceById(4));
    }

    //TODO: fler tester. kom ihåg TDD...
}


package se.chasacademy.fjax26.smarthome.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.chasacademy.fjax26.smarthome.model.Camera;
import se.chasacademy.fjax26.smarthome.model.Lamp;
import se.chasacademy.fjax26.smarthome.model.SmartDevice;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}


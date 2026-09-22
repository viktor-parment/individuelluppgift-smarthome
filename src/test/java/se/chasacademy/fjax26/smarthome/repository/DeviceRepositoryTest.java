package se.chasacademy.fjax26.smarthome.repository;

import org.junit.jupiter.api.Test;
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
}

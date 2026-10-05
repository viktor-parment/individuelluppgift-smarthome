package se.chasacademy.fjax26.smarthome.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.chasacademy.fjax26.smarthome.model.Lamp;
import se.chasacademy.fjax26.smarthome.model.SmartDevice;
import se.chasacademy.fjax26.smarthome.repository.DeviceRepository;

import java.time.Clock;

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
}

package se.chasacademy.fjax26.smarthome.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.chasacademy.fjax26.smarthome.model.Lamp;
import se.chasacademy.fjax26.smarthome.model.SmartDevice;
import se.chasacademy.fjax26.smarthome.repository.DeviceRepository;

import java.time.Clock;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
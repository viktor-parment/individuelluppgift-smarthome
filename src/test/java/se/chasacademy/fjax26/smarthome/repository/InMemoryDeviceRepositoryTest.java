package se.chasacademy.fjax26.smarthome.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.chasacademy.fjax26.smarthome.exception.DeviceNotFoundException;
import se.chasacademy.fjax26.smarthome.exception.DuplicateDeviceIdException;
import se.chasacademy.fjax26.smarthome.model.Camera;
import se.chasacademy.fjax26.smarthome.model.Lamp;
import se.chasacademy.fjax26.smarthome.model.SmartDevice;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryDeviceRepositoryTest {

    private DeviceRepository deviceRepository;

    @BeforeEach
    public void setUp() {
        deviceRepository = new InMemoryDeviceRepository();
    }

    @Test
    public void addingADeviceShouldStoreItInList() {
        //Arrange with BeforeEach

        //Act
        deviceRepository.addDevice(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));

        //Assert
        assertEquals(1, deviceRepository.getDeviceList().size());
    }

    @Test
    public void getDeviceListShouldReturnTheWholeList() {
        //Arrange with BeforeEach

        //Act
        deviceRepository.addDevice(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));
        deviceRepository.addDevice(new Camera(2, "Hall", true, Clock.systemDefaultZone()));
        //Assert
        assertEquals(2, deviceRepository.getDeviceList().size());
    }

    @Test
    public void getDeviceListShouldContainTheDevicesThatWereAdded() {
        //Arrange
        Lamp lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        Camera camera = new Camera(2, "Hall", true, Clock.systemDefaultZone());

        //Act
        deviceRepository.addDevice(lamp);
        deviceRepository.addDevice(camera);

        //Assert
        assertTrue(deviceRepository.getDeviceList().contains(lamp));
        assertTrue(deviceRepository.getDeviceList().contains(camera));
    }

    @Test
    public void getDeviceListShouldBeEmptyWhenNothingIsAdded() {
        //Arrange with BeforeEach
        //Act & Assert
        assertEquals(0, deviceRepository.getDeviceList().size());
    }

    @Test
    public void findDeviceByIdShouldReturnDeviceWithSameIdAsInput() {
        //Arrange
        deviceRepository.addDevice(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));
        deviceRepository.addDevice(new Camera(2, "Kitchen", true, Clock.systemDefaultZone()));
        deviceRepository.addDevice(new Lamp(3, "Toilet", true, Clock.systemDefaultZone()));
        //Act
        SmartDevice found = deviceRepository.findDeviceById(3);

        //Assert
        assertEquals(3, found.getId());

    }

    @Test
    public void findDeviceByIdShouldThrowExceptionWhenNoMatchIsFound() {
        //Arrange
        deviceRepository.addDevice(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));
        deviceRepository.addDevice(new Camera(2, "Kitchen", true, Clock.systemDefaultZone()));
        deviceRepository.addDevice(new Lamp(3, "Toilet", true, Clock.systemDefaultZone()));
        //Act & Assert
        assertThrows(DeviceNotFoundException.class, () -> deviceRepository.findDeviceById(4));
    }

    @Test
    public void getDeviceListShouldNotAllowExternalModification() {
        //Arrange
        deviceRepository.addDevice(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));
        //Act & Assert
        assertThrows(UnsupportedOperationException.class,
                () -> deviceRepository.getDeviceList().add(new Lamp(2, "Kitchen", true, Clock.systemDefaultZone())));
    }

    @Test
    public void addingADeviceWithAnAlreadyExistingIdShouldThrowException() {
        //Arrange
        deviceRepository.addDevice(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));

        //Act & Arrange
        assertThrows(DuplicateDeviceIdException.class,
                () -> deviceRepository.addDevice(new Camera(1, "Kitchen", true, Clock.systemDefaultZone())));

    }

    @Test
    public void findDeviceByNameShouldReturnDeviceWithSameNameAsInput() {
        //Arrange
        deviceRepository.addDevice(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));

        //Act
        SmartDevice found = deviceRepository.findDeviceByName("Hall");

        //Assert
        assertEquals("Hall", found.getName());
    }

    @Test
    public void removeDeviceShouldRemoveDeviceFromList() {
        //Arrange
        deviceRepository.addDevice(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));

        //Act
        deviceRepository.removeDevice(1);

        //Assert
        assertEquals(0, deviceRepository.getDeviceList().size());
    }

    @Test
    public void removeDeviceShouldOnlyRemoveTheSpecifiedDeviceFromList() {
        //Arrange
        deviceRepository.addDevice(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));
        deviceRepository.addDevice(new Camera(2, "Kitchen", true, Clock.systemDefaultZone()));

        //Act
        deviceRepository.removeDevice(1);

        //Assert
        assertEquals(1, deviceRepository.getDeviceList().size());
        assertThrows(DeviceNotFoundException.class, () -> deviceRepository.findDeviceById(1));

    }

    @Test
    public void removeDeviceThatDoesNotExistShouldThrowException() {
        //Arrange
        deviceRepository.addDevice(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));

        //Act & Assert
        assertThrows(DeviceNotFoundException.class, () -> deviceRepository.removeDevice(5));

    }
}


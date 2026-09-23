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

public class DeviceRepositoryTest {

    private DeviceRepository deviceRepository;

    @BeforeEach
    public void setUp() {
        deviceRepository = new DeviceRepository();
    }
    @Test
    public void addingADeviceShouldStoreItInList(){
        //Arrange with BeforeEach

        //Act
        deviceRepository.add(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));

        //Assert
        assertEquals(1, deviceRepository.getDevicesList().size());
    }

    @Test
    public void getDeviceListShouldReturnTheWholeList(){
        //Arrange with BeforeEach

        //Act
        deviceRepository.add(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));
        deviceRepository.add(new Camera(2, "Hall", true, Clock.systemDefaultZone()));
        //Assert
        assertEquals(2, deviceRepository.getDevicesList().size());
    }

    @Test
    public void getDeviceListShouldContainTheDevicesThatWereAdded() {
        //Arrange
        Lamp lamp = new Lamp(1, "Hall", true, Clock.systemDefaultZone());
        Camera camera = new Camera(2, "Hall", true, Clock.systemDefaultZone());

        //Act
        deviceRepository.add(lamp);
        deviceRepository.add(camera);

        //Assert
        assertTrue(deviceRepository.getDevicesList().contains(lamp));
        assertTrue(deviceRepository.getDevicesList().contains(camera));
    }

    @Test
    public void getDeviceListShouldBeEmptyWhenNothingIsAdded(){
        //Arrange with BeforeEach
        //Act & Assert
        assertEquals(0, deviceRepository.getDevicesList().size());
    }

    @Test
    public void findDeviceByIdShouldReturnDeviceWithSameIdAsInput(){
        //Arrange
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
        deviceRepository.add(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));
        deviceRepository.add(new Camera(2, "Kök", true, Clock.systemDefaultZone()));
        deviceRepository.add(new Lamp(3, "Toa", true, Clock.systemDefaultZone()));
        //Act & Assert
        assertThrows(DeviceNotFoundException.class, () ->  deviceRepository.findDeviceById(4));
    }

    @Test
    public void getDeviceListShouldNotAllowExternalModification(){
        //Arrange
        deviceRepository.add(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));
        //Act & Assert
        assertThrows(UnsupportedOperationException.class,
                () -> deviceRepository.getDevicesList().add(new Lamp(2, "Kök", true, Clock.systemDefaultZone())));
    }

    @Test
    public void addingADeviceWithAnAlreadyExistingIdShouldThrowException(){
        //Arrange
        deviceRepository.add(new Lamp(1, "Hall", true, Clock.systemDefaultZone()));

        //Act & arrange
        assertThrows(DuplicateDeviceIdException.class,
                () -> deviceRepository.add(new Camera(1, "Kök", true, Clock.systemDefaultZone())));


    }




}


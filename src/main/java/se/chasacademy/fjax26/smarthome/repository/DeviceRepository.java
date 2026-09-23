package se.chasacademy.fjax26.smarthome.repository;

import se.chasacademy.fjax26.smarthome.exception.DeviceNotFoundException;
import se.chasacademy.fjax26.smarthome.exception.DuplicateDeviceIdException;
import se.chasacademy.fjax26.smarthome.model.SmartDevice;

import java.util.ArrayList;
import java.util.List;

public class DeviceRepository {
    private final List<SmartDevice> devices = new ArrayList<>();

    public void add(SmartDevice device) {
        for (SmartDevice existingDevice : devices){
            if (existingDevice.getId() == device.getId()){
                throw new DuplicateDeviceIdException();
            }
        }
        devices.add(device);
    }

    public List<SmartDevice> getDevicesList() {
        return List.copyOf(devices);
    }

    public SmartDevice findDeviceById(int id) {
        for (SmartDevice device : devices) {
            if (id == device.getId()) {
                return device;
            }
        }
        throw new DeviceNotFoundException("Hittar ingen apparat med det ID:t");
    }
}



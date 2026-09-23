package se.chasacademy.fjax26.smarthome.repository;

import se.chasacademy.fjax26.smarthome.exception.DeviceNotFoundException;
import se.chasacademy.fjax26.smarthome.exception.DuplicateDeviceIdException;
import se.chasacademy.fjax26.smarthome.model.SmartDevice;

import java.util.ArrayList;
import java.util.List;

public class DeviceRepository {
    private final List<SmartDevice> devices = new ArrayList<>();

    private boolean exists(int id) {
        for (SmartDevice device : devices) {
            if (device.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void add(SmartDevice device) {
        if (exists(device.getId())) {
            throw new DuplicateDeviceIdException("Device with id " + device.getId() + " already exists.");
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
        throw new DeviceNotFoundException("Can't find a device with that ID");
    }
}



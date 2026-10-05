package se.chasacademy.fjax26.smarthome.repository;

import se.chasacademy.fjax26.smarthome.exception.DeviceNotFoundException;
import se.chasacademy.fjax26.smarthome.exception.DuplicateDeviceIdException;
import se.chasacademy.fjax26.smarthome.model.SmartDevice;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDeviceRepository implements DeviceRepository {
    private final List<SmartDevice> devices = new ArrayList<>();


    private boolean exists(int id) {
        for (SmartDevice device : devices) {
            if (device.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addDevice(SmartDevice device) {
        if (exists(device.getId())) {
            throw new DuplicateDeviceIdException("Device with id " + device.getId() + " already exists.");
        }
        devices.add(device);
    }

    @Override
    public void removeDevice(int id) {
        SmartDevice device = findDeviceById(id); // kastar redan DeviceNotFoundException om den inte finns
        devices.remove(device);
    }

    @Override
    public List<SmartDevice> getDevicesList() {
        return List.copyOf(devices);
    }

    @Override
    public SmartDevice findDeviceById(int id) {
        for (SmartDevice device : devices) {
            if (id == device.getId()) {
                return device;
            }
        }
        throw new DeviceNotFoundException("Can't find a device with that ID.");
    }

    @Override
    public SmartDevice findDeviceByName(String name) {
        for (SmartDevice device : devices) {
            if (name.equals(device.getName())) {
                return device;
            }
        }
        throw new DeviceNotFoundException("Can't find a device with that name.");
    }
}



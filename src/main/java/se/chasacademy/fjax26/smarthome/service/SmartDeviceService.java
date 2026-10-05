package se.chasacademy.fjax26.smarthome.service;


import se.chasacademy.fjax26.smarthome.model.SmartDevice;
import se.chasacademy.fjax26.smarthome.repository.DeviceRepository;

import java.util.List;

public class SmartDeviceService {
    private final DeviceRepository repository;

    public SmartDeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    public void addDevice(SmartDevice device) {
        if (device == null) {
            throw new IllegalArgumentException("Device cannot be null");
        }
        repository.addDevice(device);
    }

    public void removeDevice(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid device ID");
        }
        repository.removeDevice(id);
    }

    public SmartDevice findDeviceById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid device ID");
        }
        return repository.findDeviceById(id);
    }

    public SmartDevice findDeviceByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid device name");
        }
        return repository.findDeviceByName(name);
    }

    public List<SmartDevice> getDeviceList() {
        return repository.getDeviceList();
    }
}
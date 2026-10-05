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
        repository.addDevice(device);
    }

    public void removeDevice(int id) {
        repository.removeDevice(id);
    }

    public SmartDevice findDeviceById(int id) {
        return repository.findDeviceById(id);
    }

    public SmartDevice findDeviceByName(String name) {
        return repository.findDeviceByName(name);
    }

    public List<SmartDevice> getDevicesList() {
        return repository.getDevicesList();
    }
}
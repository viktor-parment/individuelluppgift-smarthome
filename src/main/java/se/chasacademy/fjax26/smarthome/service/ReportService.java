package se.chasacademy.fjax26.smarthome.service;

import se.chasacademy.fjax26.smarthome.model.SmartDevice;
import se.chasacademy.fjax26.smarthome.repository.DeviceRepository;

import java.util.List;

public class ReportService {
    private final DeviceRepository repository;

    public ReportService(DeviceRepository repository) {
        this.repository = repository;
    }

    public List<SmartDevice> listAllDevices() {
        return repository.getDeviceList();
    }

    public boolean isDeviceActive(int id) {
        SmartDevice device = repository.findDeviceById(id);
            return device.isActive();
    }

    public boolean isDeviceActive(String name) {
        SmartDevice device = repository.findDeviceByName(name);
            return device.isActive();
    }

    public long countActiveDevices() {
        return repository.getDeviceList().stream()
                .filter(SmartDevice::isActive)
                .count();
    }

    public long countInactiveDevices() {
        return repository.getDeviceList().stream()
                .filter(device -> !device.isActive())
                .count();
    }
}

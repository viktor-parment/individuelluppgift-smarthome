package se.chasacademy.fjax26.smarthome.service;

import se.chasacademy.fjax26.smarthome.model.SmartDevice;
import se.chasacademy.fjax26.smarthome.repository.DeviceRepository;

import java.util.List;

public class ReportService {
    private DeviceRepository repository;

    public ReportService(DeviceRepository repository) {
        this.repository = repository;
    }

    public List<SmartDevice> listAllDevices() {
        return repository.getDeviceList();
    }

}

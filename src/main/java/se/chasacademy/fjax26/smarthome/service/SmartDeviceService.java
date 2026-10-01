package se.chasacademy.fjax26.smarthome.service;


import se.chasacademy.fjax26.smarthome.repository.DeviceRepository;

public class SmartDeviceService {
    private final DeviceRepository repository;

    public SmartDeviceService(DeviceRepository repository) {
        this.repository = repository;

    }
}
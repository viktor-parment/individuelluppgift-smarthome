package se.chasacademy.fjax26.smarthome.repository;

import se.chasacademy.fjax26.smarthome.model.SmartDevice;

import java.util.ArrayList;
import java.util.List;

public class DeviceRepository {
    private List<SmartDevice> devices = new ArrayList<>();
    public void add(SmartDevice device){
        devices.add(device);
    }

    public List<SmartDevice> getDevicesList() {
        return devices;
    }
}


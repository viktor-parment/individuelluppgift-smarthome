package se.chasacademy.fjax26.smarthome.repository;

import se.chasacademy.fjax26.smarthome.model.SmartDevice;
import java.util.List;

public interface DeviceRepository {
    void add(SmartDevice device);
    void removeDevice(int id);
    SmartDevice findDeviceById(int id);
    SmartDevice findDeviceByName(String name);
    List<SmartDevice> getDevicesList();
}
package se.chasacademy.fjax26.smarthome;

import se.chasacademy.fjax26.smarthome.exception.DeviceNotFoundException;
import se.chasacademy.fjax26.smarthome.exception.DuplicateDeviceIdException;
import se.chasacademy.fjax26.smarthome.model.Camera;
import se.chasacademy.fjax26.smarthome.model.Lamp;
import se.chasacademy.fjax26.smarthome.model.Radiator;
import se.chasacademy.fjax26.smarthome.model.SmartDevice;
import se.chasacademy.fjax26.smarthome.repository.DeviceRepository;
import se.chasacademy.fjax26.smarthome.repository.InMemoryDeviceRepository;
import se.chasacademy.fjax26.smarthome.service.ReportService;
import se.chasacademy.fjax26.smarthome.service.SmartDeviceService;

import java.time.Clock;

import static java.lang.IO.println;

public class Main {

    public static void main(String[] args) {
        DeviceRepository repository = new InMemoryDeviceRepository();
        SmartDeviceService deviceService = new SmartDeviceService(repository);
        ReportService reportService = new ReportService(repository);
        Clock clock = Clock.systemDefaultZone();

        println("Welcome to SmartHome-demo!\n");
        println("Adding units...\n");
        Lamp lamp = new Lamp(1, "Hall", true, clock);
        Camera camera = new Camera(2, "Living room", true, clock);
        Radiator radiator = new Radiator(3, "Bedroom", false, clock, 22.5);
        deviceService.addDevice(lamp);
        deviceService.addDevice(camera);
        deviceService.addDevice(radiator);

        println("All units:");
        reportService.listAllDevices().forEach(device -> println(device));

        println("\nDimming the light in the hall...");
        lamp.setBrightness(50);
        println(lamp);

        println("\nStarting recording on the camera...");
        camera.toggleRecording();
        println(camera);

        println("\nTurning off both the lamp and the camera...");
        lamp.deactivateDevice();
        camera.deactivateDevice();
        println(lamp);
        println(camera);

        println("\nSearching for device with name 'Bedroom'...");
        SmartDevice found = deviceService.findDeviceByName("Bedroom");
        println("Found: " + found);

        println("\nStatus report:");
        println("Active devices: " + reportService.countActiveDevices());
        println("Inactive devices: " + reportService.countInactiveDevices());

        println("\nError handling: trying to add a device with an existing ID...");
        try {
            Lamp duplicate = new Lamp(1, "Kitchen lamp", false, clock);
            deviceService.addDevice(duplicate);
        } catch (DuplicateDeviceIdException e) {
            println("Error caught: " + e.getMessage());
        }

        println("\nError handling: searching for a device that doesn't exist...");
        try {
            deviceService.findDeviceById(10);
        } catch (DeviceNotFoundException e) {
            println("Error caught: " + e.getMessage());
        }

        println("\nDemo completed.");
    }
}
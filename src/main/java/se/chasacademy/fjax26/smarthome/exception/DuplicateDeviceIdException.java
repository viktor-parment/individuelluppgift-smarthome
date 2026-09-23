package se.chasacademy.fjax26.smarthome.exception;

public class DuplicateDeviceIdException extends RuntimeException {
    public DuplicateDeviceIdException() {
        super("Device with that ID already exits.");
    }
}

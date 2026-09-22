package se.chasacademy.fjax26.smarthome.model;

public interface Activatable {

    void activateDevice();
    void deactivateDevice();
    boolean isActive();
}

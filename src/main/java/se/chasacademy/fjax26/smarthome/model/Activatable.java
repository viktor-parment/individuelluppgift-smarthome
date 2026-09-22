package se.chasacademy.fjax26.smarthome.model;

public interface Activatable {

    boolean activateDevice();
    boolean deactivateDevice();
    boolean isActive();
}

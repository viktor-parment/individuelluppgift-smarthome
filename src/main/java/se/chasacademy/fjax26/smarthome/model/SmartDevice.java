package se.chasacademy.fjax26.smarthome.model;

import se.chasacademy.fjax26.smarthome.exception.DeviceStateException;

import java.time.Clock;

public abstract class SmartDevice implements Activatable {
    private int id;
    private String name;
    private boolean active;
    private Clock clock;

    public SmartDevice(int id, String name, boolean active, Clock clock){
        this.id = id;
        this.name=name;
        this.active = active;
        this.clock = clock;
    }

    @Override
    public boolean activateDevice() {
        return setActive(true);
    }

    @Override
    public boolean deactivateDevice() {
        return setActive(false);
    }

    public boolean isActive() {
        return active;
    }

    private boolean setActive(boolean active) {
        if (this.active == active) {
            throw new DeviceStateException("Enheten är redan " + (active ? "aktiverad" : "avaktiverad"));
        }
        this.active = active;
        return this.active;
    }
}

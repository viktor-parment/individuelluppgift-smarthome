package se.chasacademy.fjax26.smarthome.model;

import se.chasacademy.fjax26.smarthome.exception.DeviceStateException;

import java.time.Clock;

public abstract class SmartDevice implements Activatable {
    private int id;
    private String name;
    private boolean active;
    private Clock clock;

    public SmartDevice(int id, String name, boolean active, Clock clock){
        if (id <= 0){
            throw new IllegalArgumentException("Id kan inte vara negativt eller noll.");
        }
        this.id = id;

        if (name.isBlank()){
            throw new IllegalArgumentException("namn kan inte vara tomt.");
        }
        this.name=name;
        this.active = active;
        this.clock = clock;
    }

    public int getId() {
        return id;
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
    @Override
    public String toString(){
        return String.format("ID:%d Name:%s, Active:", id, name, active);
    }
}

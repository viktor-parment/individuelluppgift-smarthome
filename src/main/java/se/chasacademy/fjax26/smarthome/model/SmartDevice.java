package se.chasacademy.fjax26.smarthome.model;

import se.chasacademy.fjax26.smarthome.exception.DeviceStateException;

import java.time.Clock;
import java.time.LocalDateTime;

public abstract class SmartDevice implements Activatable {
    private int id;
    private String name;
    private boolean active;
    private Clock clock;
    private final LocalDateTime installationDate;
    private LocalDateTime lastActivation;
    private LocalDateTime lastDeactivation;

    public SmartDevice(int id, String name, boolean active, Clock clock){
        if (id <= 0){
            throw new IllegalArgumentException("Id kan inte vara negativt eller noll.");
        }
        this.id = id;

        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("namn kan inte vara tomt eller null.");
        }
        this.name=name;
        this.active = active;
        this.clock = clock;
        this.installationDate = LocalDateTime.now(clock);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
        if (active) {
            lastActivation = LocalDateTime.now(clock);
        } else {
            lastDeactivation = LocalDateTime.now(clock);
        }
        return this.active;
    }

    public LocalDateTime getInstallationDate() {
        return installationDate;
    }

    public LocalDateTime getLastActivation() {
        return lastActivation;
    }

    public LocalDateTime getLastDeactivation() {
        return lastDeactivation;
    }

    @Override
    public String toString(){
        return String.format("ID:%d Name:%s, Active:%b", id, name, active);
    }
}

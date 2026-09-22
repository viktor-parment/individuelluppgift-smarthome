package se.chasacademy.fjax26.smarthome.model;

import java.time.Clock;

public abstract class SmartDevice implements Activatable {
    private int id;
    private String name;
    private boolean active;
    private Clock clock;

    public SmartDevice(int id, String name, Clock clock){
        this.id = id;
        this.name=name;
        active = false;
        this.clock = clock;
    }

    boolean activeDevice(){
        return active = true;
    }

    boolean deactiveDevice(){
        return active = false;
    }
}

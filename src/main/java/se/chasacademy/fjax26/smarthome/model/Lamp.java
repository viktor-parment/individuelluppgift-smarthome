package se.chasacademy.fjax26.smarthome.model;

import java.time.Clock;

public class Lamp extends SmartDevice{

    private int brightness;

    public Lamp(int id, String name, boolean active, Clock clock, int brightness) {
        super(id, name, active, clock);
        this.brightness = brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

}

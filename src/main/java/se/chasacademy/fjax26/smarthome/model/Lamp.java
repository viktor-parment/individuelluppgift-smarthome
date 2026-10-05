package se.chasacademy.fjax26.smarthome.model;

import java.time.Clock;

public class Lamp extends SmartDevice{
    private int brightness;

    public Lamp(int id, String name, boolean active, Clock clock) {
        super(id, name, active, clock);
        setBrightness(100);
    }

    public void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100){
            throw new IllegalArgumentException("Ljusstyrkan kan bara vara mellan 0 och 100");
        }
        this.brightness = brightness;
    }

    public int getBrightness() {
        return brightness;
    }
}

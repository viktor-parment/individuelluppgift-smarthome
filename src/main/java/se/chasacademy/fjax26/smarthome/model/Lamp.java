package se.chasacademy.fjax26.smarthome.model;

import java.time.Clock;

public class Lamp extends SmartDevice {
    private int brightness;

    public Lamp(int id, String name, boolean active, Clock clock) {
        super(id, name, active, clock);
        setBrightness(100);
    }

    public void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("Brightness can only be between 0 and 100");
        }
        this.brightness = brightness;
    }

    public int getBrightness() {
        return brightness;
    }

    @Override
    public String toString() {
        return String.format("Unit: Lamp      | [Brightness: %3d%%] | ", brightness) + super.toString();
    }
}

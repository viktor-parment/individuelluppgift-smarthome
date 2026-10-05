package se.chasacademy.fjax26.smarthome.model;

import java.time.Clock;

public class Radiator extends SmartDevice {

    private double targetTemperature;

    public Radiator(int id, String name, boolean active, Clock clock, double targetTemperature){
        super(id, name, active, clock);
        setTargetTemperature(targetTemperature);
    }

    public void setTargetTemperature(double targetTemperature) {
        if (targetTemperature < 0 || targetTemperature > 30){
            throw new IllegalArgumentException("Måltemperaturen måste vara mellan 0 och 30 grader");
        }
        this.targetTemperature = targetTemperature;
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }
}
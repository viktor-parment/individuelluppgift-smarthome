package se.chasacademy.fjax26.smarthome.model;

import java.time.Clock;

public class Radiator extends SmartDevice {

    private double targetTemprature;

    public Radiator(int id, String name, boolean active, Clock clock, double targetTemprature){
        super(id, name, active, clock);
        if (targetTemprature < 0 || targetTemprature > 30){
            throw new IllegalArgumentException("Måltemperaturen måste vara mellan 0 och 30 grader");
        }
        this.targetTemprature = targetTemprature;
    }

    public void setTargetTemperature(double targetTemperature) {
        this.targetTemprature = targetTemperature;
    }

    public double getTargetTemprature() {
        return targetTemprature;
    }
}

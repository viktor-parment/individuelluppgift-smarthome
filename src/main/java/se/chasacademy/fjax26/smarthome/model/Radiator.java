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
            throw new IllegalArgumentException("Target temperature must be between 0 and 30 degrees");
        }
        this.targetTemperature = targetTemperature;
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }

    @Override
    public String toString() {
        return String.format("Unit: Radiator  | [Target Temp: %5.1f°C] | ", targetTemperature) + super.toString();
    }
}
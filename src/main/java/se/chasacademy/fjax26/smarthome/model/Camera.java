package se.chasacademy.fjax26.smarthome.model;

import java.time.Clock;

public class Camera extends SmartDevice{
    private boolean isRecording;

    public Camera(int id, String name,boolean active, Clock clock){
        super(id, name, active, clock);
        isRecording = false;
    }

    public boolean toggleRecording(){
        isRecording = !isRecording;
        return isRecording;
    }

    public boolean isRecording() {
        return isRecording;
    }

    @Override
    public String toString() {
        return String.format("Unit: Camera    | [Recording: %-4s] | ", isRecording ? "On" : "Off") + super.toString();
    }
}

package se.chasacademy.fjax26.smarthome.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.*;

public class CameraTest {

    private Camera camera;

    @BeforeEach
    void setUp() {
        camera = new Camera(1, "Entré", true, Clock.systemDefaultZone());
    }

    @Test
    void toggleRecordingShouldSwitchFromFalseToTrue() {
        //Arrange with BeforeEach (isRecording starts as false)

        //Act
        boolean result = camera.toggleRecording();

        //Assert
        assertTrue(result);
    }

    @Test
    void toggleRecordingCalledTwiceShouldReturnToFalse() {
        //Arrange
        camera.toggleRecording();

        //Act
        boolean result = camera.toggleRecording();

        //Assert
        assertFalse(result);
    }
}
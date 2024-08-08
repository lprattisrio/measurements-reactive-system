package com.exercise.reactive.centralservice.domain.alerts;

import com.exercise.reactive.centralservice.domain.measurement.Measurement;
import com.exercise.reactive.centralservice.domain.measurement.TemperatureMeasurement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureAlertTest {

    @Test
    void testGenerateMessage() {
        Measurement measurement = new TemperatureMeasurement("Sensor1", 30.0f);
        TemperatureAlert alert = new TemperatureAlert(measurement);

        String expectedMessage = "There is a temperature alert on sensor Sensor1, sensor detected 30.0 degrees celsius of temperature";
        String actualMessage = alert.generateMessage();

        assertEquals(expectedMessage, actualMessage, "The alert message was not as expected");
    }
}

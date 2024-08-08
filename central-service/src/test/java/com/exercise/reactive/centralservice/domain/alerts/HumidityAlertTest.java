package com.exercise.reactive.centralservice.domain.alerts;


import com.exercise.reactive.centralservice.domain.measurement.HumidityMeasurement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HumidityAlertTest {

    @Test
    void testGenerateMessage() {
        HumidityAlert alert = new HumidityAlert(new HumidityMeasurement("1234", 75.0f));

        String expectedMessage = "There is a humidity alert on sensor 1234, sensor detected 75.0% of humidity";
        assertEquals(expectedMessage, alert.generateMessage());
    }

    @Test
    void testGetSensorId() {
        HumidityAlert alert = new HumidityAlert(new HumidityMeasurement("1234", 75.0f));
        assertEquals("1234", alert.getSensorId());
    }

    @Test
    void testGetValue() {
        HumidityAlert alert = new HumidityAlert(new HumidityMeasurement("1234", 75.0f));

        assertEquals(75.0, alert.getValue());
    }

}

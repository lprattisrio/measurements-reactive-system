package com.exercise.reactive.centralservice.domain.alerts;

import com.exercise.reactive.centralservice.domain.measurement.Measurement;
import com.exercise.reactive.centralservice.domain.measurement.TemperatureMeasurement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AlertTest {

    @Test
    void testNoArgsConstructor() {
        Alert alert = new Alert() {
            @Override
            public String generateMessage() {
                return null;
            }
        };
        assertNull(alert.getSensorId());
        assertEquals(0.0, alert.getValue());
    }

    @Test
    void testAllArgsConstructor() {
        Alert alert = new Alert("sensor1", 1.0f) {
            @Override
            public String generateMessage() {
                return null;
            }
        };
        assertEquals("sensor1", alert.getSensorId());
        assertEquals(1.0, alert.getValue());
    }

    @Test
    void testMeasurementConstructor() {
        Measurement measurement = new TemperatureMeasurement("sensor1", 1.0f);
        Alert alert = new Alert(measurement) {
            @Override
            public String generateMessage() {
                return null;
            }
        };
        assertEquals(measurement.getSensorId(), alert.getSensorId());
        assertEquals(measurement.getValue(), alert.getValue());
    }
}

package com.exercise.reactive.centralservice.domain.measurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HumidityMeasurementTest {
    @Test
    void test_constructor_and_getters() {
        String sensorId = "sensor123";
        float value = 65.5f;
        Measurement measurement = new HumidityMeasurement(sensorId, value);
        assertEquals(sensorId, measurement.getSensorId());
        assertEquals(value, measurement.getValue());
    }

    @Test
    void test_negative_value() {
        String sensorId = "sensor123";
        float value = -10.0f;
        Measurement measurement = new HumidityMeasurement(sensorId, value);
        assertEquals(sensorId, measurement.getSensorId());
        assertEquals(value, measurement.getValue());
    }

    @Test
    void test_zero_value() {
        String sensorId = "sensor123";
        float value = 0.0f;
        Measurement measurement = new HumidityMeasurement(sensorId, value);
        assertEquals(sensorId, measurement.getSensorId());
        assertEquals(value, measurement.getValue());
    }
}

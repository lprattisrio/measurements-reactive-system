package com.exercise.reactive.centralservice.domain.measurement;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MeasurementTest {

    public static class TestMeasurement extends Measurement {
        public TestMeasurement(String sensorId, float value) {
            super(sensorId, value);
        }

        public TestMeasurement() {
            super();
        }
    }

    @Test
    void test_constructor_and_getters() {
        String sensorId = "sensor123";
        float value = 65.5f;
        Measurement measurement = new TestMeasurement(sensorId, value);

        assertEquals(sensorId, measurement.getSensorId());
        assertEquals(value, measurement.getValue());
    }

    @Test
    void test_no_args_constructor() {
        Measurement measurement = new TestMeasurement();

        assertNull(measurement.getSensorId());
        assertEquals(0.0f, measurement.getValue());
    }
}

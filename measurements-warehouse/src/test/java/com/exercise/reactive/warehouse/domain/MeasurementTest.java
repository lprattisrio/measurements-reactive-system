package com.exercise.reactive.warehouse.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MeasurementTest {

    @Test
    void measurement_creation_with_correct_values() {
        MeasurementType type = MeasurementType.TEMPERATURE;
        float value = 456f;
        String sensorId = "123";

        Measurement measurement = new Measurement(type, value, sensorId);

        assertThat(measurement.getType()).isEqualTo(type);
        assertThat(measurement.getValue()).isEqualTo(value);
        assertThat(measurement.getSensorId()).isEqualTo(sensorId);
    }

    @Test
    void measurement_equality_based_on_values() {
        Measurement measurement1 = new Measurement(MeasurementType.TEMPERATURE, 456f, "123");
        Measurement measurement2 = new Measurement(MeasurementType.TEMPERATURE, 456f, "123");

        assertThat(measurement1).isEqualTo(measurement2);
    }

    @Test
    void measurement_inequality_based_on_type() {
        Measurement measurement1 = new Measurement(MeasurementType.TEMPERATURE, 456f, "123");
        Measurement measurement2 = new Measurement(MeasurementType.HUMIDITY, 456f, "123");

        assertThat(measurement1).isNotEqualTo(measurement2);
    }

    @Test
    void measurement_inequality_based_on_value() {
        Measurement measurement1 = new Measurement(MeasurementType.TEMPERATURE, 456f, "123");
        Measurement measurement2 = new Measurement(MeasurementType.TEMPERATURE, 789f, "123");

        assertThat(measurement1).isNotEqualTo(measurement2);
    }

    @Test
    void measurement_inequality_based_on_sensorId() {
        Measurement measurement1 = new Measurement(MeasurementType.TEMPERATURE, 456f, "123");
        Measurement measurement2 = new Measurement(MeasurementType.TEMPERATURE, 456f, "456");

        assertThat(measurement1).isNotEqualTo(measurement2);
    }
}



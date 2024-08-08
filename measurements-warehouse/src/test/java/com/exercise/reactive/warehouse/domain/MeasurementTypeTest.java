package com.exercise.reactive.warehouse.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

class MeasurementTypeTest {

    @Test
    void measurement_type_values() {
        assertThat(MeasurementType.values()).containsExactlyInAnyOrder(MeasurementType.TEMPERATURE, MeasurementType.HUMIDITY);
    }

    @Test
    void measurement_type_valueOf() {
        assertThat(MeasurementType.valueOf("TEMPERATURE")).isEqualTo(MeasurementType.TEMPERATURE);
        assertThat(MeasurementType.valueOf("HUMIDITY")).isEqualTo(MeasurementType.HUMIDITY);
    }

    @Test
    void measurement_type_invalid_valueOf() {
        Throwable thrown = catchThrowable(() -> MeasurementType.valueOf("PRESSURE"));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
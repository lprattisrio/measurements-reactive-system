package com.exercise.reactive.centralservice.ports.in.kafka.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeasurementTypeDtoTest {

    @Test
    void testValueOf() {
        assertEquals(MeasurementTypeDto.TEMPERATURE, MeasurementTypeDto.valueOf("TEMPERATURE"));
        assertEquals(MeasurementTypeDto.HUMIDITY, MeasurementTypeDto.valueOf("HUMIDITY"));
    }
}

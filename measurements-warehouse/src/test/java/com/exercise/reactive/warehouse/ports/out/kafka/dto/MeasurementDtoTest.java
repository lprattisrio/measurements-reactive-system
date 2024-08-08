package com.exercise.reactive.warehouse.ports.out.kafka.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeasurementDtoTest {

    @Test
    void test_constructor_and_getters() {
        MeasurementDto dto = new MeasurementDto(MeasurementTypeDto.TEMPERATURE, 23.5f);
        
        assertEquals(MeasurementTypeDto.TEMPERATURE, dto.type());
        assertEquals(23.5f, dto.value());
    }

    @Test
    void test_equals_and_hashCode() {
        MeasurementDto dto1 = new MeasurementDto(MeasurementTypeDto.TEMPERATURE, 23.5f);
        MeasurementDto dto2 = new MeasurementDto(MeasurementTypeDto.TEMPERATURE, 23.5f);
        MeasurementDto dto3 = new MeasurementDto(MeasurementTypeDto.HUMIDITY, 50.0f);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void test_to_string() {
        MeasurementDto dto = new MeasurementDto(MeasurementTypeDto.HUMIDITY, 50.0f);
        String expectedString = "MeasurementDto[type=HUMIDITY, value=50.0]";
        
        assertEquals(expectedString, dto.toString());
    }
}

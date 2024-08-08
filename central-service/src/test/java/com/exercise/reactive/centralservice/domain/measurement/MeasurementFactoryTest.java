package com.exercise.reactive.centralservice.domain.measurement;

import com.exercise.reactive.centralservice.ports.in.kafka.dto.MeasurementDto;
import com.exercise.reactive.centralservice.ports.in.kafka.dto.MeasurementTypeDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class MeasurementFactoryTest {

    @Test
    void create_humidity_measurement() {
        MeasurementDto dto = new MeasurementDto(MeasurementTypeDto.HUMIDITY, 33f);

        String key = "sensor123";
        Measurement measurement = MeasurementFactory.create(key, dto);

        assertInstanceOf(HumidityMeasurement.class, measurement);
        assertEquals(key, measurement.getSensorId());
        assertEquals(dto.value(), measurement.getValue());
    }

    @Test
    void create_temperature_measurement() {
        MeasurementDto dto = new MeasurementDto(MeasurementTypeDto.TEMPERATURE, 33f);

        String key = "sensor123";
        Measurement measurement = MeasurementFactory.create(key, dto);

        assertInstanceOf(TemperatureMeasurement.class, measurement);
        assertEquals(key, measurement.getSensorId());
        assertEquals(dto.value(), measurement.getValue());
    }
}

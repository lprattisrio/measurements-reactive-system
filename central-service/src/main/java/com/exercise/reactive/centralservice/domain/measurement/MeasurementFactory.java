package com.exercise.reactive.centralservice.domain.measurement;

import com.exercise.reactive.centralservice.ports.in.kafka.dto.MeasurementDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MeasurementFactory {

    public static Measurement create(String key, MeasurementDto value) {
        return switch (value.type()) {
            case HUMIDITY -> new HumidityMeasurement(key, value.value());
            case TEMPERATURE -> new TemperatureMeasurement(key, value.value());
        };
    }
}

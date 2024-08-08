package com.exercise.reactive.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Measurement {
    MeasurementType type;
    float value;
    String sensorId;
}

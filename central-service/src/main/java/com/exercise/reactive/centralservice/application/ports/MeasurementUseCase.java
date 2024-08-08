package com.exercise.reactive.centralservice.application.ports;

import com.exercise.reactive.centralservice.domain.measurement.Measurement;

public interface MeasurementUseCase {
    void checkMeasurement(Measurement measurement);
}

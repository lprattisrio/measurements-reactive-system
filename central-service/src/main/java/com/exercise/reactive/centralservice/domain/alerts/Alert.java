package com.exercise.reactive.centralservice.domain.alerts;

import com.exercise.reactive.centralservice.domain.measurement.Measurement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Alert {

    private String sensorId;
    private float value;

    protected Alert(Measurement measurement) {
        this.sensorId = measurement.getSensorId();
        this.value = measurement.getValue();
    }

    public abstract String generateMessage();

}

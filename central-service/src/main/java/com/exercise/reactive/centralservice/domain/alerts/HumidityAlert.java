package com.exercise.reactive.centralservice.domain.alerts;

import com.exercise.reactive.centralservice.domain.measurement.Measurement;

public class HumidityAlert extends Alert {

    public HumidityAlert(Measurement measurement) {
        super(measurement);
    }

    @Override
    public String generateMessage() {
        return "There is a humidity alert on sensor "+ this.getSensorId() +
                ", sensor detected "+ this.getValue() + "% of humidity";
    }

}

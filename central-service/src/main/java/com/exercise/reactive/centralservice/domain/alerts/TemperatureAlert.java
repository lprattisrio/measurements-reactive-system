package com.exercise.reactive.centralservice.domain.alerts;

import com.exercise.reactive.centralservice.domain.measurement.Measurement;

public class TemperatureAlert extends Alert {

    public TemperatureAlert(Measurement measurement) {
        super(measurement);
    }

    @Override
    public String generateMessage() {
        return "There is a temperature alert on sensor "+ this.getSensorId() + ", sensor detected "+
                this.getValue() + " degrees celsius of temperature";
    }

}

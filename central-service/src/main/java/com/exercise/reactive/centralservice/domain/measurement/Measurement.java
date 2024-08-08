package com.exercise.reactive.centralservice.domain.measurement;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Measurement {

    private String sensorId;

    private float value;

}

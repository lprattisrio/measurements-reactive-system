package com.exercise.reactive.warehouse.ports.out.kafka.dto;

public record MeasurementDto(MeasurementTypeDto type, float value) {

}


package com.exercise.reactive.centralservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "alerts")
public record AlertsConfiguration(Thresholds thresholds) {
    public record Thresholds(float temperature, float humidity) {
    }
}


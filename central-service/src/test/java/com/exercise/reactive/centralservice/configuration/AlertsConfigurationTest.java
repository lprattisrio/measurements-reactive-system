package com.exercise.reactive.centralservice.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

class AlertsConfigurationTest {

    private Environment environment;

    @BeforeEach
    public void setup() {
        MockEnvironment mockEnvironment = new MockEnvironment();
        mockEnvironment.withProperty("alerts.thresholds.temperature", "50");
        mockEnvironment.withProperty("alerts.thresholds.humidity", "70");
        environment = mockEnvironment;
    }

    @Test
    void testThresholds() {
        AlertsConfiguration configuration = Binder.get(environment).bind("alerts", AlertsConfiguration.class).get();
        assertThat(configuration.thresholds().temperature()).isEqualTo(50);
        assertThat(configuration.thresholds().humidity()).isEqualTo(70);
    }
}


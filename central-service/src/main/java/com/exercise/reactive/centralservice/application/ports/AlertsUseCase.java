package com.exercise.reactive.centralservice.application.ports;

import com.exercise.reactive.centralservice.domain.alerts.Alert;

public interface AlertsUseCase {
    void handleAlert(Alert alert);
}

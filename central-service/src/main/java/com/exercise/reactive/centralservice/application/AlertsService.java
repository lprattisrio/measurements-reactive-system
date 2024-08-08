package com.exercise.reactive.centralservice.application;

import com.exercise.reactive.centralservice.application.ports.AlertsUseCase;
import com.exercise.reactive.centralservice.domain.alerts.Alert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AlertsService implements AlertsUseCase {

    @Override
    public void handleAlert(Alert alert) {
        log.info(alert.generateMessage());
    }
}

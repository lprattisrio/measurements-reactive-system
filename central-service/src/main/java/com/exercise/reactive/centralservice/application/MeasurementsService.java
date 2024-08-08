package com.exercise.reactive.centralservice.application;

import com.exercise.reactive.centralservice.application.ports.AlertsUseCase;
import com.exercise.reactive.centralservice.application.ports.MeasurementUseCase;
import com.exercise.reactive.centralservice.configuration.AlertsConfiguration;
import com.exercise.reactive.centralservice.domain.alerts.HumidityAlert;
import com.exercise.reactive.centralservice.domain.alerts.TemperatureAlert;
import com.exercise.reactive.centralservice.domain.measurement.HumidityMeasurement;
import com.exercise.reactive.centralservice.domain.measurement.Measurement;
import com.exercise.reactive.centralservice.domain.measurement.TemperatureMeasurement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeasurementsService implements MeasurementUseCase {

    private final AlertsConfiguration alertsConfiguration;
    private final AlertsUseCase alertsUseCase;

    @Override
    public void checkMeasurement(Measurement measurement) {
        AlertsConfiguration.Thresholds thresholds = alertsConfiguration.thresholds();
        switch (measurement) {
            case TemperatureMeasurement tm when tm.getValue() > thresholds.temperature() -> alertsUseCase.handleAlert(new TemperatureAlert(tm));
            case HumidityMeasurement hm when hm.getValue() > thresholds.humidity() -> alertsUseCase.handleAlert(new HumidityAlert(hm));
            default -> log.debug("Nothing to alert");
        }
    }

}

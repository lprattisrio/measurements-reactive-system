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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import static org.mockito.Mockito.*;

class MeasurementsServiceTest {

    @InjectMocks
    private MeasurementsService measurementsService;

    @Mock
    private AlertsConfiguration alertsConfiguration;

    @Mock
    private AlertsUseCase alertsUseCase;

    @Mock
    private AlertsConfiguration.Thresholds thresholds;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(alertsConfiguration.thresholds()).thenReturn(thresholds);
    }

    @Test
    void testCheckMeasurement_TemperatureAlert() {
        TemperatureMeasurement tm = new TemperatureMeasurement("t1", 100);
        when(thresholds.temperature()).thenReturn(50f);

        measurementsService.checkMeasurement(tm);

        verify(alertsUseCase, times(1)).handleAlert(any(TemperatureAlert.class));
    }

    @Test
    @Disabled
    void testCheckMeasurement_HumidityAlert() {
        HumidityMeasurement hm = new HumidityMeasurement("h1", 50);
        when(thresholds.humidity()).thenReturn(50f);

        measurementsService.checkMeasurement(hm);

        verify(alertsUseCase, times(1)).handleAlert(any(HumidityAlert.class));
    }

    @Test
    void testCheckMeasurement_NoAlert() {
        TemperatureMeasurement tm = new TemperatureMeasurement("t1", 30f);
        when(thresholds.temperature()).thenReturn(50f);

        measurementsService.checkMeasurement(tm);

        verify(alertsUseCase, times(0)).handleAlert(any());
    }
}

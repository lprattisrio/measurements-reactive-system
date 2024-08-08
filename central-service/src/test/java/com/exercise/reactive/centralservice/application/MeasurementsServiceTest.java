package com.exercise.reactive.centralservice.application;

import com.exercise.reactive.centralservice.application.ports.AlertsUseCase;
import com.exercise.reactive.centralservice.configuration.AlertsConfiguration;
import com.exercise.reactive.centralservice.domain.alerts.HumidityAlert;
import com.exercise.reactive.centralservice.domain.alerts.TemperatureAlert;
import com.exercise.reactive.centralservice.domain.measurement.HumidityMeasurement;
import com.exercise.reactive.centralservice.domain.measurement.Measurement;
import com.exercise.reactive.centralservice.domain.measurement.TemperatureMeasurement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        Measurement tm = new TemperatureMeasurement("t1", 100);
        when(thresholds.temperature()).thenReturn(50f);

        measurementsService.checkMeasurement(tm);

        verify(alertsUseCase, times(1)).handleAlert(any(TemperatureAlert.class));
    }

    @Test
    void testCheckMeasurement_HumidityAlert() {
        Measurement hm = new HumidityMeasurement("h1", 51);
        when(thresholds.humidity()).thenReturn(50f);

        measurementsService.checkMeasurement(hm);

        verify(alertsUseCase, times(1)).handleAlert(any(HumidityAlert.class));
    }

    @Test
    void testCheckMeasurement_TemperatureNoAlert() {
        Measurement tm = new TemperatureMeasurement("t1", 30f);
        when(thresholds.temperature()).thenReturn(50f);

        measurementsService.checkMeasurement(tm);

        verify(alertsUseCase, times(0)).handleAlert(any());
    }

    @Test
    void testCheckMeasurement_HumidityNoAlert() {
        Measurement tm = new HumidityMeasurement("h1", 30f);
        when(thresholds.humidity()).thenReturn(50f);

        measurementsService.checkMeasurement(tm);

        verify(alertsUseCase, times(0)).handleAlert(any());
    }
}

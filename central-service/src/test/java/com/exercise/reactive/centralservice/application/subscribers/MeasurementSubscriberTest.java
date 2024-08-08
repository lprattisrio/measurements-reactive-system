package com.exercise.reactive.centralservice.application.subscribers;

import com.exercise.reactive.centralservice.application.MeasurementsService;
import com.exercise.reactive.centralservice.domain.measurement.Measurement;
import com.exercise.reactive.centralservice.domain.measurement.TemperatureMeasurement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MeasurementSubscriberTest {


    @Mock
    private MeasurementsService measurementsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInit() {
        Measurement measurement = new TemperatureMeasurement("h1", 15);
        Flux<Measurement> flux = Flux.just(measurement);

        MeasurementSubscriber measurementSubscriber = new MeasurementSubscriber(flux, measurementsService);
        measurementSubscriber.init();

        verify(measurementsService, times(1)).checkMeasurement(measurement);

    }
}

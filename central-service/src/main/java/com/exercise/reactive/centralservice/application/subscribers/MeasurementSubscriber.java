package com.exercise.reactive.centralservice.application.subscribers;

import com.exercise.reactive.centralservice.application.MeasurementsService;
import com.exercise.reactive.centralservice.domain.measurement.Measurement;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeasurementSubscriber {

    private final Flux<Measurement> flux;
    private final MeasurementsService measurementsService;

    @PostConstruct
    public void init() {
        flux.subscribe(measurementsService::checkMeasurement, e -> log.error("Error processing measurements", e));
    }
}

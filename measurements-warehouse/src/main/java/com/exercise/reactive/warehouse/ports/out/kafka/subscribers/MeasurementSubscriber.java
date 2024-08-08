package com.exercise.reactive.warehouse.ports.out.kafka.subscribers;

import com.exercise.reactive.warehouse.domain.Measurement;
import com.exercise.reactive.warehouse.ports.out.kafka.dto.MeasurementDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import static com.exercise.reactive.warehouse.ports.out.kafka.dto.MeasurementTypeDto.fromDomainType;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeasurementSubscriber {

    private final Flux<Measurement> flux;
    private final ReactiveKafkaProducerTemplate<String, MeasurementDto> producer;

    @PostConstruct
    public void init() {
        flux.subscribe(this::sendToQueue, e -> log.error("Error processing measurements", e));
    }

    private void sendToQueue(Measurement measurement) {
        producer.send("measurements-topic", measurement.getSensorId(),
                        new MeasurementDto(fromDomainType(measurement.getType()), measurement.getValue()))
                .doOnSuccess(r -> log.debug("Measurement published to kafka: {}", measurement))
                .subscribe();
    }
}

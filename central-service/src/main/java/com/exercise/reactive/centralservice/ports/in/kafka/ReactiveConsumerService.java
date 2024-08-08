package com.exercise.reactive.centralservice.ports.in.kafka;

import com.exercise.reactive.centralservice.domain.measurement.Measurement;
import com.exercise.reactive.centralservice.domain.measurement.MeasurementFactory;
import com.exercise.reactive.centralservice.ports.in.kafka.dto.MeasurementDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class ReactiveConsumerService {

    @Bean
    public Flux<Measurement> getMeasurement(ReactiveKafkaConsumerTemplate<String, MeasurementDto> consumer) {
        return consumer.receiveAutoAck()
                .doOnError(throwable -> log.error("something bad happened while consuming : {}", throwable.getMessage()))
                .mapNotNull(r -> MeasurementFactory.create(r.key(), r.value()))
                .publish()
                .autoConnect();
    }
}

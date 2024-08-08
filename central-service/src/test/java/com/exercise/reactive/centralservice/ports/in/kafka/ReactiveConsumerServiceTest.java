package com.exercise.reactive.centralservice.ports.in.kafka;

import com.exercise.reactive.centralservice.domain.measurement.Measurement;
import com.exercise.reactive.centralservice.domain.measurement.MeasurementFactory;
import com.exercise.reactive.centralservice.ports.in.kafka.dto.MeasurementDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

public class ReactiveConsumerServiceTest {

}

package com.exercise.reactive.centralservice.ports.in.kafka.configuration;

import com.exercise.reactive.centralservice.ports.in.kafka.dto.MeasurementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeasurementsConsumerConfig {

    public static final String TOPIC = "measurements-topic";

    //TODO: Try to abstract some functionality

    @Bean
    public ReactiveKafkaConsumerTemplate<String, MeasurementDto> getTemplate(ReceiverOptions<String, MeasurementDto> kafkaReceiverOptions) {
        return new ReactiveKafkaConsumerTemplate<>(kafkaReceiverOptions);
    }

    @Bean
    public ReceiverOptions<String, MeasurementDto> createKafkaReceiver(KafkaProperties props) {
        var kafkaOptions = ReceiverOptions.<String,MeasurementDto>create(props.buildConsumerProperties(null))
                .withKeyDeserializer(new StringDeserializer())
                .withValueDeserializer(new JsonDeserializer<>(MeasurementDto.class)
                        .trustedPackages("*")
                        .ignoreTypeHeaders());
        return kafkaOptions.subscription(Collections.singleton(TOPIC));
    }

}

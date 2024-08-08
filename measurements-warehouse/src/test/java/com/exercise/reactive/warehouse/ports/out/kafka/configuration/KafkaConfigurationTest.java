package com.exercise.reactive.warehouse.ports.out.kafka.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class KafkaConfigurationTest {

    private KafkaConfiguration<String, String> kafkaConfiguration;
    private KafkaProperties kafkaProperties;
    private KafkaProperties.Producer producer;

    @BeforeEach
    public void setup() {
        kafkaProperties = mock(KafkaProperties.class);
        producer = mock(KafkaProperties.Producer.class);
        kafkaConfiguration = new KafkaConfiguration<>();
    }

    @Test
    void test_get_reactive_kafka_producer_template() {
        when(kafkaProperties.getBootstrapServers()).thenReturn(Collections.singletonList("localhost:9092"));
        when(kafkaProperties.getProducer()).thenReturn(producer);
        when(producer.getClientId()).thenReturn("test-client");

        assertNotNull(kafkaConfiguration.getReactiveKafkaProducerTemplate(kafkaProperties));
    }

}

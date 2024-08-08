package com.exercise.reactive.warehouse.ports.out.kafka.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration <K, V> {

    @Bean
    public ReactiveKafkaProducerTemplate<K, V> getReactiveKafkaProducerTemplate(KafkaProperties kafkaProperties) {
        Map<String, Object> props = getPropertiesMap(kafkaProperties);
        return new ReactiveKafkaProducerTemplate<>(KafkaSender.create(SenderOptions.create(props)));
    }

    private static Map<String, Object> getPropertiesMap(KafkaProperties kafkaProperties) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        KafkaProperties.Producer producer = kafkaProperties.getProducer();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producer.getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producer.getValueSerializer());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, producer.getClientId());

        return props;
    }
}

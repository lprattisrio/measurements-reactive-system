package com.exercise.reactive.centralservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CentralServiceApplicationTest {

    @Test
    void contextLoads() {
        ConfigurableApplicationContext context = SpringApplication.run(CentralServiceApplication.class);
        assertNotNull(context);
        assertNotNull(context.getBeanFactory().getBean(ReactiveKafkaConsumerTemplate.class));
    }
}

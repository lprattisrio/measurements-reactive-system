package com.exercise.reactive.warehouse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MeasurementsWarehouseApplicationTest {

	@Test
	void contextLoads() {
		ConfigurableApplicationContext context = SpringApplication.run(MeasurementsWarehouseApplication.class);
		assertNotNull(context.getBean(ReactiveKafkaProducerTemplate.class));
	}
}

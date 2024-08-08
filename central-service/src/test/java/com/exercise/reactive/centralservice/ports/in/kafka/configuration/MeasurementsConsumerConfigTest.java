package com.exercise.reactive.centralservice.ports.in.kafka.configuration;

import com.exercise.reactive.centralservice.ports.in.kafka.dto.MeasurementDto;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeasurementsConsumerConfigTest {

    @Mock
    private KafkaProperties kafkaProperties;

    @InjectMocks
    private MeasurementsConsumerConfig measurementsConsumerConfig;

    @BeforeEach
    public void setUp() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "test:9092");
        when(kafkaProperties.buildConsumerProperties(null)).thenReturn(props);
    }

    @Test
    void test_get_template() {
        var receiverOptions = measurementsConsumerConfig.createKafkaReceiver(kafkaProperties);
        var template = measurementsConsumerConfig.getTemplate(receiverOptions);
        assertNotNull(template);
    }

    @Test
    void test_create_kafka_receiver() {
        var receiverOptions = measurementsConsumerConfig.createKafkaReceiver(kafkaProperties);
        assertEquals("test:9092", receiverOptions.bootstrapServers());
        assertNotNull(receiverOptions);

        Deserializer<String> keyDeserializer = receiverOptions.keyDeserializer();
        assertNotNull(keyDeserializer);
        assertEquals(StringDeserializer.class, keyDeserializer.getClass());

        Deserializer<MeasurementDto> valueDeserializer = receiverOptions.valueDeserializer();
        assertNotNull(valueDeserializer);
        assertEquals(JsonDeserializer.class, valueDeserializer.getClass());
    }
}

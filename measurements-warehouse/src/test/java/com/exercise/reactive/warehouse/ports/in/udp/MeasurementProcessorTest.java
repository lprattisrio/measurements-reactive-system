package com.exercise.reactive.warehouse.ports.in.udp;

import com.exercise.reactive.warehouse.domain.MeasurementType;
import com.exercise.reactive.warehouse.ports.in.udp.MeasurementProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.messaging.Message;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MeasurementProcessorTest {

    @Test
    void process_message_correctly() {
        MeasurementProcessor processor = new MeasurementProcessor();
        Message<byte[]> mockMessage = Mockito.mock(Message.class);
        Mockito.when(mockMessage.getPayload()).thenReturn("time=123;value=456".getBytes());

        processor.processMessage(mockMessage, MeasurementType.TEMPERATURE);

        StepVerifier.create(processor.getMeasurementFlux().take(1))
                .expectNextMatches(measurement -> measurement.getType() == MeasurementType.TEMPERATURE
                        && measurement.getValue() == 456f
                        && measurement.getSensorId().equals("123"))
                .verifyComplete();
    }

    @Test
    void process_message_with_invalid_payload() {
        MeasurementProcessor processor = new MeasurementProcessor();
        Message<byte[]> mockMessage = Mockito.mock(Message.class);
        Mockito.when(mockMessage.getPayload()).thenReturn("invalid_payload".getBytes());

        assertThrows(IllegalArgumentException.class, () -> {
            processor.processMessage(mockMessage, MeasurementType.TEMPERATURE);
        });
    }
}

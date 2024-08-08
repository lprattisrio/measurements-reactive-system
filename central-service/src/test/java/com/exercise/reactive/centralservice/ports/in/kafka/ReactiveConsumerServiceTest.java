package com.exercise.reactive.centralservice.ports.in.kafka;

import com.exercise.reactive.centralservice.domain.measurement.Measurement;
import com.exercise.reactive.centralservice.ports.in.kafka.dto.MeasurementDto;
import com.exercise.reactive.centralservice.ports.in.kafka.dto.MeasurementTypeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReactiveConsumerServiceTest {

    @Mock
    private ReactiveKafkaConsumerTemplate<String, MeasurementDto> consumer;

    @InjectMocks
    private ReactiveConsumerService reactiveConsumerService;

    @BeforeEach
    public void setUp() {
        when(consumer.receiveAutoAck()).thenReturn(Flux.empty());
    }

    @Test
    void test_get_measurement_success() {
        ReceiverRecord<String, MeasurementDto> recordMock = mock(ReceiverRecord.class);
        when(recordMock.key()).thenReturn("t1");
        when(recordMock.value()).thenReturn(new MeasurementDto(MeasurementTypeDto.TEMPERATURE, 23.5f));
        when(consumer.receiveAutoAck()).thenReturn(Flux.just(recordMock));

        Flux<Measurement> measurementsFlux = reactiveConsumerService.getMeasurement(consumer);

        StepVerifier.create(measurementsFlux)
                .expectNextMatches(measurement -> "t1".equals(measurement.getSensorId()) && measurement.getValue() == 23.5f)
                .verifyComplete();
    }

    @Test
    void test_get_measurement_success_multiple_subscribers() {
        ReceiverRecord<String, MeasurementDto> recordMock = mock(ReceiverRecord.class);
        when(recordMock.key()).thenReturn("t1");
        when(recordMock.value()).thenReturn(new MeasurementDto(MeasurementTypeDto.TEMPERATURE, 23.5f));
        when(consumer.receiveAutoAck()).thenReturn(Flux.just(recordMock));

        Flux<Measurement> measurementsFlux = reactiveConsumerService.getMeasurement(consumer);

        StepVerifier.create(measurementsFlux)
                .expectNextMatches(measurement -> "t1".equals(measurement.getSensorId()) && measurement.getValue() == 23.5f)
                .thenCancel()
                .verify();

        Flux<Measurement> measurementsFluxForSecondSubscriber = reactiveConsumerService.getMeasurement(consumer);

        StepVerifier.create(measurementsFluxForSecondSubscriber)
                .expectNextMatches(measurement -> "t1".equals(measurement.getSensorId()) && measurement.getValue() == 23.5f)
                .thenCancel()
                .verify();
    }

    @Test
    void testGetMeasurementError() {
        when(consumer.receiveAutoAck()).thenReturn(Flux.error(new RuntimeException("Test exception")));
        Flux<Measurement> measurementsFlux = reactiveConsumerService.getMeasurement(consumer);
        StepVerifier.create(measurementsFlux)
                .expectErrorMessage("Test exception")
                .verify();
    }
}

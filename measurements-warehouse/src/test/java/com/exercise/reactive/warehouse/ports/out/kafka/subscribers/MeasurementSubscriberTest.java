package com.exercise.reactive.warehouse.ports.out.kafka.subscribers;

import com.exercise.reactive.warehouse.domain.Measurement;
import com.exercise.reactive.warehouse.domain.MeasurementType;
import com.exercise.reactive.warehouse.ports.out.kafka.dto.MeasurementDto;
import com.exercise.reactive.warehouse.ports.out.kafka.dto.MeasurementTypeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.publisher.TestPublisher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MeasurementSubscriberTest {

    @Mock
    private Logger log;

    @Mock
    private ReactiveKafkaProducerTemplate<String, MeasurementDto> producer;

    @Mock
    private Flux<Measurement> flux;

    @InjectMocks
    private MeasurementSubscriber measurementSubscriber;

    private TestPublisher<Measurement> testPublisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testPublisher = TestPublisher.create();
        Flux<Measurement> flux = testPublisher.flux();
        measurementSubscriber = new MeasurementSubscriber(flux, producer);
        measurementSubscriber.init();
    }

    @Test
    void init_should_subscribe_to_flux_and_send_to_queue() {
        Measurement measurement = new Measurement(MeasurementType.TEMPERATURE, 456f, "123");
        MeasurementDto measurementDto = new MeasurementDto(MeasurementTypeDto.TEMPERATURE, 456f);

        when(producer.send(anyString(), anyString(), any())).thenReturn(Mono.empty());

        testPublisher.next(measurement);

        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<MeasurementDto> valueCaptor = ArgumentCaptor.forClass(MeasurementDto.class);

        verify(producer, times(1)).send(topicCaptor.capture(), keyCaptor.capture(), valueCaptor.capture());

        assertThat(topicCaptor.getValue()).isEqualTo("measurements-topic");
        assertThat(keyCaptor.getValue()).isEqualTo("123");
        assertThat(valueCaptor.getValue()).isEqualToComparingFieldByField(measurementDto);
    }

    @Test
    void send_to_queue_should_handle_success() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Measurement measurement = new Measurement(MeasurementType.TEMPERATURE, 456f, "123");
        MeasurementDto measurementDto = new MeasurementDto(MeasurementTypeDto.TEMPERATURE, 456f);

        when(producer.send(anyString(), anyString(), any())).thenReturn(Mono.empty());

        Method sendToQueueMethod = MeasurementSubscriber.class.getDeclaredMethod("sendToQueue", Measurement.class);
        sendToQueueMethod.setAccessible(true);
        sendToQueueMethod.invoke(measurementSubscriber, measurement);

        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<MeasurementDto> valueCaptor = ArgumentCaptor.forClass(MeasurementDto.class);

        verify(producer, times(1)).send(topicCaptor.capture(), keyCaptor.capture(), valueCaptor.capture());

        assertThat(topicCaptor.getValue()).isEqualTo("measurements-topic");
        assertThat(keyCaptor.getValue()).isEqualTo("123");
        assertThat(valueCaptor.getValue()).isEqualToComparingFieldByField(measurementDto);
    }
}

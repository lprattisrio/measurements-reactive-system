package com.exercise.reactive.warehouse.ports.in.udp;

import com.exercise.reactive.warehouse.domain.MeasurementType;
import com.exercise.reactive.warehouse.ports.in.udp.MeasurementProcessor;
import com.exercise.reactive.warehouse.ports.in.udp.TemperatureReceiver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class TemperatureReceiverTest {

    private TemperatureReceiver temperatureReceiver;
    private MeasurementProcessor measurementProcessor;

    @BeforeEach
    public void setUp() {
        measurementProcessor = mock(MeasurementProcessor.class);
        temperatureReceiver = new TemperatureReceiver();
    }

    @Test
    void temperature_channel_is_not_null() {
        assertNotNull(temperatureReceiver.temperatureChannel());
    }

    @Test
    void temperature_udp_adapter_has_correct_port() {
        UnicastReceivingChannelAdapter adapter = temperatureReceiver.temperatureUdpAdapter();
        assertNotNull(adapter);
        assertEquals(3344, adapter.getPort());
    }

    @Test
    void temperature_handler_calls_process_message() {
        Message<String> message = MessageBuilder.withPayload("test").build();
        doNothing().when(measurementProcessor).processMessage(any(Message.class), eq(MeasurementType.TEMPERATURE));
        temperatureReceiver.temperatureHandler(measurementProcessor).handleMessage(message);
        verify(measurementProcessor, times(1)).processMessage(any(Message.class), eq(MeasurementType.TEMPERATURE));
    }
}

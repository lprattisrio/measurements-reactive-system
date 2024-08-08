package com.exercise.reactive.warehouse.ports.in.udp;

import com.exercise.reactive.warehouse.domain.MeasurementType;
import com.exercise.reactive.warehouse.ports.in.udp.HumidityReceiver;
import com.exercise.reactive.warehouse.ports.in.udp.MeasurementProcessor;
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

class HumidityReceiverTest {

    private HumidityReceiver humidityReceiver;
    private MeasurementProcessor measurementProcessor;

    @BeforeEach
    public void setUp() {
        measurementProcessor = mock(MeasurementProcessor.class);
        humidityReceiver = new HumidityReceiver();
    }

    @Test
    void humidity_channel_is_not_null() {
        assertNotNull(humidityReceiver.humidityChannel());
    }

    @Test
    void humidity_udp_adapter_has_correct_port() {
        UnicastReceivingChannelAdapter adapter = humidityReceiver.humidityUdpAdapter();
        assertNotNull(adapter);
        assertEquals(3355, adapter.getPort());
    }

    @Test
    void humidity_handler_calls_process_message() {
        Message<String> message = MessageBuilder.withPayload("test").build();
        doNothing().when(measurementProcessor).processMessage(any(Message.class), eq(MeasurementType.HUMIDITY));
        humidityReceiver.humidityHandler(measurementProcessor).handleMessage(message);
        verify(measurementProcessor, times(1)).processMessage(any(Message.class), eq(MeasurementType.HUMIDITY));
    }
}

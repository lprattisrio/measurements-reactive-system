package com.exercise.reactive.warehouse.ports.in.udp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import static com.exercise.reactive.warehouse.domain.MeasurementType.HUMIDITY;


@Configuration
public class HumidityReceiver {

    private static final int PORT = 3355;

    @Bean
    public MessageChannel humidityChannel() {
        return new DirectChannel();
    }

    @Bean
    public UnicastReceivingChannelAdapter humidityUdpAdapter() {
        UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(PORT);
        adapter.setOutputChannel(humidityChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "humidityChannel")
    public MessageHandler humidityHandler(MeasurementProcessor measurementProcessor) {
        return message -> measurementProcessor.processMessage(message, HUMIDITY);
    }
}

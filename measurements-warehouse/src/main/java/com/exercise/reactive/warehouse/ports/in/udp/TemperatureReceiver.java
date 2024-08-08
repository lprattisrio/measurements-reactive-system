package com.exercise.reactive.warehouse.ports.in.udp;

import com.exercise.reactive.warehouse.domain.MeasurementType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class TemperatureReceiver {

    private static final int PORT = 3344;

    @Bean
    public MessageChannel temperatureChannel() {
        return new DirectChannel();
    }

    @Bean
    public UnicastReceivingChannelAdapter temperatureUdpAdapter() {
        UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(PORT);
        adapter.setOutputChannel(temperatureChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "temperatureChannel")
    public MessageHandler temperatureHandler(MeasurementProcessor measurementProcessor) {
        return message -> measurementProcessor.processMessage(message, MeasurementType.TEMPERATURE);
    }
}

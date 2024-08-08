package com.exercise.reactive.warehouse.ports.in.udp;

import com.exercise.reactive.warehouse.domain.Measurement;
import com.exercise.reactive.warehouse.domain.MeasurementType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class MeasurementProcessor {

    private static final Sinks.Many<Measurement> sink = Sinks.many().multicast().onBackpressureBuffer();

    public void processMessage(Message<?> msg, MeasurementType type) {

        if (log.isDebugEnabled()) {
            log.debug("Message received: {}", new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8));
        }

        final String[] parts = new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8).split(";");
        if (parts.length >= 2) {
            Measurement measurement = new Measurement(type,(float) Double.parseDouble(parts[1].split("=")[1]),
                    parts[0].split("=")[1]);
            sink.tryEmitNext(measurement);
        } else {
            throw new IllegalArgumentException("Invalid measurement type: " + type);
        }
    }

    @Bean
    public Flux<Measurement> getMeasurementFlux() {
        return sink.asFlux();
    }
}

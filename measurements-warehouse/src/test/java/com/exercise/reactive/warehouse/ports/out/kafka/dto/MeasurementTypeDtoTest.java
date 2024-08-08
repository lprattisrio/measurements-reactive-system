package com.exercise.reactive.warehouse.ports.out.kafka.dto;

import com.exercise.reactive.warehouse.domain.MeasurementType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MeasurementTypeDtoTest {

    @ParameterizedTest
    @EnumSource(MeasurementTypeDto.class)
    void testEnumValues(MeasurementTypeDto typeDto) {
        assertNotNull(typeDto.getDomainType());
    }

    @ParameterizedTest
    @MethodSource("provideMeasurementTypes")
    void testFromDomainType(MeasurementType domainType, MeasurementTypeDto expectedDto) {
        assertEquals(expectedDto, MeasurementTypeDto.fromDomainType(domainType));
    }

    private static Stream<Arguments> provideMeasurementTypes() {
        return Stream.of(
            Arguments.of(MeasurementType.TEMPERATURE, MeasurementTypeDto.TEMPERATURE),
            Arguments.of(MeasurementType.HUMIDITY, MeasurementTypeDto.HUMIDITY),
            Arguments.of(null, null)
        );
    }
}

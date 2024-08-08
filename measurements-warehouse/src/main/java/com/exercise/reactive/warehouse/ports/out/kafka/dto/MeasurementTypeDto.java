package com.exercise.reactive.warehouse.ports.out.kafka.dto;

import com.exercise.reactive.warehouse.domain.MeasurementType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum MeasurementTypeDto {

    TEMPERATURE(MeasurementType.TEMPERATURE),
    HUMIDITY(MeasurementType.HUMIDITY);

    private final MeasurementType domainType;

    public static MeasurementTypeDto fromDomainType(MeasurementType domainType) {
        for (MeasurementTypeDto type : MeasurementTypeDto.values()) {
            if (type.getDomainType() == domainType) {
                return type;
            }
        }
        return null;
    }

}
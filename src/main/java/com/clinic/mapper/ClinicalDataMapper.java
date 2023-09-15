package com.clinic.mapper;

import com.clinic.dto.ClinicalDataRequest;
import com.clinic.entity.ClinicalData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClinicalDataMapper {

    public ClinicalData mapTo(ClinicalDataRequest request) {
        return ClinicalData.builder().componentName(request.getComponentName()).componentValue(request.getComponentValue()).measuredDateTime(LocalDateTime.now()).build();
    }
}

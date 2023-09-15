package com.clinic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicalDataRequest {

    private int patientId;
    private String componentName;
    private String componentValue;
}

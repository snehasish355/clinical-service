package com.clinic.controllers;

import com.clinic.dao.ClinicalDataRepository;
import com.clinic.dao.PatientRepository;
import com.clinic.dto.ClinicalDataRequest;
import com.clinic.entity.ClinicalData;
import com.clinic.entity.Patient;
import com.clinic.mapper.ClinicalDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ClinicalDataController {

    private ClinicalDataMapper mapper;
    private PatientRepository patientRepository;
    private ClinicalDataRepository clinicalDataRepository;

    @PostMapping(value = "/clinicals")
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request) {
        Patient patient = patientRepository.findById(request.getPatientId()).get();
        ClinicalData clinicalData = mapper.mapTo(request);
        clinicalData.setPatient(patient);
        return clinicalDataRepository.save(clinicalData);
    }
}

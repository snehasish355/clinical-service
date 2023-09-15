package com.clinic.controllers;

import com.clinic.dao.PatientRepository;
import com.clinic.entity.ClinicalData;
import com.clinic.entity.Patient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
    private Map<String, String> filter = new HashMap<>();

    @GetMapping(value = "/patients")
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @GetMapping(value = "/patient/{id}")
    public Patient getAllPatient(@PathVariable int id) {
        return patientRepository.findById(id).get();
    }

    @PostMapping(value = "/addPatient")
    public Patient savePatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }


    @GetMapping(value = "/analysis/{id}")
    public Patient analyze(@PathVariable int id) {
        Patient patient = patientRepository.findById(id).get();
        List<ClinicalData> clinicalDBData = patient.getClinicalDataList();
        List<ClinicalData> clinicalData = new ArrayList<>(clinicalDBData);
        for (ClinicalData data : clinicalData) {
            if (filter.containsKey(data.getComponentName())) {
                clinicalDBData.remove(data);
                continue;
            } else {
                filter.put(data.getComponentName(), null);
            }
            if (data.getComponentName().equals("hw")) {
                String[] hw = data.getComponentValue().split("/");
                if (hw != null && hw.length > 1) {
                    double heightInMeter = Double.parseDouble(hw[0]) * 0.4536F;
                    double bmi = Double.parseDouble(hw[1]) / (heightInMeter * heightInMeter);
                    ClinicalData bmiData = ClinicalData.builder().componentName("bmi").componentValue(Double.toString(bmi)).measuredDateTime(LocalDateTime.now()).build();
                    clinicalDBData.add(bmiData);
                }
            }
        }
        filter.clear();
        return patient;
    }
}

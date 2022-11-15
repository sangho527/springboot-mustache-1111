package com.mustache.springbootmustache1111.controller;


import com.mustache.springbootmustache1111.domain.dto.HospitalResponse;
import com.mustache.springbootmustache1111.domain.entity.Hospital;
import com.mustache.springbootmustache1111.repository.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalRepository hospitalRepository;

    public HospitalRestController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) { //ResponseEntity도 Dto타입
        Optional<Hospital> hospital = hospitalRepository.findById(id); // Entity
        HospitalResponse hospitalResponse = Hospital.of(hospital.get()); //
        return ResponseEntity.ok().body(hospitalResponse); // return은 dto
    }
}
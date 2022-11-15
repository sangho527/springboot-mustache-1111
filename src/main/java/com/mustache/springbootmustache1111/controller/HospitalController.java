package com.mustache.springbootmustache1111.controller;

import com.mustache.springbootmustache1111.domain.dto.HospitalResponse;
import com.mustache.springbootmustache1111.domain.entity.Hospital;
import com.mustache.springbootmustache1111.repository.HospitalRepository;
import com.mustache.springbootmustache1111.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {

    private final HospitalRepository hospitalRepository;
    private final HospitalService hospitalService;

    public HospitalController(HospitalRepository hospitalRepository, HospitalService hospitalService) {
        this.hospitalRepository = hospitalRepository;
        this.hospitalService = hospitalService;
    }


    @GetMapping("")
    public String hospitalList(Model model, Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        log.info("size:{}", hospitals.getSize());
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospital/list";
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) { // ResponseEntity도 DTO타입
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); // DTO
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }

}
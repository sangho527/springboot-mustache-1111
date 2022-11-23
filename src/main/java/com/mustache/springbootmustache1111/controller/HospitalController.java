package com.mustache.springbootmustache1111.controller;

import com.mustache.springbootmustache1111.domain.entity.Hospital;
import com.mustache.springbootmustache1111.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {
    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    @GetMapping("")
    public String hospitalList(@RequestParam(required = false) String keyword, Model model, Pageable pageable) {
        log.info(keyword);
        Page<Hospital> hospitals;

        if (keyword != null) {
            hospitals = hospitalRepository.findByRoadNameAddressContaining(keyword, pageable);
        }else {
            hospitals = hospitalRepository.findAll(pageable);
        }

        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospitals/list";
    }
}

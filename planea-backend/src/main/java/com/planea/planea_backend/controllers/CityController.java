package com.planea.planea_backend.controllers;

import com.planea.planea_backend.dtos.CityDto;
import com.planea.planea_backend.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<CityDto> getAllCities() {
        return cityService.findAll();
    }
}

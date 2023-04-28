package com.devmountain.dogBlog.controllers;


import com.devmountain.dogBlog.dtos.VaccinationsDTO;
import com.devmountain.dogBlog.entities.Vaccinations;
import com.devmountain.dogBlog.services.VaccinationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vaccinations")

public class VaccinationsController {
    @Autowired
    private VaccinationsService vaccinationsService;

    @PostMapping("/users/{userId}")
    public void addVaccination(@RequestBody VaccinationsDTO vaccinationsDTO, @PathVariable Long userId){
        vaccinationsService.addVaccination(vaccinationsDTO, userId);
    }

    @DeleteMapping("/{vaccinationId}")
    public void deleteVaccination(@PathVariable Long vaccinationId){
        vaccinationsService.deleteVaccination(vaccinationId);
    }

    @PutMapping("/{vaccinationId}")
    public void editVaccination(@RequestBody VaccinationsDTO vaccinationsDTO, @PathVariable Long vaccinationId) {
        vaccinationsService.editVaccination(vaccinationsDTO);
    }

    @GetMapping("/users/{userId}/vaccinations")
    public List<Vaccinations> getAllVaccinationsByUserId(@PathVariable Long userId){
        return vaccinationsService.getAllVaccinationsByUserId(userId);
    }

    @GetMapping("/{vaccinationId}")
    public Optional<VaccinationsDTO> getVaccinationById(@PathVariable Long vaccinationId){
        return vaccinationsService.getVaccinationById(vaccinationId);
    }


}
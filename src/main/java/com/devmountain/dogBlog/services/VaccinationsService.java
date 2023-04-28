package com.devmountain.dogBlog.services;

import com.devmountain.dogBlog.dtos.VaccinationsDTO;
import com.devmountain.dogBlog.entities.Vaccinations;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface VaccinationsService {
    @Transactional
    void addVaccination(VaccinationsDTO vaccinationsDTO, Long vaccinationId);

    @Transactional
    void deleteVaccination(Long vaccinationId);

    @Transactional
    void editVaccination(VaccinationsDTO vaccinationsDTO);

    List<Vaccinations> getAllVaccinationsByUserId(Long userId);

    Optional<VaccinationsDTO> getVaccinationById(Long vaccinationId);
}

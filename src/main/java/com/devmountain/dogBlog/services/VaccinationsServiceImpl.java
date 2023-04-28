package com.devmountain.dogBlog.services;

import com.devmountain.dogBlog.dtos.VaccinationsDTO;
import com.devmountain.dogBlog.entities.Accounts;
import com.devmountain.dogBlog.entities.Vaccinations;
import com.devmountain.dogBlog.repositories.AccountsRepository;
import com.devmountain.dogBlog.repositories.VaccinationsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationsServiceImpl implements VaccinationsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private VaccinationsRepository vaccinationsRepository;

    @Override
    @Transactional

    public void addVaccination(VaccinationsDTO vaccinationsDTO, Long vaccinationId){
        Optional<Accounts> accountsOptional = accountsRepository.findById(vaccinationId);
        Vaccinations vaccinations = new Vaccinations(vaccinationsDTO);
        accountsOptional.ifPresent(vaccinations::setAccounts);
        vaccinationsRepository.saveAndFlush(vaccinations);
    }

    @Override
    public void deleteVaccination(Long vaccinationId){
        Optional<Vaccinations> vaccinationsOptional = vaccinationsRepository.findById(vaccinationId);
        vaccinationsOptional.ifPresent(vaccinations -> vaccinationsRepository.delete(vaccinations));
    }

    @Override

    @Transactional
    public void editVaccination(VaccinationsDTO vaccinationsDTO){
        Optional<Vaccinations>  vaccinationsOptional = vaccinationsRepository.findById(vaccinationsDTO.getId());
            vaccinationsOptional.ifPresent(vaccinations ->{
            vaccinations.setVaccinationDate(vaccinationsDTO.getVaccinationDate());
            vaccinations.setVaccinationName(vaccinationsDTO.getVaccinationName());
            vaccinations.setVetName(vaccinationsDTO.getVetName());
            vaccinationsRepository.save(vaccinations);
        });
    }


    @Override
    public List<Vaccinations> getAllVaccinationsByUserId(Long userId) {
        Optional<Accounts> accountsOptional = accountsRepository.findById(userId);
        if (accountsOptional.isPresent()) {
            return vaccinationsRepository.findAllByAccountsEquals(accountsOptional.get());
        } else {
            return Collections.emptyList();
        }
    }
    @Override
    public Optional<VaccinationsDTO> getVaccinationById(Long vaccinationId){
        Optional<Vaccinations> vaccinationsOptional = vaccinationsRepository.findById(vaccinationId);
        if (vaccinationsOptional.isPresent()){
            return Optional.of(new VaccinationsDTO(vaccinationsOptional.get()));
        }
        return Optional.empty();
    }


}

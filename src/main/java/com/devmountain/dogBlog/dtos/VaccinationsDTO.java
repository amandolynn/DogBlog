package com.devmountain.dogBlog.dtos;


import com.devmountain.dogBlog.entities.Vaccinations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VaccinationsDTO implements Serializable {

    private Long id;

    private String vaccinationName;

    private Date vaccinationDate;

    private String vetName;

    private AccountsDTO accountsDTO;

    public VaccinationsDTO (Vaccinations vaccinations){
        if (vaccinations.getId() != null){
            this.id = vaccinations.getId();
        }
        if (vaccinations.getVaccinationName() != null) {
            this.vaccinationName = vaccinations.getVaccinationName();
        }
        this.vaccinationDate = vaccinations.getVaccinationDate();

        if(vaccinations.getVetName() != null){
            this.vetName = vaccinations.getVetName();
        }
    }

}

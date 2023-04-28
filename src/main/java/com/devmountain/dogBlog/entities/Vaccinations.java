package com.devmountain.dogBlog.entities;


import com.devmountain.dogBlog.dtos.VaccinationsDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Vaccinations")

public class Vaccinations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;

    @Column
    private String vaccinationName;

    @Column
    private Date vaccinationDate;

    @Column
    private String vetName;



    @ManyToOne
    @JsonManagedReference
    private Accounts accounts;

    public Vaccinations (VaccinationsDTO vaccinationsDTO){

        if (vaccinationsDTO.getVaccinationName() != null) {
            this.vaccinationName = vaccinationsDTO.getVaccinationName();
        }
        this.vaccinationDate = vaccinationsDTO.getVaccinationDate();

        this.vetName = vaccinationsDTO.getVetName();
        if(vaccinationsDTO.getVetName() != null){
            this.vetName = vaccinationsDTO.getVetName();
        }
    }

}


package com.devmountain.dogBlog.dtos;


import com.devmountain.dogBlog.entities.Accounts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountsDTO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String dogBreed;
    private int dogAge;
    private String dogGender;

    private Set<AppointmentsDTO> appointmentsDTOSet = new HashSet<>();
    private Set<StatusUpdateDTO> statusUpdateDTOSet = new HashSet<>();
    private Set<VaccinationsDTO> vaccinationsDTOSet = new HashSet<>();

    public AccountsDTO(Accounts accounts) {
        if (accounts.getId() != null) {
            this.id = accounts.getId();
        }
        if (accounts.getUsername() != null) {
            this.username = accounts.getUsername();
        }
        if (accounts.getPassword() != null) {
            this.password = accounts.getPassword();
        }

        if (accounts.getDogBreed() != null) {
            this.dogBreed = accounts.getDogBreed();
        }
        if (accounts.getDogGender() != null) {
            this.dogGender = accounts.getDogGender();
        }
        if (accounts.getDogAge() != 0) {
            this.dogAge = accounts.getDogAge();
        }

    }
}




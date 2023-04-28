package com.devmountain.dogBlog.entities;

import com.devmountain.dogBlog.dtos.AccountsDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String dogBreed;
    @Column
    private int dogAge;

    @Column(length = 1)
    private String dogGender;



    @OneToMany(mappedBy = "accounts", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<StatusUpdate> statusUpdatesSet = new HashSet<>();
    @OneToMany(mappedBy = "accounts", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Appointments> appointments = new HashSet<>();

    @OneToMany(mappedBy = "accounts", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Vaccinations> vaccinations = new HashSet<>();


    public Accounts(AccountsDTO accountsDTO){
        if (accountsDTO.getUsername() != null) {
            this.username = accountsDTO.getUsername();
        }
        if (accountsDTO.getPassword() != null){
          this.password = accountsDTO.getPassword();
      }
        if(accountsDTO.getDogBreed() != null){
            this.dogBreed = accountsDTO.getDogBreed();
        }
        this.dogGender = accountsDTO.getDogGender();

        this.dogAge = accountsDTO.getDogAge();


    }
}

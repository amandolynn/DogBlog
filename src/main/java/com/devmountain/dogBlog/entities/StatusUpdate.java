package com.devmountain.dogBlog.entities;


import com.devmountain.dogBlog.dtos.StatusUpdateDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "StatusUpdate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne
    @JsonBackReference
    private Accounts accounts;

    public StatusUpdate(StatusUpdateDTO statusUpdateDTO){
        if (statusUpdateDTO.getBody() != null){
            this.body = statusUpdateDTO.getBody();
        }
    }

}
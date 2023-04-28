package com.devmountain.dogBlog.entities;

import com.devmountain.dogBlog.dtos.AppointmentsDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Appointments")
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String typeOfAppointment;

    @Column(columnDefinition = "VARCHAR(255)")
    private String appointmentDate;

    @Column(columnDefinition = "VARCHAR(255)")
    private String appointmentTime;

    @ManyToOne
    @JsonManagedReference
    private Accounts accounts;

    public Appointments(AppointmentsDTO appointmentsDTO) {
        if(appointmentsDTO.getTypeOfAppointment() != null){
            this.typeOfAppointment = appointmentsDTO.getTypeOfAppointment();
        }
        if(appointmentsDTO.getAppointmentDate() != null){
            this.appointmentDate = appointmentsDTO.getAppointmentDate();
        }
        if(appointmentsDTO.getAppointmentTime() != null){
            this.appointmentTime = appointmentsDTO.getAppointmentTime();
        }
        if(this.typeOfAppointment == null){
            throw new IllegalArgumentException("Type of appointment cannot be null");
        }
        if(this.appointmentDate == null){
            throw new IllegalArgumentException("Appointment date cannot be null");
        }
        if(this.appointmentTime == null){
            throw new IllegalArgumentException("Appointment time cannot be null");
        }
    }
    public void setAppointmentTime(String appointmentTime) {
        if (appointmentTime == null || appointmentTime.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment time cannot be null or empty");
        }
        this.appointmentTime = appointmentTime;
    }

    }


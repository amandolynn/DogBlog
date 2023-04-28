package com.devmountain.dogBlog.dtos;

import com.devmountain.dogBlog.entities.Appointments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppointmentsDTO implements Serializable {
    private Long id;

    private String typeOfAppointment;

    private String appointmentDate;

    private String appointmentTime;

    private AccountsDTO accountsDTO;


    public AppointmentsDTO(Appointments appointments) {
        if (appointments.getId() != null) {
            this.id = appointments.getId();
        }
        if (appointments.getTypeOfAppointment() != null) {
            this.typeOfAppointment = appointments.getTypeOfAppointment();
        }
        if (appointments.getAppointmentDate() != null) {
            this.appointmentDate = appointments.getAppointmentDate();
        }
        if (appointments.getAppointmentTime() != null) {
            this.appointmentTime = appointments.getAppointmentTime();
        }

    }
}
package com.devmountain.dogBlog.services;

import com.devmountain.dogBlog.dtos.AppointmentsDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface AppointmentsService {
    @Transactional
    void createAppointment(AppointmentsDTO appointmentsDTO, Long userId);

    @Transactional
    void deleteAppointment(Long appointmentId);

    @Transactional
    void editAppointment(AppointmentsDTO appointmentsDTO);

    List<AppointmentsDTO> getAllAppointmentsByUserId(Long userId);

    Optional<AppointmentsDTO> getAppointmentById(Long appointmentId);
}

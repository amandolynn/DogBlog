package com.devmountain.dogBlog.controllers;


import com.devmountain.dogBlog.dtos.AppointmentsDTO;
import com.devmountain.dogBlog.services.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/appointments")

public class AppointmentsController {
    @Autowired
    private AppointmentsService appointmentsService;

    @PostMapping("/users/{userId}")
    public void createAppointment(@RequestBody AppointmentsDTO appointmentsDTO, @PathVariable Long userId){
        appointmentsService.createAppointment(appointmentsDTO, userId);
    }

    @DeleteMapping("/{appointmentId}")
    public void deleteAppointment(@PathVariable Long appointmentId){
        appointmentsService.deleteAppointment(appointmentId);
    }

    @PutMapping("/{appointmentId}")
    public void editAppointment(@RequestBody AppointmentsDTO appointmentsDTO, @PathVariable Long appointmentId) {
        appointmentsService.editAppointment(appointmentsDTO);
    }

    @GetMapping("/users/{userId}")
    public List<AppointmentsDTO> getAllAppointmentsByUserId(@PathVariable Long userId){
        return appointmentsService.getAllAppointmentsByUserId(userId);
    }

    @GetMapping("/{appointmentId}")
    public Optional<AppointmentsDTO> getAppointmentById(@PathVariable Long appointmentId){
        return appointmentsService.getAppointmentById(appointmentId);
    }


}

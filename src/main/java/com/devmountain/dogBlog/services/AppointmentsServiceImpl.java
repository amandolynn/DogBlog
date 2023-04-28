package com.devmountain.dogBlog.services;


import com.devmountain.dogBlog.dtos.AppointmentsDTO;

import com.devmountain.dogBlog.entities.Appointments;

import com.devmountain.dogBlog.entities.Accounts;
import com.devmountain.dogBlog.repositories.AppointmentsRepository;
import com.devmountain.dogBlog.repositories.AccountsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Override
    @Transactional

    public void createAppointment(AppointmentsDTO appointmentsDTO, Long userId){
        Optional<Accounts> usersOptional = accountsRepository.findById(userId);
        Appointments appointments = new Appointments(appointmentsDTO);
        usersOptional.ifPresent(appointments::setAccounts);
        appointmentsRepository.saveAndFlush(appointments);
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        Optional<Appointments> appointmentsOptional = appointmentsRepository.findById(appointmentId);
        appointmentsOptional.ifPresent(appointments -> appointmentsRepository.delete(appointments));
    }




    @Override
    @Transactional
    public void editAppointment(AppointmentsDTO appointmentsDTO){
        Optional<Appointments>  appointmentsOptional = appointmentsRepository.findById(appointmentsDTO.getId());
        appointmentsOptional.ifPresent(appointments ->{
            appointments.setAppointmentDate(appointmentsDTO.getAppointmentDate());
            appointments.setAppointmentTime(appointmentsDTO.getAppointmentTime());
            appointments.setTypeOfAppointment(appointmentsDTO.getTypeOfAppointment());
            appointmentsRepository.save(appointments);
        });
    }

    @Override
    public List<AppointmentsDTO> getAllAppointmentsByUserId(Long userId) {
        Optional<Accounts> userOptional = accountsRepository.findById(userId);
        if (userOptional.isPresent()) {
            List<Appointments> appointments = appointmentsRepository.findAllByAccountsEquals(userOptional.get());
            return appointments.stream().map(AppointmentsDTO::new).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<AppointmentsDTO> getAppointmentById(Long appointmentId){
        Optional<Appointments> appointmentsOptional = appointmentsRepository.findById(appointmentId);
        if (appointmentsOptional.isPresent()){
            return Optional.of(new AppointmentsDTO(appointmentsOptional.get()));
        }
        return Optional.empty();
    }


}

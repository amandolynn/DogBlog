package com.devmountain.dogBlog.repositories;

import com.devmountain.dogBlog.entities.Accounts;
import com.devmountain.dogBlog.entities.Appointments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {

    List<Appointments> findAllByAccountsEquals(Accounts accounts);
}


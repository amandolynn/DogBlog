package com.devmountain.dogBlog.repositories;

import com.devmountain.dogBlog.entities.Accounts;
import com.devmountain.dogBlog.entities.Vaccinations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationsRepository extends JpaRepository<Vaccinations, Long> {
    List<Vaccinations> findAllByAccountsEquals(Accounts accounts);
}

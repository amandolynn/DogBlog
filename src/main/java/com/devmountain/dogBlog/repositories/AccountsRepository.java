package com.devmountain.dogBlog.repositories;


import com.devmountain.dogBlog.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByUsername(String username);
}

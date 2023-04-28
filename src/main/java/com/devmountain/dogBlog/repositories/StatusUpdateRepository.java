package com.devmountain.dogBlog.repositories;

import com.devmountain.dogBlog.entities.Accounts;
import com.devmountain.dogBlog.entities.StatusUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StatusUpdateRepository extends JpaRepository<StatusUpdate, Long> {
    List<StatusUpdate> findAllByAccountsEquals(Accounts accounts);
}

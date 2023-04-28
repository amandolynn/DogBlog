package com.devmountain.dogBlog.services;

import com.devmountain.dogBlog.dtos.AccountsDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AccountsService {
    @Transactional
    List<String> signUpUser(AccountsDTO accountsDTO);

    @Transactional
    List<String> loginUser(AccountsDTO accountsDTO);
}

package com.devmountain.dogBlog.services;

import com.devmountain.dogBlog.dtos.AccountsDTO;
import com.devmountain.dogBlog.entities.Accounts;
import com.devmountain.dogBlog.repositories.AccountsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional

    public List<String> signUpUser(AccountsDTO accountsDTO){
        List<String> response = new ArrayList<>();
        Accounts accounts = new Accounts(accountsDTO);
        accountsRepository.saveAndFlush(accounts);
        response.add("http://localhost:8080/login.html");
        return response;
    }

    @Override
    @Transactional
    public List<String> loginUser(AccountsDTO accountsDTO){
        List<String> response = new ArrayList<>();
        Optional<Accounts> accountsOptional = accountsRepository.findByUsername(accountsDTO.getUsername());
        if (accountsOptional.isPresent()){
            if(passwordEncoder.matches(accountsDTO.getPassword(), accountsOptional.get().getPassword())){
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(accountsOptional.get().getId()));
            }else {
                response.add("Username or password is incorrect");
            }
        }else response.add("Username or password is incorrect");

        return response;
    }


}

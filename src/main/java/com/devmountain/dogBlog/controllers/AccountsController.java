package com.devmountain.dogBlog.controllers;

import com.devmountain.dogBlog.dtos.AccountsDTO;
import com.devmountain.dogBlog.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AccountsController {
    @Autowired
    private AccountsService accountsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> signUpUser (@RequestBody AccountsDTO accountsDTO){
        String passHash = passwordEncoder.encode(accountsDTO.getPassword());
        accountsDTO.setPassword(passHash);
        return  accountsService.signUpUser(accountsDTO);


    }

    @PostMapping("/login")
    public List<String> loginUser(@RequestBody AccountsDTO accountsDTO){

        return accountsService.loginUser(accountsDTO);
    }



}

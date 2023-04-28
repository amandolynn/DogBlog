package com.devmountain.dogBlog.services;

import com.devmountain.dogBlog.dtos.StatusUpdateDTO;
import com.devmountain.dogBlog.entities.StatusUpdate;
import com.devmountain.dogBlog.entities.Accounts;
import com.devmountain.dogBlog.repositories.StatusUpdateRepository;
import com.devmountain.dogBlog.repositories.AccountsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StatusUpdateServiceImpl implements StatusUpdateService {
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private StatusUpdateRepository statusUpdateRepository;

    @Override
    public List<StatusUpdateDTO> getAllStatusesByUserId(Long userId){
        Optional<Accounts> accountsOptional = accountsRepository.findById(userId);
        if (accountsOptional.isPresent()){
            List<StatusUpdate> statusUpdateList = statusUpdateRepository.findAllByAccountsEquals(accountsOptional.get());
            return statusUpdateList.stream().map(note -> new StatusUpdateDTO(note)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void updateStatus(StatusUpdateDTO statusUpdateDTO, Long userId) {
        Optional<Accounts> accountsOptional = accountsRepository.findById(userId);
        StatusUpdate statusUpdate = new StatusUpdate(statusUpdateDTO);
        accountsOptional.ifPresent(statusUpdate::setAccounts);
        statusUpdateRepository.saveAndFlush(statusUpdate);
    }

    @Override
    @Transactional
    public void deleteStatusById(Long statusId) {
        Optional<StatusUpdate> statusUpdateOptional = statusUpdateRepository.findById(statusId);
        statusUpdateOptional.ifPresent(statusUpdate -> statusUpdateRepository.delete(statusUpdate));
    }

    @Override
    @Transactional
    public void editExistingStatus(StatusUpdateDTO statusUpdateDTO) {
        Optional<StatusUpdate> statusUpdateOptional = statusUpdateRepository.findById(statusUpdateDTO.getId());
        statusUpdateOptional.ifPresent(statusUpdate -> {
            statusUpdate.setBody(statusUpdateDTO.getBody());
            statusUpdateRepository.saveAndFlush(statusUpdate);
        });
    }

    @Override
    public Optional<StatusUpdateDTO> getStatusById(Long statusId) {
        Optional<StatusUpdate> statusUpdateOptional = statusUpdateRepository.findById(statusId);
        if (statusUpdateOptional.isPresent()){
            return Optional.of(new StatusUpdateDTO(statusUpdateOptional.get()));
        }
        return Optional.empty();
    }
}

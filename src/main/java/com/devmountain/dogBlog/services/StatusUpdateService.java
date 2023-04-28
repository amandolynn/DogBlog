package com.devmountain.dogBlog.services;

import com.devmountain.dogBlog.dtos.StatusUpdateDTO;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


public interface StatusUpdateService {
    List<StatusUpdateDTO> getAllStatusesByUserId(Long userId);

    @Transactional
    void updateStatus(StatusUpdateDTO statusUpdateDTO, Long userId);

    @Transactional
    void deleteStatusById(Long statusId);

    @Transactional
    void editExistingStatus(StatusUpdateDTO statusUpdateDTO);

    Optional<StatusUpdateDTO> getStatusById(Long statusId);
}

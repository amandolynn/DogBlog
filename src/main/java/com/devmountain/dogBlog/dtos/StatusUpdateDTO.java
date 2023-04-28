package com.devmountain.dogBlog.dtos;

import com.devmountain.dogBlog.entities.StatusUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdateDTO implements Serializable {
    private Long id;
    private String body;
    private AccountsDTO accountsDTO;

    public StatusUpdateDTO(StatusUpdate statusUpdate){
        if (statusUpdate.getId() != null){
            this.id = statusUpdate.getId();
        }
        if (statusUpdate.getBody() != null){
            this.body = statusUpdate.getBody();
        }
    }
}

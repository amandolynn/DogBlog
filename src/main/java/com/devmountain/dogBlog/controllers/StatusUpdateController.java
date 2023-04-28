
package com.devmountain.dogBlog.controllers;

import com.devmountain.dogBlog.dtos.StatusUpdateDTO;
import com.devmountain.dogBlog.repositories.StatusUpdateRepository;
import com.devmountain.dogBlog.services.StatusUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;
        import java.util.Optional;

@RestController
@RequestMapping("api/v1/status-updates")
public class StatusUpdateController {
    @Autowired
    private StatusUpdateService statusUpdateService;
    @Autowired
    private StatusUpdateRepository statusUpdateRepository;

    @GetMapping("/user/{userId}")
    public List<StatusUpdateDTO> getAllStatusesByUserId(@PathVariable Long userId){
        return statusUpdateService.getAllStatusesByUserId(userId);
    }

    @GetMapping("/{statusId}")
    public Optional<StatusUpdateDTO> getStatusById(@PathVariable Long statusId){
        return statusUpdateService.getStatusById(statusId);
    }

    @PostMapping("/user/{userId}")
    public void updateStatus(@RequestBody StatusUpdateDTO statusUpdateDTO,@PathVariable Long userId){
        statusUpdateService.updateStatus(statusUpdateDTO, userId);
    }

    @DeleteMapping("/{statusId}")
    public void deleteStatusById(@PathVariable Long statusId){
        statusUpdateService.deleteStatusById(statusId);
    }


    @PutMapping("/{statusId}")
    public void editExistingStatus(@RequestBody StatusUpdateDTO statusUpdateDTO){
        statusUpdateService.editExistingStatus(statusUpdateDTO);
    }
}

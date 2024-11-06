package sds_java.mysport.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sds_java.mysport.entity.enums.Events;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.service.ActionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/action")
public class ActionController {
    private final ActionService actionService;

    @GetMapping("/getEvents")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<ApiResponse> getAllActions(
                    @RequestParam Events event
                    ){
        ApiResponse allByEvent = actionService.getAllByEvent(event);
        return new ResponseEntity<>(allByEvent, HttpStatus.OK);
    }
}

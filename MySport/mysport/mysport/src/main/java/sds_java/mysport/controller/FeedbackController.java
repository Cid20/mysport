package sds_java.mysport.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sds_java.mysport.entity.User;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.req.ReqFeedback;
import sds_java.mysport.security.CurrentUser;
import sds_java.mysport.service.FeedbackService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ApiResponse> saveFeedback(
            @CurrentUser User user,
            @RequestBody ReqFeedback reqFeedback
            ){
        ApiResponse apiResponse = feedbackService.saveFeedback(user, reqFeedback);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll/{categoryId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN','ROLE_MODERATOR')")
    public ResponseEntity<ApiResponse> getAll(
            @PathVariable Long categoryId,
            @RequestParam int size,
            @RequestParam int page
    ){
        ApiResponse all = feedbackService.getAll(categoryId, size, page);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}

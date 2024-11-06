package sds_java.mysport.controller;

import io.swagger.v3.core.model.ApiDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sds_java.mysport.entity.User;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.req.ReqView;
import sds_java.mysport.security.CurrentUser;
import sds_java.mysport.service.VdViewsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/views")
public class VdViewsController {
    private final VdViewsService viewsService;

    @PostMapping("/addView")
    public ResponseEntity<ApiResponse> add(@RequestBody ReqView reqView,
                                           @CurrentUser User user){
        ApiResponse apiResponse = viewsService.addView(user, reqView);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getView/{videoId}")
    public ResponseEntity<ApiResponse> getViews(@PathVariable Long videoId){
        ApiResponse views = viewsService.getViews(videoId);
        return new ResponseEntity<>(views,HttpStatus.OK);
    }
}

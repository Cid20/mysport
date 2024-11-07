package sds_java.mysport.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sds_java.mysport.entity.enums.UserRole;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.auth.AuthLogin;
import sds_java.mysport.payload.auth.AuthRegister;
import sds_java.mysport.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse> logIn(@Valid @RequestBody AuthLogin authLogin) {
        return ResponseEntity.ok(authService.login(authLogin));
    }

    @PostMapping("/register/user")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody AuthRegister authRegister
    ) {
        return ResponseEntity.ok(authService.register(authRegister, UserRole.ROLE_USER));
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @PostMapping("/admin/save-admin")
    public ResponseEntity<ApiResponse> adminSaveTeacher(@Valid @RequestBody AuthRegister auth
    ) {
        return ResponseEntity.ok(authService.adminSaveLibrarian(auth));
    }

}

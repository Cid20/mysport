package sds_java.mysport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sds_java.mysport.entity.User;
import sds_java.mysport.entity.enums.UserRole;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.ResponseError;
import sds_java.mysport.payload.auth.AuthLogin;
import sds_java.mysport.payload.auth.AuthRegister;
import sds_java.mysport.payload.auth.ResponseLogin;
import sds_java.mysport.repository.UserRepository;
import sds_java.mysport.security.JwtProvider;

import java.time.LocalDateTime;
import java.util.Random;

import static sds_java.mysport.entity.enums.UserRole.ADMIN;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse login(AuthLogin authLogin) {
        User user = userRepository.findByPhone(authLogin.getPhone()).orElse(null);
        if (user == null) {
            return new ApiResponse(ResponseError.NOTFOUND("User"));
        }

        String token = jwtProvider.generateToken(authLogin.getPhone());
        return new ApiResponse(new ResponseLogin(token, user.getUserRole().name(), user.getId()));
    }

    public ApiResponse register(AuthRegister auth, UserRole role) {
        if (userRepository.existsByPhone(auth.getPhone())) {
            return new ApiResponse(ResponseError.ALREADY_EXIST("Phone number"));
        }

        User user = saveUser(auth,role);
        userRepository.save(user); // save user
        return new ApiResponse("Success. Please activate your profile");

    }

    public ApiResponse adminSaveLibrarian(AuthRegister auth) {
        if (userRepository.existsByPhone(auth.getPhone())) {
            return new ApiResponse(ResponseError.ALREADY_EXIST("Phone number"));
        }

        saveUser(auth, ADMIN);
        return new ApiResponse("Success");
    }

    private User saveUser(AuthRegister auth, UserRole role) {
        User user = User.builder()
                .userRole(auth.getUserRole())
                .username(auth.getUsername())
                .phone(auth.getPhone())
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(auth.getPassword()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();

        return userRepository.save(user);
    }
}

package sds_java.mysport.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sds_java.mysport.entity.User;
import sds_java.mysport.entity.enums.UserRole;
import sds_java.mysport.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value(value = "${spring.jpa.hibernate.ddl-auto}")

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User user1 = User.builder()
                    .username("admin")
                    .phone("998901234567")
                    .password(passwordEncoder.encode("root123"))
                    .userRole(UserRole.SUPER_ADMIN)
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
            userRepository.save(user1);
        }
    }
}

package com.crm.config;

import com.crm.user.appuser.model.Role;
import com.crm.user.appuser.model.User;
import com.crm.user.appuser.repository.UserRepository;
import com.crm.user.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {

            String email = "example@niepodam.pl";
            if (userRepository.findByEmail(email).isEmpty()) {
                User admin = new User(
                        "Super admin",
                        "example@niepodam.pl",
                        passwordEncoder.bCryptPasswordEncoder().encode("root"),
                        Role.SUPER_ADMIN
                );
                userRepository.save(admin);
                System.out.println("✅ Super admin user added");
                System.out.println(admin.getAuthorities());
            }

        };
    }
}

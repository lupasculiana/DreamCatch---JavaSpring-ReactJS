package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoginConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository repository){
        return args -> {
            Login admin = new Login(
                    "lupasculiana",
                    "psswrd"
            );
            Login user1 = new Login(
                    "serbys",
                    "1234"
            );

            repository.saveAll(
                    List.of(admin,user1)
            );
        };
    }
}

package com.xyz.transporte.config;

import com.xyz.transporte.entity.Usuario;
import com.xyz.transporte.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner inicializarUsuarios(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (usuarioRepository.findByUsername("admin").isEmpty()) {

                Usuario admin = new Usuario();

                admin.setUsername("admin");
                admin.setPassword(
                        passwordEncoder.encode("1234")
                );
                admin.setRol("ADMIN");

                usuarioRepository.save(admin);
            }

            if (usuarioRepository.findByUsername("supervisor").isEmpty()) {

                Usuario supervisor = new Usuario();

                supervisor.setUsername("supervisor");
                supervisor.setPassword(
                        passwordEncoder.encode("1234")
                );
                supervisor.setRol("SUPERVISOR");

                usuarioRepository.save(supervisor);
            }
        };
    }
}
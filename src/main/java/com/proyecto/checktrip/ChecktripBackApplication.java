package com.proyecto.checktrip;

import com.proyecto.checktrip.config.RsaKeyProperties;
import com.proyecto.checktrip.entities.Role;
import com.proyecto.checktrip.repo.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ChecktripBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChecktripBackApplication.class, args);
    }
}

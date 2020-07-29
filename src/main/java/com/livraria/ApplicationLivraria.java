package com.livraria;

import com.github.javafaker.Faker;
import com.livraria.entities.Cliente;
import com.livraria.entities.Endereco;
import com.livraria.enums.SexoEnum;
import com.livraria.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Locale;

@SpringBootApplication
public class ApplicationLivraria {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT ", "DELETE");
//    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationLivraria.class, args);
    }

}

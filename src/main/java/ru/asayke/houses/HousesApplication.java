package ru.asayke.houses;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HousesApplication {
    public static void main(String[] args) {
        SpringApplication.run(HousesApplication.class, args);
    }

    //TODO move to @Configuration file
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
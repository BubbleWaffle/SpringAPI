package dev.bublwafl.springapi;

import dev.bublwafl.springapi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringApiApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner loadData(BookService bookService) {
        return args -> {

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringApiApplication.class, args);
    }
}
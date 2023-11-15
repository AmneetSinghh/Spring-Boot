package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {


    /*
     * CommandLineRunner :  executes code, when application context is loaded.

     * * StudentRepository : is injected through Spring IOC container,
     * because we defined as @Repository internaly they are @Component, every @component at runtime
     * while starting application, converted to beans and managed by IOC container.
     */
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
           Student amneet =  new Student(
                    1L,
                    "Amneet",
                    "harryamneet600@gmail.com",
                    LocalDate.of(2000, Month.JANUARY,5)
            );
            Student aditya =  new Student(
                    "aditya",
                    "aditya@gmail.com",
                    LocalDate.of(2005, Month.JANUARY,6)
            );
            Student geetika =  new Student(
                    "geetika",
                    "geetika@gmail.com",
                    LocalDate.of(2001, Month.JANUARY,8)
            );
            repository.saveAll(List.of(amneet,aditya,geetika));
        };
    }
}

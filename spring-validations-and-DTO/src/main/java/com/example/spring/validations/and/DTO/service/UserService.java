package com.example.spring.validations.and.DTO.service;

import com.example.spring.validations.and.DTO.model.Address;
import com.example.spring.validations.and.DTO.model.UserV1;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    /*
     * Builder pattern
     */
     public UserV1 getUserById(long id){
        return UserV1.builder()
                .id(UUID.randomUUID())
                .name("Harry")
                .address(new Address("India","Pune","Baner","1415660"))
                .phoneNumber("9914075977")
                .createdAt(System.currentTimeMillis())
                .build();
    }
}

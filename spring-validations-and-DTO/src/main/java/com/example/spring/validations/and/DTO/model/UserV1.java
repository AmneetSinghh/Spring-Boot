package com.example.spring.validations.and.DTO.model;

import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserV1 {
    private UUID id;
    private String name;
    private String phoneNumber;
    private int age;
    private String fatherName;
    private String motherName;
    private String dto;
    private Address address; /* nested Dto Json */
    private String dataBlob;
    private long createdAt;
    private long updatedAt;
}

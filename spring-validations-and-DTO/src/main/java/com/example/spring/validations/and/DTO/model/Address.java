package com.example.spring.validations.and.DTO.model;

import com.example.spring.validations.and.DTO.model.group.UserRegisterBasicInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    @NotBlank(message = "city not blank",groups = UserRegisterBasicInfo.class)
    private String city;
    @NotBlank(message = "pincode not blank",groups = UserRegisterBasicInfo.class)
    private String pincode;
    @NotBlank(message = "state not blank",groups = UserRegisterBasicInfo.class)
    private String state;
    private String country;
}

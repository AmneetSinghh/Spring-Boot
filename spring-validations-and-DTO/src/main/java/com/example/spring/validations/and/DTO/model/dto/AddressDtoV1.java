package com.example.spring.validations.and.DTO.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressDtoV1 {
    @JsonIgnore
    private String city;
    @JsonIgnore
    private String pincode;
    @JsonIgnore
    private String state;
    @JsonIgnore
    private String country;

    public String getAddress(){
        return String.join("_", country, state,city,pincode);
    }
}

package com.example.spring.validations.and.DTO.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDtoV1 {
    private UUID id;
    @JsonIgnore
    private String name;
    @JsonProperty("address")
    private AddressDtoV1 address;
    private String phoneNumber;



    public String getFormattedName(){
        return "Demo_"+ name;
    }
}

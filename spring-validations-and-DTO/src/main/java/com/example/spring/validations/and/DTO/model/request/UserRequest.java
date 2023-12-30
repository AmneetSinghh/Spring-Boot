package com.example.spring.validations.and.DTO.model.request;

import com.example.spring.validations.and.DTO.model.Address;
import com.example.spring.validations.and.DTO.model.group.UserRegisterAdvancedInfo;
import com.example.spring.validations.and.DTO.model.group.UserRegisterBasicInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    @NotBlank
    private String name;

    @NotBlank(message = "E-mail cannot be blank",groups = UserRegisterBasicInfo.class)
    @Size(min = 3, max = 20, message = "E-mail must be between 3 and 20 characters.",groups = UserRegisterBasicInfo.class)
    @Email(message = "Email is invalid",groups = UserRegisterBasicInfo.class)
    private String email;

    @NotBlank(message = "Password cannot be blank.",groups = UserRegisterBasicInfo.class)
    @Size(min = 8, message = "Password must be at least 8 characters.",groups = UserRegisterBasicInfo.class)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "Password must contain at least one lowercase letter, one uppercase letter, and one digit."
    ,groups = UserRegisterAdvancedInfo.class)
    private String pwd;

    @NotBlank(message = "age not blank")
    private String age;

//    private Address address
    @NotBlank(message = "mothername not blank",groups = UserRegisterAdvancedInfo.class)
    private String motherName;


    @NotBlank(groups = {UserRegisterBasicInfo.class, UserRegisterAdvancedInfo.class})
    private String captcha;

    @Valid()
    @NotNull(groups = UserRegisterBasicInfo.class)
    private Address address;

    /*
     * Provide valid annotation to nested groups
     */

}

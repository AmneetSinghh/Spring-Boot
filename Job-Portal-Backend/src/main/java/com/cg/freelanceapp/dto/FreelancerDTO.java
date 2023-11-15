package com.cg.freelanceapp.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FreelancerDTO {
	@NotEmpty(message = "userName cannot be empty")
	private String userName;
	@NotEmpty(message = "firstName cannot be empty")
	private String firstName;
	@NotEmpty(message = "lastName cannot be empty")
	private String lastName;
	@NotEmpty(message = "password cannot be empty")
	private String password;

}

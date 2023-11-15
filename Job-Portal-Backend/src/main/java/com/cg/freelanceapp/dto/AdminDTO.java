package com.cg.freelanceapp.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AdminDTO {

	@NotEmpty(message = "userName cant be empty")
	private String userName;
	@NotEmpty(message = "firstName cant be empty")
	private String firstName;
	@NotEmpty(message = "lastName cant be empty")
	private String lastName;
	@NotEmpty(message = "Password cant be empty")
	private String password;

}

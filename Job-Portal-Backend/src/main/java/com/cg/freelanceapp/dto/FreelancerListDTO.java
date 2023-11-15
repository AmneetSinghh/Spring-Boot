package com.cg.freelanceapp.dto;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class FreelancerListDTO {
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;

}

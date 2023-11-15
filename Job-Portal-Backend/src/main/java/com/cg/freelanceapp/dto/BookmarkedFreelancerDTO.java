package com.cg.freelanceapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookmarkedFreelancerDTO {
	
	@NotNull(message = "recruiterId cant be null")
	private Long recruiterId;
	@NotNull(message = "freelancerId cant be null")
	private Long freelancerId;

}

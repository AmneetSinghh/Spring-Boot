package com.cg.freelanceapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class JobDTO {
//	@NotNull(message = "freelancerid cant be null")
	private long freelancerid;
//	@NotNull(message = "skillId cant be null")
	private long skillId;
//	@NotNull(message = "recruiterid cant be null")
	private long recruiterId;

	private String jobTitle;
	private String jobDescription;

}
package com.cg.freelanceapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SkillExperienceDTO {
	@NotNull(message = "Experience cannot be empty")
	private Integer years;
	@NotNull(message = "Skill Id cannot be blank")
	private Long skillId;
	@NotNull(message = "Freelancer Id cannot be blank")
	private Long freelancerId;

}
package com.cg.freelanceapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SkillDTO {
	@NotEmpty(message = "Skill cannot be blank")
	private String name;
	@NotBlank(message = "Skill description cannot be empty")
	private String description;
}

package com.cg.freelanceapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SkillExperienceListDTO {
	private Long id;
	private Long skillId;
	private String skillName;
	private Integer experience;
	private Long freelancerId;
	private String freelancerName;
	private String freelancerUName;

}

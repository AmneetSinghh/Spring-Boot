package com.cg.freelanceapp.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class SkillExperience implements Serializable {

	@Id
	@Column(name = "skill_exp_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "skill_exp_seq")
	@SequenceGenerator(name = "skill_exp_seq", sequenceName = "skill_exp_seq", allocationSize = 1)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private Skill skill;

	private Integer years;

	@ManyToOne(targetEntity = Freelancer.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "freelancer_id")
	private Freelancer freelancer;

}

package com.cg.freelanceapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Skill implements Serializable {

	@Id
	@Column(name = "skill_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "skill_seq")
	@SequenceGenerator(name = "skill_seq", sequenceName = "skill_seq", allocationSize = 1)
	private Long id;
	@Column(unique = true, nullable = false)
	private String name;
	private String description;

}

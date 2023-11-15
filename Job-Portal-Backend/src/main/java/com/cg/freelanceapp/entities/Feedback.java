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
public class Feedback implements Serializable {

	@Id
	@Column(name = "feedback_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "feedback_seq")
	@SequenceGenerator(name = "feedback_seq", sequenceName = "feedback_seq", allocationSize = 1)
	Long id;
	Integer ranges;
	String comments;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Recruiter.class)
	@JoinColumn(name = "recruiter_id")
	Recruiter createdBy;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Freelancer.class)
	@JoinColumn(name = "freelancer_id")
	Freelancer createdFor;

}

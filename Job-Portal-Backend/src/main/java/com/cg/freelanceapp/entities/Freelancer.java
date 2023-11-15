package com.cg.freelanceapp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Freelancer implements Serializable {

	private static final long serialVersionUID = -8358203589467846311L;
	@Id
	@Column(name = "freelancer_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "freelancer_seq")
	@SequenceGenerator(name = "freelancer_seq", sequenceName = "freelancer_seq", allocationSize = 1)
	private Long id;

	@Column(unique = true, nullable = false)
	private String userName;

	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String password;

	@OneToMany(targetEntity = JobApplication.class)
	private List<JobApplication> appliedJobs;

	// mapped by means when skill_experience is added, it automaticaly added into this freelancer.
	@OneToMany(mappedBy = "createdFor", targetEntity = Feedback.class, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	private List<Feedback> feedbacks;

	@OneToMany(mappedBy = "freelancer", targetEntity = SkillExperience.class, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	private List<SkillExperience> skills;

	@OneToMany(mappedBy = "freelancer", targetEntity = BookmarkedJob.class, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	private List<BookmarkedJob> bookmarkedJobs;


}

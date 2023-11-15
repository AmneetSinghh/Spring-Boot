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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Recruiter implements Serializable {


	@Id
	@Column(name = "recruiter_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "recruiter_seq")
	@SequenceGenerator(name = "recruiter_seq", sequenceName = "recruiter_seq", allocationSize = 1)
	private Long id;

	@Column(unique = true, nullable = false)
	private String userName;
	@Column(nullable = false)
	private String firstName;
	private String lastName;
	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "postedBy", targetEntity = Job.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH, CascadeType.DETACH })
	private List<Job> postedJobs;

	@OneToMany(mappedBy = "createdBy", targetEntity = Feedback.class, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	private List<Feedback> feedbacks;

	@OneToMany(mappedBy = "bookmarkedBy", targetEntity = BookmarkedFreelancer.class, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	private List<BookmarkedFreelancer> freelancers;


}

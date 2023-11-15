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
import javax.validation.constraints.NotNull;

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
public class BookmarkedFreelancer implements Serializable {

	private static final long serialVersionUID = 9145091598260400574L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "bkd_fr_seq")
	@SequenceGenerator(name = "bkd_fr_seq", sequenceName = "bkd_fr_seq", allocationSize = 1)
	@Column(name = "bkd_fr_id", updatable = false)
	private Long id;

	@OneToOne(targetEntity = Freelancer.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "freelancer_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Freelancer freelancer;

	@ManyToOne(targetEntity = Recruiter.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "recruiter_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Recruiter bookmarkedBy;


	public BookmarkedFreelancer(@NotNull(message = "Freelancer id should not be empty.") Freelancer freelancer,
			@NotNull(message = "Recruiter id should not be empty.") Recruiter bookmarkedBy) {
		super();
		this.freelancer = freelancer;
		this.bookmarkedBy = bookmarkedBy;
	}

}

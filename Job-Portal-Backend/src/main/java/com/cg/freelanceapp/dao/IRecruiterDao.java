package com.cg.freelanceapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.freelanceapp.dto.RecruiterListDTO;
import com.cg.freelanceapp.entities.Recruiter;

/**************************************************************************************
 * @author Aditya 
 * Description: This is the DAO Interface for Recruiter module. 
 * Created Date: 19 April, 2021 
 * Version : v1.0.0
 *************************************************************************************/
@Repository
public interface IRecruiterDao extends JpaRepository<Recruiter, Long> {


	public Recruiter findByUserName(String userName);

	public boolean existsByUserName(String userName);
	
	@Query("select new com.cg.freelanceapp.dto.RecruiterListDTO(r.id, r.userName, r.firstName, r.lastName, r.password) from Recruiter r")
	public List<RecruiterListDTO> findAllRecruiters();
}

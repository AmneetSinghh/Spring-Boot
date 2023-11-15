package com.cg.freelanceapp.dao;

import java.util.List;

import com.cg.freelanceapp.dto.SkillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.freelanceapp.dto.FreelancerDTO;
import com.cg.freelanceapp.dto.FreelancerListDTO;
import com.cg.freelanceapp.entities.Freelancer;

/**************************************************************************************
 * @author Aditya 
 * Description: This is the DAO Interface for Freelancer module. 
 * Created Date: 19 April, 2021 
 * Version : v1.0.0
 *************************************************************************************/
@Repository
public interface IFreelancerDao extends JpaRepository<Freelancer, Long> {

	public Freelancer findByUserName(String userName);

	public boolean existsByUserName(String userName);
	
	@Query(value = "select new com.cg.freelanceapp.dto.FreelancerListDTO(f.id, f.userName, f.firstName, f.lastName, f.password) from Freelancer f where not f.firstName like 'dummy%' order by f.id")
	public List<FreelancerListDTO> findAllFreelancers();

	@Query("SELECT DISTINCT s.name FROM Freelancer f " +
			"JOIN f.skills se " +
			"JOIN se.skill s " +
			"WHERE f.id = :freelancerId")
	List<String> findAllSkillsByFreelancerId(@Param("freelancerId") Long freelancerId);
}

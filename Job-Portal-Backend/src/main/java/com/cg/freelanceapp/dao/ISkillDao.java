package com.cg.freelanceapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.freelanceapp.entities.Skill;

@Repository
public interface ISkillDao extends JpaRepository<Skill, Long> {

	/******************************************************************************
	 * Method      : existsByName
	 * @param        name
	 * @return       boolean
	 * Description : This method checks if a skill exists by the given unique name.
	 *****************************************************************************/
	boolean existsByName(String name);

	/******************************************************************************
	 * Method      : findByName
	 * @param        name
	 * @return       String
	 * Description : This method returns a skill by the given unique name.
	 *****************************************************************************/
	Skill findByName(String name);


}

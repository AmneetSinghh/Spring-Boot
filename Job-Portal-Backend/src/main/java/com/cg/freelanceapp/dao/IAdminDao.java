package com.cg.freelanceapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.freelanceapp.entities.Admin;

@Repository
public interface IAdminDao extends JpaRepository<Admin, Long> {

	public Admin findByUserName(String userName);

	public boolean existsByUserName(String userName);
}

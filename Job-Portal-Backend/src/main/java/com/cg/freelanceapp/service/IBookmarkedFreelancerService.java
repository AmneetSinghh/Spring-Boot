package com.cg.freelanceapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.freelanceapp.dto.BookmarkedFreelancerDTO;
import com.cg.freelanceapp.dto.BookmarkedFreelancerListDTO;
import com.cg.freelanceapp.entities.BookmarkedFreelancer;


@Service
public interface IBookmarkedFreelancerService {

	BookmarkedFreelancer bookmarkFreelancer(BookmarkedFreelancerDTO bookmarkedFreelancerDto);

	void deleteBookmarkedFreelancerById(Long id);

	BookmarkedFreelancer findById(Long id);
	
	List<BookmarkedFreelancerListDTO> getAll();

	BookmarkedFreelancer save(BookmarkedFreelancerDTO bookmarkedFreelancerDto);

}

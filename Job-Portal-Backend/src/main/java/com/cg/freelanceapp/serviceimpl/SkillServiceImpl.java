package com.cg.freelanceapp.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.freelanceapp.dao.ISkillDao;
import com.cg.freelanceapp.dto.SkillDTO;
import com.cg.freelanceapp.entities.Skill;
import com.cg.freelanceapp.exceptions.DuplicateSkillException;
import com.cg.freelanceapp.exceptions.InvalidSkillException;
import com.cg.freelanceapp.service.ISkillService;


@Service
@Transactional
public class SkillServiceImpl implements ISkillService {

	@Autowired
	ISkillDao skillDao;

	@Override
	public Skill findById(Long id) {
		return skillDao.findById(id).get();
	}

	@Override
	public List<Skill> getAllSkills() {
		return skillDao.findAll();
	}


	@Override
	public Skill getSkill(Long id) {
		return skillDao.getOne(id);
	}

	@Override
	public String remove(Long id) {
		if (skillDao.existsById(id)) {
			skillDao.deleteById(id);
			return "Deleted";
		} else {
			throw new InvalidSkillException();
		}
	}

	@Override
	public Skill save(Skill skill) {
		return skillDao.save(skill);
	}

	public Skill save(SkillDTO skillDto) {
		Skill skill = new Skill();
		if (skillDao.existsByName(skillDto.getName())) {
			throw new DuplicateSkillException();
		} else {
			skill.setName(skillDto.getName());
			skill.setDescription(skillDto.getDescription());
			return skillDao.save(skill);
		}
	}

	@Override
	public Skill update(Long id, Skill skill) {
		if (skillDao.existsById(id)) {
			return skillDao.save(skill);
		} else {
			throw new InvalidSkillException();
		}
	}

}

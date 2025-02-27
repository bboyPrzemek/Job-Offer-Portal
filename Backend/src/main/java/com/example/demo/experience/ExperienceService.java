package com.example.demo.experience;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {
	@Autowired
	private ExperienceRepository experienceRepository;
	
	public Set<Experience> searchExperinceWhereNameIn(List<String> experiences){
		return experienceRepository.findByNameIn(experiences);
	}
}

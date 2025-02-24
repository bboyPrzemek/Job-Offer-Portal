package com.example.demo.technology;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.worktype.Worktype;

@Service
public class TechnologyService {
	@Autowired
	private TechnologyRepository technologyRepository;
	
	
	public Set<Technology> searchTechnologyWhereNameIn(List<String> technologies){
		return technologyRepository.findByNameIn(technologies);
	}

}

package com.example.demo.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {
	@Autowired
	private PositionRepository positionRepository;
	
	public Position searchPositionByName(String name){
		return positionRepository.findByName(name);
	}
}

package com.example.demo.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	public Location findLocationByCity(String city) {
		return locationRepository.findByCity(city);
	}
}

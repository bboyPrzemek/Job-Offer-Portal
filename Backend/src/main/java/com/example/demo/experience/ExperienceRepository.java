package com.example.demo.experience;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long>{
	Set<Experience> findByNameIn(List<String> names);

}

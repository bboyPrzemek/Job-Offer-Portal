package com.example.demo.position;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.experience.Experience;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long>{
	Position findByName(String name);

}

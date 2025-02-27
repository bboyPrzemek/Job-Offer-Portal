package com.example.demo.worktype;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorktypeRepository extends JpaRepository<Worktype, Long> {
	Set<Worktype> findByNameIn(List<String> names);
}

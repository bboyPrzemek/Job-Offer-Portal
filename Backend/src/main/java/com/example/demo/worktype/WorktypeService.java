package com.example.demo.worktype;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorktypeService {
	@Autowired
	private WorktypeRepository worktypeRepository;
	
	public Set<Worktype> searchWorktypeWhereNameIn(List<String> worktypes){
		return worktypeRepository.findByNameIn(worktypes);
	}
}

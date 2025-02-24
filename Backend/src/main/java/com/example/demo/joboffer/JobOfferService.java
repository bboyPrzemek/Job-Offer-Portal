package com.example.demo.joboffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazebit.persistence.PagedList;
import com.example.demo.experience.Experience;
import com.example.demo.experience.ExperienceService;
import com.example.demo.location.Location;
import com.example.demo.location.LocationService;
import com.example.demo.position.Position;
import com.example.demo.position.PositionService;
import com.example.demo.technology.Technology;
import com.example.demo.technology.TechnologyService;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import com.example.demo.worktype.Worktype;
import com.example.demo.worktype.WorktypeService;

@Service
public class JobOfferService {
	
	@Autowired
	private JobOfferRepository jobOfferRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ExperienceService experienceService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private TechnologyService technologyService;
	@Autowired
	private WorktypeService worktypeService;
	@Autowired
	private LocationService locationService;
	
	
	public List<JobOffer> getJobOffers(){
		List<JobOffer> jobOffers = jobOfferRepository.findAll();
		return jobOffers;
	}
	
	public JobOfferDTO searchOffers(JobOfferSearchCriteria jobOfferSearchCriteria){
		PagedList<JobOffer> pagedJobOffers =  jobOfferRepository.findJobOffers(jobOfferSearchCriteria);
		
		
		Map<Long, JobOffer> jo = new LinkedHashMap<>();
		pagedJobOffers.forEach(p -> jo.put(p.getId(), p));
		List<JobOffer> pagedOffers = new ArrayList<>(jo.values());
		
		return new JobOfferDTO(
				pagedOffers,
				pagedJobOffers.getPage(), 
				pagedJobOffers.getTotalPages(),
				pagedJobOffers.getTotalSize());
		
	}
	
	public JobOffer findJobOfferById(Long Id){
		return jobOfferRepository.findJobOfferById(Id);
	}
	
	public List<JobOffer> getOffersByUserId(){
		Long userId = userService.getLoggedUserId();
		return jobOfferRepository.findJobOffersByUserId(userId);
	}
	
	public JobOffer saveJobOffer(NewJobOfferDto newJobOfferDto) {
		List<String> experiences = Arrays.stream(newJobOfferDto.getExperiences())
				.map(NewJobOfferDto.Experiences :: getValue)
				.collect(Collectors.toList());
		
		String position = newJobOfferDto.getPosition();
		List<String> technologies = Arrays.stream(newJobOfferDto.getTechnologies())
				.map(NewJobOfferDto.Technologies :: getValue)
				.collect(Collectors.toList());
		
		List<String> workTypes = Arrays.stream(newJobOfferDto.getWorktypes())
				.map(NewJobOfferDto.Worktypes :: getValue)
				.collect(Collectors.toList());
		
		String location = newJobOfferDto.getLocation();
		String title = newJobOfferDto.getTitle();
		String details = newJobOfferDto.getDetails();
		Double salaryMin = newJobOfferDto.getSalaryMin();
		Double salaryMax = newJobOfferDto.getSalaryMax();
		
		Set<Experience> exp = experienceService.searchExperinceWhereNameIn(experiences);
		Set<Technology> tech = technologyService.searchTechnologyWhereNameIn(technologies);
		Set<Worktype> wt = worktypeService.searchWorktypeWhereNameIn(workTypes);
		Position pos = positionService.searchPositionByName(position);
		Location loc = locationService.findLocationByCity(location);
		
		
		
		Long userId = userService.getLoggedUserId();
		User user = userService.findUserById(userId).get();
		
		JobOffer jobOffer = new JobOffer();
		jobOffer.setUser(user);
		jobOffer.setExperiences(exp);
		jobOffer.setTechnologies(tech);
		jobOffer.setWorktypes(wt);
		jobOffer.setPosition(pos);
		jobOffer.setLocation(loc);
		jobOffer.setSalaryMax(salaryMax);
		jobOffer.setSalaryMin(salaryMin);
	
		jobOffer.setTitle(title);
		jobOffer.setDetails(details);
		
		JobOffer j = jobOfferRepository.save(jobOffer);
		
		return j;
	}
	
	public void removeJobOffer(){
		
	}
}

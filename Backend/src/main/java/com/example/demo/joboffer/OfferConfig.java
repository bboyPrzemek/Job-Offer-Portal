package com.example.demo.joboffer;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.example.demo.experience.Experience;
import com.example.demo.experience.ExperienceRepository;
import com.example.demo.location.Location;
import com.example.demo.location.LocationRepository;
import com.example.demo.position.Position;
import com.example.demo.position.PositionRepository;
import com.example.demo.registration.RegisterUserDTO;
import com.example.demo.technology.Technology;
import com.example.demo.technology.TechnologyRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import com.example.demo.worktype.Worktype;
import com.example.demo.worktype.WorktypeRepository;
import jakarta.persistence.EntityManagerFactory;

@Configuration
public class OfferConfig {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public CriteriaBuilderFactory createCriteriaBuilderFactory() {
	    CriteriaBuilderConfiguration config = Criteria.getDefault();
	    return config.createCriteriaBuilderFactory(entityManagerFactory);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(
			JobOfferRepository jobOfferRepository, 
			UserService userService,
			LocationRepository locationRepository, 
			ExperienceRepository experienceRepository,
			TechnologyRepository technologyRepository,
			PositionRepository positionRepository,
			WorktypeRepository worktypeRepository) {
		return args->{
			
			Set<Worktype> worktypeSet = new HashSet<>();
			Worktype remote = new Worktype("Remote");
			worktypeRepository.save(remote);
			worktypeSet.add(remote);
			
			Set<Worktype> worktypeSet2 = new HashSet<>();
			Worktype office = new Worktype("Office");
			worktypeRepository.save(office);
			worktypeSet2.add(office);
			
			Worktype h = new Worktype("Hybrid");
			worktypeRepository.save(h);
			
			Position position = new Position("Backend");
			positionRepository.save(position);
			
			Position position2 = new Position("Frontend");
			positionRepository.save(position2);
			
			Position position3 = new Position("Fullstack");
			positionRepository.save(position3);
			
			Experience exp = new Experience("Mid");
			Set<Experience> eset = new HashSet<>();
			experienceRepository.save(exp);
			eset.add(exp);
			
			
			Experience exp2 = new Experience("Junior");
			Experience exp3 = new Experience("Senior");
			
			experienceRepository.save(exp2);
			experienceRepository.save(exp3);
			
			Technology t = new Technology("Salesforce");
			Technology t2 = new Technology("Java");
			Set<Technology> tset = new HashSet<>();
			technologyRepository.save(t);
			technologyRepository.save(t2);
			tset.add(t);
			
			
			
			RegisterUserDTO registerUserDTO = new RegisterUserDTO("a", "b", "TTMS Koszalin");
			
			Location l = new Location("Koszalin");
			
			locationRepository.save(l);
		
			User u = userService.saveUser(registerUserDTO);
			JobOffer j = 
					new JobOffer("Java Developer", "opis", Double.valueOf(7000), Double.valueOf(10000), u, l, tset,eset, position, worktypeSet);
			j.setCreatedDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
			
			jobOfferRepository.save(j);
			
			
			RegisterUserDTO registerUserDTO2 = new RegisterUserDTO("1", "2", "Accenture");
			
			User u2 = userService.saveUser(registerUserDTO2);
			Location l1 = new Location("Poznań");
			locationRepository.save(l1);
			
			
			for (int i = 0; i< 100; i++) {
				JobOffer j2 = new JobOffer("Salesforce Developer" + i, "opis2 + 1",Double.valueOf(5000+ 1), Double.valueOf(6000+ i),u2,l1,tset,eset, position2, worktypeSet2);
				Integer k = i;
				if (k == 30) {
					k = 0;
				}
				
				j2.setCreatedDate(new GregorianCalendar((2014+k), Calendar.MARCH, (11+k)).getTime());
				jobOfferRepository.save(j2);
			}
			
			Location l2 = new Location("Warszawa");
			Location l3 = new Location("Gdańsk");
			Location l4 = new Location("Kraków");
			Location l5= new Location("Lublin");
			
			locationRepository.save(l2);
			locationRepository.save(l3);
			locationRepository.save(l4);
			locationRepository.save(l5);
		};
	}
}

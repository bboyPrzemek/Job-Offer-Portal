package com.example.demo.joboffer;

import java.util.Date;
import java.util.Set;
import com.example.demo.experience.Experience;
import com.example.demo.location.Location;
import com.example.demo.position.Position;
import com.example.demo.technology.Technology;
import com.example.demo.user.User;
import com.example.demo.worktype.Worktype;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Entity
public class JobOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String title;
	private String details;
	private Double salaryMin;
	private Double salaryMax;
	private Date createdDate;
	
	@ManyToOne
	@JsonManagedReference
	private User user;
	
	@ManyToOne
	private Location location;
	
	@ManyToOne
	private Position position;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( 
				name = "offers_technologies", 
				joinColumns = @JoinColumn(name = "offer_id"),
				inverseJoinColumns = @JoinColumn(name = "technology_id"))
	private Set<Technology> technologies;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
				name = "offers_experiences", 
				joinColumns = @JoinColumn(name = "offer_id"),
				inverseJoinColumns = @JoinColumn(name = "experience_id"))
	private Set<Experience> experiences;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( 
				name = "offers_worktypes", 
				joinColumns = @JoinColumn(name = "offer_id"),
				inverseJoinColumns = @JoinColumn(name = "worktype_id"))
	private Set<Worktype> worktypes;
	
	public JobOffer(String title, String details, User user) {
		this.title = title;
		this.details = details;
		this.user = user;
	}
	
	public JobOffer(String title, String details,Double salaryMin, Double salaryMax, User user, Location location, Set<Technology> technologies, Set<Experience> experiences, Position position, Set<Worktype> worktypes) {
		this.title = title;
		this.details = details;
		this.salaryMin = salaryMin;
		this.salaryMax = salaryMax;
		this.user = user;
		this.location = location;
		this.technologies = technologies;
		this.experiences = experiences;
		this.position = position;
		this.worktypes = worktypes;
	}

	@Override
	public String toString() {
		return "JobOffer [Id=" + Id + ", title=" + title + ", details=" + details + ", salaryMin=" + salaryMin
				+ ", salaryMax=" + salaryMax + ", user=" + user + ", location=" + location + "]";
	}
}

package com.example.demo.user;

import java.util.List;
import com.example.demo.joboffer.JobOffer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(name="users")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(nullable = false, unique = true)
	@Setter
	@JsonIgnore
	private String email;
	
	@Setter
	@JsonIgnore
	private String password;
	private String displayName;
	
	@OneToMany(mappedBy="user")
	@JsonBackReference
	private List<JobOffer> jobOffers;
	
	public User() {}
	
	public User(String email) {
		this.email = email;
	}
	
	public User(String email, String password,  String displayName) {
		this.email = email;
		this.password = password;
		this.displayName = displayName;
	}
}

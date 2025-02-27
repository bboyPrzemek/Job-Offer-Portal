package com.example.demo.joboffer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class JobOfferController {
	
	@Autowired
	private JobOfferService jobOfferService;
	
	@CrossOrigin
	@GetMapping
	public JobOfferDTO searchData(JobOfferSearchCriteria jobOfferSearchCriteria){
		return jobOfferService.searchOffers(jobOfferSearchCriteria);
	}
	
	@CrossOrigin
	@GetMapping("/userOffers")
	public List<JobOffer> searchData(){
		return jobOfferService.getOffersByUserId();
	}
	
	@CrossOrigin
	@GetMapping("/offer/{Id}")
	public JobOffer findById(@PathVariable Long Id) {
		return jobOfferService.findJobOfferById(Id);
	}
	
	@CrossOrigin
	@PostMapping("/create")
	public ResponseEntity create(@Valid @RequestBody NewJobOfferDto newJobOfferDto){
		System.out.println(newJobOfferDto.toString());
           return new ResponseEntity<>(jobOfferService.saveJobOffer(newJobOfferDto), HttpStatus.CREATED); 
	}
}

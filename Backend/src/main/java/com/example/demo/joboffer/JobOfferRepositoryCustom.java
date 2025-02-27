package com.example.demo.joboffer;

import java.util.List;

import com.blazebit.persistence.PagedList;

public interface JobOfferRepositoryCustom {
	JobOfferDTO findJobOffers(JobOfferSearchCriteria jobOfferSearchCriteria);
	List<JobOffer> findJobOffersByUserId(Long Id);
	JobOffer findJobOfferById(Long Id);
}

package com.example.demo.joboffer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.PagedList;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

public class JobOfferRepositoryImpl implements JobOfferRepositoryCustom{
	
	private static final int LIMIT = 10;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CriteriaBuilderFactory cbf;

	@Override
	public PagedList<JobOffer> findJobOffers(JobOfferSearchCriteria jobOfferSearchCriteria) {
		
		int page = jobOfferSearchCriteria.getPage();
		
		if (page <= 0 ) {
			page = 1;
		}
		CriteriaBuilder<JobOffer> criteriaBuilder = cbf.create(entityManager, JobOffer.class).from(JobOffer.class, "j")
				.innerJoinFetch("worktypes","ow")
				.innerJoinFetch("experiences", "exp")
				.innerJoinFetch("location", "loc")
				.innerJoinFetch("technologies", "tech")
				.innerJoinFetch("position", "pos")
				.innerJoinFetch("user", "u");
	
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getTitle())) {
			criteriaBuilder  = criteriaBuilder.where("j.title").like(false).expression("'" + jobOfferSearchCriteria.getTitle() + "'").noEscape();
		}
		
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getCity())) {
			criteriaBuilder  = criteriaBuilder.where("loc.city").eq(jobOfferSearchCriteria.getCity());
		}
		
		if (jobOfferSearchCriteria.getSalaryMin() != null && jobOfferSearchCriteria.getSalaryMin() > 0) {
			criteriaBuilder  = criteriaBuilder.where("j.salaryMin").ge(jobOfferSearchCriteria.getSalaryMin());
		}
		
		if (jobOfferSearchCriteria.getSalaryMax() != null && jobOfferSearchCriteria.getSalaryMax() > 0) {
			criteriaBuilder  = criteriaBuilder.where("j.salaryMax").le(jobOfferSearchCriteria.getSalaryMax());
		}
		
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getExp())) {
			criteriaBuilder  = criteriaBuilder.where("exp.name").eq(jobOfferSearchCriteria.getExp());
		}
		
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getTech())) {
			criteriaBuilder  = criteriaBuilder.where("tech.name").eq(jobOfferSearchCriteria.getTech());
		}
		
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getPos())) {
			criteriaBuilder  = criteriaBuilder.where("pos.name").eq(jobOfferSearchCriteria.getPos());
		}
	
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getWorkType())) {
			criteriaBuilder  = criteriaBuilder.where("ow.name").eq(jobOfferSearchCriteria.getWorkType());
		} 
		
		String orderBy = jobOfferSearchCriteria.getOrderBy();
		orderBy = StringUtils.isBlank(orderBy) == true ? "salaryMin" : orderBy; 
		
		
		Boolean sortType = jobOfferSearchCriteria.getSort() == null || jobOfferSearchCriteria.getSort().equals("asc") ? true : false;
		
		return criteriaBuilder.orderBy(orderBy, sortType).orderByAsc("Id").page((page - 1) * LIMIT, LIMIT).getResultList();
	}

	@Override
	public List<JobOffer> findJobOffersByUserId(Long Id) {
		
		if (Id == null) {
			return new ArrayList<JobOffer>();
		}
	
		CriteriaBuilder<JobOffer> criteriaBuilder = cbf.create(entityManager, JobOffer.class).from(JobOffer.class, "j")
				.innerJoinFetch("worktypes","ow")
				.innerJoinFetch("experiences", "exp")
				.innerJoinFetch("location", "loc")
				.innerJoinFetch("technologies", "tech")
				.innerJoinFetch("position", "pos")
				.innerJoinFetch("user", "u");
		
		criteriaBuilder.where("u.Id").eq(Id);
		
		return criteriaBuilder.getResultList();
	}
	
	
	@Override
	public JobOffer findJobOfferById(Long Id) {
		if (Id == null) {
			return new JobOffer();
		}
	
		CriteriaBuilder<JobOffer> criteriaBuilder = cbf.create(entityManager, JobOffer.class).from(JobOffer.class, "j")
				.innerJoinFetch("worktypes","ow")
				.innerJoinFetch("experiences", "exp")
				.innerJoinFetch("location", "loc")
				.innerJoinFetch("technologies", "tech")
				.innerJoinFetch("position", "pos")
				.innerJoinFetch("user", "u");
		
		criteriaBuilder.where("j.Id").eq(Id);
		
		try {
			return criteriaBuilder.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	
}

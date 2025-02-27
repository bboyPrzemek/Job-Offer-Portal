package com.example.demo.joboffer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
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
	public JobOfferDTO findJobOffers(JobOfferSearchCriteria jobOfferSearchCriteria) {
		
		int page = jobOfferSearchCriteria.getPage();
		
		if (page <= 0 ) {
			page = 1;
		}
		
		long recordsCount = getRecordCount(jobOfferSearchCriteria);
		
		int totalPages = (int) (((recordsCount - 1) / LIMIT) + 1);
		
		List<Long> ids = queryRootID(jobOfferSearchCriteria);
		List<JobOffer> records = queryRecords(ids);
		
		return new JobOfferDTO(records, page, totalPages, recordsCount);
	}
	
	public List<JobOffer> queryRecords(List<Long> ids){
		CriteriaBuilder<JobOffer> criteriaBuilder = cbf.create(entityManager, JobOffer.class).from(JobOffer.class, "j")
				.innerJoinFetch("worktypes","ow")
				.innerJoinFetch("experiences", "exp")
				.innerJoinFetch("location", "loc")
				.innerJoinFetch("technologies", "tech")
				.innerJoinFetch("position", "pos")
				.innerJoinFetch("user", "u")
				.where("j.Id").in(ids);
		return criteriaBuilder.getResultList();
	}
	
	public List<Long> queryRootID(JobOfferSearchCriteria jobOfferSearchCriteria){
		CriteriaBuilder<JobOffer> criteriaBuilder = cbf.create(entityManager, JobOffer.class)
				.select("Id")
				.from(JobOffer.class, "j");
		int page = jobOfferSearchCriteria.getPage();
		
		if (page > 0 ) {
			page -= 1;
		}
		
		criteriaBuilder = prepareCriteria(jobOfferSearchCriteria, criteriaBuilder);
		
		String orderBy = jobOfferSearchCriteria.getOrderBy();
		orderBy = StringUtils.isBlank(orderBy) == true ? "salaryMin" : orderBy; 
		
		Boolean sortType = jobOfferSearchCriteria.getSort() == null || jobOfferSearchCriteria.getSort().equals("asc") ? true : false;
		
		criteriaBuilder.setFirstResult(page * 10);
		criteriaBuilder.setMaxResults(LIMIT);
		
		List<Long> jobOfferIds = (List<Long>)(List<?>)criteriaBuilder.orderBy(orderBy, sortType).orderByAsc("Id").getResultList();
		return jobOfferIds;
	}


	public Long getRecordCount(JobOfferSearchCriteria jobOfferSearchCriteria) {
		CriteriaBuilder<JobOffer> criteriaBuilder = cbf.create(entityManager, JobOffer.class)
				.select("count(Id)")
				.from(JobOffer.class, "j");
	
		criteriaBuilder = prepareCriteria(jobOfferSearchCriteria, criteriaBuilder);
		return(Long)(Object)criteriaBuilder.getSingleResult();
	}
	
	public CriteriaBuilder<JobOffer> prepareCriteria(JobOfferSearchCriteria jobOfferSearchCriteria, CriteriaBuilder<JobOffer> criteriaBuilder){
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getTitle())) {
			criteriaBuilder  = criteriaBuilder.where("j.title").like(false).expression("'" + jobOfferSearchCriteria.getTitle() + "'").noEscape();
		}
		
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getCity())) {
			criteriaBuilder  = criteriaBuilder.where("j.location.city").eq(jobOfferSearchCriteria.getCity());
		}
		
		if (jobOfferSearchCriteria.getSalaryMin() != null && jobOfferSearchCriteria.getSalaryMin() > 0) {
			criteriaBuilder  = criteriaBuilder.where("j.salaryMin").ge(jobOfferSearchCriteria.getSalaryMin());
		}
		
		if (jobOfferSearchCriteria.getSalaryMax() != null && jobOfferSearchCriteria.getSalaryMax() > 0) {
			criteriaBuilder  = criteriaBuilder.where("j.salaryMax").le(jobOfferSearchCriteria.getSalaryMax());
		}
		
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getExp())) {
			criteriaBuilder  = criteriaBuilder.where("j.experiences.name").eq(jobOfferSearchCriteria.getExp());
		}
		
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getTech())) {
			criteriaBuilder  = criteriaBuilder.where("j.technologies.name").eq(jobOfferSearchCriteria.getTech());
		}
		
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getPos())) {
			criteriaBuilder  = criteriaBuilder.where("j.position.name").eq(jobOfferSearchCriteria.getPos());
		}
	
		if (!StringUtils.isBlank(jobOfferSearchCriteria.getWorkType())) {
			criteriaBuilder  = criteriaBuilder.where("j.worktypes.name").eq(jobOfferSearchCriteria.getWorkType());
		} 
		return criteriaBuilder;
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

package com.example.demo.joboffer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long>, JobOfferRepositoryCustom, JpaSpecificationExecutor<JobOffer> {
	
	@Override
	@Query("SELECT j, loc, u, t, exp, pos, wt from JobOffer j "
			+ "join fetch j.location loc "
			+ "join fetch j.user u "
			+ "left join fetch j.technologies t "
			+ "left join fetch j.experiences exp "
			+ "left join fetch j.position pos "
			+ "left join fetch j.worktypes wt")
	List<JobOffer> findAll();
}

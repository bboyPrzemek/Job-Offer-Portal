package com.example.demo.joboffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
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
}

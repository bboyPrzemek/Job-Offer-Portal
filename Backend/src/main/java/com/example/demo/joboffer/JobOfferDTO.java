package com.example.demo.joboffer;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobOfferDTO {
		private List<JobOffer> jobOffers;
		private Integer currentPage;
		private Integer pages;
		private Long totalRecords;
}

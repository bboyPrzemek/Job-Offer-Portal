package com.example.demo.joboffer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobOfferSearchCriteria {
	private String title;
	private String city;
	private Double salaryMin;
	private Double salaryMax;
	private String exp;
	private String tech;
	private String pos;
	private String workType;
	private String sort;
	private String orderBy;
	private int page;
	
	@Override
	public String toString() {
		return "JobOfferSearchCriteria [title=" + title + ", city=" + city + ", salaryMin=" + salaryMin + ", salaryMax="
				+ salaryMax + ", exp=" + exp + ", tech=" + tech + ", pos=" + pos + ", workType=" + workType + ", sort="
				+ sort + ", orderBy=" + orderBy + ", page=" + page + "]";
	}
}

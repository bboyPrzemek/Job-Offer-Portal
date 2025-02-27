package com.example.demo.joboffer;

import org.hibernate.validator.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class NewJobOfferDto {
	
	@NotBlank(message = "Title cannot be null")
	private String title;
	@NotBlank(message = "Details cannot be null")
	private String details;
	@NotBlank(message = "Location cannot be null")
	private String location;
	@NotBlank(message = "Position cannot be null")
	private String position;
	@Min(1)
	@NotNull
	private Double salaryMin;
	@Min(1)
	@NotNull
	private Double salaryMax;
	@NotEmpty(message = "Experiences cannot be null")
	private Experiences[] experiences;
	@NotEmpty(message = "Technologies cannot be null")
	private Technologies[] technologies;
	@NotEmpty(message = "Worktypes cannot be null")
	private Worktypes[] worktypes;
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public static class Worktypes{
		private String id;
		private String value;
	}
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public static class Experiences{
		private String id;
		private String value;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public static class Technologies{
		private String id;
		private String value;
	}
	
	@Override
	public String toString() {
		return "NewJobOfferDto [title=" + title + ", details=" + details + ", location=" + location + ", experiences="
				+ experiences + ", position=" + position + ", technologies=" + technologies + ", worktypes="
				+ worktypes + "]";
	}
}

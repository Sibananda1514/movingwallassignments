package com.mindfire.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {

	private Integer id;
	private String title;
	private String responsible;
	private String description;
	private String severity;
	private boolean status;
}

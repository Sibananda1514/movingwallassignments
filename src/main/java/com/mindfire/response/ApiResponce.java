package com.mindfire.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponce<T> {

	private String message;
	private T result;
	private Long timeStamp=System.currentTimeMillis();
}

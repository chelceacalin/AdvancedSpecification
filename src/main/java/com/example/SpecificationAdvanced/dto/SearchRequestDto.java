package com.example.SpecificationAdvanced.dto;

import lombok.Data;

@Data
public class SearchRequestDto {

	String column;
	String value;
	Operation operation = Operation.EQUAL;

	public enum Operation {
		EQUAL, LIKE, IN
	}
}

package com.example.SpecificationAdvanced.dto;

import lombok.Data;

@Data
public class SearchRequestDto {

	String column;
	String value;
	Operation operation = Operation.EQUAL;
	String joinTable;

	public enum Operation {
		EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN,BETWEEN,JOIN
	}
}

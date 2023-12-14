package com.example.SpecificationAdvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

	private GlobalOperator globalOperator = GlobalOperator.AND;

	private List<SearchRequestDto> searchRequestDto;

	public enum GlobalOperator {
		AND, OR
	}
}

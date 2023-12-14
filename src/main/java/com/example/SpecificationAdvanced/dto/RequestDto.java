package com.example.SpecificationAdvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

	private GlobalOperator globalOperator = GlobalOperator.AND;

	private List<SearchRequestDto> searchRequestDto;

	private PageRequestDto pageRequestDto = new PageRequestDto(0, 5, Sort.Direction.ASC, "id");

	public enum GlobalOperator {
		AND, OR
	}
}

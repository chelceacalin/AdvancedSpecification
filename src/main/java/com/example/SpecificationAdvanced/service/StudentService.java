package com.example.SpecificationAdvanced.service;

import com.example.SpecificationAdvanced.dto.RequestDto;
import com.example.SpecificationAdvanced.dto.SearchRequestDto;
import com.example.SpecificationAdvanced.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {


	public Pair<List<SearchRequestDto>, RequestDto.GlobalOperator> getStudentSpecificationList(String encodedRequest) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<SearchRequestDto> searchRequestDtoList = new ArrayList<>();
		RequestDto.GlobalOperator globalOperator;
		try {
			Map<String, Object> map = objectMapper.readValue(JsonUtil.decodeBase64(encodedRequest), new TypeReference<>() {
			});

			globalOperator = map.containsKey("globalOperator") ? RequestDto.GlobalOperator.valueOf((String) map.get("globalOperator")) : RequestDto.GlobalOperator.OR;

			if (map.containsKey("searchRequestDto") && map.get("searchRequestDto") instanceof List<?> searchRequestDtoObjectList) {
				for (Object obj : searchRequestDtoObjectList) {
					if (obj instanceof Map<?, ?> searchRequestMap) {
						SearchRequestDto searchRequestDto = createSearchRequestDTO(searchRequestMap);
						searchRequestDtoList.add(searchRequestDto);
					}
				}
			} else {
				throw new RuntimeException("Invalid JSON Request");
			}

		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return Pair.of(searchRequestDtoList, globalOperator);
	}

	public SearchRequestDto createSearchRequestDTO(Map<?, ?> searchRequestMap) {
		SearchRequestDto searchRequestDto = new SearchRequestDto();
		searchRequestDto.setColumn(getStringValue(searchRequestMap, "column"));
		searchRequestDto.setValue(getStringValue(searchRequestMap, "value"));
		searchRequestDto.setOperation(getOperation(searchRequestMap));
		searchRequestDto.setJoinTable(getStringValue(searchRequestMap, "joinTable"));
		return searchRequestDto;
	}

	private SearchRequestDto.Operation getOperation(Map<?, ?> map) {
		return map.containsKey("operation") ? (SearchRequestDto.Operation) map.get("operation") : SearchRequestDto.Operation.EQUAL;
	}

	private String getStringValue(Map<?, ?> map, String key) {
		return map.containsKey(key) ? (String) map.get(key) : "";
	}
}

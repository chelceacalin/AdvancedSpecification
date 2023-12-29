package com.example.SpecificationAdvanced.util;

import com.example.SpecificationAdvanced.dto.RequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRequestConverter implements Converter<String, RequestDto> {

	@Override
	public RequestDto convert(@Nonnull String input) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			return objectMapper.readValue(input, RequestDto.class);
		} catch (Exception e) {
			throw new RuntimeException("Error trying to convert value!");
		}
	}
}
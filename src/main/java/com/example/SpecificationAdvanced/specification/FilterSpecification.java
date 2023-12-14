package com.example.SpecificationAdvanced.specification;

import com.example.SpecificationAdvanced.dto.RequestDto;
import com.example.SpecificationAdvanced.dto.SearchRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterSpecification<T> {

	public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtos, RequestDto.GlobalOperator globalOperator) {
		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();
			for (SearchRequestDto requestDto : searchRequestDtos) {
				predicates.add(switch (requestDto.getOperation()) {
					case LIKE ->
							criteriaBuilder.like(criteriaBuilder.lower(root.get(requestDto.getColumn())), "%" + requestDto.getValue().toLowerCase() + "%");
					case IN -> root.get(requestDto.getColumn()).in((Object) requestDto.getValue().split(","));
					case GREATER_THAN ->
							criteriaBuilder.greaterThan(root.get(requestDto.getColumn()), requestDto.getValue());
					case LESS_THAN -> criteriaBuilder.lessThan(root.get(requestDto.getColumn()), requestDto.getValue());
					case BETWEEN ->
							criteriaBuilder.between(root.get(requestDto.getColumn()), requestDto.getValue().split(",")[0], requestDto.getValue().split(",")[1]);
					case JOIN ->
							criteriaBuilder.equal(root.join(requestDto.getJoinTable()).get(requestDto.getColumn()), requestDto.getValue());
					default -> criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
				});

			}
			if (globalOperator.equals(RequestDto.GlobalOperator.AND)) {
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			} else {
				return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
			}
		};
	}
}

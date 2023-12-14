package com.example.SpecificationAdvanced.controller;

import com.example.SpecificationAdvanced.dto.PageRequestDto;
import com.example.SpecificationAdvanced.dto.RequestDto;
import com.example.SpecificationAdvanced.model.Student;
import com.example.SpecificationAdvanced.repository.StudentRepository;
import com.example.SpecificationAdvanced.specification.FilterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentRepository studentRepository;
	private final FilterSpecification<Student> filterSpecification;

	@GetMapping("/{name}")
	public Student getStudentByName(@PathVariable(name = "name", required = false) String name) {
		return studentRepository.findByName(name);
	}

	@PostMapping("/specification")
	public List<Student> getStudents(@RequestBody RequestDto requestDto) {
		Specification<Student> specification = filterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
		return studentRepository.findAll(specification);
	}

	@PostMapping("/specification/pagination")
	public Page<Student> getStudentsPaginated(@RequestBody RequestDto requestDto) {
		Specification<Student> specification = filterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
		return studentRepository.findAll(specification, new PageRequestDto().getPageable(requestDto.getPageRequestDto()));
	}
}

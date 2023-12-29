package com.example.SpecificationAdvanced.controller;

import com.example.SpecificationAdvanced.dto.PageRequestDto;
import com.example.SpecificationAdvanced.dto.RequestDto;
import com.example.SpecificationAdvanced.dto.SearchRequestDto;
import com.example.SpecificationAdvanced.model.Student;
import com.example.SpecificationAdvanced.repository.StudentRepository;
import com.example.SpecificationAdvanced.service.StudentService;
import com.example.SpecificationAdvanced.specification.FilterSpecification;
import com.example.SpecificationAdvanced.util.StringToRequestConverter;
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
	private final StudentService studentService;

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

	@GetMapping("/specification")
	public List<Student> getStudentSpecification(@RequestParam String encodedRequest) {
//		encodedRequest = JsonUtil.encodeBase64("""
//				{
//				    "globalOperator":"OR",
//				    "searchRequestDto":[
//				        {
//				            "column":"name",
//				            "value":"Calin",
//				            "joinTable":""
//				        }
//				    ]
//				}
//				""");
//

		List<SearchRequestDto> searchRequestDtoList = studentService.getStudentSpecificationList(encodedRequest).getFirst();
		RequestDto.GlobalOperator globalOperator = studentService.getStudentSpecificationList(encodedRequest).getSecond();
		return studentRepository.findAll(filterSpecification.getSearchSpecification(searchRequestDtoList, globalOperator));
	}


	@GetMapping("/specification/pagination")
	public List<Student> getStudentsWithGetRequest(
			@RequestParam(name = "input") String input) {
		StringToRequestConverter stringToRequestConverter = new StringToRequestConverter();
		RequestDto requestDto = stringToRequestConverter.convert(input);
		return studentRepository.findAll();
	}


}

package com.example.SpecificationAdvanced.repository;

import com.example.SpecificationAdvanced.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> , JpaSpecificationExecutor<Student> {

	Student findByName(String name);

	List<Student> findByAddressCity(String name);

}

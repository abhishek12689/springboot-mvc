package com.dwivedi.springbootmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwivedi.springbootmvc.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
}

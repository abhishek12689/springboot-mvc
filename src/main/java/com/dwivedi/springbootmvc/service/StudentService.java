package com.dwivedi.springbootmvc.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dwivedi.springbootmvc.model.Student;
import com.dwivedi.springbootmvc.repositories.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepo;
	
	@Transactional
	public String addStudent(Student student) {
		String errMsg  = null;
		studentRepo.save(student);
		return errMsg;
	}
	
	@Transactional(readOnly=true)
	public Student getStudent(Integer id) {
		Optional<Student> opt = studentRepo.findById(id);
		Student student = opt.isPresent() ? opt.get() : null;
		return student;
	}
	
	@Transactional(readOnly=true)
	public List<Student> getAllStudents() {
		List<Student> students = studentRepo.findAll();
		return students;
	}

	public void removeStudent(Integer id) {
		studentRepo.deleteById(id);
	}
}

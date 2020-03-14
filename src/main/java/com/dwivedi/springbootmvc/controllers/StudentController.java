package com.dwivedi.springbootmvc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dwivedi.springbootmvc.model.Student;
import com.dwivedi.springbootmvc.repositories.StudentRepository;
import com.dwivedi.springbootmvc.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService service;

	@RequestMapping(value = { "/", "/home" })
	public ModelAndView goToHome(@ModelAttribute Student newStudent) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("index");

		return mv;
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public ModelAndView addStudent(@ModelAttribute Student student) {
		ModelAndView mv = new ModelAndView();
		String res = service.addStudent(student);
		if (res != null) {
			mv.setViewName("error");
			mv.addObject("msg", res);
		} else {
			mv.setViewName("addSuccess");
			mv.addObject("student", student);
		}
		return mv;
	}

	@RequestMapping(value = "/removeStudent/{id}")
	public String removeStudent(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("info");
		service.removeStudent(id);
		List<Student> students = service.getAllStudents();
		mv.addObject("students", students);

		return "redirect:/getAllStudents";
	}

	@RequestMapping(value = "/getAllStudents")
	public ModelAndView getStudents() {
		ModelAndView mv = new ModelAndView("info");
		List<Student> students = service.getAllStudents();
		mv.addObject("students", students);

		return mv;
	}

	@RequestMapping(value = "/getStudent/{id}")
	public ModelAndView getStudent(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("info");
		Student student = service.getStudent(id);
		List<Student> students = new ArrayList<>();
		students.add(student);
		mv.addObject("student", student);

		return mv;
	}

	@RequestMapping(value = "/updateStudent/{id}")
	public ModelAndView getUpdateForm(@PathVariable int id) {
		Student newStudent = service.getStudent(id);
		ModelAndView mv = new ModelAndView("update");
		mv.addObject("newStudent", newStudent);

		return mv;
	}
	
	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	public ModelAndView updateStudent(@ModelAttribute Student newStudent) {
		service.addStudent(newStudent);
		ModelAndView mv = new ModelAndView("updateSuccess");
		mv.addObject("newStudent", newStudent);

		return mv;
	}
}

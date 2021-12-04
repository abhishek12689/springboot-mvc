package com.dwivedi.springbootmvc.controllers;

import com.dwivedi.springbootmvc.model.Person;
import com.dwivedi.springbootmvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

	@Autowired
	PersonService service;

	@RequestMapping(value = { "/", "/home" })
	public ModelAndView goToHome(@ModelAttribute Person newPerson) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("index");

		return mv;
	}

	@RequestMapping(value = "/addPerson", method = RequestMethod.POST)
	public ModelAndView addPerson(@ModelAttribute Person person) {
		ModelAndView mv = new ModelAndView();
		String res = service.addPerson(person);
		if (res != null) {
			mv.setViewName("error");
			mv.addObject("msg", res);
		} else {
			mv.setViewName("addSuccess");
			mv.addObject("person", person);
		}
		return mv;
	}

	@RequestMapping(value = "/removePerson/{id}")
	public String removePerson(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("info");
		service.removePerson(id);
		List<Person> people = service.getAllPersons();
		mv.addObject("people", people);

		return "redirect:/getAllPersons";
	}

	@RequestMapping(value = "/getAllPersons")
	public ModelAndView getPersons() {
		ModelAndView mv = new ModelAndView("info");
		List<Person> people = service.getAllPersons();
		mv.addObject("people", people);

		return mv;
	}

	@RequestMapping(value = "/getPerson/{id}")
	public ModelAndView getPerson(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("info");
		Person person = service.getPerson(id);
		List<Person> Persons = new ArrayList<>();
		Persons.add(person);
		mv.addObject("person", person);

		return mv;
	}

	@RequestMapping(value = "/updatePerson/{id}")
	public ModelAndView getUpdateForm(@PathVariable int id) {
		Person newPerson = service.getPerson(id);
		ModelAndView mv = new ModelAndView("update");
		mv.addObject("newPerson", newPerson);

		return mv;
	}
	
	@RequestMapping(value = "/updatePerson", method = RequestMethod.POST)
	public ModelAndView updatePerson(@ModelAttribute Person newPerson) {
		service.addPerson(newPerson);
		ModelAndView mv = new ModelAndView("updateSuccess");
		mv.addObject("newPerson", newPerson);

		return mv;
	}
}

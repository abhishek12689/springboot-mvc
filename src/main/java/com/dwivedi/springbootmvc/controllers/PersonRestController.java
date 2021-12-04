package com.dwivedi.springbootmvc.controllers;

import com.dwivedi.springbootmvc.model.Person;
import com.dwivedi.springbootmvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonRestController {

    @Autowired
    private PersonService service;

    @RequestMapping(method = RequestMethod.GET, value = "/persons")
    public List<Person> getAllPersons() {
        return service.getAllPersons();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/persons/name/length/{length}")
    public List<Person> getAllPersons(@PathVariable int length) {
        return service.getAllByFirstNameLength(length);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/persons-paged")
    public List<Person> getAllPersonsByPage(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                            @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
                                            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                                            @RequestParam(name = "salary") int salary) {
        return service.getAllPersonsByPageAndSorted(salary, pageNumber, pageSize, sortBy);
    }
}

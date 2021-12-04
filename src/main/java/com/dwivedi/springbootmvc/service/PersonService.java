package com.dwivedi.springbootmvc.service;

import com.dwivedi.springbootmvc.model.Person;
import com.dwivedi.springbootmvc.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepo;

    @Transactional
    public String addPerson(Person person) {
        String errMsg = null;
        personRepo.save(person);
        return errMsg;
    }

    @Transactional(readOnly = true)
    public Person getPerson(Integer id) {
        Optional<Person> opt = personRepo.findById(id);
        Person person = opt.isPresent() ? opt.get() : null;
        return person;
    }

    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        List<Person> people = personRepo.findAll();
        return people;
    }

    public void removePerson(Integer id) {
        personRepo.deleteById(id);
    }

    public List<Person> getAllPersonsByPageAndSorted(int salary, int pageNumber, int pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return personRepo.findAllBySalary(salary, pageRequest);
    }

    public List<Person> getAllByFirstNameLength(int length) {
        return personRepo.findAllByFirstNameLength(length);
    }
}

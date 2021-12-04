package com.dwivedi.springbootmvc.repositories;

import com.dwivedi.springbootmvc.model.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findAllBySalary(int salary, Pageable pageable);

    @Query("SELECT p from Person p WHERE LENGTH(p.firstName) = :length")
    List<Person> findAllByFirstNameLength(int length);
}

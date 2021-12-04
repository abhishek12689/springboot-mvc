package com.dwivedi.springbootmvc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person implements Comparable<Person> {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private int salary;

    public Person() {

    }

    public Person(int id, String firstName, String lastName, int salary) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Person person) {
        return this.getId() - person.getId();
    }
}

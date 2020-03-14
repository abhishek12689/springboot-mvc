package com.dwivedi.springbootmvc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student implements Comparable<Student> {
	@Id
	private int id;
	private String firstName;
	private String lastName;

	public Student() {

	}

	public Student(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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

	@Override
	public int compareTo(Student student) {
		if(this.getId() > student.getId()) {
			return 1;
		}
		else if(this.getId() < student.getId()) {
			return -1;
		}
		else {
			return 0;
		}
	}
}

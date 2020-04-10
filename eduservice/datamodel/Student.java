package com.csye6225.spring2020.eduservice.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Student")
public class Student {
	
	private String studentId;
	private String firstName;
	private String lastName;
	private String program;
	private List<String> courses = new ArrayList<>();
	
	
	public Student() {
		
	}
	
	public Student(String studentId, String firstName, 
			String lastName, String program, List<String> courses) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.program = program;
		this.courses = courses;
	}
	
	@DynamoDBHashKey(attributeName="studentId")
	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String profId) {
		this.studentId = profId;
	}
	
	@DynamoDBAttribute(attributeName="firstName")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@DynamoDBAttribute(attributeName="lastName")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@DynamoDBAttribute(attributeName="courses")
	public List<String> getCourses() {
		return courses;
	}
	public void setCourses(List<String> list) {
		this.courses.addAll(list);
	}
	
	@DynamoDBAttribute(attributeName="program")
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	
}

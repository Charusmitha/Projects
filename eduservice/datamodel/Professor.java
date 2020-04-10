package com.csye6225.spring2020.eduservice.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Professor")
public class Professor {
	
	private String professorId;
	private String firstName;
	private String lastName;
	private String department;
	private String joiningDate;
	
	public Professor() {
		
	}
	
	public Professor(String professorId, String firstName, 
			String lastName, String department, String joiningDate) {
		this.professorId = professorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.joiningDate = joiningDate;
	}
	
	@DynamoDBHashKey(attributeName="professorId")
	public String getProfessorId() {
		return professorId;
	}
	
	public void setProfessorId(String profId) {
		this.professorId = profId;
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
	
	@DynamoDBAttribute(attributeName="department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@DynamoDBAttribute(attributeName="joiningDate")
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	@DynamoDBIgnore
	@Override
	public String toString() { 
		return "ProfId=" + getProfessorId() + ", firstName=" + getFirstName()+ ", lastName=" + getLastName()
				+ ", department=" + getDepartment() + ", joiningDate=" + getJoiningDate();
	}
}

package com.csye6225.spring2020.eduservice.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Program")
public class Program {
	
	private String programId;
	private String programName;
	private String programDesc;
	private List<String> courses = new ArrayList<>();
	
	public Program() {
		
	}
	
	public Program(String programId, String programName, 
			String programDesc, List<String> courses) {
		this.programId = programId;
		this.programName = programName;
		this.programDesc = programDesc;
		this.courses = courses;
	}
	
	@DynamoDBHashKey(attributeName="programId")
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	@DynamoDBAttribute(attributeName="programName")
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	@DynamoDBAttribute(attributeName="programDesc")
	public String getProgramDesc() {
		return programDesc;
	}
	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}
	@DynamoDBAttribute(attributeName="courses")
	public List<String> getCourses() {
		return courses;
	}
	public void setCourses(List<String> courses) {
		this.courses.addAll(courses);
	}
	

}

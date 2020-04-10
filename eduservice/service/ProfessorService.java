package com.csye6225.spring2020.eduservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.spring2020.eduservice.datamodel.Department;
import com.csye6225.spring2020.eduservice.datamodel.DynamoDBConnector;
import com.csye6225.spring2020.eduservice.datamodel.Professor;

public class ProfessorService {
	
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 
	DepartmentService departmentService = new DepartmentService();
	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	
	public ProfessorService() {
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	// Getting a list of all professor 
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : mapper.scan(Professor.class, scanExpression)) {
			list.add(prof);
		}
		return list ;
	}

	// Adding a professor
	public Professor addProfessor(Professor professor) {
		
		String dept = professor.getDepartment();
		
		List<Department> list = departmentService.getAllDepartments();
		boolean isDeptCreated = false;
		
		for(Department department: list)
		{
			String temp = department.getDeptId();
			if(temp.equals(dept))
			{
				isDeptCreated = true;
				break;
			}
		}
		
		if (isDeptCreated == false) 
		{
			System.out.println("Department " + dept + " is not created");
	        throw new NotFoundException();
		}
		
		mapper.save(professor);
		return professor;		
	}
	
	
	// Getting One Professor
	public Professor getProfessor(String profId) {
		
		 //Simple HashKey Load
		 Professor prof2 = mapper.load(Professor.class, profId);
	     if(prof2 == null)
	     {
	    	 throw new NullPointerException();
	     }
		
		return prof2;
	}
	
	// Deleting a professor
	public Professor deleteProfessor(String profId) {
		Professor deletedProfDetails = mapper.load(Professor.class, profId);
		mapper.delete(deletedProfDetails);
		return deletedProfDetails;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(String profId, Professor prof) {	
		Professor profObject = mapper.load(Professor.class, profId);
		profObject = prof;
		mapper.save(prof);
		return profObject;
	}
	
	// Get professors in a department 
	public List<Professor> getProfessorsByDepartment(String department) {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : mapper.scan(Professor.class, scanExpression)) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list ;
	}
	
	// Get professors for a year with a size limit

}

package com.csye6225.spring2020.eduservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.spring2020.eduservice.datamodel.DynamoDBConnector;
import com.csye6225.spring2020.eduservice.datamodel.Program;
import com.csye6225.spring2020.eduservice.datamodel.Student;

public class StudentService {
	
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 
	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	ProgramService programService = new ProgramService();
	
	public StudentService() {
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	// Getting a list of all Students 
	// GET "..webapi/Students"
	public List<Student> getAllStudents() {	
		//Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for (Student student : mapper.scan(Student.class, scanExpression)) {
			list.add(student);
		}
		return list ;
	}

	// Adding a Student
	public Student addStudent(Student student) {
		
		String program = student.getProgram();
		
		List<Program> list = programService.getAllPrograms();
		boolean isProgramCreated = false;
		
		for(Program pgm: list)
		{
			String temp = pgm.getProgramId();
			if(temp.equals(program))
			{
				isProgramCreated = true;
				break;
			}
		}
		
		if (isProgramCreated == false) 
		{
			System.out.println("Program " + program + " is not created");
	        throw new NotFoundException();
		}
		
		
		mapper.save(student);
		return student;
	}
	
	
	// Getting One Student
	public Student getStudent(String studentId) {
		
		 Student student = mapper.load(Student.class, studentId);
		 if(student == null)
	     {
	    	 throw new NullPointerException();
	     }
		
		return student;
	}
	
	// Getting Courses of a Student
		public List<String> getCourses(String studentId) {
			
			 Student student = mapper.load(Student.class, studentId);
			 if(student == null)
		     {
		    	 throw new NullPointerException();
		     }
		     List<String> courses = student.getCourses();
			
			return courses;
		}
	
	// Deleting a Student
	public Student deleteStudent(String studentId) {
		Student deletedStudentDetails = mapper.load(Student.class, studentId);
		mapper.delete(deletedStudentDetails);
		return deletedStudentDetails;
	}
	
	// Updating Student Info
	public Student updateStudentInformation(String studentId, Student student) {	
		Student studentObject = mapper.load(Student.class, studentId);
		studentObject = student;
		mapper.save(student);
		return studentObject;
	}
	
	// Get Students in a program 
	public List<Student> getStudentsByProgram(String program) {	
		//Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for (Student student : mapper.scan(Student.class, scanExpression)) {
			if (student.getProgram().equals(program)) {
				list.add(student);
			}
		}
		return list ;
	}

}

package com.csye6225.spring2020.eduservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.spring2020.eduservice.datamodel.Assignment;
import com.csye6225.spring2020.eduservice.datamodel.Course;
import com.csye6225.spring2020.eduservice.datamodel.DynamoDBConnector;
import com.csye6225.spring2020.eduservice.datamodel.Student;

public class CourseService {
	
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 
	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	StudentService studentService = new StudentService();

	public CourseService() {
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	public List<Course> getAllCourses() {
		ArrayList<Course> list = new ArrayList<>();
		for (Course course : mapper.scan(Course.class, scanExpression)) {
			list.add(course);
		}
		return list;
	}

	public Course getCourse(String courseId) {
		Course course = mapper.load(Course.class, courseId);
		if(course == null)
	    {
	    	throw new NullPointerException();
	    }
		return course;
	}

	public List<String> getCourseStudents(String courseId) {
		Course course = mapper.load(Course.class, courseId);
		if(course == null)
	    {
	    	throw new NullPointerException();
	    }
		return course.getStudents();
	}

	public Course deleteCourse(String courseId) {
		Course deletedCourseDetails = mapper.load(Course.class, courseId);
		mapper.delete(deletedCourseDetails);
		return deletedCourseDetails;
	}

	public Course addCourse(Course course) {
		
		String studentTA = course.getStudentTA();
		boolean isStudentTACreated = false;
		
		List<String> studentIds = course.getStudents();
		List<Student> list = studentService.getAllStudents();
		int count = 0;
		
		for(String studentId: studentIds)
		{
			for(Student std: list)
			{
				String temp = std.getStudentId();
				if(temp.equals(studentId))
				{
					count++;
				}
			}
		}
		
		for(Student std: list)
		{
			String temp = std.getStudentId();
			if(temp.equals(studentTA))
			{
				isStudentTACreated = true;
				break;
			}
		}
		
		if (count != studentIds.size() || isStudentTACreated == false) 
		{
			System.out.println("Student is not created");
	        throw new NotFoundException();
		}
		
		mapper.save(course);
		return course;
	}

	public Course updateCourseInformation(String courseId, Course course) {
		Course courseObject = mapper.load(Course.class, courseId);
		courseObject = course;
		mapper.save(course);
		return courseObject;
	}

	public List<String> getCourseAnnouncements(String courseId) {
		Course courseObject = mapper.load(Course.class, courseId);
		if(courseObject == null)
	     {
	    	 throw new NullPointerException();
	     }
		return courseObject.getBoard().getAnnouncements();
	}

	public List<Assignment> getCourseAssignments(String courseId) {
		Course courseObject = mapper.load(Course.class, courseId);
		if(courseObject == null)
	    {
	    	throw new NullPointerException();
	    }
		return courseObject.getBoard().getAssignments();
	}

}

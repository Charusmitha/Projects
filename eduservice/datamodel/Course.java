package com.csye6225.spring2020.eduservice.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

@DynamoDBTable(tableName="Course")
public class Course {
	
	private String courseId;
	private String courseName;
	private String professor;
	private String studentTA;
	private List<String> students = new ArrayList<>();
	private Board board;
	
	public Course() {
		
	}
	
	public Course(String courseId, String courseName, 
			String professor, String studentTA, List<String> students, Board board) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.professor = professor;
		this.studentTA = studentTA;
		this.students = students;
		this.board = board;
	}

	@DynamoDBHashKey(attributeName="courseId")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@DynamoDBAttribute(attributeName="courseName")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@DynamoDBAttribute(attributeName="professor")
	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	@DynamoDBAttribute(attributeName="studentTA")
	public String getStudentTA() {
		return studentTA;
	}

	public void setStudentTA(String studentTA) {
		this.studentTA = studentTA;
	}
	
	public List<String> getStudents() {
		return students;
	}

	public void setStudents(List<String> students) {
		this.students.addAll(students);
	}
	
	@DynamoDBTypeConverted(converter = BoardTypeConverter.class)
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}

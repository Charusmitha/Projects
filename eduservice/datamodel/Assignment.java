package com.csye6225.spring2020.eduservice.datamodel;

public class Assignment {
	
	private String assignmentName;
	private String assignmentDesc;
	private String grade;
	
	public Assignment() {
		
	}
	
	public Assignment(String assignmentName, String assignmentDesc, 
			String grade) {
		this.assignmentName = assignmentName;
		this.assignmentDesc = assignmentDesc;
		this.grade = grade;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public String getAssignmentDesc() {
		return assignmentDesc;
	}

	public void setAssignmentDesc(String assignmentDesc) {
		this.assignmentDesc = assignmentDesc;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}

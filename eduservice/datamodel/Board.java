package com.csye6225.spring2020.eduservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private List<String> announcements = new ArrayList<>();
	private List<Assignment> assignments = new ArrayList<>();
	
	public Board() {
		
	}
	
	public Board(List<String> announcements, List<Assignment> assignments, 
			String finalGrade) {
		this.announcements = announcements;
		this.assignments = assignments;
	}

	public List<String> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<String> announcements) {
		this.announcements.addAll(announcements);
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments.addAll(assignments);
	}

}

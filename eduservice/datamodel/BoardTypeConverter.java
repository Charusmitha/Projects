package com.csye6225.spring2020.eduservice.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class BoardTypeConverter implements DynamoDBTypeConverter<String, Board>{

	@Override
	public String convert(Board object) {
		Board itemBoard = (Board) object;
		List<String> announcements = itemBoard.getAnnouncements();
		List<Assignment> assignments = itemBoard.getAssignments();
        String boardItems = "";
        try {
            if (itemBoard != null) {
            	for(String s: announcements) {
            		boardItems += s + "\t";
            	}
            	boardItems += "\n";
            	for(Assignment assignment: assignments)
            	{
            		String name = assignment.getAssignmentName();
            		String desc = assignment.getAssignmentDesc();
            		String grade = assignment.getGrade();
            		boardItems += name + "  " + desc + "  " + grade + "\t";
            	}
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return boardItems;
	}

	@Override
	public Board unconvert(String s) {
		Board itemBoard = new Board();
        try {
            if (s != null && s.length() != 0) {
                String[] all = s.split("\n");
                String[] announcements = all[0].split("\t");
                String[] assignments = all[1].split("\t");
                List<String> announcementList = new ArrayList<>();
                List<Assignment> assignmentList = new ArrayList<>();
                for(String st: announcements) {
                	announcementList.add(st);
                }
                itemBoard.setAnnouncements(announcementList);
                for(String st: assignments) {
                	String[] str = st.split("  ");
                	Assignment asmnt = new Assignment();
                	asmnt.setAssignmentName(str[0]);
                	asmnt.setAssignmentDesc(str[1]);
                	asmnt.setGrade(str[2]);
                	assignmentList.add(asmnt);
                }
                itemBoard.setAssignments(assignmentList);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return itemBoard;
	}
	
}

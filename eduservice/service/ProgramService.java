package com.csye6225.spring2020.eduservice.service;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.spring2020.eduservice.datamodel.DynamoDBConnector;
import com.csye6225.spring2020.eduservice.datamodel.Program;

public class ProgramService {
	
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 
	DepartmentService departmentService = new DepartmentService();
	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

	public ProgramService() {
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	public List<Program> getAllPrograms() {
		ArrayList<Program> list = new ArrayList<>();
		for (Program program : mapper.scan(Program.class, scanExpression)) {
			list.add(program);
		}
		return list;
	}

	public Program getProgram(String programId) {
		Program program = mapper.load(Program.class, programId);
		if(program == null)
	     {
	    	 throw new NullPointerException();
	     }
		return program;
	}

	public Program deleteProgram(String programId) {
		Program deletedProgramDetails = mapper.load(Program.class, programId);
		mapper.delete(deletedProgramDetails);
		return deletedProgramDetails;
	}

	public Program addProgram(Program program) {
		mapper.save(program);
		return program;
	}

	public Program updateProgramInformation(String programId, Program program) {
		Program programObject = mapper.load(Program.class, programId);
		programObject = program;
		mapper.save(program);
		return programObject;
	}

}

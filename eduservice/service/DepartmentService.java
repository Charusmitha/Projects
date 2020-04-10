package com.csye6225.spring2020.eduservice.service;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.spring2020.eduservice.datamodel.Department;
import com.csye6225.spring2020.eduservice.datamodel.DynamoDBConnector;

public class DepartmentService {

	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 
	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	
	public DepartmentService() {
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	public List<Department> getAllDepartments() {
		ArrayList<Department> list = new ArrayList<>();
		for (Department dept : mapper.scan(Department.class, scanExpression)) {
			list.add(dept);
		}
		return list;
	}

	public Department getDepartment(String deptId) {
		Department dept = mapper.load(Department.class, deptId);
		if(dept == null)
	    {
	    	throw new NullPointerException();
	    }
		return dept;
	}

	public Department deleteDepartment(String deptId) {
		Department deletedDepartmentDetails = mapper.load(Department.class, deptId);
		mapper.delete(deletedDepartmentDetails);
		return deletedDepartmentDetails;
	}

	public Department addDepartment(Department department) {
		mapper.save(department);
		return department;
	}

	public Department updateDepartmentInformation(String deptId, Department department) {
		Department departmentObject = mapper.load(Department.class, deptId);
		departmentObject = department;
		mapper.save(department);
		return departmentObject;
	}
	


}

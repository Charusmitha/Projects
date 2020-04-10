package com.csye6225.spring2020.eduservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.spring2020.eduservice.datamodel.Department;
import com.csye6225.spring2020.eduservice.service.DepartmentService;

//.. /webapi/myresource
@Path("/departments")
public class DepartmentResource {
	
DepartmentService departmentService = new DepartmentService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getDepartments() {
		return departmentService.getAllDepartments();
	}	
	
	@GET
	@Path("/{DepartmentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Department getDepartment(@PathParam("DepartmentId") String deptId) {
		return departmentService.getDepartment(deptId);
	}
	
	@DELETE
	@Path("/{DepartmentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Department deleteProram(@PathParam("DepartmentId") String deptId) {
		return departmentService.deleteDepartment(deptId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Department addDepartment(Department Department) {
			return departmentService.addDepartment(Department);
	}
	
	@PUT
	@Path("/{DepartmentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Department updateDepartment(@PathParam("DepartmentId") String deptId, 
			Department department) {
		return departmentService.updateDepartmentInformation(deptId, department);
	}

}

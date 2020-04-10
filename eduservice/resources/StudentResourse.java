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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.spring2020.eduservice.datamodel.Student;
import com.csye6225.spring2020.eduservice.service.StudentService;

//.. /webapi/myresource
@Path("/students")

public class StudentResourse {
	
StudentService studentService = new StudentService();
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudents() {
		return studentService.getAllStudents();
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsByProgram(
			@QueryParam("program") String program) {
		
		if (program == null) {
			return studentService.getAllStudents();
		}
		return studentService.getStudentsByProgram(program);
		
	}
	
	// ... webapi/Students/1 
	@GET
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("StudentId") String studentId) {
		System.out.println("Student Resource: Looking for: " + studentId);
		return studentService.getStudent(studentId);
	}
	
	@GET
	@Path("/{StudentId}/courses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getCourses(@PathParam("StudentId") String studentId) {
		return studentService.getCourses(studentId);
	}
	
	@DELETE
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("StudentId") String studentId) {
		return studentService.deleteStudent(studentId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addProfessor(Student student) {
			return studentService.addStudent(student);
	}
	
	@PUT
	@Path("/{StudentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("StudentId") String studentId, 
			Student student) {
		return studentService.updateStudentInformation(studentId, student);
	}

}

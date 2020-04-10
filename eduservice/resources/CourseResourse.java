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

import com.csye6225.spring2020.eduservice.datamodel.Assignment;
import com.csye6225.spring2020.eduservice.datamodel.Course;
import com.csye6225.spring2020.eduservice.service.CourseService;

//.. /webapi/myresource
@Path("/courses")

public class CourseResourse {
	
CourseService courseService = new CourseService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourses() {
		return courseService.getAllCourses();
	}	
	
	@GET
	@Path("/{CourseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("CourseId") String CourseId) {
		return courseService.getCourse(CourseId);
	}
	
	@GET
	@Path("/{CourseId}/students")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getCourseStudents(@PathParam("CourseId") String CourseId) {
		return courseService.getCourseStudents(CourseId);
	}
	
	@GET
	@Path("/{CourseId}/announcements")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getCourseAnnouncements(@PathParam("CourseId") String CourseId) {
		return courseService.getCourseAnnouncements(CourseId);
	}
	
	@GET
	@Path("/{CourseId}/assignments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assignment> getCourseAssignments(@PathParam("CourseId") String CourseId) {
		return courseService.getCourseAssignments(CourseId);
	}
	
	@DELETE
	@Path("/{CourseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course deleteProram(@PathParam("CourseId") String CourseId) {
		return courseService.deleteCourse(CourseId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addCourse(Course Course) {
			return courseService.addCourse(Course);
	}
	
	@PUT
	@Path("/{CourseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course updateCourse(@PathParam("CourseId") String CourseId, 
			Course Course) {
		return courseService.updateCourseInformation(CourseId, Course);
	}

}

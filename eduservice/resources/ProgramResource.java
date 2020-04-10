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

import com.csye6225.spring2020.eduservice.datamodel.Program;
import com.csye6225.spring2020.eduservice.service.ProgramService;

//.. /webapi/myresource
@Path("/programs")

public class ProgramResource {
	
ProgramService programService = new ProgramService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getPrograms() {
		return programService.getAllPrograms();
	}	
	
	@GET
	@Path("/{ProgramId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("ProgramId") String programId) {
		return programService.getProgram(programId);
	}
	
	@DELETE
	@Path("/{ProgramId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProram(@PathParam("ProgramId") String programId) {
		return programService.deleteProgram(programId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program program) {
			return programService.addProgram(program);
	}
	
	@PUT
	@Path("/{ProgramId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("ProgramId") String programId, 
			Program program) {
		return programService.updateProgramInformation(programId, program);
	}


}

package com.pafprj.Healthcare;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("staff")
public class StaffResource
{
	
	StaffRepository staffRepo = new StaffRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Staff> getStaff()
	{
		return staffRepo.getstaff();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	
	public Staff getMember(@PathParam("id") int id)
	{
		return staffRepo.getMember(id);
	}
	
	
	
	@POST
	@Path("add")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Staff add(Staff s1) 
	{
		staffRepo.createMember(s1);
		return s1;
	}
	
	
	@PUT
	@Path("update")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Staff update(Staff s1) 
	{
		staffRepo.updateMember(s1);
		return s1;
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Staff delete(@PathParam("id") int id) 
	{
		Staff s = staffRepo.getMember(id);
		if(s.geteId()!="0") {
			
			staffRepo.deleteMember(id);
		}
		return s;
			
	}
	
	
	
	
	

}

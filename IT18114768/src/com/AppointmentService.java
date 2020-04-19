package com;

import model.Appointment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Appointments")
public class AppointmentService {
	
	Appointment appointmentObj = new Appointment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String ReadData()
	 {
	 return appointmentObj.readData();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String InsertData(
     @FormParam("A_PatientName") String PatientName,
	 @FormParam("A_PatientNIC") String PatientNIC,
	 @FormParam("A_PatientPhoneNo") String PatientPhoneNo,
	 @FormParam("A_DoctorName") String DoctorName,
	 @FormParam("A_HospitalName") String HospitalName,
	 @FormParam("A_Date") String Date,
	 @FormParam("A_Time") String Time)
	
	{
	 String output = appointmentObj.insertData(PatientName,PatientNIC, PatientPhoneNo, DoctorName, HospitalName, Date,Time);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateData(String Data)
	{
	//Convert the input string to a JSON object
	 JsonObject appointmentObject = new JsonParser().parse(Data).getAsJsonObject();
	//Read the values from the JSON object
	 String AppointmentID = appointmentObject.get("A_AppointmentID").getAsString();
	 String PatientName = appointmentObject.get("A_PatientName").getAsString();
	 String PatientNIC = appointmentObject.get("A_PatientNIC").getAsString();
	 String PatientPhoneNo = appointmentObject.get("A_PatientPhoneNo").getAsString();
	 String DoctorName = appointmentObject.get("A_DoctorName").getAsString();
	 String HospitalName = appointmentObject.get("A_HospitalName").getAsString();
	 String Date = appointmentObject.get("A_Date").getAsString();
	 String Time = appointmentObject.get("A_Time").getAsString();
	 
	 String output = appointmentObj.updateData(AppointmentID, PatientName, PatientNIC, PatientPhoneNo, DoctorName, HospitalName, Date, Time);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String  DeleteData(String Data)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(Data,"", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String A_AppointmentID = doc.select("AppointmentID").text();
	 String output = appointmentObj.deleteData(A_AppointmentID);
	return output;
	}
	
	

}

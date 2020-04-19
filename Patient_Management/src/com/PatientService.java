package com;

import model.Patient;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Patients")
public class PatientService {
	
	Patient patientObj = new Patient();

//	 Implement the Read Items Operation 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPatient() {
		return patientObj.readPatient();
	}

//	 Implement the Create/Insert Item Operation 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("pNic") String pNic, @FormParam("pName") String pName,
			@FormParam("pAddress") String pAddress, @FormParam("pEmail") String pEmail, @FormParam("pTele") String pTele,
			@FormParam("pAge") String pAge, @FormParam("pStatus") String pStatus, @FormParam("pAllergic") String pAllergic,
			@FormParam("pWard") String pWard, @FormParam("pBed") String pBed) {
		String output = patientObj.insertPatient(pNic, pName, pAddress, pEmail, pTele, pAge, pStatus, pAllergic, pWard, pBed);
		return output;
	}

//	 Implement the Update Operation 
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String patientData) {
		
		// Convert the input string to a JSON object
		JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
		
		// Read the values from the JSON object
		String pID = patientObject.get("pID").getAsString();
		String pNic = patientObject.get("pNic").getAsString();
		String pName = patientObject.get("pName").getAsString();
		String pAddress = patientObject.get("pAddress").getAsString();
		String pEmail = patientObject.get("pEmail").getAsString();
		String pTele = patientObject.get("pTele").getAsString();
		String pAge = patientObject.get("pAge").getAsString();
		String pStatus = patientObject.get("pStatus").getAsString();
		String pAllergic = patientObject.get("pAllergic").getAsString();
		String pWard = patientObject.get("pWard").getAsString();
		String pBed = patientObject.get("pBed").getAsString();
		
		String output = patientObj.updatePatient(pID, pNic, pName, pAddress, pEmail, pTele, pAge, pStatus, pAllergic, pWard, pBed);
		return output;
	}
	
//	 Implement the Delete Operation 

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String pID = doc.select("pID").text();
		String output = patientObj.deletePatient(pID);
		return output;
	}

}

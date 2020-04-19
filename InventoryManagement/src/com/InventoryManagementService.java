package com;

//public class InventoryManagementService {

import model.Inventory;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Items")
public class InventoryManagementService {
	Inventory itemObj = new Inventory();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)

	public String readItems() {
		return itemObj.readItems();
	}

	// insert

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("name") String name, @FormParam("type") String type,
			@FormParam("desc") String desc, @FormParam("quantity") String quantity) {
		String output = itemObj.insertItem(name, type, desc, quantity);
		return output;
	}

	// update

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		// Read the values from the JSON object
		String stockId = itemObject.get("stockId").getAsString();
		String name = itemObject.get("name").getAsString();
		String type = itemObject.get("type").getAsString();
		String desc = itemObject.get("desc").getAsString();
		String quantity = itemObject.get("quantity").getAsString();
		String output = itemObj.updateItem( stockId, name, type, desc, quantity);
		return output;
	}

	// Delete

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		// Read the value from the element <stockId>
		String stockId = doc.select("stockId").text();
		String output = itemObj.deleteItem(stockId);
		return output;
	}

}
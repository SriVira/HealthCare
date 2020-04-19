package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Inventory {
	// A common method to connect to the DB /---------------
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost/inventory management", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// Insert Method /------------------

	public String insertItem(String name, String type, String desc, String quantity) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into items (`stockId`,`name`,`type`,`desc`,`quantity`)" + " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4, desc);
			preparedStmt.setString(5, quantity);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";

		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Read Method /-----------

	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Item Name</th><th>Item Type</th><th>Item Description</th><th>Item Quantity</th>";

			String query = "select * from items";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String stockId = Integer.toString(rs.getInt("stockId"));
				String name = rs.getString("name");
				String type = rs.getString("type");
				String desc = rs.getString("desc");
				String quantity = Integer.toString(rs.getInt("quantity"));

				// Add into the html table
				output += "<tr><td>" + name + "</td>";
				output += "<td>" + type + "</td>";
				output += "<td>" + desc + "</td>";
				output += "<td>" + quantity + "</td>";

				// buttons
				// output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\"
				// class=\"btn btn-secondary\"></td>"
				// + "<td><form method=\"post\" action=\"items.jsp\">"
				// + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn
				// btn-danger\">"
				// + "<input name=\"itemID\" type=\"hidden\" value=\"" + stockId + "\">" +
				// "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Update Method /--------------------
	public String updateItem( String ID, String name, String type, String desc, String quantity) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE items SET name=?,type=?,desc=?,quantity=? WHERE stockId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, desc);
			preparedStmt.setInt(4, Integer.parseInt(quantity));
			preparedStmt.setInt(5, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Delete Method /--------------------------

	public String deleteItem(String stockId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from items where stockId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(stockId));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";

		} catch (Exception e) {

			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

} // Main End /---------

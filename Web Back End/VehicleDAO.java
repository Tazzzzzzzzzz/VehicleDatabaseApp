/**
 */
/**/
package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
//Vehicle Dao Class
public class VehicleDAO {
	//Connection Function
	private static Connection getDBConnection() {
		//Sets dbConnection to null. No connection
		Connection dbConnection = null;
		try {
			//Sets up JDBC for SQLITE
			Class.forName("org.sqlite.JDBC");
			//Displays error if its not found
		} catch (ClassNotFoundException e) {
			//Prints error message to console
			System.out.println(e.getMessage());
		} try {
			//Sets the db url to be the vehicle sql database
			String dbURL = "jdbc:sqlite:vehicles.sqlite";
			//dbConnection gets the vehicle sql database
			dbConnection = DriverManager.getConnection(dbURL);
			//return the connection
			return dbConnection;
		} catch (SQLException e) {
			//Prints an error message if its not found
			System.out.println(e.getMessage());
		} return dbConnection;
	}
	//Creates a method to get all vehicles. Its an array method
	public ArrayList<Vehicle> getAllVehicles() throws SQLException {
		//Displays message in console for getting vehicles back
		System.out.println("\nRetrieving all vehicles from the database.....");
		//Sets up connections and null most of the values
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		//Creates a query string which is the SQL command
		String query = "SELECT * FROM vehicles;";
		//sets temp to null//Not really used here.
		Vehicle temp = null;
		//Creates the vehicle array list
		ArrayList<Vehicle> vehicleList = new ArrayList<>();
		//Try and catch statement 
		try {
			//Gets the dbconnection function
			dbConnection = getDBConnection();
			//creates the sql statement
			statement = dbConnection.createStatement();
			//prints the sql query that was used to console
			System.out.println("SQL Query = " + query);
			//Executes the query
			resultset = statement.executeQuery(query);
			//While loop that gets all the values
			while(resultset.next()) {
				int vehicle_id = resultset.getInt("vehicle_id");
				String make = resultset.getString("make");
				String model = resultset.getString("model");
				int year = resultset.getInt("year");
				int price = resultset.getInt("price");
				String license_number = resultset.getString("license_number");
				String colour = resultset.getString("colour");
				int number_doors = resultset.getInt("number_doors");
				String transmission = resultset.getString("transmission");
				int mileage = resultset.getInt("mileage");
				String fuel_type = resultset.getString("fuel_type");
				int engine_size = resultset.getInt("engine_size");
				String body_style = resultset.getString("body_style");
				String condition = resultset.getString("condition");
				String notes = resultset.getString("Notes");
				//Adds the vehicle to the list of vehicles
				vehicleList.add(new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes));	
			}
		} catch (SQLException e) { 
			//Catches the errors
			System.out.println(e.getMessage());
		} finally {
			//End of function closes resultset,statement and connection
			if(resultset != null) {
				resultset.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(dbConnection != null) {
				dbConnection.close();
			}
			//Returns the list of vehicles
		} return vehicleList;
	}
	
//Get a single vehicle based on the id
	public Vehicle getVehicle(int v_id) throws SQLException {
		//Prints message and sets up connection, statement and resultset to null
		System.out.println("\nRetrieving your selected vehicle.....");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		//Set query to be the sql for selecting a single vehicle
		String query = "SELECT * FROM vehicles WHERE vehicle_id = "+v_id+";";
		//Sets vehicle to null
		Vehicle vehicle = null;
		//Creates a try and catch
		try {
			//Gets the connection function
			dbConnection = getDBConnection();
			//Creates the statement
			statement = dbConnection.createStatement();
			//Displays the sql query
			System.out.println("SQL Query = " + query);
			//sets result set to execute the query 
			resultset = statement.executeQuery(query);
			//While loop created that gets the vehicle with that certain id
			while(resultset.next()) {
				int vehicle_id = resultset.getInt("vehicle_id");
				String make = resultset.getString("make");
				String model = resultset.getString("model");
				int year = resultset.getInt("year");
				int price = resultset.getInt("price");
				String license_number = resultset.getString("license_number");
				String colour = resultset.getString("colour");
				int number_doors = resultset.getInt("number_doors");
				String transmission = resultset.getString("transmission");
				int mileage = resultset.getInt("mileage");
				String fuel_type = resultset.getString("fuel_type");
				int engine_size = resultset.getInt("engine_size");
				String body_style = resultset.getString("body_style");
				String condition = resultset.getString("condition");
				String notes = resultset.getString("Notes");
				//Creates the vehicle, from the sql database
				vehicle = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes);
			}
			//Closes resultset,statement and connection
		} finally {
			if(resultset != null) {
				resultset.close();
			} if(statement != null) {
				statement.close();
			} if(dbConnection != null) {
				dbConnection.close();
			}
		}
		//Returns the vehicle
		return vehicle;
	}
	//Boolean method for delete vehicle, takes it vehicle id parameter
	public boolean deleteVehicle(int vehicle_id) throws SQLException {
		//sets boolean to false
		boolean b = false;
		//Displays message in console
		System.out.println("\nDeleting your selected vehicle.....");
		//Sets up connection and statement and nulls it.
		Connection dbConnection = null;
		Statement statement = null;
		//Sets query to be the SQL for deleting something based on ID
		String query = "DELETE FROM vehicles WHERE vehicle_id = " +vehicle_id + ";";
		//Creates a try and catch statement
		try {
			//gets connection function
			dbConnection = getDBConnection();
			//creates the statement 
			statement = dbConnection.createStatement();
			//Prints sql query
			System.out.println("SQL Query = " + query);
			//b is true when the statement is executed
			b = statement.execute(query);
			//Closes all statements and db connection if not null. If it is it wont close
		} finally {
			if(statement != null) {
				statement.close();
			} if(dbConnection != null) {
				dbConnection.close();
			}
		}
		//returns boolean
		return b;
	}
	//Boolean method to inset vehicle. Takes a vehicle parameter
	public boolean insertVehicle(Vehicle vehicle) throws SQLException {
		//Displays message for inserting vehicle in database
		System.out.println("Inserting your NEW vehicle into the database .....");
		//Nulls connection for setup and use 
		Connection dbConnection = null;
		Statement statement = null;
		//Creates query which inserts values into the vehicle database
		String query = "INSERT INTO vehicles (vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, Notes) VALUES (\"" +
				//Gets all data for the insert that is needed
				vehicle.getVehicle_id() + "\"," 
				+ "\"" + vehicle.getMake() + "\","
				+ "\"" + vehicle.getModel() + "\","
				+ "\"" + vehicle.getYear() + "\","
				+ "\"" + vehicle.getPrice() + "\","
				+ "\"" + vehicle.getLicense_number() + "\","
				+ "\"" + vehicle.getColour() + "\","
				+ "\"" + vehicle.getNumber_doors() + "\","
				+ "\"" + vehicle.getTransmission() + "\","
				+ "\"" + vehicle.getMileage() + "\","
				+ "\"" + vehicle.getFuel_type() + "\","
				+ "\"" + vehicle.getEngine_size() + "\","
				+ "\"" + vehicle.getBody_style() + "\","
				+ "\"" + vehicle.getCondition() + "\","
				+ "\"" + vehicle.getNotes() + "\")";
		//Creates a try and catch
		try {
			//gets the connection function
			dbConnection = getDBConnection();
			//creates the statement for the connection
			statement = dbConnection.createStatement();
			//Prints the query
			System.out.println("SQL Query = " + query);
			//executes the query that was created above
			statement.execute(query);
		} finally {
			//Closes off the statemen and connection
			if(statement != null) {
				statement.close();
			} if(dbConnection != null) {
				dbConnection.close();
			}
			//Returns false if it hasnt worked
		} return false;
	}
	//Boolean method to update a vehicle, takes to parameters vehicle and the vehicle id.
	public boolean updateVehicle(Vehicle vehicle, int vehicle_id) throws SQLException {
		//Prints message that vehicle is being updated
		System.out.println("Updating your vehicle into the database .....");
		//Nulls connection and sets them up
		Connection dbConnection = null;
		Statement statement = null;
		//Creates the query statement for SQL. Gets parameters
		String query = "UPDATE vehicles SET vehicle_id = '"+vehicle.getVehicle_id()+"', make = '"+vehicle.getMake()+"', model = '"+vehicle.getModel()+"', year = '"+vehicle.getYear()+"', price = '"+vehicle.getPrice()+"', license_number = '"+vehicle.getLicense_number()+"', colour = '"+vehicle.getColour()+"', number_doors = '"+vehicle.getNumber_doors()+"', transmission = '"+vehicle.getTransmission()+"', mileage = '"+vehicle.getMileage()+"', fuel_type = '"+vehicle.getFuel_type()+"', engine_size = '"+vehicle.getEngine_size()+"', body_style = '"+vehicle.getBody_style()+"', condition = '"+vehicle.getCondition()+"', Notes = '"+vehicle.getNotes()+"' WHERE vehicle_id = " +vehicle_id+ ";";
		try {
			//Sets up the connection and creates the statement, that prints the query to the console and executes it
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("SQL Query = " + query);
			statement.executeUpdate(query);
			//Closes off the statement and the connection
		} finally {
			if(statement != null) {
				statement.close();
			} if(dbConnection != null) {
				dbConnection.close();
			}
			//Returns false if it didn't work
		} return false;
	}
	
	
}

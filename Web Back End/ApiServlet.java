/**
 * @author Tasbeel Arif
 */
/**/
//Add this class to the servlets pacakage
package servlets;
//All my imports
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import com.google.gson.Gson;
import models.Vehicle;
import models.VehicleDAO;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

import java.util.*;

//Sets up api class which uses the HttpServlet
public class ApiServlet extends HttpServlet {
	/**
	 * 
	 */
	//Sets serial version
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//Creates a vehicle dao object
	VehicleDAO dao = new VehicleDAO();
	//Creates a GSON object
	Gson gson = new Gson();
	//Sets out to be a print writer
	PrintWriter out;

	@Override
	//DOGET request. Gets vehicle data
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Try and catch statement
		try {
			//ArrayList of vehicles from the dao
		ArrayList<Vehicle> vehicles = dao.getAllVehicles();
		//the response is for an application json
		resp.setContentType("application/json");
		//response getwriter write out
		out = resp.getWriter();
		//Converts the vehicle object to a json string
		String json = gson.toJson(vehicles);
		//Writes that json sring
		out.write(json);
		//Closes the print writer
		out.close();
		
		}catch(Exception e) {
			///Prints any error
			e.printStackTrace();
		}
		
		
	}
	@Override
	//DoPost request. Add vehicle request
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//requests the json parameter
		String json = req.getParameter("json");
		//creates the vehicle gson object from the vehicle class
		Vehicle v = new Gson().fromJson(json, Vehicle.class);
		//response writer
		Writer out = resp.getWriter();
		//sets boolean insert false
		boolean insert = false;
		//creates try and catch statement
		try {
			//insert is true if the vehicle passed into the dao insert is added
			insert = dao.insertVehicle(v);
			//catches an sql exception
		}catch(SQLException e) {
			e.printStackTrace();
			//if its inserted displays vehicle added message
		}if(insert) {
			out.write("Vehicle Not Added");
		}else {
			//if not added. displays vehicle not added message
			out.write("Vehicle Added");
		}
		//closes writer
		out.close();
		
	}
	@Override
	//doput request. used to update a vehicle in sql
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//gets the json parameter
		String json = req.getParameter("json");
		//creates the vehicle as gson from the vehicle class
		Vehicle v = new Gson().fromJson(json, Vehicle.class);
		//response writer
		Writer out = resp.getWriter();
		//sets update to false
		boolean updated = false;
		//creates try and catch
		try {
			//updated is true when the vehicle object and id, is passed into the update vehicle dao
			updated = dao.updateVehicle(v, v.getVehicle_id());
			//catches the sql exception
		}catch(SQLException e) {
			e.printStackTrace();
			//if its updated
		}if(updated) {
			//displays the postive message
			out.write("Vehicle Not Found");
		}else {
			//displays the negative message
			out.write("Vehicle Updated");
		}
	
	}
	//Creates a do delete request
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//sets boolean deleted to false
		
		
		String vehicle_id = req.getParameter("vehicle_id");
		Writer out = resp.getWriter();
		boolean deleted = false;
		System.out.println(vehicle_id);
		try {
			
			//passes into vehicle id to the delete dao
			deleted = dao.deleteVehicle(Integer.parseInt(vehicle_id));
			
		}catch(Exception h) {
			//catches exceptions
			h.printStackTrace();
		}
		//displays mostive message if done
		if(deleted) {
			out.write("Vehicle id not found");
		}else {
			//displays negative message if it isnt
			out.write("Vehicle deleted");
		}
	}
	

}


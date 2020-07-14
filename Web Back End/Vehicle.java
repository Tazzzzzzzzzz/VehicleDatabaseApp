/**
 * The Class Vehicle.
 *
 * @author Tasbeel Arif
 */
/**
		* Getter and setter methods for vehicle database
		*@param- Vehicle
		*@return- Getters and Setters for each variable
		*@throws SQLException-
		*@exception SQLException e-
		*/
package models;
public class Vehicle {
	
	public Vehicle() {
		// TODO Auto-generated constructor stub
	}
/* Declare all variables for getters and setters*/
	private int vehicle_id;
	private String make;
	private String model;
	private int year;
	private int price;
	private String license_number;
	private String colour;
	private int number_doors;
	private String transmission;
	private int mileage;
	private String fuel_type;
	private int engine_size;
	private String body_style;
	private String condition;
	private String notes;
/*Create vehicle constructor for this object*/
	public Vehicle(int vehicle_id, String make, String model, int year, int price, String license_number, String colour,
			int number_doors, String transmission, int mileage, String fuel_type, int engine_size, String body_style,
			String condition, String notes) {
		/*all current object declarations*/
		this.vehicle_id = vehicle_id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
		this.license_number = license_number;
		this.colour = colour;
		this.number_doors = number_doors;
		this.transmission = transmission;
		this.mileage = mileage;
		this.fuel_type = fuel_type;
		this.engine_size = engine_size;
		this.body_style = body_style;
		this.condition = condition;
		this.notes = notes;
	}

/*creates getter for vehicle id to get vehicleid*/
	public int getVehicle_id() {
		return vehicle_id;
	}
	/* creates setter for vehicle id and reads in integer vehicle id*/

	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	/*returns make*/
	public String getMake() {
		return make;
	}
	/*sets make for this object*/
	public void setMake(String make) {
		this.make = make;
	}
	/*returns model value*/
	public String getModel() {
		return model;
	}
	/*sets model for this object*/
	public void setModel(String model) {
		this.model = model;
	}
	/*returns year integer*/
	public int getYear() {
		return year;
	}
	/*sets year for current object*/
	public void setYear(int year) {
		this.year = year;
	}
	/*returns price integer*/
	public int getPrice() {
		return price;
	}
	/*sets price for current object*/
	public void setPrice(int price) {
		this.price = price;
	}
	/*returns license number*/
	public String getLicense_number() {
		return license_number;
	}
	/*sets license number for current object*/
	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}
	/*return colour */
	public String getColour() {
		return colour;
	}
	/*sets colour for current obj*/
	public void setColour(String colour) {
		this.colour = colour;
	}
	/*returns number of doors*/
	public int getNumber_doors() {
		return number_doors;
	}
	/*sets number of doors for current obj*/
	public void setNumber_doors(int number_doors) {
		this.number_doors = number_doors;
	}
	/*returns transmission*/
	public String getTransmission() {
		return transmission;
	}
	/*sets transmission for current obj*/
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	/*returns mileage*/
	public int getMileage() {
		return mileage;
	}
	/*sets mileage for current obj*/
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	/*return fuel type */
	public String getFuel_type() {
		return fuel_type;
	}
	/*sets fuel type for current obj*/
	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}
	/*returns engine size*/
	public int getEngine_size() {
		return engine_size;
	}
	/*sets engine size for current obj*/
	public void setEngine_size(int engine_size) {
		this.engine_size = engine_size;
	}
	/*returns body style*/
	public String getBody_style() {
		return body_style;
	}
	/*sets body style for current obj*/
	public void setBody_style(String body_style) {
		this.body_style = body_style;
	}
	/*return condition*/
	public String getCondition() {
		return condition;
	}
	/*sets condition for current obj*/
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/*returns notes*/
	public String getNotes() {
		return notes;
	}
	/*sets notes for current obj*/
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	/*tostring used to display all get parametersfor testing and displaying table of vehicle data*/
	public String toString() {
		return "VehicleID =" + getVehicle_id() + ", Make=" + getMake() + ", Model=" + getModel()
				+ ", Year=" + getYear() + ", Price=" + getPrice() + ", License_number="
				+ getLicense_number() + ", Colour=" + getColour() + ", Number_doors=" + getNumber_doors()
				+ ", Transmission=" + getTransmission() + ", Mileage=" + getMileage() + ", Fuel Type="
				+ getFuel_type() + ", Engine Size=" + getEngine_size() + ", Body Style=" + getBody_style()
				+ ", Condition=" + getCondition() + ", Notes=" + getNotes();
	}
	


}


public class Vehicle {
	
	private String color;
	private String brand;
	private String make;
	private int year;

	public Vehicle(String color, String brand, String make, int year) {
		this.color = color;
		this.brand = brand;
		this.make = make;
		this.year = year;
	}
	/**
	 * Sets the year of the of the vehicle
	 * @param y the new year of the vehicle
	 */
	public void setyear(int y) {
		year = y;
	}
	/**
	 * Sets the brand of the vehicle
	 * @param s the new brand of the vehicle
	 */
	public void setbrand(String s) {
		brand = s;
	}
	/**
	 * Sets the make of the vehicle
	 * @param s the new make of the vehicle
	 */
	public void setmake(String s) {
		make = s;
	}
	/**
	 * Sets the color of the vehicle
	 * @param s the new color of the vehicle
	 */
	public void setcolor(String s) {
		color = s;
	}
	/**
	 * Returns the color of vehicle
	 * @return the color of the vehicle
	 */
	public String getcolor() {
		return color;
	}
	/**
	 * Returns the brand of the vehicle
	 * @return the brand of the vehicle
	 */
	public String getbrand() {
		return brand;
	}
	/**
	 * Returns the make of the vehicle
	 * @return the make of the vehicle
	 */
	public String getmake() {
		return make;
	}
	/**
	 * Returns the year of the vehicle
	 * @return the year of the vehicle
	 */
	public int getyear() {
		return year;
	}
	
	@Override
	public String toString() {
		return color + " " + brand + " " + make + " " + String.valueOf(year);
	}
}

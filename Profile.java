
public class Profile {
	
	private String name;
	private Vehicle vehicle;
	
	public Profile(String name, Vehicle vehicle) {
		this.name = name;
		this.vehicle = vehicle;
	}
	/**
	 * Returns the name of the profile
	 * @return the name of the profile
	 */
	public String getname() {
		return name;
	}
	/**
	 * Returns the vehicle of the profile
	 * @return the vehicle of the profile
	 */
	public Vehicle getvehicle() {
		return vehicle;
	}
	/**
	 * Sets the name of the profile
	 * @param s the new name of the profile
	 */
	public void setname(String s) {
		name = s;
	}
	/**
	 * Sets the vehicle of the profile
	 * @param v the new vehicle of the profile
	 */
	public void setvehicle(Vehicle v) {
		vehicle = v;
	}
	
	@Override
	public String toString() {
		return name + ", " + vehicle.toString();
		
	}
}

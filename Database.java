import java.awt.Color;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;


public class Database {

	private ArrayList<Profile> data;
	private String dbname;
	private Path dbpath;
	
	public Database(ArrayList<Profile> data, String dbname, Path dbpath) {
		this.data = data;
		this.dbname = dbname;
		this.dbpath = dbpath;
	}
	/**
	 * Returns the ArrayList of Profiles in the database
	 * @return an ArrayList<Profile> representation of the data in the database
	 */
	public ArrayList<Profile> getdata() {
		return data;
	}
	/**
	 * Sets the data in the database
	 * @param dat the data to be set
	 */
	public void setdata(ArrayList<Profile> dat) {
		data = dat;
	}
	/**
	 * Returns the name of the database
	 * @return the name of the database
	 */
	public String getdbname() {
		return dbname;
	}
	/**
	 * Sets the name of the database
	 * @param s the name of the database
	 */
	public void setdbname(String s) {
		dbname = s;
	}
	/**
	 * Sets the Path of the database
	 * @param p the Path of the database
	 */
	public void setPath(Path p) {
		dbpath = p;
	}
	/**
	 * Returns the Path of the database
	 * @return the Path of the database
	 */
	public Path getPath() {
		return dbpath;
	}
	/**
	 * Add a Profile to the database
	 * @param p the Profile to be added
	 */
	public void addprofile(Profile p) {
		data.add(p);
	}
	/**
	 * Returns the ArrayList of Colors for the database, a compilation of converted Colors from the vehicles in each profile
	 * @return the compiled ArrayList<Color> of the database
	 */
	public ArrayList<Color> getarraycolor() {
		ArrayList<Color> a = new ArrayList<Color>();
		for (int i = 0; i < data.size(); i++) {
			a.add(stringToColor(data.get(i).getvehicle().getcolor()));
		}
		return a;
	}
	
	/**
	 * If value is a recognizable color, return the Color object that corresponds to it; else return a default gray color
	 */
	private static Color stringToColor(final String value) {
		if (value == null) {
			return Color.black;
		}
		try {
			return Color.decode(value);
		} catch (NumberFormatException nfe) {
			try {
				final Field f = Color.class.getField(value);
				return (Color) f.get(null);
			} catch (Exception ce) {
				return new Color(192,192,192);
			}
		}
	}
	 
	
	
	
	
	@Override
	public String toString() {
		String s = dbname;
		for (int i = 0; i < data.size(); i++) {
			s += data.get(i).toString();
		}
		return s;
	}
	
	
}

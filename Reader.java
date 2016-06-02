import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Reader {

	/**
	 * Returns the database that is constructed from parsing a given file
	 * @param f the File to be parsed
	 * @return the Database constructed from parsing
	 * @throws IOException
	 */
	public static Database createdbfromFile(File f) throws IOException {
		try {
		String y = f.getName();
		if (y.length() > 60) {
			y.substring(0, 50);
		}
		
		Scanner scanner1 = new Scanner(f);
		Database db = new Database(new ArrayList<Profile>(), y, f.toPath());
				
		while (scanner1.hasNext()) {
			String s = scanner1.nextLine();
			String[] tarray = s.split(",");
			String[] tarray2 = tarray[1].split(" ");
			Profile p = new Profile(tarray[0], new Vehicle(tarray2[1],tarray2[2],tarray2[3],Integer.parseInt(tarray2[4]))); 
			db.addprofile(p);	
	
		}
		scanner1.close();
	
		return db;
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Invalid File");
		}
		return null;
	}
	/**
	 * Attempts to save the given database in its Path location
	 * @param db the Database to save
	 * @throws IOException
	 */
	public static void savedb(Database db) throws IOException {
		File t = db.getPath().toFile();
		FileWriter f = new FileWriter(t, false); 
		String s = "";
		f.write(s);
		f.close();

		f = new FileWriter(t, true); 
		for (int i = 0; i < db.getdata().size(); i++) {
			Profile p = db.getdata().get(i);
			f.write(p.getname() + ", " + p.getvehicle().getcolor() + " " + p.getvehicle().getbrand() + " " + p.getvehicle().getmake() + " " + Integer.toString(p.getvehicle().getyear()));
			f.write(System.lineSeparator());
		}
		f.close();
	}
}

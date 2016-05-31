
public class Accessor {

	private DList<Database> dblist;
	
	public Accessor(DList<Database> dblist) {
		this.dblist = dblist;
	}
	/**
	 * Returns a linked list of the databases in the accessor
	 * @return the DList<Database> of the accessor
	 */
	public DList<Database> getdblist() {
		return dblist;
	}
	/**
	 * Adds a database to the accessor
	 * @param d the database to be added
	 */
	public void addDB(Database d) {
		dblist.add(0, d);
	}
	
	@Override
	public String toString() {
		return dblist.toString();
	}
}

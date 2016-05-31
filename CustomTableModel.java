import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {
	
	private static final String[] columnNames = {"Name", "Car Color", "Car Brand", "Car Make", "Model Year"};
    private final ArrayList<Profile> list;

	public CustomTableModel(Database db) {
		if (db != null && db.getdata() != null) list = db.getdata();
		else list = new ArrayList<Profile>();
	}
	/**
	 * Adds a profile to the model
	 * @param p the Profile to be added to the model
	 */
	public void addElement(Profile p) {
        // Adds the element in the last position in the list
        list.add(p);
        fireTableRowsInserted(list.size()-1, list.size()-1);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
    	if (!list.isEmpty()) return list.size();
    	else return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	try {
        switch(columnIndex) {
            case 0: return list.get(rowIndex).getname();
            case 1: return list.get(rowIndex).getvehicle().getcolor();
            case 2: return list.get(rowIndex).getvehicle().getbrand();
            case 3: return list.get(rowIndex).getvehicle().getmake();
            case 4: return list.get(rowIndex).getvehicle().getyear();
        }
    	} catch (IndexOutOfBoundsException e) {
        	
        }
        return null;
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	try {
    	Profile p = list.get(rowIndex);
    	if (columnIndex == 0) p.setname((String) aValue);
    	else if (columnIndex == 1) p.getvehicle().setcolor((String) aValue);
    	else if (columnIndex == 2) p.getvehicle().setbrand((String) aValue);
    	else if (columnIndex == 3) p.getvehicle().setmake((String) aValue);
    	else if (columnIndex == 4) p.getvehicle().setyear((int) aValue);
    	fireTableCellUpdated(rowIndex, columnIndex);
    	} catch (Exception e){
    		JOptionPane.showMessageDialog(null, "You must add a profile first");
    	}
    }
    /**
     * Overrides the default - sets all cells to be editable
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
    	return true;
    }
    /**
     * Returns the database attached to the CustomTableModel
     * @return the database attached to the CustomTableModel
     */
    public ArrayList<Profile> retdb() {
    	return list;
    }
    
}

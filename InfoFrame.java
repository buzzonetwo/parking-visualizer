import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InfoFrame extends JFrame {

	private JPanel contentPane;
	private CustomTableModel ctm;
	private JTable table;
	private Database db;
	private JButton btnDeleteProfile;
	private JButton btnAddProfile;

	

	/**
	 * Create the frame.
	 */
	public InfoFrame(Database d) {
		db = d;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(8, 8, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][][][][][]", "[][][grow]"));

		JLabel lblNewLabel = new JLabel("Current Lot: " + db.getdbname());
		contentPane.add(lblNewLabel, "cell 0 0");
		
		btnAddProfile = new JButton("Add Profile");
		btnAddProfile.addActionListener(new AddProfileListener());
		contentPane.add(btnAddProfile, "cell 2 0");
		
		btnDeleteProfile = new JButton("Delete Profile");
		contentPane.add(btnDeleteProfile, "cell 3 0");
		btnDeleteProfile.addActionListener(new DeleteProfileListener());

		JButton btnNewButton = new JButton("Statistics");
		contentPane.add(btnNewButton, "cell 8 0");
		btnNewButton.addActionListener(new StatisticsListener());
		
		ctm = new CustomTableModel(db);
		ctm.addTableModelListener(new CustomTableModelListener());
		
		table = new JTable(ctm);
		table.getTableHeader().setReorderingAllowed(false); //No changing header row
		table.getTableHeader().setResizingAllowed(false);
		contentPane.add(new JScrollPane(table), "cell 0 2 9 1,grow");
		
	}
	
	private class StatisticsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			StatFrame s = new StatFrame(db);
			s.setTitle("Statistics");
			s.setVisible(true);
		}
		
	}
	
	private class CustomTableModelListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			
			if (e.getColumn() == 0) {
				db.getdata().get(e.getFirstRow()).setname((String) ctm.getValueAt(e.getFirstRow(), e.getColumn()));
			}
			else if (e.getColumn() == 1) {
				db.getdata().get(e.getFirstRow()).getvehicle().setcolor(((String) ctm.getValueAt(e.getFirstRow(), e.getColumn())));
			}
			else if (e.getColumn() == 2) {
				db.getdata().get(e.getFirstRow()).getvehicle().setbrand(((String) ctm.getValueAt(e.getFirstRow(), e.getColumn())));
			}
			else if (e.getColumn() == 3) {
				db.getdata().get(e.getFirstRow()).getvehicle().setmake(((String) ctm.getValueAt(e.getFirstRow(), e.getColumn())));
			}
			else if (e.getColumn() == 4) {
				db.getdata().get(e.getFirstRow()).getvehicle().setyear(((int) ctm.getValueAt(e.getFirstRow(), e.getColumn())));
			}
			
			
			try {
				Reader.savedb(db);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	private class AddProfileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			db.addprofile(new Profile("John Doe", new Vehicle("white", "Kia", "Optima", 2000)));
			ctm.fireTableDataChanged();
		}
		
	}
	
	private class DeleteProfileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int a[] = table.getSelectedRows();
			for (int i = 0; i < a.length; i++) {
				System.out.println(a[i]);
			}
			for (int i = a.length-1; i > -1; i--) {
				db.getdata().remove(a[i]);
			}
			
			ctm.fireTableDataChanged();
		}
		
	}

}

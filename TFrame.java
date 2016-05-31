import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class TFrame extends JFrame {

	private JPanel contentPane;
	private Accessor bridge;
	private Database currdb;
	private int currdbnum;
	private JLabel lblNewLabel;
	private JPanel renderer;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TFrame frame = new TFrame();
					frame.setTitle("Parking Visualizer");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public TFrame() {
		bridge = new Accessor(new DList<Database>());
		currdb = new Database(null, "No Lots", null);
		currdbnum = -1;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][100px][][][][][][][][grow,fill][][43.00]", "[][60,grow][]"));
		
		lblNewLabel = new JLabel(currdb.getdbname());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 1 0 9 1");
	
		JButton btnDetailedInfo = new JButton("Detailed Info");
		contentPane.add(btnDetailedInfo, "cell 1 2");
		btnDetailedInfo.addActionListener(new DetailedInfoListener());

		JButton btnNewButton_2 = new JButton("New Lot from File");
		contentPane.add(btnNewButton_2, "cell 2 2");
		btnNewButton_2.addActionListener(new NewLotListener()); 
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.addActionListener(new LeftButtonListener());
		
		JButton btnNewButton_3 = new JButton("New Empty Lot");
		contentPane.add(btnNewButton_3, "cell 3 2");
		btnNewButton_3.addActionListener(new EmptyLotListener());
		
		JButton btnNewButton_4 = new JButton("Delete Lot");
		contentPane.add(btnNewButton_4, "cell 4 2");
		btnNewButton_4.addActionListener(new DeleteListener());
		
		JButton btnNewButton_1 = new JButton(">");
		contentPane.add(btnNewButton_1, "cell 11 2,growx");
		btnNewButton_1.addActionListener(new RightButtonListener());

		renderer = new JPanel();
		contentPane.add(renderer, "cell 1 1 10 1,grow");
		contentPane.add(btnNewButton, "cell 0 2,growx");
		
	}
	
	private class NewLotListener implements ActionListener {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt files", "txt");
		
		public void actionPerformed(ActionEvent e) {
			fc.setFileFilter(filter);
			int returnVal = fc.showOpenDialog(TFrame.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				try {
					bridge.addDB(Reader.createdbfromFile(file));
					if (bridge.getdblist().get(0) == null) {
						throw new NullPointerException();
					}
					currdb = bridge.getdblist().get(0);
					currdbnum = 0;
					updatelabel();
					setpanelcars(renderer);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(contentPane, "Failed to add");
				} catch (NullPointerException e2 ) {
					
				}
			}
		}
	}
	
	private class LeftButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (bridge.getdblist().getLength() > 0) {
				currdbnum -= 1;
					if (currdbnum < 0 ) {
						currdbnum = bridge.getdblist().getLength()-1;
					}
					currdb = bridge.getdblist().get(currdbnum);
					updatelabel();
			}
			setpanelcars(renderer);
		}
		
	}
	
	private class RightButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (bridge.getdblist().getLength() > 0) {
				currdbnum += 1;
					if (currdbnum > bridge.getdblist().getLength()-1) {
						currdbnum = 0;
					}
					currdb = bridge.getdblist().get(currdbnum);
					updatelabel();
					
			}
			setpanelcars(renderer);
		}
		
	}
	
	private class DetailedInfoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (currdb.getdata() != null && currdbnum > -1) {
				InfoFrame details = new InfoFrame(currdb);
				details.setTitle("Detailed Information");
				details.setVisible(true);
				setpanelcars(renderer);
			}
			else {
				JOptionPane.showMessageDialog(contentPane, "No Lots");
			}
		}
	}
	
	private class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (bridge.getdblist().getLength() > 0) {
				bridge.getdblist().remove(currdbnum);
				if (bridge.getdblist().getLength() > 0) {
					if (currdbnum > 0) {
						currdbnum -= 1;
					}
					else {
						currdbnum = 1;
					}
					currdb = bridge.getdblist().get(currdbnum);
					updatelabel();
					setpanelcars(renderer);
				}
				else {
					currdbnum = -1;
					updatelabel("No Lots");
					removepanelcars(renderer);
				}
			}
			else {
				JOptionPane.showMessageDialog(contentPane, "No Lots");
			}
		}
	}
	
	private class EmptyLotListener implements ActionListener {
		JFileChooser fc = new JFileChooser();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String s = JOptionPane.showInputDialog(contentPane, "Please enter a name for the lot");	
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setAcceptAllFileFilterUsed(false);
			int returnVal = fc.showSaveDialog(TFrame.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				Path p = fc.getSelectedFile().toPath();
				p = Paths.get(p.toString() + "\\" + s + ".txt");
				Database d = new Database(new ArrayList<Profile>(), s+".txt", p);
				bridge.addDB(d);
				currdbnum = bridge.getdblist().getLength()-1;
				currdb = bridge.getdblist().get(currdbnum);
				updatelabel("Current Lot: "+s+".txt");
				removepanelcars(renderer);
			}
		}
	}
	/**
	 * Dynamically render cars on to the given panel
	 * @param p the panel to render cars on
	 */
	public void setpanelcars(JPanel p) {
		p.removeAll();		
		p.setVisible(false);
		p.setVisible(true);
		if (currdb != null && currdb.getdbname() != null) {
			ArrayList<Color> c = currdb.getarraycolor();
			for (int i = 0; i < c.size(); i++) {
				int a;
				if (c.size() > 10) a = (int) (30/(Math.log(c.size())));
				else if (c.size() > 3) a = (int) (23/(Math.log(c.size())));
				else a = (int) (10/(Math.log(c.size())));
				GPanel t = new GPanel(c.get(i));
				t.setPreferredSize(new Dimension(7*a, 12*a));
				t.setBackground(c.get(i));
				t.setBorder(BorderFactory.createLineBorder(Color.black));
				p.add(t);
			}
		}
	}
	/**
	 * Remove all elements from the given panel
	 * @param p the panel to remove from
	 */
	public void removepanelcars(JPanel p) {
		p.removeAll();
		//refreshing the rendering of the panel
		p.setVisible(false);
		p.setVisible(true);
	}
	/**
	 * Updates the label with the current lot's name
	 */
	public void updatelabel() {
		if (currdb != null && currdb.getdbname() != null) {
			lblNewLabel.setText("Current Lot: " + currdb.getdbname());
	
		}
	}
	/**
	 * Sets the label to a given string
	 * @param s the new string
	 */
	public void updatelabel(String s) {
		lblNewLabel.setText(s);
	}
	
	class GPanel extends JPanel {
		
		private int w;
		private int h;
		private Color s; // special color
		
        public GPanel(Color c) {
        	int r = c.getRed();
        	int g = c.getGreen();
        	int b = c.getBlue();
        	if (r < 200) r += 50;
        	else r = 245;
        	if (g < 200) g += 50;
        	else g = 245;
        	if (b < 200) b += 50;
        	else b = 245;
        	s = new Color(r,g,b);
        }

        public void updatevars() {
        	w = this.getWidth();
        	h = this.getHeight();
        }
        
        @Override // draws the car
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            updatevars();
            
            g.setColor(Color.white);
            g.fillRect(0, 0, w/6, h/8);
            g.fillRect((w-w/6), 0, w/6, h/8);
            g.setColor(Color.red);
            g.fillRect(0, h-(h/11), w/6, h/11);
            g.fillRect(w-(w/6), h-(h/11), w/6, h/11);
            
            g.setColor(s);
            g.fillRect(0, 26*h/100, w, 57*h/100);
            
            g.setColor(Color.black);
            g.drawRect(0, 0, w/6, h/8);
            g.drawRect((w-w/6), 0, w/6, h/8);
            g.drawRect(0, h-(h/11), w/6, h/11);
            g.drawRect(w-(w/6), h-(h/11), w/6, h/11);
           
            g.drawRect(w/6, 2*h/5, w-(2*w/6), h/3);
            g.drawLine(0, 26*h/100, w, 26*h/100);
            g.drawLine(0, 83*h/100, w, 83*h/100);
           
            g.drawLine(0, 26*h/100, w/6, 2*h/5);
            g.drawLine((w/6 + w-(2*w/6)), 2*h/5, w, 26*h/100);
            g.drawLine(0, 83*h/100, w/6, (2*h/5 + h/3));
            g.drawLine((w/6 + w-(2*w/6)), (2*h/5 + h/3), w, 83*h/100);
            
            g.drawRect(0, 52*h/100, w/6, 0);
            g.drawRect(w-(w/6), 52*h/100, w/6, 0);
        }
    }
}

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JTextArea;

public class StatFrame extends JFrame {

	private JPanel contentPane;
	private JButton buttonLeft;
	private JLabel lblNewLabel;
	private JButton buttonRight;
	private JTextArea textArea;
	
	
	private int cycle;
	private final String[] titles = {"Data", "Other"};

	/**
	 * Create the frame.
	 */
	public StatFrame(Database d) {
		cycle = 0;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[41px,grow][41px][][][][][][][][][][]", "[23px][grow]"));
		

		lblNewLabel = new JLabel("New label");
		contentPane.add(lblNewLabel, "cell 1 0 7 1,alignx center");
		lblNewLabel.setText(titles[cycle]);
		
		
		buttonLeft = new JButton("<");
		contentPane.add(buttonLeft, "cell 0 0,alignx left,aligny top");
		buttonLeft.addActionListener(new LeftListener());
		
		buttonRight = new JButton(">");
		contentPane.add(buttonRight, "cell 13 0,alignx left,aligny top");
		buttonRight.addActionListener(new RightListener());
		
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea); 
		contentPane.add(scrollPane, "cell 0 1 14 1,grow");
	}
	
	private class LeftListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cycle-=1;
			if (cycle < 0) {
				cycle = titles.length-1;
			}
			lblNewLabel.setText(titles[cycle]);
		}
		
	}
	
	private class RightListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cycle+=1;
			if (cycle >= titles.length) {
				cycle = 0;
			}
			lblNewLabel.setText(titles[cycle]);
		}
		
	}

}

package views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;

public class PatientProfile extends JFrame {

	private JPanel contentPane;
	private static String pname;


	/**
	 * Create the frame.
	 */
	public PatientProfile(String pname) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Profile", null, panel, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("History", null, panel_5, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Meds", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Labs", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Orders", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblEnderWiggin = new JLabel(pname);
		lblEnderWiggin.setFont(new Font("Roboto", Font.BOLD, 30));
		panel_4.add(lblEnderWiggin);
	}
	
	public Container getContentPane() {
		return contentPane;
	}

}

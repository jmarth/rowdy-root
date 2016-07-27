package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import database.SurgeryTemplatesTableGateway;
import models.Surgery;
import models.SurgeryTemplatesList;

public class ClosedProcedureView extends JPanel {
	
	// Containers --------------------------------------
		JPanel titlePanel;
		JPanel mainPanel;
		
		
		// Labels ------------------------------------------
		private JLabel titleLabel;
		
		
		// Fields ------------------------------------------
		private JTextArea textArea;

	public ClosedProcedureView(Surgery tmp) {
		setLayout(new BorderLayout());
		
		mainPanel = new JPanel(new BorderLayout());
		
		titleLabel = new JLabel(tmp.getTitle());
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			
		titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel.add(titleLabel);
		
		textArea = new JTextArea();
		textArea.setText(tmp.getBody());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setColumns(60);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArea.setEditable(false);
		
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		mainPanel.add(textArea, BorderLayout.CENTER);
		add(mainPanel, BorderLayout.CENTER);
	}

}

package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import models.CL;
import models.Surgery;

public class ClosedProcedureView extends JPanel implements viewinterface {
	
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
		mainPanel.setBorder(new LineBorder(CL.red, 3));
		
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

	@Override
	public void showview() {
		// TODO Auto-generated method stub
		
	}

}

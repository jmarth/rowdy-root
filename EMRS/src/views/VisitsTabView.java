package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import models.Patient;

public class VisitsTabView extends JPanel{
	public VisitsTabView(final Patient patient, final JTabbedPane tabbedPane) {
		GridBagLayout gbl_visitTestPanel = new GridBagLayout();
		gbl_visitTestPanel.columnWidths = new int[]{0, 0};
		gbl_visitTestPanel.rowHeights = new int[]{0, 0};
		gbl_visitTestPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_visitTestPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		this.setLayout(gbl_visitTestPanel);
		
		JButton btnNewVisit = new JButton("New Visit");
		btnNewVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfTab("Visits");
				tabbedPane.setComponentAt(index, new VisitTabViewNewVisit(tabbedPane, patient));//, atg, allergyTable));
			}
		});
		GridBagConstraints gbc_btnNewVisit = new GridBagConstraints();
		gbc_btnNewVisit.gridx = 0;
		gbc_btnNewVisit.gridy = 0;
		this.add(btnNewVisit, gbc_btnNewVisit);
		
	}
}

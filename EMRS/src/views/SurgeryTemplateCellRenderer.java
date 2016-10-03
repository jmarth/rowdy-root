package views;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import models.Document;
import models.SurgeryTemplate;

public class SurgeryTemplateCellRenderer implements ListCellRenderer{
	
	private final DefaultListCellRenderer DEFAULT_RENDERER = new DefaultListCellRenderer();
	@Override
	public Component getListCellRendererComponent(JList list, Object v, int index, boolean isSelected, boolean cellHasFocus) {
		SurgeryTemplate value = (SurgeryTemplate) v;
		JLabel renderer = (JLabel) DEFAULT_RENDERER.getListCellRendererComponent(list, value.getTitle(), index, isSelected, cellHasFocus);
		return renderer;
		
	}

}

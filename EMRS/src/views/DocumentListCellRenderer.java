package views;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import models.Document;

/**
 * Customizes row info in JList
 * Don't want to use Person's toString() in JLists. Only want to see each person's full name.
 * @author marcos
 *
 */
public class DocumentListCellRenderer implements ListCellRenderer {
	/**
	 * Can use default rendered to keep the visual parts I like (e.g., row height, highlight color, etc.)
	 */
	private final DefaultListCellRenderer DEFAULT_RENDERER = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(JList list, Object v, int index, boolean isSelected, boolean cellHasFocus) {
		Document value = (Document)v;
		JLabel renderer = (JLabel) DEFAULT_RENDERER.getListCellRendererComponent(list, value.getRenderedDisplayString(), index, isSelected, cellHasFocus);
		return renderer;
	}

}


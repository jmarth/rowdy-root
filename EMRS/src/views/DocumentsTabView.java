package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.jdesktop.swingx.JXBusyLabel;

import models.CL;
import net.coobird.thumbnailator.Thumbnails;

public class DocumentsTabView extends JPanel {
	
	private JScrollPane scroller;
	
	private JPanel pane;
	
	private JPanel buttonPanel;
	
	private static PDDocument doc;
	
	private static PDFRenderer docRenderer;
	
	private static JButton uploadButton;
	
	private final JFileChooser fc = new JFileChooser();
	
	private final FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
	
	private File filePath;
	
	public DocumentsTabView() {
		
		this.setLayout(new BorderLayout());
		
		pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBackground(CL.antiqueWhite);
		
		scroller = new JScrollPane(pane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		fc.setFileFilter(filter);
		
		uploadButton = new JButton("Upload");
		uploadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int retval = fc.showOpenDialog(null);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					pane.removeAll();
					filePath = fc.getSelectedFile();
					loadPDF(filePath);
				}
			}
		});
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(uploadButton);
		
		add(scroller, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void loadPDF(File filePath) {
		int numPages, i;
		JLabel label = null;
		BufferedImage temp = null;
		BufferedImage scaled = null;
		
		try {
			doc = PDDocument.load(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		numPages = doc.getNumberOfPages();
		
		docRenderer = new PDFRenderer(doc);
			
		try {
			for (i = 0; i < numPages; i++) {
				temp = docRenderer.renderImage(i);

				label = new JLabel(new ImageIcon(temp));
				label.setAlignmentX(Component.CENTER_ALIGNMENT);
				pane.add(label);
				label = null;
				pane.add(new JSeparator(SwingConstants.HORIZONTAL));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			doc.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		doc = null;
		
	}

}

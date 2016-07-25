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
import java.util.List;

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

import controller.DocumentListController;
import database.DocumentTableGateway;
import database.GatewayException;
import models.CL;
import models.Document;
import models.DocumentList;
import models.Patient;
import net.coobird.thumbnailator.Thumbnails;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DocumentsTabView extends JPanel {
	
	private DocumentTableGateway dtg;
	
	private JScrollPane scroller;
	
	private JPanel pane;
	
	private JPanel buttonPanel;
	
	private static PDDocument doc;
	
	private static PDFRenderer docRenderer;
	
	private static JButton uploadButton;
	
	private final JFileChooser fc = new JFileChooser();
	
	private final FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF FILES", "pdf");
	
	private File filePath;
	private JPanel filesPanel;
	private JList fileList;
	private Document selectedDocument;
	private DocumentList dl;
	private Patient p;
	private DocumentListController docListController;
	
	public DocumentsTabView(DocumentTableGateway dtg, Patient p) {
		
		this.setLayout(new BorderLayout());
		this.dtg = dtg;
		this.p = p;
		
		dl = new DocumentList(p);
		dl.setGateway(dtg);
		dl.loadFromGateway();
		
		docListController = new DocumentListController(dl);
		
		
		pane = new JPanel();
		pane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
		
		filesPanel = new JPanel();
		filesPanel.setBackground(Color.CYAN);
		FlowLayout flowLayout = (FlowLayout) filesPanel.getLayout();
		flowLayout.setHgap(10);
		scroller.setRowHeaderView(filesPanel);
		
		
		fileList = new JList(docListController);
		fileList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() > 1){
					int index = fileList.locationToIndex(evt.getPoint());
					selectedDocument = docListController.getElementAt(index);
					
					// Open the selected Document onto the screen
				}
			}
		});
		fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fileList.setForeground(Color.DARK_GRAY);
		fileList.setFont(new Font("Sitka Heading", Font.BOLD, 15));
		fileList.setBackground(Color.CYAN);
		fileList.setCellRenderer(new DocumentListCellRenderer());
		
		filesPanel.add(fileList);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	
	
	
	/**
	 * 
	 * @param filePath
	 */
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

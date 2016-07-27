package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import controller.DocumentListController;
import database.DocumentTableGateway;
import database.GatewayException;
import models.CL;
import models.Document;
import models.DocumentList;
import models.Patient;

public class DocumentsTabView extends JPanel {
	
	private DocumentTableGateway dtg;
	
	private JScrollPane scroller;
	
	private JPanel pane;
	
	private JPanel buttonPanel;
	
	private static PDDocument doc;
	
	private static PDFRenderer docRenderer;
	
	private static JButton uploadButton;
	
	private final JFileChooser fc = new JFileChooser();
	
	private final FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF FILES", "pdf", "jpg");
	
	private File filePath;
	private JPanel filesPanel;
	private JList fileList;
	private Document selectedDocument;
	private DocumentList dl;
	private Patient p;
	private DocumentListController docListController;
	private JScrollPane docScrollPane;
	private JButton btnRemoveDocument;
	
	public DocumentsTabView(final DocumentTableGateway dtg, final Patient p) {
		
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
				// Add and insert into DB.. update JList
				int retval = fc.showOpenDialog(null);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					pane.removeAll();
					filePath = fc.getSelectedFile();
				} else {
					return;
				}
				
				String ext = FilenameUtils.getExtension(filePath.getAbsolutePath());
				Document tmpDoc = new Document(p.getId(), filePath.getName(), filePath.getAbsolutePath(), ext);
				try {
					Long newId = dtg.insertDocument(tmpDoc);
					tmpDoc.setId(newId);
				} catch (GatewayException e1) {
					System.out.println(e1.getMessage());
				}
				
				dl.addDocumentToList(tmpDoc);
				docListController.setList(dl);
				
				fileList.repaint();
				fileList.updateUI();
			}
		});
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(uploadButton);
		
		add(scroller, BorderLayout.CENTER);
		
		filesPanel = new JPanel();
		filesPanel.setBackground(Color.CYAN);
		scroller.setRowHeaderView(filesPanel);
		GridBagLayout gbl_filesPanel = new GridBagLayout();
		gbl_filesPanel.columnWidths = new int[]{120, 0};
		gbl_filesPanel.rowHeights = new int[]{600, 0, 0};
		gbl_filesPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_filesPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		filesPanel.setLayout(gbl_filesPanel);
		
		docScrollPane = new JScrollPane();
		docScrollPane.setBorder(BorderFactory.createEmptyBorder());
		docScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		docScrollPane.setPreferredSize(new Dimension(120, 600));
		GridBagConstraints gbc_docScrollPane = new GridBagConstraints();
		gbc_docScrollPane.fill = GridBagConstraints.VERTICAL;
		gbc_docScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_docScrollPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_docScrollPane.gridx = 0;
		gbc_docScrollPane.gridy = 0;
		filesPanel.add(docScrollPane, gbc_docScrollPane);
		
		
		fileList = new JList(docListController);
		fileList.setVisibleRowCount(10);
		fileList.setBorder(null);
		docScrollPane.setViewportView(fileList);
		
		// Mouse listener for selected document
		// Show selected document
		fileList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() > 1){
					int index = fileList.locationToIndex(evt.getPoint());
					selectedDocument = docListController.getElementAt(index);
					
					pane.removeAll();
					
					// Open the selected Document onto the screen
					File file = new File(selectedDocument.getPath());
					
					if(selectedDocument.getType().equalsIgnoreCase("pdf")){
						loadPDF(file);
					} else if(selectedDocument.getType().equalsIgnoreCase("jpg")){
						loadJPG(file);
					}
					
					// Calls to recreate the pane after selected file
					pane.updateUI();
					pane.validate();
					pane.repaint();
					
				}
			}
		});
		fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fileList.setForeground(Color.DARK_GRAY);
		fileList.setFont(new Font("Sitka Heading", Font.BOLD, 15));
		fileList.setBackground(Color.CYAN);
		fileList.setCellRenderer(new DocumentListCellRenderer());
		
		btnRemoveDocument = new JButton("Remove");
		btnRemoveDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Do the removing part
				int selectedIndex = fileList.getSelectedIndex();
				Document selectedDocument = docListController.getElementAt(selectedIndex);
				
				try {
					dtg.removeDocument(selectedDocument.getId());
				} catch (GatewayException e) {
					System.out.println(e.getMessage());
				}
				dl.removeDocumentFromList(selectedDocument);
				docListController.setList(dl);
				
				// Calls to clear pane after deletion of file
				pane.removeAll();
				pane.updateUI();
				pane.validate();
				pane.repaint();
				
				fileList.repaint();
				fileList.updateUI();
			}
		});
		GridBagConstraints gbc_btnRemoveDocument = new GridBagConstraints();
		gbc_btnRemoveDocument.gridx = 0;
		gbc_btnRemoveDocument.gridy = 1;
		filesPanel.add(btnRemoveDocument, gbc_btnRemoveDocument);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	
	
	
	/**
	 * 
	 * @param filePath
	 */
	private void loadPDF(File file) {
		int numPages, i;
		JLabel label = null;
		BufferedImage temp = null;
		BufferedImage scaled = null;
		
		try {
			doc = PDDocument.load(file);
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
	
	/**
	 * Load .jpg file into the pane
	 * @param filePath File path of the .jpg to load into pane
	 */
	private void loadJPG(File file){
		BufferedImage image = null;
        try
        {
          image = ImageIO.read(file);
        }
        catch (Exception e)
        {
          e.printStackTrace();
          System.exit(1);
        }
        
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        pane.add(jLabel, BorderLayout.CENTER);
	}

}

package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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
import models.MasterModel;
import models.Patient;
import net.coobird.thumbnailator.Thumbnails;

public class DocumentsTabView extends JPanel implements viewinterface  {
	
	//private DocumentTableGateway dtg;
	
	private JScrollPane scroller;
	
	private JScrollPane fileScroller;
	
	private JSplitPane splitPane;
	
	private JPanel docPane;
	
	private JPanel buttonPanel;
	
	private JPanel parentPane;
	
	private JPanel removeButtonPane;
	
	private static PDDocument doc;
	
	private static PDFRenderer docRenderer;
	
	private static JButton uploadButton;
	
	private final JFileChooser fc = new JFileChooser();
	
	private final FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf", "jpg");
	
	private File filePath;
	private JPanel filesPanel;
	private JList fileList;
	private Document selectedDocument;
	//private DocumentList dl;
	//private Patient p;
	private DocumentListController docListController;
	private JScrollPane docScrollPane;
	private JButton btnRemoveDocument;
	
	//public DocumentsTabView(final DocumentTableGateway dtg, final Patient p) {
	public DocumentsTabView(){	
		this.setLayout(new BorderLayout());
		//this.dtg = dtg;
		//this.p = p;
		
		//dl = new DocumentList(p);
		//dl.setGateway(dtg);
		//dl.loadFromGateway();
		//this.getMasterModel().
		
		docListController = new DocumentListController(this.getMasterModel().getdL());
		
		
		docPane = new JPanel();
		docPane.setLayout(new BoxLayout(docPane, BoxLayout.Y_AXIS));
		docPane.setBackground(CL.antiqueWhite);
		
		scroller = new JScrollPane(docPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
		fc.setFileFilter(filter);
		
		uploadButton = new JButton("Upload");
		uploadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add and insert into DB.. update JList
				int retval = fc.showOpenDialog(null);
				
				if (retval == JFileChooser.APPROVE_OPTION) {
					docPane.removeAll();
					filePath = fc.getSelectedFile();
				} else {
					return;
				}
				
				String ext = FilenameUtils.getExtension(filePath.getAbsolutePath());
				Document tmpDoc = new Document(DocumentsTabView.this.getMasterModel().getCurrPatient().getId(),
						filePath.getName(), filePath.getAbsolutePath(), ext);
				try {
					tmpDoc.setId(DocumentsTabView.this.getMasterModel().getdL().insert(tmpDoc));
					//Long newId = dtg.insertDocument(tmpDoc);
					//tmpDoc.setId(newId);
				} catch (GatewayException e1) {
					System.out.println(e1.getMessage());
				}
				
				//dl.addDocumentToList(tmpDoc);
				//docListController.setList(dl);
				
				fileList.repaint();
				fileList.updateUI();
			}
		});
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(uploadButton);
		
		parentPane = new JPanel(new BorderLayout());
		parentPane.add(scroller, BorderLayout.CENTER);
		parentPane.add(buttonPanel, BorderLayout.SOUTH);
		parentPane.setBorder(null);
		
		filesPanel = new JPanel(new BorderLayout());
		filesPanel.setBackground(Color.WHITE);
		
		
		fileList = new JList(docListController);
		fileList.setVisibleRowCount(10);
		fileList.setBorder(new EmptyBorder(5, 5, 5, 5));
		fileList.setBackground(Color.WHITE);
		
		fileScroller = new JScrollPane(fileList);
		
		filesPanel.add(fileScroller, BorderLayout.CENTER);
		
		// Mouse listener for selected document
		// Show selected document
		fileList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() > 1){
					int index = fileList.locationToIndex(evt.getPoint());
					selectedDocument = docListController.getElementAt(index);
					
					docPane.removeAll();
					
					// Open the selected Document onto the screen
					File file = new File(selectedDocument.getPath());
					
					if(selectedDocument.getType().equalsIgnoreCase("pdf")){
						loadPDF(file);
					} else if(selectedDocument.getType().equalsIgnoreCase("jpg")){
						loadJPG(file);
					}
					
					// Calls to recreate the pane after selected file
					docPane.updateUI();
					docPane.validate();
					docPane.repaint();
					
				}
			}
		});
		fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fileList.setForeground(Color.DARK_GRAY);
		fileList.setFont(new Font("Sitka Heading", Font.BOLD, 15));
		fileList.setCellRenderer(new DocumentListCellRenderer());
		
		btnRemoveDocument = new JButton("Remove");
		btnRemoveDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Do the removing part
				int selectedIndex = fileList.getSelectedIndex();
				Document selectedDocument = docListController.getElementAt(selectedIndex);
				
				try {
					//dtg.removeDocument(selectedDocument.getId())
					DocumentsTabView.this.getMasterModel().getdL().delete(selectedDocument.getId());;
				} catch (GatewayException e) {
					System.out.println(e.getMessage());
				}
				//dl.removeDocumentFromList(selectedDocument);
				//docListController.setList(dl);
				
				// Calls to clear pane after deletion of file
				docPane.removeAll();
				docPane.updateUI();
				docPane.validate();
				docPane.repaint();
				
				fileList.repaint();
				fileList.updateUI();
			}
		});
		
		removeButtonPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		removeButtonPane.add(btnRemoveDocument);
		
		filesPanel.add(removeButtonPane, BorderLayout.SOUTH);
		filesPanel.setBorder(null);
		filesPanel.setPreferredSize(new Dimension(75, 900));
		
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.25);
		splitPane.setLeftComponent(filesPanel);
		splitPane.setRightComponent(parentPane);
		splitPane.setEnabled(false);
		splitPane.setBorder(null);

		

		add(splitPane);

		
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
				docPane.add(label);
				label = null;
				//docPane.add(new JSeparator(SwingConstants.HORIZONTAL));
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
        
        BufferedImage scaled = null;
        
        try {
			scaled = Thumbnails.of(image).size(850, 1100).asBufferedImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
        
        ImageIcon imageIcon = new ImageIcon(scaled);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        docPane.add(jLabel, BorderLayout.CENTER);
	}




	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MasterModel getMasterModel() {
		return ((PatientRecordView)this.getParent()).getMasterModel();
	}

	@Override
	public void ShowView() {
		// TODO Auto-generated method stub
		this.setVisible(true);
		
	}

	@Override
	public void reload() {
		//TODO
	}

	@Override
	public HomeView getHomeView() {
		return ((PatientRecordView)this.getParent()).getHomeView();
	}

}

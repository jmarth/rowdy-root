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

//import controller.DocumentListController;
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

	private JPanel filesPanel;
	private JList fileList;
	private Document selectedDocument;
	private JScrollPane docScrollPane;
	private JButton btnRemoveDocument;
	
	public DocumentsTabView(){
		this.setLayout(new BorderLayout());
		selectedDocument=null;
		docPane = new JPanel();
		docPane.setLayout(new BoxLayout(docPane, BoxLayout.Y_AXIS));
		docPane.setBackground(CL.antiqueWhite);
		scroller = new JScrollPane(docPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		fc.setFileFilter(filter);
		uploadButton = new JButton("Upload");
		uploadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add and insert into DB.. update JList
				int retval = fc.showOpenDialog(null);
				File file;
				if (retval == JFileChooser.APPROVE_OPTION) {
					docPane.removeAll();
					file = fc.getSelectedFile();
				} else {
					return;
				}
				
				String ext = FilenameUtils.getExtension(file.getAbsolutePath());
				Document tmpDoc = new Document(DocumentsTabView.this.getMasterModel().getCurrPatient().getId(),
						file.getName(), file.getAbsolutePath(), ext);
				try {
					DocumentsTabView.this.getMasterModel().getdL().insert(tmpDoc);
					reload();
				} catch (GatewayException e1) {
					System.out.println(e1.getMessage());
				}
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
		
		
		//fileList = new JList(docListController);
		fileList = new JList ();
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
				if(evt.getClickCount() > 0){
					int index = fileList.locationToIndex(evt.getPoint());
					if(index>-1)
						fileList.setSelectedIndex(index);
					selectedDocument = (Document)fileList.getSelectedValue();
					loadDocpane();
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
				try {
					Document rvd = (Document)fileList.getSelectedValue();
					if(selectedDocument!=null && rvd.getPath().equals(selectedDocument.getPath())){
						DocumentsTabView.this.getMasterModel().getdL().delete(selectedIndex);
						selectedDocument=null;
					}else{
						DocumentsTabView.this.getMasterModel().getdL().delete(selectedIndex);
					}
					System.out.println(selectedDocument);
					reload();
				} catch (GatewayException e) {
					System.out.println(e.getMessage());
				}
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
	private void loadDocpane(){
		docPane.removeAll();
		if(selectedDocument!=null){
			File filePath = new File(selectedDocument.getPath());
			if(selectedDocument.getType().equalsIgnoreCase("pdf")){
				loadPDF(filePath);
			} else if(selectedDocument.getType().equalsIgnoreCase("jpg")){
				loadJPG(filePath);
			}
		}
		docPane.getParent().validate();
		this.repaint();
	}
	private void loadPDF(File file) {
		int numPages, i;
		JLabel label = null;
		BufferedImage temp = null;
		BufferedImage scaled = null;
		
		try {
			doc = PDDocument.load(file);
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
				System.err.println("cann't load page: " +"i");
			}
			try {
				doc.close();
			} catch (IOException e1) {
				System.err.println("cann't close PDF file");
			}
		} catch (IOException e) {
			System.err.println("Cann't open file: " + file.getAbsolutePath());
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
		reload();
		this.setVisible(true);
		
	}

	@Override
	public void reload() {
		fileList.removeAll();
		fileList.setListData(this.getMasterModel().getdL().getMyList().toArray());
		loadDocpane();
	}

	@Override
	public HomeView getHomeView() {
		return ((PatientRecordView)this.getParent()).getHomeView();
	}

}

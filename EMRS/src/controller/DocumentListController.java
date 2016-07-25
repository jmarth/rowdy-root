package controller;

import javax.swing.AbstractListModel;

import models.Document;
import models.DocumentList;

@SuppressWarnings("serial")
public class DocumentListController extends AbstractListModel {

	private DocumentList myList;
	
	/*
	 * /**
	 * GUI container housing this object's list controller's JList Allows this
	 * controller to tell the view to repaint() if models in list change
	 *
	 * private MDIChild myWarehouseListView;
	 */
	
	public DocumentListController(DocumentList dl) {
		super();
		myList = dl;
	}
	
	/*
	 * public void setMyDocumentListView(MDIChild wlv) {
	 *	myDocumentListView = wlv;
	 *	}
	 */
	
	@Override
	public Document getElementAt(int index) {
		if (index > getSize()) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
		}
		return myList.getList().get(index);
	}
	
	@Override
	public int getSize() {
		return myList.getList().size();
	}
	
}

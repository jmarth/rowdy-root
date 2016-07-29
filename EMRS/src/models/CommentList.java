package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.CommentTableGateway;
import database.GatewayException;

public class CommentList {
	
	private List<Comment> myList;
	private CommentTableGateway gateway;
	private HashMap<Long, Comment> myIdMap;
	
	/**
	 * Construct a new AllergyList
	 */
	public CommentList(){
		myList = new ArrayList<Comment>();
		myIdMap = new HashMap<Long, Comment>();
	}
	
	/**
	 * Load records from DB into AllergyList
	 */
	public void loadFromGateway() {
		//fetch list of objects from the database
		try {
			List<Comment> comments = gateway.fetchComments();
			for(Comment tmpComments: comments){
				myIdMap.put(tmpComments.getId(), tmpComments);
				myList.add(tmpComments);
			}
		} catch (GatewayException e) {
			return;
		}
	}
	
	/**
	 * Returns ArrayList of Allergies in the AllergyList
	 * @return All Allergies in list
	 */
	public List<Comment> getAllergyList() {
		return myList;
	}
	
	public List<Comment> getCommentListForPatient(Patient p){
		List<Comment> tmpList = new ArrayList<Comment>();
		
		try {
			tmpList = gateway.fetchCommentsForPatient(p);
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tmpList;
	}

	public void setGateway(CommentTableGateway gateway) {
		this.gateway = gateway;
	}
	
	

}

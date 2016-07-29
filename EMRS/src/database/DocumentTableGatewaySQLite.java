package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Document;
import models.Patient;

public class DocumentTableGatewaySQLite implements DocumentTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public DocumentTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch all Documents for a Patient in the DB
	 * 
	 * @throws GatewayException 
	 */
	public List<Document> fetchPatientDocuments(Patient p) throws GatewayException{
		
		ArrayList<Document> documents = new ArrayList<Document>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			//fetch Documents
			st = conn.prepareStatement("select * from documents WHERE pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of allergies to return
			while(rs.next()) {
				
				Document tmpDoc = new Document(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("name"),
						rs.getString("path"),
						rs.getString("type")
						);
				documents.add(tmpDoc);
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			
			try {
				if(rs != null)
					rs.close();
				
				if(st != null)
					st.close();
				
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
		
		return documents;
	}
	
	/**
	 * Insert Document into Database
	 */
	public long insertDocument(Document d) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("insert INTO documents (pid,"
					+ " name,"
					+ " path,"
					+ " type) "
					+ " values ( ?, ?, ?, ? ) ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, d.getPid());
			st.setString(2, d.getName());
			st.setString(3, d.getPath());
			st.setString(4, d.getType());
	
			st.executeUpdate();
			
			//get the generated key
			rs = st.getGeneratedKeys();
			
			if(rs != null && rs.next()) {
			    newId = rs.getLong(1);			    
			} else {
				throw new GatewayException("Could not insert new record.");
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			//clean up
			try {
				if(st != null)
					st.close();
				
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
		
		return newId;
	}
	
	/**
	 * Remove Document from the DB
	 * 
	 * @param Document to remove
	 */
	public void removeDocument(Long did) throws GatewayException{
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM documents"
					+ " WHERE id = ? ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, did);

			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			//clean up
			try {
				if(st != null)
					st.close();
				
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
	}
}

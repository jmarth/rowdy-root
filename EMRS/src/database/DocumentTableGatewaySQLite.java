package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import models.Allergy;
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
}

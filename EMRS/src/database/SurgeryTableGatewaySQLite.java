package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Patient;
import models.Surgery;

public class SurgeryTableGatewaySQLite implements SurgeryTableGateway {
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public SurgeryTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch all Surgeries in the DB
	 * @throws GatewayException 
	 */
	public List<Surgery> fetchSurgeries() throws GatewayException{
		
		ArrayList<Surgery> surgeries = new ArrayList<Surgery>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch Surgeries
			st = conn.prepareStatement("select * from surgeries");
			rs = st.executeQuery();
			
			//add each to list of surgeries to return
			while(rs.next()) {
				Surgery tmpSurgery = new Surgery(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("title"),
						rs.getString("body")
					);
				surgeries.add(tmpSurgery);
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			//clean up
			try {
				if(rs != null)
					rs.close();
				
				if(st != null)
					st.close();
				
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
		
		return surgeries;
	}
	
	/**
	 * Fetch all Surgeries for a Patient in the DB
	 * @throws GatewayException 
	 */
	public List<Surgery> fetchSurgeriesForPatient(Patient p) throws GatewayException{
		
		ArrayList<Surgery> surgeries = new ArrayList<Surgery>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch Surgeries
			st = conn.prepareStatement("select * from surgeries WHERE pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of surgeries to return
			while(rs.next()) {
				Surgery tmpSurgery = new Surgery(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("title"),
						rs.getString("body")
					);
				surgeries.add(tmpSurgery);
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
		
		return surgeries;
	}
	
	/**
	 * Insert Allergy into Database
	 */
	public long insertSurgery(Surgery s) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("insert INTO surgeries (pid,"
					+ " title,"
					+ " body)"
					+ " values ( ?, ?, ?) ", PreparedStatement.RETURN_GENERATED_KEYS);
			st.setLong(1, s.getPid());
			st.setString(2, s.getTitle());
			st.setString(3, s.getBody());
	
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
}

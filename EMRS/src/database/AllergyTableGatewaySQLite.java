package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Allergy;
import models.Patient;

public class AllergyTableGatewaySQLite implements AllergyTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public AllergyTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch all Allergies in the DB
	 * @throws GatewayException 
	 */
	public List<Allergy> fetchAllergies() throws GatewayException{
		
		ArrayList<Allergy> allergies = new ArrayList<Allergy>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch Allergies
			st = conn.prepareStatement("select * from allergies");
			
			rs = st.executeQuery();
			
			//add each to list of allergies to return
			
			while(rs.next()) {
				Allergy tmpAllergy = new Allergy(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("allergy"),
						rs.getString("severity"),
						rs.getString("adverse_reaction")
						);
				allergies.add(tmpAllergy);
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
		
		return allergies;
	}
	
	/**
	 * Fetch all Allergies for a Patient in the DB
	 * 
	 * @throws GatewayException 
	 */
	public List<Allergy> fetchAllergiesForPatient(Patient p) throws GatewayException{
		
		ArrayList<Allergy> allergies = new ArrayList<Allergy>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			//fetch Allergies
			st = conn.prepareStatement("select * from allergies WHERE pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of allergies to return
			while(rs.next()) {
				
				Allergy tmpAllergy = new Allergy(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("allergy"),
						rs.getString("severity"),
						rs.getString("adverse_reaction")
						);
				allergies.add(tmpAllergy);
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
		
		return allergies;
	}
	
	/**
	 * Insert Allergy into Database
	 */
	public long insertAllergy(Allergy a) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("insert INTO allergies (pid,"
					+ " allergy,"
					+ " severity,"
					+ " adverse_reaction) "
					+ " values ( ?, ?, ?, ? ) ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, a.getPid());
			st.setString(2, a.getAllergy());
			st.setString(3, a.getSeverity());
			st.setString(4, a.getAdverseReaction());
	
			st.executeUpdate();
			
			//get the generated key
			rs = st.getGeneratedKeys();
			
			if(rs != null && rs.next()) {
			    newId = rs.getLong(1);
			    System.out.println("Allergy is ID: " + newId + "");
			    
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
	 * Update an Allergy in the DB
	 * 
	 * @param a Allergy to update
	 */
	public void updateAllergy(Allergy a) throws GatewayException{
		
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			conn.setAutoCommit(false);

			st = conn.prepareStatement("UPDATE allergies SET"
					+ " allergy = ?,"
					+ " severity = ?,"
					+ " adverse_reaction = ?"
					+ " WHERE id = ? ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setString(1, a.getAllergy());
			st.setString(2, a.getSeverity());
			st.setString(3, a.getAdverseReaction());
			st.setLong(4, a.getId());

			st.executeUpdate();
			
			conn.commit();
			
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
	
	/**
	 * Remove an Allergy from the DB
	 * 
	 * @param a Allergy to update
	 */
	public void removeAllergy(Long aid) throws GatewayException{
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM allergies"
					+ " WHERE id = ? ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, aid);

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

package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.DistanceVision;
import models.Patient;

public class DistanceVisionTableGatewaySQLite implements DistanceVisionTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public DistanceVisionTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch all visits from DB
	 * @return list of visits
	 * @throws GatewayException
	 */
	public List<DistanceVision> fetchDistanceVisions() throws GatewayException {
		
		ArrayList<DistanceVision> dvl = new ArrayList<DistanceVision>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select * from distance_visions");
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				DistanceVision dv = new DistanceVision(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getString("DVODSC"),
						rs.getString("DVOSSC"),
						rs.getString("DVODCC"),
						rs.getString("DVOSCC")
						);
				
				dvl.add(dv);
				
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
		
		return dvl;
	}
	
	/**
	 * Fetch visits from DB for patient
	 * @return list of visits for patient
	 * @throws GatewayException
	 */
	public List<DistanceVision> fetchDistanceVisionsForPatient(Patient p) throws GatewayException {
		
		ArrayList<DistanceVision> dvl = new ArrayList<DistanceVision>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from distance_visions where vid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of parts to return
			while(rs.next()) {
				DistanceVision dv = new DistanceVision(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getString("DVODSC"),
						rs.getString("DVOSSC"),
						rs.getString("DVODCC"),
						rs.getString("DVOSCC")
						);
				
				dvl.add(dv);
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
		
		return dvl;
	}
	
	/**
	 * Inserts visit into visits table
	 */
	public long insertDistanceVision(DistanceVision dv) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"insert INTO distance_visions "
					+ "(vid,"
					+ " DVODSC,"
					+ " DVOSSC,"
					+ " DVODCC,"
					+ " DVOSCC)"
					+ " values ( ?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, dv.getVid());
			st.setString(2, dv.getDVODSC());
			st.setString(3, dv.getDVOSSC());
			st.setString(4, dv.getDVODCC());
			st.setString(5, dv.getDVOSCC());

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

	@Override
	public void removeDistanceVision(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}


}

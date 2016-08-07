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
import models.VisionExam;

public class VisionTableGatewaySQLite implements VisionTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public VisionTableGatewaySQLite() throws GatewayException, IOException {

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
	public List<VisionExam> fetchVisionExams() throws GatewayException {
		
		ArrayList<VisionExam> visits = new ArrayList<VisionExam>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
//			System.out.print("getting info");
			
			st = conn.prepareStatement("select * from visits");
			rs = st.executeQuery();
			
//			System.out.print("\ninfo loaded");
			
			//add each to list of parts to return
			while(rs.next()) {
				
				//System.out.print("\ncreating visit object");
				
				VisionExam ve = new VisionExam(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getString("dVODsc"),
						rs.getString("dVOSsc"),
						rs.getString("dVODcc"),
						rs.getString("dVOScc"),
						rs.getFloat("aRscODSphere"),
						rs.getFloat("aRscODCyl"),
						rs.getFloat("aRscODAxis"),
						rs.getFloat("aRscOSSphere"),
						rs.getFloat("aRscOSCyl"),
						rs.getFloat("aRscOSAxis"),
						rs.getFloat("aRccODSphere"),
						rs.getFloat("aRccODCyl"),
						rs.getFloat("aRccODAxis"),
						rs.getFloat("aRccOSSphere"),
						rs.getFloat("aRccOSCyl"),
						rs.getFloat("aRccOSAxis"),
						rs.getFloat("glsRxODSphere"),
						rs.getFloat("glsRxODCyl"),
						rs.getFloat("glsRxODAxis"),
						rs.getFloat("glsRxODAdd"),
						rs.getFloat("glsRxOSSphere"),
						rs.getFloat("glsRxOSCyl"),
						rs.getFloat("glsRxOSAxis"),
						rs.getFloat("glsRxOSAdd"),
						rs.getString("glsRxNotes"),
						rs.getString("dateCreated")
						);
				
//				System.out.print("\nvisit object created");
				
				visits.add(ve);
				
//				System.out.print("\nvisit object added");
				
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
		
		return visits;
	}
	
	/**
	 * Fetch visits from DB for patient
	 * @return list of visits for patient
	 * @throws GatewayException
	 */
	public List<VisionExam> fetchVisionExamsForPatient(Patient p) throws GatewayException {
		
		ArrayList<VisionExam> visits = new ArrayList<VisionExam>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from visits where pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of parts to return
			while(rs.next()) {
				VisionExam ve = new VisionExam(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getString("dVODsc"),
						rs.getString("dVOSsc"),
						rs.getString("dVODcc"),
						rs.getString("dVOScc"),
						rs.getFloat("aRscODSphere"),
						rs.getFloat("aRscODCyl"),
						rs.getFloat("aRscODAxis"),
						rs.getFloat("aRscOSSphere"),
						rs.getFloat("aRscOSCyl"),
						rs.getFloat("aRscOSAxis"),
						rs.getFloat("aRccODSphere"),
						rs.getFloat("aRccODCyl"),
						rs.getFloat("aRccODAxis"),
						rs.getFloat("aRccOSSphere"),
						rs.getFloat("aRccOSCyl"),
						rs.getFloat("aRccOSAxis"),
						rs.getFloat("glsRxODSphere"),
						rs.getFloat("glsRxODCyl"),
						rs.getFloat("glsRxODAxis"),
						rs.getFloat("glsRxODAdd"),
						rs.getFloat("glsRxOSSphere"),
						rs.getFloat("glsRxOSCyl"),
						rs.getFloat("glsRxOSAxis"),
						rs.getFloat("glsRxOSAdd"),
						rs.getString("glsRxNotes"),
						rs.getString("dateCreated")
						);
				
				visits.add(ve);
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
		
		return visits;
	}
	
	/**
	 * Inserts visit into visits table
	 */
	public long insertVisionExam(VisionExam ve) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"insert INTO visits"
					+ "(vid,"
							
					+ " dVODsc,"
					+ " dVOSsc,"
					+ " dVODcc,"
					+ " dVOScc,"
					
					+ " aRscODSphere,"
					+ " aRscODCyl,"
					+ " aRscODAxis,"
					+ " aRscOSSphere,"
					+ " aRscOSCyl,"
					+ " aRscOSAxis,"
					
					+ " aRccODSphere,"
					+ " aRccODCyl,"
					+ " aRccODAxis,"
					+ " aRccOSSphere,"
					+ " aRccOSCyl,"
					+ " aRccOSAxis,"
					
					+ " glsRxODSphere,"
					+ " glsRxODCyl,"
					+ " glsRxODAxis,"
					+ " glsRxODAdd,"
					
					+ " glsRxOSSphere,"
					+ " glsRxOSCyl,"
					+ " glsRxOSAxis,"
					+ " glsRxOSAdd,"
					
					+ " glsRxNotes)"
					+ " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, ve.getVid());
			
			st.setString(2, ve.getdVODsc());
			st.setString(3, ve.getdVOSsc());
			st.setString(4, ve.getdVODcc());
			st.setString(5, ve.getdVOScc());
			
			st.setFloat(6, ve.getaRscODSphere());
			st.setFloat(7, ve.getaRscODCyl());
			st.setFloat(8, ve.getaRscODAxis());
			st.setFloat(9, ve.getaRscOSSphere());
			st.setFloat(10, ve.getaRscOSCyl());
			st.setFloat(11, ve.getaRscOSAxis());
			
			st.setFloat(12, ve.getaRccODSphere());
			st.setFloat(13, ve.getaRccODCyl());
			st.setFloat(14, ve.getaRccODAxis());
			st.setFloat(15, ve.getaRccOSSphere());
			st.setFloat(16, ve.getaRccOSCyl());
			st.setFloat(17, ve.getaRccOSAxis());
			
			st.setFloat(18, ve.getGlsRxODSphere());
			st.setFloat(19, ve.getGlsRxODCyl());
			st.setFloat(20, ve.getGlsRxODAxis());
			st.setFloat(21, ve.getGlsRxODAdd());
			
			st.setFloat(22, ve.getGlsRxOSSphere());
			st.setFloat(23, ve.getGlsRxOSCyl());
			st.setFloat(24, ve.getGlsRxOSAxis());
			st.setFloat(25, ve.getGlsRxOSAdd());
			
			st.setString(26, ve.getGlsRxNotes());

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
	public void removeVisionExam(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}


}

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
import models.SlitLampExam;

public class SLETableGatewaySQLite implements SLETableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public SLETableGatewaySQLite() throws GatewayException, IOException {

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
	public List<SlitLampExam> fetchSlitLampExams() throws GatewayException {
		
		ArrayList<SlitLampExam> SLEs = new ArrayList<SlitLampExam>();
		
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
				
//				System.out.print("\ncreating visit object");
				
				SlitLampExam v = new SlitLampExam(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("ODPupilsAb"),
						rs.getString("ODPupils"),
						rs.getBoolean("OSPupilsAb"),
						rs.getString("OSPupils"),
						rs.getBoolean("ODACAb"),
						rs.getString("ODAC"),
						rs.getBoolean("OSACAb"),
						rs.getString("OSAC"),
						rs.getBoolean("ODPseudoPh"),
						rs.getBoolean("OSPseudoPh"),
						rs.getBoolean("ODPCO"),
						rs.getBoolean("OSPCO"),
						rs.getString("ODNS"),
						rs.getString("ODNSNotes"),
						rs.getString("OSNS"),
						rs.getString("OSNSNotes"),
						rs.getString("ODCortical"),
						rs.getString("ODCorticalNotes"),
						rs.getString("OSCortical"),
						rs.getString("OSCorticalNotes"),
						rs.getString("ODPSC"),
						rs.getString("ODPSCNotes"),
						rs.getString("OSPSC"),
						rs.getString("OSPSCNotes"),
						rs.getString("dateCreated")
						);
				
//				System.out.print("\nvisit object created");
				
				SLEs.add(v);
				
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
		
		return SLEs;
	}
	
	/**
	 * Fetch visits from DB for patient
	 * @return list of visits for patient
	 * @throws GatewayException
	 */						  
	public List<SlitLampExam> fetchSlitLampExamsForPatient(Patient p) throws GatewayException {
		
		ArrayList<SlitLampExam> SLEs = new ArrayList<SlitLampExam>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from visits where pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			
			
			//add each to list of parts to return
			while(rs.next()) {
				SlitLampExam v = new SlitLampExam(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("ODPupilsAb"),
						rs.getString("ODPupils"),
						rs.getBoolean("OSPupilsAb"),
						rs.getString("OSPupils"),
						rs.getBoolean("ODACAb"),
						rs.getString("ODAC"),
						rs.getBoolean("OSACAb"),
						rs.getString("OSAC"),
						rs.getBoolean("ODPseudoPh"),
						rs.getBoolean("OSPseudoPh"),
						rs.getBoolean("ODPCO"),
						rs.getBoolean("OSPCO"),
						rs.getString("ODNS"),
						rs.getString("ODNSNotes"),
						rs.getString("OSNS"),
						rs.getString("OSNSNotes"),
						rs.getString("ODCortical"),
						rs.getString("ODCorticalNotes"),
						rs.getString("OSCortical"),
						rs.getString("OSCorticalNotes"),
						rs.getString("ODPSC"),
						rs.getString("ODPSCNotes"),
						rs.getString("OSPSC"),
						rs.getString("OSPSCNotes"),
						rs.getString("dateCreated")
						);
				
				SLEs.add(v);
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
		
		return SLEs;
	}
	
	/**
	 * Inserts visit into visits table
	 */
	public long insertSlitLampExams(SlitLampExam sle) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"insert INTO visits"
					+ "(vid,"
							
					+ " ODPupilsAb,"
					+ " ODPupils,"
					+ " OSPupilsAb,"
					+ " OSPupils,"
					
					+ " ODACAb,"
					+ " ODAC,"
					+ " OSACAb,"
					+ " OSAC,"
					
					+ " ODPseudoPh,"
					+ " OSPseudoPh,"
					+ " ODPCO,"
					+ " OSPCO,"
					
					+ " ODNS,"
					+ " ODNSNotes,"
					+ " OSNS,"
					+ " OSNSNotes,"
					
					+ " ODCortical,"
					+ " ODCorticalNotes,"
					+ " OSCortical,"
					+ " OSCorticalNotes,"
					
					+ " ODPSC,"
					+ " ODPSCNotes,"
					+ " OSPSC,"
					+ " OSPSCNotes) "
					
					+ " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, sle.getVid());
			
			st.setBoolean(2, sle.isODPupilsAb());
			st.setString(3, sle.getODPupils());
			st.setBoolean(4, sle.isOSPupilsAb());
			st.setString(5, sle.getOSPupils());
			
			st.setBoolean(6, sle.isODACAb());
			st.setString(7, sle.getODAC());
			st.setBoolean(8, sle.isOSACAb());
			st.setString(9, sle.getOSAC());
			
			st.setBoolean(10, sle.isODPseudoPh());
			st.setBoolean(11, sle.isOSPseudoPh());
			st.setBoolean(12, sle.isODPCO());
			st.setBoolean(13, sle.isOSPCO());
			
			st.setString(14, sle.getODNS());
			st.setString(15, sle.getODNSNotes());
			st.setString(16, sle.getOSNS());
			st.setString(17, sle.getOSNSNotes());
			
			st.setString(18, sle.getODCortical());
			st.setString(19, sle.getODCorticalNotes());
			st.setString(20, sle.getOSCortical());
			st.setString(21, sle.getOSCorticalNotes());
			
			st.setString(22, sle.getODPSC());
			st.setString(23, sle.getODPSCNotes());
			st.setString(24, sle.getOSPSC());
			st.setString(25, sle.getOSPSCNotes());
	
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
	public void removeSlitLampExam(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}

}

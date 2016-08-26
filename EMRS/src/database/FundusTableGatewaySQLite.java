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
import models.FundusExam;

public class FundusTableGatewaySQLite implements FundusTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public FundusTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch all fundus exams from DB
	 * @return list of fundus exams
	 * @throws GatewayException
	 */
	public List<FundusExam> fetchFundusExams() throws GatewayException {
		
		ArrayList<FundusExam> fundusExams = new ArrayList<FundusExam>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			System.out.print("getting info");
			
			st = conn.prepareStatement("select * from fundus_exams");
			rs = st.executeQuery();
			
			System.out.print("\ninfo loaded");
			
			//add each to list of parts to return
			while(rs.next()) {
				
				//System.out.print("\ncreating FundusExam object");
				

				FundusExam fe = new FundusExam(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("isDialated"),
						rs.getString("dialNotes"),
						rs.getBoolean("isCDODAb"),
						rs.getFloat("CDOD"),
						rs.getString("CDODNotes"),
						rs.getBoolean("isCDOSAb"),
						rs.getFloat("CDOS"),
						rs.getString("CDOSNotes"),
						rs.getBoolean("isMaculaODAb"),
						rs.getString("MaculaODNotes"),
						rs.getBoolean("isMaculaOSAb"),
						rs.getString("MaculaOSNotes"),
						rs.getBoolean("isRetinaODAb"),
						rs.getString("RetinaODNotes"),
						rs.getBoolean("isRetinaOSAb"),
						rs.getString("RetinaOSNotes")
						);
				
				//System.out.print("\nFundusExamobject created");
				
				fundusExams.add(fe);
				
				//System.out.print("\nFundusExam object added");
				
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
		
		return fundusExams;
	}
	
	/**
	 * Fetch Fundus Exams from DB for patient
	 * @return list of Fundus Exams for patient
	 * @throws GatewayException
	 */
	public List<FundusExam> fetchFundusExamsForPatient(Patient p) throws GatewayException {
		
		ArrayList<FundusExam> fundusExams = new ArrayList<FundusExam>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from fundus_exams where pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of parts to return
			while(rs.next()) {
				FundusExam fe = new FundusExam(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("isDialated"),
						rs.getString("dialNotes"),
						rs.getBoolean("isCDODAb"),
						rs.getFloat("CDOD"),
						rs.getString("CDODNotes"),
						rs.getBoolean("isCDOSAb"),
						rs.getFloat("CDOS"),
						rs.getString("CDOSNotes"),
						rs.getBoolean("isMaculaODAb"),
						rs.getString("MaculaODNotes"),
						rs.getBoolean("isMaculaOSAb"),
						rs.getString("MaculaOSNotes"),
						rs.getBoolean("isRetinaODAb"),
						rs.getString("RetinaODNotes"),
						rs.getBoolean("isRetinaOSAb"),
						rs.getString("RetinaOSNotes")
						);
				
				fundusExams.add(fe);
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
		
		return fundusExams;
	}
	
	/**
	 * Inserts Fundus Exam into fundus table
	 */
	public long insertFundusExam(FundusExam fe) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"insert INTO fundus_exams"
					+ " (vid,"
					+ " isDialated,"
					+ " dialNotes,"
					+ " isCDODAb,"
					+ " CDOD,"
					+ " CDODNotes,"
					+ " isCDOSAb,"
					+ " CDOS,"
					+ " CDOSNotes,"
					+ " isMaculaODAb,"
					+ " MaculaODNotes,"
					+ " isMaculaOSAb,"
					+ " MaculaOSNotes,"
					+ " isRetinaODAb,"
					+ " RetinaODNotes,"
					+ " isRetinaOSAb,"
					+ " RetinaOSNotes)"
					+ " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
	
			st.setLong(1, fe.getVid());
			st.setBoolean(2, fe.isDialated());
			st.setString(3, fe.getDial_Notes());
			
			st.setBoolean(4, fe.isCDODAb());
			st.setFloat(5, fe.getCDOD());
			st.setString(6, fe.getCDODNotes());
			
			st.setBoolean(7, fe.isCDOSAb());
			st.setFloat(8, fe.getCDOS());
			st.setString(9, fe.getCDOSNotes());
			
			st.setBoolean(10, fe.isMaculaODAb());
			st.setString(11, fe.getMaculaODNotes());
			st.setBoolean(12, fe.isMaculaOSAb());
			st.setString(13, fe.getMaculaOSNotes());
			
			st.setBoolean(14, fe.isRetinaODAb());
			st.setString(15, fe.getRetinaODNotes());
			st.setBoolean(16, fe.isRetinaOSAb());
			st.setString(17, fe.getRetinaOSNotes());
	
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
	public void removeFundusExam(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}
}

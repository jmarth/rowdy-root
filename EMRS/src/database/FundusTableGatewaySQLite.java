package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.FundusExam;

public class FundusTableGatewaySQLite implements FundusTableGateway {

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
	 * Fetch FundusExam from DB for Visit
	 * @return list of FundusExams for Visit
	 * @throws GatewayException
	 */
	public FundusExam fetchFundusExamForVisit(long vid) throws GatewayException {
				
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch FundusExam
			st = conn.prepareStatement("SELECT * FROM fundus_exams WHERE vid=?");
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			// rs has one
			if (rs.next() )
			{
				FundusExam fe = new FundusExam(
						rs.getLong("id"),
						rs.getLong("vid"),
						
						rs.getInt("isDialated"),
						rs.getString("dialNotes"),
						
						rs.getInt("isCDODAb"),
						rs.getString("CDOD"),
						rs.getString("CDODNotes"),
						
						rs.getInt("isCDOSAb"),
						rs.getString("CDOS"),
						rs.getString("CDOSNotes"),
						
						rs.getInt("isMaculaODAb"),
						rs.getString("MaculaODNotes"),
						
						rs.getInt("isMaculaOSAb"),
						rs.getString("MaculaOSNotes"),
						
						rs.getInt("isRetinaODAb"),
						rs.getString("RetinaODNotes"),
						
						rs.getInt("isRetinaOSAb"),
						rs.getString("RetinaOSNotes")
						);
				
				return fe;
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
		
		return null;
	}
	
	/**
	 * Inserts FundusExam into fundus_exams table
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
			
			st.setInt(2, fe.getDialated());
			st.setString(3, fe.getDialNotes());
			
			st.setInt(4, fe.getCDODAb());
			st.setString(5, fe.getCDOD());
			st.setString(6, fe.getCDODNotes());
			
			st.setInt(7, fe.getCDOSAb());
			st.setString(8, fe.getCDOS());
			st.setString(9, fe.getCDOSNotes());
			
			st.setInt(10, fe.getMaculaODAb());
			st.setString(11, fe.getMaculaODNotes());
			
			st.setInt(12, fe.getMaculaOSAb());
			st.setString(13, fe.getMaculaOSNotes());
			
			st.setInt(14, fe.getRetinaODAb());
			st.setString(15, fe.getRetinaODNotes());
			
			st.setInt(16, fe.getRetinaOSAb());
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
	public void updateFundusExam(FundusExam fe) throws GatewayException {
		PreparedStatement st = null;
		
		try {
			conn.setAutoCommit(false);
			st = conn.prepareStatement(
					"UPDATE fundus_exams SET"
					+ " vid = ?,"
					+ " isDialated = ?,"
					+ " dialNotes = ?,"
					
					+ " isCDODAb = ?,"
					+ " CDOD = ?,"
					+ " CDODNotes = ?,"
					
					+ " isCDOSAb = ?,"
					+ " CDOS = ?,"
					+ " CDOSNotes = ?,"
					
					+ " isMaculaODAb = ?,"
					+ " MaculaODNotes = ?,"
					
					+ " isMaculaOSAb = ?,"
					+ " MaculaOSNotes = ?,"
					
					+ " isRetinaODAb = ?,"
					+ " RetinaODNotes = ?,"
					
					+ " isRetinaOSAb = ?,"
					+ " RetinaOSNotes = ?"
					+ " WHERE id = ?");
	
			st.setLong(1, fe.getVid());
			
			st.setInt(2, fe.getDialated());
			st.setString(3, fe.getDialNotes());
			
			st.setInt(4, fe.getCDODAb());
			st.setString(5, fe.getCDOD());
			st.setString(6, fe.getCDODNotes());
			
			st.setInt(7, fe.getCDOSAb());
			st.setString(8, fe.getCDOS());
			st.setString(9, fe.getCDOSNotes());
			
			st.setInt(10, fe.getMaculaODAb());
			st.setString(11, fe.getMaculaODNotes());
			
			st.setInt(12, fe.getMaculaOSAb());
			st.setString(13, fe.getMaculaOSNotes());
			
			st.setInt(14, fe.getRetinaODAb());
			st.setString(15, fe.getRetinaODNotes());
			
			st.setInt(16, fe.getRetinaOSAb());
			st.setString(17, fe.getRetinaOSNotes());
			
			st.setLong(18, fe.getId());
	
			st.executeUpdate();
			
			conn.commit();
			conn.setAutoCommit(true);
			
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
	
	@Override
	public void removeFundusExam(Long vid) throws GatewayException {
		
	}
	
	public void close() {
		
	}

}

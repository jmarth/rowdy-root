package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
	 * Fetch Fundus Exams from DB for patient
	 * @return list of Fundus Exams for patient
	 * @throws GatewayException
	 */
	public FundusExam fetchFundusExamForVisit(long vid) throws GatewayException {
				
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from fundus_exams where pid=?");
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			//add each to list of parts to return
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
	public void removeFundusExam(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Object> fetchFundusExamsForVisit(long id) throws GatewayException {
ArrayList<Object> row = new ArrayList<Object>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from fundus_exams where vid=?");
			st.setLong(1, id);
			
			rs = st.executeQuery();
			
			//get metadata
		    ResultSetMetaData meta = null;
		    meta = rs.getMetaData();
		    
		    int colCount = meta.getColumnCount();
		    //System.out.println("====fundus======" + colCount);
			
			while(rs.next()) {
				for (int i = 3; i <= colCount; i++) {
					row.add(rs.getObject(i));
//					System.out.println("column #"+ i + " : " + rs.getObject(i));
				}
			}
			//System.out.println("\n****************\n fundus ROW:"+row.toString());
			
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
		
		return row;
	}
}

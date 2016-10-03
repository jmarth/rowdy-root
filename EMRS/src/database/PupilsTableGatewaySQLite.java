package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Pupils;

public class PupilsTableGatewaySQLite implements PupilsTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public PupilsTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch visits from DB for patient
	 * @return list of visits for patient
	 * @throws GatewayException
	 */
	public Pupils fetchPupilsForVisit(long vid) throws GatewayException {
		
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from pupils where vid=?");
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				Pupils pu = new Pupils(
						rs.getLong("id"),
						rs.getLong("vid"),
						
						rs.getInt("isBothPupilsNormal"),
						rs.getString("bothShape"),
						rs.getString("bothDiameter"),
						rs.getInt("isBothRAPD"),
						rs.getInt("isBothSynechia"),
						
						rs.getInt("isRightPupilNormal"),
						rs.getString("rightShape"),
						rs.getString("rightDiameter"),
						rs.getInt("isRightRAPD"),
						rs.getInt("isRightSynechia"),
						
						rs.getInt("isLeftPupilNormal"),
						rs.getString("leftShape"),
						rs.getString("leftDiameter"),
						rs.getInt("isLeftRAPD"),
						rs.getInt("isLeftSynechia")
						);
			return pu;
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
	 * Inserts visit into visits table
	 */
	public long insertPupils(Pupils p) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"insert INTO pupils "
					+ "(vid,"
							
					+ " isBothPupilsNormal,"
					+ " bothShape,"
					+ " bothDiameter,"
					+ " isBothRAPD,"
					+ " isBothSynechia,"
					
					+ " isRightPupilNormal,"
					+ " rightShape,"
					+ " rightDiameter,"
					+ " isRightRAPD,"
					+ " isRightSynechia,"
					
					+ " isLeftPupilNormal,"
					+ " leftShape,"
					+ " leftDiameter,"
					+ " isLeftRAPD,"
					+ " isLeftSynechia)"
					+ " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, p.getVid());
			
			st.setInt(2, p.isBothPupilsNormal());
			st.setString(3, p.getBothShape());
			st.setString(4, p.getBothDiameter());
			st.setInt(5, p.isBothRAPD());
			st.setInt(6, p.isBothSynechia());
			
			st.setInt(7, p.isRightPupilNormal());
			st.setString(8, p.getRightShape());
			st.setString(9, p.getRightDiameter());
			st.setInt(10, p.isRightRAPD());
			st.setInt(11, p.isRightSynechia());
			
			st.setInt(12, p.isLeftPupilNormal());
			st.setString(13, p.getLeftShape());
			st.setString(14, p.getLeftDiameter());
			st.setInt(15, p.isLeftRAPD());
			st.setInt(16, p.isLeftSynechia());
			
			st.executeUpdate();
			
			//get the generated key
			rs = st.getGeneratedKeys();
//			System.out.println("GeneratedKeys: " + rs.getCl);
			
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
	public ArrayList<Object> fetchPupilsColsForVisit(long id) throws GatewayException {

	    ArrayList<Object> row = new ArrayList<Object>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from pupils where vid=?");
			st.setLong(1, id);
			
			rs = st.executeQuery();
			//get metadata
		    ResultSetMetaData meta = null;
		    meta = rs.getMetaData();
		    
		    int colCount = meta.getColumnCount();
//		    System.out.println("====PUPILS======" + colCount);
//		    System.out.println("META: " + colCount);
			
			while(rs.next()) {
				for (int i = 3; i <= colCount; i++) {
					row.add(rs.getObject(i));
//					System.out.println("column #"+ i + " : " + rs.getObject(i));
				}
			}
//			System.out.println("\n****************\n PUPILS ROW:"+row.toString());
			
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

	@Override
	public void removePupils(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}


}

package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Pupils;
import models.Patient;

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
	 * Fetch all visits from DB
	 * @return list of visits
	 * @throws GatewayException
	 */
	public List<Pupils> fetchPupils() throws GatewayException {
		
		ArrayList<Pupils> pl = new ArrayList<Pupils>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select * from pupils");
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				Pupils p = new Pupils(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("areBothPupilsNormal"),
						rs.getString("bothShape`"),
						rs.getInt("bothDiameter"),
						rs.getBoolean("isBothRAPD`"),
						rs.getBoolean("isBothSynechia`"),
						rs.getBoolean("isRightPupilNormal"),
						rs.getString("rightShape`"),
						rs.getInt("rightDiameter"),
						rs.getBoolean("isRightRAPD`"),
						rs.getBoolean("isRightSynechia`"),
						rs.getBoolean("isLeftPupilNormal"),
						rs.getString("leftShape`"),
						rs.getInt("leftDiameter"),
						rs.getBoolean("isLeftRAPD`"),
						rs.getBoolean("isLeftSynechia")
						);
				
				pl.add(p);
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
		
		return pl;
	}
	
	/**
	 * Fetch visits from DB for patient
	 * @return list of visits for patient
	 * @throws GatewayException
	 */
	public List<Pupils> fetchPupilsForPatient(Patient p) throws GatewayException {
		
		ArrayList<Pupils> pl = new ArrayList<Pupils>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from pupils where vid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				Pupils pu = new Pupils(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("areBothPupilsNormal"),
						rs.getString("bothShape`"),
						rs.getInt("bothDiameter"),
						rs.getBoolean("isBothRAPD`"),
						rs.getBoolean("isBothSynechia`"),
						rs.getBoolean("isRightPupilNormal"),
						rs.getString("rightShape`"),
						rs.getInt("rightDiameter"),
						rs.getBoolean("isRightRAPD`"),
						rs.getBoolean("isRightSynechia`"),
						rs.getBoolean("isLeftPupilNormal"),
						rs.getString("leftShape`"),
						rs.getInt("leftDiameter"),
						rs.getBoolean("isLeftRAPD`"),
						rs.getBoolean("isLeftSynechia")
						);
				
				pl.add(pu);
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
		
		return pl;
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
			
			st.setBoolean(2, p.isBothPupilsNormal());
			st.setString(3, p.getBothShape());
			st.setInt(4, p.getBothDiameter());
			st.setBoolean(5, p.isBothRAPD());
			st.setBoolean(6, p.isBothSynechia());
			
			st.setBoolean(7, p.isRightPupilNormal());
			st.setString(8, p.getRightShape());
			st.setInt(9, p.getRightDiameter());
			st.setBoolean(10, p.isRightRAPD());
			st.setBoolean(11, p.isRightSynechia());
			
			st.setBoolean(12, p.isLeftPupilNormal());
			st.setString(13, p.getLeftShape());
			st.setInt(14, p.getLeftDiameter());
			st.setBoolean(15, p.isLeftRAPD());
			st.setBoolean(16, p.isLeftSynechia());
			
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
	public void removePupils(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}


}

package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.DistanceVision;

public class DistanceVisionTableGatewaySQLite implements DistanceVisionTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: Creates database connection
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
	 * Fetch DistanceVision from DB for Visit
	 * @return DistanceVision for Visit
	 * @throws GatewayException
	 */
	public DistanceVision fetchDistanceVisionForVisit(long vid) throws GatewayException {
		
		DistanceVision dv = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			//fetch DistanceVision for Visit
			st = conn.prepareStatement("SELECT * FROM distance_visions WHERE vid=?");
			
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			// ResultSet contains only one.
			if(rs.next()){
				
					dv = new DistanceVision(
					rs.getLong("id"),
					rs.getLong("vid"),
					rs.getString("DVODSC"),
					rs.getString("DVOSSC"),
					rs.getString("DVODCC"),
					rs.getString("DVOSCC")
					);
					
				return dv;
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
	 * Inserts DistanceVision into distance_visions table
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

	@Override
	public void updateDistanceVision(DistanceVision dv) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
}

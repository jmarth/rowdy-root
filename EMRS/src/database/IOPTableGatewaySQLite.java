package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.IOPMeasurement;

public class IOPTableGatewaySQLite implements IOPTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public IOPTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			System.err.println("From IOP TG, no db connection");
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch all IOPs from DB
	 * @return list of all IOPs
	 * @throws GatewayException
	 * 
	 */
	@Deprecated
	public List<IOPMeasurement> fetchIOPMeasurements() throws GatewayException {
		
		ArrayList<IOPMeasurement> iops = new ArrayList<IOPMeasurement>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select * from iops");
			rs = st.executeQuery();
			
			
			while(rs.next()) {
				
				IOPMeasurement v = new IOPMeasurement(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getString("ODValue"),
						rs.getString("ODType"),
						rs.getString("ODNotes"),
						rs.getString("OSValue"),
						rs.getString("OSType"),
						rs.getString("OSNotes"),
						rs.getString("dateCreated")
						);
				
				iops.add(v);
				
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
		
		return iops;
	}
	
	/**
	 * Fetch IOPs from DB for Visit
	 * @return list of IOPs for a Visit
	 * @throws GatewayException
	 */
	public List<IOPMeasurement> fetchIOPMeasurementsForVisit(long vid) throws GatewayException {
		
		List<IOPMeasurement> iops = new ArrayList<IOPMeasurement>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("SELECT * FROM iops WHERE vid=?");
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			//add each to list of parts to return
			while(rs.next()) {
				IOPMeasurement iop = new IOPMeasurement(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getString("ODValue"),
						rs.getString("ODType"),
						rs.getString("ODNotes"),
						rs.getString("OSValue"),
						rs.getString("OSType"),
						rs.getString("OSNotes"),
						rs.getString("dateCreated")
						);
				iops.add(iop);
				
			}
			
			return iops;

		} catch (SQLException e) {
			
			//throw new GatewayException(e.getMessage());
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
	 * Inserts iop into iops table
	 */
	public long insertIOPMeasurement(IOPMeasurement iop) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
				
		try {
			st = conn.prepareStatement(
					"insert INTO iops"
					+ "(vid,"
					+ " ODValue,"
					+ " ODType,"
					+ " ODNotes,"
					+ " OSValue,"
					+ " OSType,"
					+ " OSNotes )"
					+ " values ( ?, ?, ?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, iop.getVid());
			st.setString(2, iop.getODValue());
			st.setString(3, iop.getODType());
			st.setString(4, iop.getODNotes());
			st.setString(5, iop.getOSValue());
			st.setString(6, iop.getOSType());
			st.setString(7, iop.getOSNotes());
	
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
	public long updateIOPMeasurements(IOPMeasurement v) throws GatewayException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void removeIOPMeasurements(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IOPMeasurement fetchIOPMeasurementForVisit(long vid) throws GatewayException {
		// TODO Auto-generated method stub
		return null;
	}
}

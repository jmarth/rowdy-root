package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.IOP;

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
	 * Fetch IOPs from DB for Visit
	 * @return list of IOPs for a Visit
	 * @throws GatewayException
	 */
	public List<IOP> fetchIOPsForVisit(long vid) throws GatewayException {
		
		List<IOP> iops = new ArrayList<IOP>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("SELECT * FROM iopms WHERE vid=?");
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			//add each to list of parts to return
			while(rs.next()) {
				IOP iop = new IOP(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getString("which"),
						rs.getString("type"),
						rs.getString("measurement"),
						rs.getString("notes"),
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
	public long insertIOP(IOP iop) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
				
		try {
			st = conn.prepareStatement(
					"insert INTO iopms"
					+ "(vid,"
					+ " which,"
					+ " type,"
					+ " measurment,"
					+ " notes )"
					+ " values ( ?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, iop.getVid());
			st.setString(2, iop.getWhich());
			st.setString(3, iop.getType());
			st.setString(4, iop.getMeasurement());
			st.setString(5, iop.getNotes());
	
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
	public long updateIOP(IOP v) throws GatewayException {
		return 0;
	}
	
	@Override
	public void removeIOP(Long vid) throws GatewayException {
		
	}

	public void close() {
		
	}
}

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

import models.IOPList;
import models.IOPMeasurement;
import models.Patient;

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
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch all iops from DB
	 * @return list of iops
	 * @throws GatewayException
	 */
	public List<IOPMeasurement> fetchIOPMeasurements() throws GatewayException {
		
		ArrayList<IOPMeasurement> iops = new ArrayList<IOPMeasurement>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
//			System.out.print("getting info");
			
			st = conn.prepareStatement("select * from iops");
			rs = st.executeQuery();
			
//			System.out.print("\ninfo loaded");
			
			//add each to list of parts to return
			while(rs.next()) {
				
//				System.out.print("\ncreating iop object");
				
				
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
				
//				System.out.print("\niop object created");
				
				iops.add(v);
				
//				System.out.print("\niop object added");
				
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
	 * Fetch iops from DB for patient
	 * @return list of iops for patient
	 * @throws GatewayException
	 */
	public List<IOPMeasurement> fetchIOPMeasurementsForVisit(long vid) throws GatewayException {
		
		List<IOPMeasurement> iops = new ArrayList<IOPMeasurement>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from iops where vid=?");
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
	public void removeIOPMeasurements(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Object> fetchIOPColsForVisit(long id) throws GatewayException {

	    ArrayList<Object> row = new ArrayList<Object>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from iops where vid=?");
			st.setLong(1, id);
			
			rs = st.executeQuery();
			
			//get metadata
		    ResultSetMetaData meta = null;
		    meta = rs.getMetaData();
		    
		    int colCount = meta.getColumnCount();
//		    System.out.println("====iop======" + colCount);
			
			while(rs.next()) {
				for (int i = 3; i <= colCount; i++) {
					row.add(rs.getObject(i));
//					System.out.println("column #"+ i + " : " + rs.getObject(i));
				}
			}
//			System.out.println("\n****************\n iop ROW:"+row.toString());
			
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
	public List<IOPMeasurement> fetchIOPMeasurementsForPatient(Patient p) throws GatewayException {
		// TODO Auto-generated method stub
		return null;
	}


}

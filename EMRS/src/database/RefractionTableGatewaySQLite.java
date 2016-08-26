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

import models.Refraction;
import models.Patient;

public class RefractionTableGatewaySQLite implements RefractionTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public RefractionTableGatewaySQLite() throws GatewayException, IOException {

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
	public List<Refraction> fetchRefractions() throws GatewayException {
		
		ArrayList<Refraction> rl = new ArrayList<Refraction>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select * from refractions");
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				Refraction r = new Refraction(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("isManifest"),
						rs.getFloat("SC_OD_Sphere"),
						rs.getFloat("SC_OD_Cyl"),
						rs.getFloat("SC_OD_Axis"),
						rs.getFloat("SC_OS_Sphere"),
						rs.getFloat("SC_OS_Cyl"),
						rs.getFloat("SC_OS_Axis"),
						rs.getFloat("CC_OD_Sphere"),
						rs.getFloat("CC_OD_Cyl"),
						rs.getFloat("CC_OD_Axis"),
						rs.getFloat("CC_OS_Sphere"),
						rs.getFloat("CC_OS_Cyl"),
						rs.getFloat("CC_OS_Axis")
						);
				
				rl.add(r);
				
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
		
		return rl;
	}
	
	/**
	 * Fetch visits from DB for patient
	 * @return list of visits for patient
	 * @throws GatewayException
	 */
	public List<Refraction> fetchRefractionsForPatient(Patient p) throws GatewayException {
		
		ArrayList<Refraction> rl = new ArrayList<Refraction>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from refractions where vid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				Refraction glsrx = new Refraction(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("isManifest"),
						rs.getFloat("SC_OD_Sphere"),
						rs.getFloat("SC_OD_Cyl"),
						rs.getFloat("SC_OD_Axis"),
						rs.getFloat("SC_OS_Sphere"),
						rs.getFloat("SC_OS_Cyl"),
						rs.getFloat("SC_OS_Axis"),
						rs.getFloat("CC_OD_Sphere"),
						rs.getFloat("CC_OD_Cyl"),
						rs.getFloat("CC_OD_Axis"),
						rs.getFloat("CC_OS_Sphere"),
						rs.getFloat("CC_OS_Cyl"),
						rs.getFloat("CC_OS_Axis")
						);
				
				rl.add(glsrx);
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
		
		return rl;
	}
	
	/**
	 * Inserts visit into visits table
	 */
	public long insertRefraction(Refraction glsRx) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"insert INTO refractions "
					+ "(vid,"
					+ " isManifest,"
					+ " SC_OD_Sphere,"
					+ " SC_OD_Cyl,"
					+ " SC_OD_Axis,"
					+ " SC_OS_Sphere,"
					+ " SC_OS_Cyl,"
					+ " SC_OS_Axis,"
					+ " CC_OD_Sphere,"
					+ " CC_OD_Cyl,"
					+ " CC_OD_Axis,"
					+ " CC_OS_Sphere,"
					+ " CC_OS_Cyl,"
					+ " CC_OS_Axis)"
					+ " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, glsRx.getVid());
			
			st.setBoolean(2, glsRx.isManifest());
			
			st.setFloat(3, glsRx.getSC_OD_Sphere());
			st.setFloat(4, glsRx.getSC_OD_Cyl());
			st.setFloat(5, glsRx.getSC_OD_Axis());
			
			st.setFloat(6, glsRx.getSC_OS_Sphere());
			st.setFloat(7, glsRx.getSC_OS_Cyl());
			st.setFloat(8, glsRx.getSC_OS_Axis());
			
			st.setFloat(9, glsRx.getCC_OD_Sphere());
			st.setFloat(10, glsRx.getCC_OD_Cyl());
			st.setFloat(11, glsRx.getCC_OD_Axis());
			
			st.setFloat(12, glsRx.getCC_OS_Sphere());
			st.setFloat(13, glsRx.getCC_OS_Cyl());
			st.setFloat(14, glsRx.getCC_OS_Axis());
			
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
	public ArrayList<Object> fetchRefractionsColsForVisit(long id) throws GatewayException {

	    ArrayList<Object> row = new ArrayList<Object>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from refractions where vid=?");
			st.setLong(1, id);
			
			rs = st.executeQuery();
			
			//get metadata
		    ResultSetMetaData meta = null;
		    meta = rs.getMetaData();
		    
		    int colCount = meta.getColumnCount();
		    System.out.println("====REFRACTIONS======" + colCount);
		    System.out.println("META: " + colCount);
			
			while(rs.next()) {
				for (int i = 3; i <= colCount; i++) {
					row.add(rs.getObject(i));
					System.out.println("column #"+ i + " : " + rs.getObject(i));
				}
			}
			System.out.println("\n****************\n REFRAC ROW:"+row.toString());
			
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
	public void removeRefraction(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}


}

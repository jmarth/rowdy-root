package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.GlassesRx;
import models.Patient;

public class GlassesRxTableGatewaySQLite implements GlassesRxTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public GlassesRxTableGatewaySQLite() throws GatewayException, IOException {

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
	public List<GlassesRx> fetchGlassesRxs() throws GatewayException {
		
		ArrayList<GlassesRx> glsrxl = new ArrayList<GlassesRx>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select * from glasses_rxs");
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				GlassesRx glsrx = new GlassesRx(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getFloat("OD_Sphere"),
						rs.getFloat("OD_Cyl"),
						rs.getFloat("OD_Axis"),
						rs.getFloat("OD_Add"),
						rs.getFloat("OS_Sphere"),
						rs.getFloat("OS_Cyl"),
						rs.getFloat("OS_Axis"),
						rs.getFloat("OS_Add"),
						rs.getString("GlassesRxNotes")
						);
				
				glsrxl.add(glsrx);
				
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
		
		return glsrxl;
	}
	
	/**
	 * Fetch visits from DB for patient
	 * @return list of visits for patient
	 * @throws GatewayException
	 */
	public List<GlassesRx> fetchGlassesRxsForPatient(Patient p) throws GatewayException {
		
		ArrayList<GlassesRx> glsrxl = new ArrayList<GlassesRx>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from glasses_rxs where vid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				GlassesRx glsrx = new GlassesRx(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getFloat("OD_Sphere"),
						rs.getFloat("OD_Cyl"),
						rs.getFloat("OD_Axis"),
						rs.getFloat("OD_Add"),
						rs.getFloat("OS_Sphere"),
						rs.getFloat("OS_Cyl"),
						rs.getFloat("OS_Axis"),
						rs.getFloat("OS_Add"),
						rs.getString("GlassesRxNotes")
						);
				
				glsrxl.add(glsrx);
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
		
		return glsrxl;
	}
	
	/**
	 * Inserts visit into visits table
	 */
	public long insertGlassesRx(GlassesRx glsRx) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"insert INTO glasses_rxs "
					+ "(vid,"
					+ " OD_Sphere,"
					+ " OD_Cyl,"
					+ " OD_Axis,"
					+ " OD_Add,"
					+ " OS_Sphere,"
					+ " OS_Cyl,"
					+ " OS_Axis,"
					+ " OS_Add,"
					+ " GlassesRxNotes)"
					+ " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, glsRx.getVid());
			st.setFloat(2, glsRx.getRx_OD_Sphere());
			st.setFloat(3, glsRx.getRx_OD_Cyl());
			st.setFloat(4, glsRx.getRx_OD_Axis());
			st.setFloat(5, glsRx.getRx_OD_Add());
			st.setFloat(6, glsRx.getRx_OS_Sphere());
			st.setFloat(7, glsRx.getRx_OS_Cyl());
			st.setFloat(8, glsRx.getRx_OS_Axis());
			st.setFloat(9, glsRx.getRx_OS_Add());
			st.setFloat(9, glsRx.getRx_OS_Add());
			st.setString(10, glsRx.getGlassesRxNotes());

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
	public void removeGlassesRx(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}


}

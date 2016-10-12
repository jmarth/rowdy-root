package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.GlassesRx;

public class GlassesRxTableGatewaySQLite implements GlassesRxTableGateway {
	
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
			System.err.println("From GlsRx TG, no connection.");
//			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch GlassesRx from DB for Visit
	 * @return GlassesRx for Visit
	 * @throws GatewayException
	 */
	public GlassesRx fetchGlassesRxForVisit(long vid) throws GatewayException {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch GlassesRx
			st = conn.prepareStatement("SELECT * FROM glasses_rxs WHERE vid=?");
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
				
				GlassesRx glsrx = new GlassesRx(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getString("OD_Sphere"),
						rs.getString("OD_Cyl"),
						rs.getString("OD_Axis"),
						rs.getString("OD_Add"),
						
						rs.getString("OS_Sphere"),
						rs.getString("OS_Cyl"),
						rs.getString("OS_Axis"),
						rs.getString("OS_Add"),
						rs.getString("GlassesRxNotes")
						);
				
				return glsrx;
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
	 * Inserts GlassesRx into glasses_rxs table
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
			st.setString(2, glsRx.getRx_OD_Sphere());
			st.setString(3, glsRx.getRx_OD_Cyl());
			st.setString(4, glsRx.getRx_OD_Axis());
			st.setString(5, glsRx.getRx_OD_Add());
			
			st.setString(6, glsRx.getRx_OS_Sphere());
			st.setString(7, glsRx.getRx_OS_Cyl());
			st.setString(8, glsRx.getRx_OS_Axis());
			st.setString(9, glsRx.getRx_OS_Add());
			
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
	public void updateGlassesRx(GlassesRx glsRx) throws GatewayException {
		
		PreparedStatement st = null;
		
		try {
			
			conn.setAutoCommit(false);
			
			st = conn.prepareStatement(
					"UPDATE glasses_rxs SET"
					+ " vid = ?,"
					+ " OD_Sphere = ?,"
					+ " OD_Cyl = ?,"
					+ " OD_Axis = ?,"
					+ " OD_Add = ?,"
					
					+ " OS_Sphere = ?,"
					+ " OS_Cyl = ?,"
					+ " OS_Axis = ?,"
					+ " OS_Add = ?,"
					+ " GlassesRxNotes = ?"
					+ " WHERE id = ?");
			
			st.setLong(1, glsRx.getVid());
			
			st.setString(2, glsRx.getRx_OD_Sphere());
			st.setString(3, glsRx.getRx_OD_Cyl());
			st.setString(4, glsRx.getRx_OD_Axis());
			st.setString(5, glsRx.getRx_OD_Add());
			
			st.setString(6, glsRx.getRx_OS_Sphere());
			st.setString(7, glsRx.getRx_OS_Cyl());
			st.setString(8, glsRx.getRx_OS_Axis());
			st.setString(9, glsRx.getRx_OS_Add());
			
			st.setString(10, glsRx.getGlassesRxNotes());
			
			st.setLong(11, glsRx.getId());

			st.executeUpdate();
			
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
	}
	
	@Override
	public void removeGlassesRx(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}
}

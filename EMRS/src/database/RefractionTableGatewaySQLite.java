package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Refraction;

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
	 * Fetch Refraction from DB for Visit
	 * @return Refraction for Visit
	 * @throws GatewayException
	 */
	public Refraction fetchRefractionForVisit(long vid) throws GatewayException {
		
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from refractions where vid=?");
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				Refraction r = new Refraction(
						rs.getLong("id"),
						rs.getLong("vid"),
						
						rs.getInt("isManifest"),
						
						rs.getString("SC_OD_Sphere"),
						rs.getString("SC_OD_Cyl"),
						rs.getString("SC_OD_Axis"),
						rs.getString("SC_OS_Sphere"),
						rs.getString("SC_OS_Cyl"),
						rs.getString("SC_OS_Axis"),
						
						rs.getString("CC_OD_Sphere"),
						rs.getString("CC_OD_Cyl"),
						rs.getString("CC_OD_Axis"),
						rs.getString("CC_OS_Sphere"),
						rs.getString("CC_OS_Cyl"),
						rs.getString("CC_OS_Axis")
						);
				
				return r;
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
			
			st.setInt(2, glsRx.isManifest());
			
			st.setString(3, glsRx.getSC_OD_Sphere());
			st.setString(4, glsRx.getSC_OD_Cyl());
			st.setString(5, glsRx.getSC_OD_Axis());
			
			st.setString(6, glsRx.getSC_OS_Sphere());
			st.setString(7, glsRx.getSC_OS_Cyl());
			st.setString(8, glsRx.getSC_OS_Axis());
			
			st.setString(9, glsRx.getCC_OD_Sphere());
			st.setString(10, glsRx.getCC_OD_Cyl());
			st.setString(11, glsRx.getCC_OD_Axis());
			
			st.setString(12, glsRx.getCC_OS_Sphere());
			st.setString(13, glsRx.getCC_OS_Cyl());
			st.setString(14, glsRx.getCC_OS_Axis());
			
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
	public void updateRefractionForVisit(Refraction r) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeRefraction(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

}

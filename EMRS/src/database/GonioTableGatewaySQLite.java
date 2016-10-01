package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Gonio;

public class GonioTableGatewaySQLite implements GonioTableGateway {
	
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException
	 */
	public GonioTableGatewaySQLite() throws GatewayException, IOException {

		try {
			
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts Gonio into gonios table
	 */
	public long insertGonioForVisit(Gonio g) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"insert INTO gonios "
					+ "(vid,"
					+ " isHxFHA,"
					+ " FHASide,"
					
					+ " isODNormal,"
					+ " odABCDNon,"
					+ " odABCDComp,"
					+ " odDegreeNon,"
					+ " odDegreeComp,"
					+ " odRSQNon,"
					+ " odRSQComp,"
					+ " odPigment,"
					+ " isODAntPigLine,"
					
					+ " isOSNormal,"
					+ " osABCDNon,"
					+ " osABCDComp,"
					+ " osDegreeNon,"
					+ " osDegreeComp,"
					+ " osRSQNon,"
					+ " osRSQComp,"
					+ " osPigment,"
					+ " isOSAntPigLine)"
					
					+ " values ( ?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?,?,?,?,?  ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, g.getVid());
			
			st.setInt(2, g.isHxFHA());
			st.setString(3, g.getFHASide());
			
			st.setInt(4, g.isODNormal());
			st.setString(5, g.getOdABCDNon());
			st.setString(6, g.getOdABCDComp());
			st.setString(7, g.getOdDegreeNon());
			st.setString(8, g.getOdDegreeComp());
			st.setString(9, g.getOdRSQNon());
			st.setString(10, g.getOdRSQComp());
			st.setString(11, g.getOdPigment());
			st.setInt(12, g.isODAntPigLine());
			
			st.setInt(13, g.isOSNormal());
			st.setString(14, g.getOsABCDNon());
			st.setString(15, g.getOsABCDComp());
			st.setString(16, g.getOsDegreeNon());
			st.setString(17, g.getOsDegreeComp());
			st.setString(18, g.getOsRSQNon());
			st.setString(19, g.getOsRSQComp());
			st.setString(20, g.getOsPigment());
			st.setInt(21, g.isOSAntPigLine());
			
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
	public void updateGonioForVisit(long vid) throws GatewayException {
		// TODO Auto-generated method stub
		// auto commit false then true after
	}

	@Override
	public void removeGonio(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Gonio fetchGonioForVisit(long vid) throws GatewayException {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from gonios where vid=?");
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			
			if (rs.next()) {
				Gonio g = new Gonio(
						rs.getLong("id"),
						rs.getLong("vid"),
						
						rs.getInt("isHxFHA"),
						rs.getString("FHASide"),
						
						rs.getInt("isODNormal"),
						rs.getString("odABCDNon"),
						rs.getString("odABCDComp"),
						rs.getString("odDegreeNon"),
						rs.getString("odDegreeComp"),
						rs.getString("odRSQNon"),
						rs.getString("odRSQComp"),
						rs.getString("odPigment"),
						rs.getInt("isODAntPigLine"),
						
						rs.getInt("isOSNormal"),
						rs.getString("osABCDNon"),
						rs.getString("osABCDComp"),
						rs.getString("osDegreeNon"),
						rs.getString("osDegreeComp"),
						rs.getString("osRSQNon"),
						rs.getString("osRSQComp"),
						rs.getString("osPigment"),
						rs.getInt("isOSAntPigLine")
						);
				return g;
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
}

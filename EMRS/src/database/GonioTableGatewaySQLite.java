package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Gonio;
import models.Patient;

public class GonioTableGatewaySQLite implements GonioTableGateway {
	
	/**
	 * external DB connection
	 */
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
	 * Fetch all visits from DB
	 * @return list of visits
	 * @throws GatewayException
	 */
	public List<Gonio> fetchGonios() throws GatewayException {
		
		ArrayList<Gonio> pl = new ArrayList<Gonio>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select * from gonios");
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				Gonio p = new Gonio(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("isHxFHA"),
						rs.getString("FHASide"),
						rs.getBoolean("isODNormal"),
						rs.getString("odABCDNon"),
						rs.getString("odABCDComp"),
						rs.getString("odRSQNon"),
						rs.getString("odRSQComp"),
						rs.getInt("odPigment"),
						rs.getBoolean("isODAntPigLine"),
						rs.getBoolean("isOSNormal"),
						rs.getString("osABCDNon"),
						rs.getString("osABCDComp"),
						rs.getString("osRSQNon"),
						rs.getString("osRSQComp"),
						rs.getInt("osPigment"),
						rs.getBoolean("isOSAntPigLine")
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
	public List<Gonio> fetchGoniosForPatient(Patient p) throws GatewayException {
		
		ArrayList<Gonio> pl = new ArrayList<Gonio>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from gonios where vid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				Gonio pu = new Gonio(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("isHxFHA"),
						rs.getString("FHASide"),
						rs.getBoolean("isODNormal"),
						rs.getString("odABCDNon"),
						rs.getString("odABCDComp"),
						rs.getString("odRSQNon"),
						rs.getString("odRSQComp"),
						rs.getInt("odPigment"),
						rs.getBoolean("isODAntPigLine"),
						rs.getBoolean("isOSNormal"),
						rs.getString("osABCDNon"),
						rs.getString("osABCDComp"),
						rs.getString("osRSQNon"),
						rs.getString("osRSQComp"),
						rs.getInt("osPigment"),
						rs.getBoolean("isOSAntPigLine")
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
	public long insertGonio(Gonio p) throws GatewayException {
		
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
					+ " odRSQNon,"
					+ " odRSQComp,"
					+ " odPigment,"
					+ " isODAntPigLine,"
					+ " isOSNormal,"
					+ " osABCDNon,"
					+ " osABCDComp,"
					+ " osRSQNon,"
					+ " osRSQComp,"
					+ " osPigment,"
					+ " isOSAntPigLine)"
					+ " values ( ?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?  ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, p.getVid());
			st.setBoolean(2, p.isHxFHA());
			st.setString(3, p.getFHASide());
			st.setBoolean(4, p.isODNormal());
			st.setString(5, p.getOdABCDNon());
			st.setString(6, p.getOdABCDComp());
			st.setString(7, p.getOdRSQNon());
			st.setString(8, p.getOdRSQComp());
			st.setInt(9, p.getOdPigment());
			st.setBoolean(10, p.isODAntPigLine());
			st.setBoolean(11, p.isOSNormal());
			st.setString(12, p.getOsABCDNon());
			st.setString(13, p.getOsABCDComp());
			st.setString(14, p.getOsRSQNon());
			st.setString(15, p.getOsRSQComp());
			st.setInt(16, p.getOsPigment());
			st.setBoolean(17, p.isOSAntPigLine());
			
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
	public void removeGonio(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}


}

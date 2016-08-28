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
						rs.getString("oSDegreeNon"),
						rs.getString("oSDegreeComp"),
						rs.getString("osRSQNon"),
						rs.getString("osRSQComp"),
						rs.getString("osPigment"),
						rs.getInt("isOSAntPigLine")
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
						rs.getString("oSDegreeNon"),
						rs.getString("oSDegreeComp"),
						rs.getString("osRSQNon"),
						rs.getString("osRSQComp"),
						rs.getString("osPigment"),
						rs.getInt("isOSAntPigLine")
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
			
			st.setLong(1, p.getVid());
			st.setInt(2, p.isHxFHA());
			st.setString(3, p.getFHASide());
			st.setInt(4, p.isODNormal());
			st.setString(5, p.getOdABCDNon());
			st.setString(6, p.getOdABCDComp());
			st.setString(7, p.getOdDegreeNon());
			st.setString(8, p.getOdDegreeComp());
			st.setString(9, p.getOdRSQNon());
			st.setString(10, p.getOdRSQComp());
			st.setString(11, p.getOdPigment());
			st.setInt(12, p.isODAntPigLine());
			st.setInt(13, p.isOSNormal());
			st.setString(14, p.getOsABCDNon());
			st.setString(15, p.getOsABCDComp());
			st.setString(16, p.getOsDegreeNon());
			st.setString(17, p.getOsDegreeComp());
			st.setString(18, p.getOsRSQNon());
			st.setString(19, p.getOsRSQComp());
			st.setString(20, p.getOsPigment());
			st.setInt(21, p.isOSAntPigLine());
			
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

	@Override
	public ArrayList<Object> fetchGonioForVisit(long id) throws GatewayException {

	    ArrayList<Object> row = new ArrayList<Object>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from gonios where vid=?");
			st.setLong(1, id);
			
			rs = st.executeQuery();
			
			//get metadata
		    ResultSetMetaData meta = null;
		    meta = rs.getMetaData();
		    
		    int colCount = meta.getColumnCount();
		    System.out.println("====goinos======" + colCount);
			
			while(rs.next()) {
				for (int i = 3; i <= colCount; i++) {
					row.add(rs.getObject(i));
					System.out.println("column #"+ i + " : " + rs.getObject(i));
				}
			}
			System.out.println("\n****************\n gonio ROW:"+row.toString());
			
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


}

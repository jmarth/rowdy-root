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

import models.Lens;
import models.Patient;

public class LensTableGatewaySQLite implements LensTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public LensTableGatewaySQLite() throws GatewayException, IOException {

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
	public List<Lens> fetchLens() throws GatewayException {
		
		ArrayList<Lens> pl = new ArrayList<Lens>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select * from lenses");
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				Lens p = new Lens(
						rs.getLong("id"),
						rs.getLong("vid"),
						
						rs.getString("NS_OD"),
						rs.getString("NS_OD_Notes"),
						rs.getString("NS_OS"),
						rs.getString("NS_OS_Notes"),
						
						rs.getInt("isStableLensOD"),
						rs.getInt("isStableLensOS"),
						
						rs.getInt("isPseudophakia_OD"),
						rs.getInt("isPseudophakia_OS"),
						
						rs.getInt("isPCO_OD"),
						rs.getInt("isPCO_OS"),
						
						rs.getString("Coritcal_OD"),
						rs.getString("Cortical_OD_Notes"),
						rs.getString("Coritcal_OS"),
						rs.getString("Cortical_OS_Notes"),
						
						rs.getString("PSC_OD"),
						rs.getString("PSC_OD_Notes"),
						rs.getString("PSC_OS"),
						rs.getString("PSC_OS_Notes")
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
				
				if(conn != null)
					conn.close();
				
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
	public Lens fetchLensForVisit(long vid) throws GatewayException {
				
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from lenses where vid=?");
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				Lens l = new Lens(
						rs.getLong("id"),
						rs.getLong("vid"),
						
						rs.getString("NS_OD"),
						rs.getString("NS_OD_Notes"),
						rs.getString("NS_OS"),
						rs.getString("NS_OS_Notes"),
						
						rs.getInt("isStableLensOD"),
						rs.getInt("isStableLensOS"),
						
						rs.getInt("isPseudophakia_OD"),
						rs.getInt("isPseudophakia_OS"),
						
						rs.getInt("isPCO_OD"),
						rs.getInt("isPCO_OS"),
						
						rs.getString("Coritcal_OD"),
						rs.getString("Cortical_OD_Notes"),
						rs.getString("Coritcal_OS"),
						rs.getString("Cortical_OS_Notes"),
						
						rs.getString("PSC_OD"),
						rs.getString("PSC_OD_Notes"),
						rs.getString("PSC_OS"),
						rs.getString("PSC_OS_Notes")
						);
				
				return l;
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
	public long insertLens(Lens p) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"insert INTO lenses "
					+ "(vid,"
							
					+ " NS_OD,"
					+ " NS_OD_Notes,"
					+ " NS_OS,"
					+ " NS_OS_Notes,"
					
					+ " isStableLensOD,"
					+ " isStableLensOS,"
					
					+ " isPseudophakia_OD,"
					+ " isPseudophakia_OS,"
					
					+ " isPCO_OD,"
					+ " isPCO_OS,"
					
					+ " Coritcal_OD,"
					+ " Cortical_OD_Notes,"
					+ " Coritcal_OS,"
					+ " Cortical_OS_Notes,"
					
					+ " PSC_OD,"
					+ " PSC_OD_Notes,"
					+ " PSC_OS,"
					+ " PSC_OS_Notes)"
					
					+ " values ( ?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, p.getVid());
			
			st.setString(2, p.getNS_OD());
			st.setString(3, p.getNS_OD_Notes());
			st.setString(4, p.getNS_OS());
			st.setString(5, p.getNS_OS_Notes());
			
			st.setInt(6, p.isStableLensOD());
			st.setInt(7, p.isStableLensOS());
			
			st.setInt(8, p.isPseudophakia_OD());
			st.setInt(9, p.isPseudophakia_OS());
			
			st.setInt(10, p.isPCO_OD());
			st.setInt(11, p.isPCO_OS());
			
			st.setString(12, p.getCoritcal_OD());
			st.setString(13, p.getCortical_OD_Notes());
			st.setString(14, p.getCoritcal_OS());
			st.setString(15, p.getCortical_OS_Notes());
			
			st.setString(16, p.getPSC_OD());
			st.setString(17, p.getPSC_OD_Notes());
			st.setString(18, p.getPSC_OS());
			st.setString(19, p.getPSC_OS_Notes());
			
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
	
	public ArrayList<Object> fetchLensColsForVisit(long id) throws GatewayException {

	    ArrayList<Object> row = new ArrayList<Object>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from lenses where vid=?");
			st.setLong(1, id);
			
			rs = st.executeQuery();
			
			//get metadata
		    ResultSetMetaData meta = null;
		    meta = rs.getMetaData();
		    
		    int colCount = meta.getColumnCount();
//		    System.out.println("====Lenses======" + colCount);
			
			while(rs.next()) {
				for (int i = 3; i <= colCount; i++) {
					row.add(rs.getObject(i));
//					System.out.println("column #"+ i + " : " + rs.getObject(i));
				}
			}
//			System.out.println("\n****************\n Lenses ROW:"+row.toString());
			
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
	public void removeLens(Long vid) throws GatewayException {
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}



}

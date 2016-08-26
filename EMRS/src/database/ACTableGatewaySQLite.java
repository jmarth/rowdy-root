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

import models.AnteriorChamber;
import models.Patient;

public class ACTableGatewaySQLite implements ACTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public ACTableGatewaySQLite() throws GatewayException, IOException {

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
	public List<AnteriorChamber> fetchAnteriorChambers() throws GatewayException {
		
		ArrayList<AnteriorChamber> pl = new ArrayList<AnteriorChamber>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select * from anterior_chambers");
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				AnteriorChamber p = new AnteriorChamber(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("isACODNormal"),
						rs.getBoolean("isACOSNormal"),
						rs.getInt("ACDepthOD"),
						rs.getInt("ACDepthOS"),
						rs.getString("ACAngleOD"),
						rs.getString("ACAngleOS"),
						rs.getString("PASOD"),
						rs.getString("PASOS"),
						rs.getInt("ACODKP"),
						rs.getInt("ACOSKP"),
						rs.getBoolean("isShuntOD"),
						rs.getBoolean("isScarringOD"),
						rs.getBoolean("isTraumaOD"),
						rs.getBoolean("isBlebOD"),
						rs.getBoolean("isShuntOS"),
						rs.getBoolean("isScarringOS"),
						rs.getBoolean("isTraumaOS"),
						rs.getBoolean("isBlebOS"),
						rs.getBoolean("isVascularOD"),
						rs.getInt("BlebOD_Num"),
						rs.getBoolean("isVascularOS"),
						rs.getInt("BlebOS_Num"),
						rs.getBoolean("isKSpindleOD"),
						rs.getBoolean("isKSpindleOS")
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
	public List<AnteriorChamber> fetchAnteriorChambersForPatient(Patient p) throws GatewayException {
		
		ArrayList<AnteriorChamber> pl = new ArrayList<AnteriorChamber>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from anterior_chambers where vid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				AnteriorChamber pu = new AnteriorChamber(
						rs.getLong("id"),
						rs.getLong("vid"),
						rs.getBoolean("isACODNormal"),
						rs.getBoolean("isACOSNormal"),
						rs.getInt("ACDepthOD"),
						rs.getInt("ACDepthOS"),
						rs.getString("ACAngleOD"),
						rs.getString("ACAngleOS"),
						rs.getString("PASOD"),
						rs.getString("PASOS"),
						rs.getInt("ACODKP"),
						rs.getInt("ACOSKP"),
						rs.getBoolean("isShuntOD"),
						rs.getBoolean("isScarringOD"),
						rs.getBoolean("isTraumaOD"),
						rs.getBoolean("isBlebOD"),
						rs.getBoolean("isShuntOS"),
						rs.getBoolean("isScarringOS"),
						rs.getBoolean("isTraumaOS"),
						rs.getBoolean("isBlebOS"),
						rs.getBoolean("isVascularOD"),
						rs.getInt("BlebOD_Num"),
						rs.getBoolean("isVascularOS"),
						rs.getInt("BlebOS_Num"),
						rs.getBoolean("isKSpindleOD"),
						rs.getBoolean("isKSpindleOS")
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
	public long insertAnteriorChamber(AnteriorChamber p) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"insert INTO anterior_chambers "
					+ "(vid,"
					+ " isACODNormal,"
					+ " isACOSNormal,"
					+ " ACDepthOD,"
					+ " ACDepthOS,"
					+ " ACAngleOD,"
					+ " ACAngleOS,"
					+ " PASOD,"
					+ " PASOS,"
					+ " ACODKP,"
					+ " ACOSKP,"
					+ " isShuntOD,"
					+ " isScarringOD,"
					+ " isTraumaOD,"
					+ " isBlebOD,"
					+ " isShuntOS,"
					+ " isScarringOS,"
					+ " isTraumaOS,"
					+ " isBlebOS,"
					+ " isVascularOD,"
					+ " BlebOD_Num,"
					+ " isVascularOS,"
					+ " BlebOS_Num,"
					+ " isKSpindleOD,"
					+ " isKSpindleOS)"
					+ " values ( ?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, p.getVid());
			st.setBoolean(2, p.isACODNormal());
			st.setBoolean(3, p.isACOSNormal());
			st.setInt(4, p.getACDepthOD());
			st.setInt(5, p.getACDepthOS());
			st.setString(6, p.getACAngleOD());
			st.setString(7, p.getACAngleOS());
			st.setString(8, p.getPASOD());
			st.setString(9, p.getPASOS());
			st.setInt(10, p.getACODKP());
			st.setInt(11, p.getACOSKP());
			st.setBoolean(12, p.isShuntOD());
			st.setBoolean(13, p.isScarringOD());
			st.setBoolean(14, p.isTraumaOD());
			st.setBoolean(15, p.isBlebOD());
			st.setBoolean(16, p.isShuntOS());
			st.setBoolean(17, p.isScarringOS());
			st.setBoolean(18, p.isTraumaOS());
			st.setBoolean(19, p.isBlebOS());
			st.setBoolean(20, p.isVascularOD());
			st.setInt(21, p.getBlebOD_Num());
			st.setBoolean(22, p.isVascularOS());
			st.setInt(23, p.getBlebOS_Num());
			st.setBoolean(24, p.isKSpindleOD());
			st.setBoolean(25, p.isKSpindleOS());
			
			
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
	public void removeAnteriorChamber(Long vid) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Object> fetchACColsForVisit(long id) throws GatewayException {

	    ArrayList<Object> row = new ArrayList<Object>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from anterior_chambers where vid=?");
			st.setLong(1, id);
			
			rs = st.executeQuery();
			
			//get metadata
		    ResultSetMetaData meta = null;
		    meta = rs.getMetaData();
		    
		    int colCount = meta.getColumnCount();
		    System.out.println("====AC======" + colCount);
			
			while(rs.next()) {
				for (int i = 3; i <= colCount; i++) {
					row.add(rs.getObject(i));
					System.out.println("column #"+ i + " : " + rs.getObject(i));
				}
			}
			System.out.println("\n****************\n AC ROW:"+row.toString());
			
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

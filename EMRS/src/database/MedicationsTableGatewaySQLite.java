package database;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Med;
import models.Patient;

/**
 * SQLite gateway
 * @author Mark
 *
 */

public class MedicationsTableGatewaySQLite implements MedicationsTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	
	public MedicationsTableGatewaySQLite() throws GatewayException, IOException {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Med> fetchMeds() throws GatewayException {
		ArrayList<Med> meds = new ArrayList<Med>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch Meds
			st = conn.prepareStatement("select * from medications");
			
			rs = st.executeQuery();
			
			//add each to list of meds to return
			
			while(rs.next()) {
				Med tmpMed = new Med(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("trade"),
						rs.getString("generic")
						);
				meds.add(tmpMed);
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
		
		return meds;
	}

	@Override
	public List<Med> fetchMedsForPatient(Patient p) throws GatewayException {
		ArrayList<Med> meds = new ArrayList<Med>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			//fetch Meds
			st = conn.prepareStatement("select * from medications WHERE pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of meds to return
			while(rs.next()) {
				
				Med tmpMed = new Med(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("trade"),
						rs.getString("generic")
						);
				meds.add(tmpMed);
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			
			try {
				if(rs != null)
					rs.close();
				
				if(st != null)
					st.close();
				
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
		
		return meds;
	}

	@Override
	public long insertMed(Med m) throws GatewayException {
		//init new id to invalid
		long newId = 0;

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("insert INTO medications (pid,"
					+ " trade,"
					+ " generic) "
					+ " values ( ?, ?, ? ) ", PreparedStatement.RETURN_GENERATED_KEYS);

			st.setLong(1, m.getPid());
			st.setString(2, m.getTradeName());
			st.setString(3, m.getGenericName());

			st.executeUpdate();

			//get the generated key
			rs = st.getGeneratedKeys();

			if(rs != null && rs.next()) {
				newId = rs.getLong(1);
				System.out.println("Hx is ID: " + newId + "");

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
	public void updateMed(Med m) throws GatewayException {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("UPDATE medications SET"
					+ " trade = ?,"
					+ " generic = ?"
					+ " WHERE id = ? ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setString(1, m.getTradeName());
			st.setString(2, m.getGenericName());
			st.setLong(3, m.getId());

			st.executeUpdate();
			
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
	}

	@Override
	public void removeMed(Long mid) throws GatewayException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM medications"
					+ " WHERE id = ? ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, mid);

			st.executeUpdate();
			
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
	}

}

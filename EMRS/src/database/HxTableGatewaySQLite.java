package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Hx;
import models.Patient;

public class HxTableGatewaySQLite implements HxTableGateway {
	
	private Connection conn = null;
	
	public HxTableGatewaySQLite() throws GatewayException, IOException {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//TODO Do not need vvv
	@Override
	public List<Hx> fetchHx() throws GatewayException {
		ArrayList<Hx> hxList = new ArrayList<Hx>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch hxList
			st = conn.prepareStatement("select * from hx");
			
			rs = st.executeQuery();
			
			//add each to list of hxList to return
			
			while(rs.next()) {
				Hx tmpHx = new Hx(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("pc"),
						rs.getString("da"),
						rs.getString("bt"),
						rs.getString("pmh"),
						rs.getString("psh"),
						rs.getString("fh"),
						rs.getString("law"),
						rs.getString("pe")
						);
				hxList.add(tmpHx);
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
		
		return hxList;
	}

	@Override
	public List<Hx> fetchHxForPatient(Patient p) throws GatewayException {
		ArrayList<Hx> hxList = new ArrayList<Hx>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			//fetch hxList
			st = conn.prepareStatement("select * from hx WHERE pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of hxList to return
			while(rs.next()) {
				
				Hx tmpHx = new Hx(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("pc"),
						rs.getString("da"),
						rs.getString("bt"),
						rs.getString("pmh"),
						rs.getString("psh"),
						rs.getString("fh"),
						rs.getString("law"),
						rs.getString("pe")
						);
				hxList.add(tmpHx);
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
		
		return hxList;
	}

	@Override
	public long insertHx(Hx hx) throws GatewayException {
		//init new id to invalid
				long newId = 0;

				PreparedStatement st = null;
				ResultSet rs = null;

				try {
					st = conn.prepareStatement("insert INTO hx (pid,"
							+ " pc,"
							+ " da,"
							+ " bt,"
							+ " pmh,"
							+ " psh,"
							+ " fh,"
							+ " law,"
							+ " pe) "
							+ " values ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) ", PreparedStatement.RETURN_GENERATED_KEYS);

					st.setLong(1, hx.getPid());
					st.setString(2, hx.getPc());
					st.setString(3, hx.getDa());
					st.setString(4, hx.getBt());
					st.setString(5, hx.getPmh());
					st.setString(6, hx.getPsh());
					st.setString(7, hx.getFh());
					st.setString(8, hx.getLaw());
					st.setString(9, hx.getPe());

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
public void updateHx(Hx hx) throws GatewayException{
		
		PreparedStatement st = null;
//		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE hx SET "
					+ "pc = ?, "
					+ "da = ?, "
					+ "bt = ?, "
					+ "pmh = ?, "
					+ "psh = ?, "
					+ "fh = ?, "
					+ "law = ?, "
					+ "pe = ? "
					+ " WHERE id = ? ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setString(1, hx.getPc());
			st.setString(2, hx.getDa());
			st.setString(3, hx.getBt());
			st.setString(4, hx.getPmh());
			st.setString(5, hx.getPsh());
			st.setString(6, hx.getFh());
			st.setString(7, hx.getLaw());
			st.setString(8, hx.getPe());

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

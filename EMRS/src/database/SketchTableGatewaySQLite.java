package database;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

public class SketchTableGatewaySQLite implements SketchTableGateway {

	/**
	 * external DB connection
	 */
	private Connection conn = null;

	/**
	 * Constructor: creates database connection
	 * 
	 * @throws GatewayException
	 * @throws IOException
	 */
	public SketchTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public long insertSketchToTable(BufferedImage bi, long vid, String table) throws GatewayException {
		long newId = -1;
		PreparedStatement st = null;
		ResultSet rs = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				ImageIO.write(bi, "png", baos);
				//TODO Flush/Close
			} catch (IOException e1) {
				System.err.println("From Sketch TG: IO Exception on write ByteArray");
				// e1.printStackTrace();
			}
			InputStream is = new ByteArrayInputStream(baos.toByteArray());
	
			try {
				st = conn.prepareStatement("insert INTO " + table + " (vid," + " image) " + " values ( ?, ?) ",
						PreparedStatement.RETURN_GENERATED_KEYS);
	
				st.setLong(1, vid);
				st.setBinaryStream(2, is, (int) baos.toByteArray().length);
	
				st.executeUpdate();
	
				// get the generated key
				rs = st.getGeneratedKeys();
	
				if (rs != null && rs.next()) {
	
					newId = rs.getLong(1);
	
				} else {
					throw new GatewayException("From Sketch TG: Could not insert new record.");
				}
	
			} catch (SQLException e) {
				throw new GatewayException(e.getMessage());
			} finally {
				// clean up
				try {
					if (st != null)
						st.close();
	
				} catch (SQLException e) {
					throw new GatewayException("SQL Error: " + e.getMessage());
				}
			}
		
		return newId;
	}

	@Override
	public BufferedImage fetchSketchForVisitByTable(long vid, String table) throws GatewayException {

		BufferedImage bi = null;

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT image FROM " + table + " WHERE vid = ?");
			st.setLong(1, vid);

			rs = st.executeQuery();

			if (rs.next()) {
				
				InputStream is = new ByteArrayInputStream(rs.getBytes("image"));
				
				bi = ImageIO.read(is);
			}
			
			return bi;
//
//				InputStream stream = rs.getBinaryStream("image");
//				ByteArrayOutputStream output = new ByteArrayOutputStream();
//
//				int i = stream.read();
//
//				while (i >= 0) {
//					output.write((char) i);
//					i = stream.read();
//				}
//
//				Image temp = Toolkit.getDefaultToolkit().createImage(output.toByteArray());
				
				// if(temp.getWidth(null) > 0) {//||temp.getHeight(null) > 0
				//
				// image = new BufferedImage(temp.getWidth(null),
				// temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
				// Graphics bg = temp.getGraphics();
				// bg.drawImage(temp, 0, 0, null);
				// bg.dispose();
				// }

//				output.close();

		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} catch (IOException e) {
			System.err.println("From Sketch TG: IO Exception in fetch.");
			e.printStackTrace();
		} finally {
			// clean up
			try {
				if (rs != null)
					rs.close();

				if (st != null)
					st.close();

			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}

		return null;
	}

	public void close() {
		// TODO
	}

	@Override
	public void updateSketchToTable(BufferedImage bi, long vid, String table) throws GatewayException {
		PreparedStatement st = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "png", baos);
			
		} catch (IOException e1) {
			System.err.println("From Sketch TG update: IO Exception on write ByteArray");
			// e1.printStackTrace();
		}
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		try {
			conn.setAutoCommit(false);
			st = conn.prepareStatement("UPDATE " + table + " SET vid = ?, image = ? WHERE vid = ? ",
					PreparedStatement.RETURN_GENERATED_KEYS);

			st.setLong(1, vid); // don't need to do this...
			st.setBinaryStream(2, is, baos.toByteArray().length);
			st.setLong(3, vid);

			st.executeUpdate();
			
			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			// clean up
			try {
				if (st != null)
					st.close();

			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
		return;
	}
}

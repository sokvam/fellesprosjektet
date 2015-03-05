package calendarProject;

import java.sql.*;

public class RoomDB {

	Connection conn;
	Statement stmt;

	public void constructor(Connection conn) {
		this.conn = conn;
		stmt = null;
	}

	public int getSize(String romID) {
		int size = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT id, size .... FROM calendarDB.Rooms WHERE id = " + romID;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				size = rs.getInt("size");

				System.out.print("ID: " + romID);
				System.out.print(", size: " + size);
				System.out.print("\n");
			}
			rs.close();
			stmt.close();

			//conn.close();

		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return size;
	}

}

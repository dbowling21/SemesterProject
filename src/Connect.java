import java.net.*;
import java.sql.*;

public class Connect {
	public static final String USER = "GalacticWafer";
	public static final String PASSWORD = "|i&o?Zy2H3";
	public static final String HOST_IP = "45.79.55.190";
	public static String schema_name = "test";
	public static final int PORT = 3306;
	
	public static void main(String[] args) {
		Connection conn = null;
		try {
			InetAddress localhost = InetAddress.getLocalHost();
			String url =
			 "jdbc:mysql://" + HOST_IP + ":" + PORT + "/" + schema_name;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, USER, PASSWORD);
			System.out.println("Database connection established");
			
			/**
			 * this block of code is where create, read, update, and delete 
			 * statements should be put together.
			 *
			 Statement stmt = conn.createStatement();
			 String query1 = "update UpdateDemo set Name='Johnson' " + "where 
			 id in(1,4)";
			 stmt.executeUpdate(query1);
			 System.out.println("Record has been updated in the table 
			 successfully..................");
			 */
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) {
				try {
					conn.close();
					System.out.println("Database connection terminated");
				}
				catch(Exception e) { /* ignore close errors */ }
			}
		}
	}
}

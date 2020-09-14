import java.sql.SQLException;
import java.util.ArrayList;

public class CRUDBuddyDriver {
	static String userName = "";
	static String password = "";
	static String ipAddress = "45.79.55.190";
	static String portNumber = "3306";
	static String databaseName = "cs3250_project";
	static String tableName = "test";
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		CRUDBuddy crud = new CRUDBuddy(userName, password, ipAddress, portNumber, databaseName);
		crud.upLoadTable("inventory_team4.csv");
	}
	
	private static void testReadColNames(CRUDBuddy crud) throws SQLException {
		ArrayList<String> headers = crud.readColumnNames("cs3250_project", "test");
		for(String header: headers) {
			System.out.println(header);
		}
	}
}

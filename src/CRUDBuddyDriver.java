import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CRUDBuddyDriver {
	static String userName = "OutbreakSource";
	static String password = "HHwp5r2)|j";
	static String ipAddress = "45.79.55.190";
	static String portNumber = "3306";
	static String databaseName = "cs3250_project";
	static String tableName = "test";
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		CRUDBuddy crud = new CRUDBuddy(userName, password, ipAddress, portNumber, databaseName);
		ResultSet rs = crud.readAllRecords("inventory_team4");
		while(rs.next()){
			System.out.print(rs.getString("product_id"));
			System.out.print(" - ");
			System.out.print(rs.getString("quantity"));
			System.out.print(" - ");
			System.out.print(rs.getString("wholesale_cost"));
			System.out.print(" - ");
			System.out.print(rs.getString("sale_price"));
			System.out.print(" - ");
			System.out.println(rs.getString("supplier_id"));
		}

	}

	public readTable(String table){

	}
	
	private static void testReadColNames(CRUDBuddy crud) throws SQLException {
		ArrayList<String> headers = crud.readColumnNames("cs3250_project", "test");
		for(String header: headers) {
			System.out.println(header);
		}
	}
}

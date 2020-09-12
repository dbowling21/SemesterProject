import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CRUDBuddyDriver {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// Login info
		String userName = "GalacticWafer";
		String password = "7!qaJ|B[t$";
		String ipAddress = "45.79.55.190";
		String portNumber = "3306";
		
		// Metadata for database access
		String databaseName = "cs3250_project";
		String tableName = "wtf_hammad_make_it_work";
		
		// row identity information
		String identifierColumn = "product_id";
		String identifierValue = "ZRDATK9CSM23";
		
		// A few of the column names to make sure we can updateRow their values
		String[] columnNames = {"quantity", "wholesale_cost", "sale_price"};
		
		// CRUDBuddy instance variable
		CRUDBuddy crud = new CRUDBuddy(userName, password, ipAddress, portNumber, databaseName);
		
		// 
		HashMap<String, Object> results = crud.readColumnValues(tableName, columnNames, identifierValue,identifierColumn);
		System.out.println(results.toString());
		
		// new values to updateRow
		String[] newValues = {"4444", "289.56", "458.99"};
		
		// because "product_id" is a string, but this may not be the thing we identify in every case
		boolean idIsString = true;
		
		// array indicating if the same index in newValues above, is a String
		boolean[] valueIsString = {false, false, false};
		
		int[] ints = crud.updateRow(tableName,columnNames, newValues, identifierValue,identifierColumn, valueIsString, idIsString);
		System.out.println("After updateRow: " + crud.readColumnValues(tableName, columnNames, identifierValue, identifierColumn).toString());
	}
	
	private static void testReadColNames(CRUDBuddy crud) throws SQLException {
		ArrayList<String> headers = crud.readColumnNames("cs3250_project", "test");
		for(String header: headers) {
			System.out.println(header);
		}
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataEntryDriver {
	private static final String FILE_NAME = "inventory_team4.csv";
	public static Table table;
	public static void main(String[] args) throws FileNotFoundException {
		table = new Table("hello_table");
		Scanner scanner = new Scanner(new File(FILE_NAME));
		scanner.nextLine();
		while(scanner.hasNextLine()) {
			String[] temp = scanner.nextLine().split(",");
			String PRODUCT_ID = temp[0];
			Integer QUANTITY = Integer.parseInt(temp[1]);
			double WHOLESALE_COST = Double.parseDouble(temp[2]);
			float SALE_PRICE = Float.parseFloat(temp[3]);
			String SUPPLIER_ID = temp[4];
			table.create(PRODUCT_ID, new DataEntry(
			 PRODUCT_ID,
			 QUANTITY,
			 WHOLESALE_COST,
			 SALE_PRICE,
			 SUPPLIER_ID
			));
 			System.out.println(table.read(PRODUCT_ID).toString());
			if(scanner.hasNextLine()) {
				scanner.nextLine();
			}
		}
	}
}


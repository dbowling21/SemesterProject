import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

class CRUDBuddyTest extends JPanel {
	JTable table;
	JScrollPane scrollPane;
	static String userName = "GalacticWafer";
	static String password = "7!qaJ|B[t$";
	static String ipAddress = "45.79.55.190";
	static String portNumber = "3306";
	static String databaseName = "cs3250_project";
	static String tableName = "wtf_hammad_make_it_work";
	
	@Test void assertReadAndUpdate() throws SQLException, ClassNotFoundException {
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
		HashMap<String, Object> results =
		 crud.readColumnValues(tableName, columnNames, identifierValue, identifierColumn);
		System.out.println(results.toString());
		
		// new values to updateRow
		String[] newValues = {"4444", "289.56", "458.99"};
		
		// because "product_id" is a string, but this may not be the thing we identify in every 
		// case
		boolean idIsString = true;
		
		// array indicating if the same index in newValues above, is a String
		boolean[] valueIsString = {false, false, false};
		
		int[] ints = crud
		 .updateRow(tableName, columnNames, newValues, identifierValue, identifierColumn,
		  valueIsString, idIsString);
		System.out.println("After updateRow: " +
		 crud.readColumnValues(tableName, columnNames, identifierValue, identifierColumn)
		  .toString());
	}
	
	@Test void assertUploadTableGuiIsOk() throws SQLException, ClassNotFoundException {
		CRUDBuddy crud = new CRUDBuddy(userName, password, ipAddress, portNumber, databaseName);
		crud.loadTable("inventory_team4.csv");
	}
	
	@Test public void assertTableViewerGiuIsOk() throws SQLException, ClassNotFoundException {
		CRUDBuddy crud = new CRUDBuddy(userName, password, ipAddress, portNumber, databaseName);
		ArrayList<String> temp = crud.readColumnNames(databaseName, tableName);
		String columnNames = (temp + "").substring(1, (temp + "").length() - 1);
		ResultSet rs = crud.query("Select " + columnNames + " from " + tableName);
		
		//JTable jt;
		int i = 0;
		ArrayList<Object[]> rows = new ArrayList<>();
		while(rs.next()) {
			i++;
			Object idx = rs.getObject("idx");
			Object product_id = rs.getObject("product_id");
			Object quantity = rs.getInt("quantity");
			Object wholesale_cost = rs.getDouble("wholesale_cost");
			Object sale_price = rs.getDouble("sale_price");
			String supplier_id = rs.getString("supplier_id");
			
			rows.add(new Object[] {
			 idx, product_id, quantity, wholesale_cost, sale_price, supplier_id
			});
		}
		Iterator<Object[]> row_it = rows.iterator();
		Object[][] data = new Object[i][6];
		for(int i1 = 0; i1 < data.length; i1++) {
			data[i1] = row_it.next();
		}
		TableFormatter tf = new TableFormatter(data, columnNames.split(","), crud);
		table = tf.getTable();
		table.setModel(new DefaultTableModel(data, columnNames.split(",")));
		for(int j = 0; j < 6; j++) {
			//TableColumn tColumn = table.getColumnModel().getColumn(j);
			//tColumn.setCellRenderer(new ColumnColorRenderer(Color.black, (j == 0 ?
			// Color.yellow : Color.white)));
		}
		tf.setData();
		
		//table.getTableHeader().setBackground(Color.black);
		//table.getTableHeader().setForeground(Color.yellow);
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add(table);
		//scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		//scrollPane.getVerticalScrollBar().setForeground(Color.yellow);
		
		scrollPane.setMinimumSize(tf.getScrollPanelSize());
		scrollPane.setPreferredSize(tf.getScrollPanelSize());
		add(scrollPane);
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		JFrame jf = new JFrame();
		
		CRUDBuddyTest t = new CRUDBuddyTest();
		t.assertTableViewerGiuIsOk();
		jf.setTitle("Test");
		Dimension onlySize = new Dimension(
		 t.scrollPane.getPreferredSize().width + 50, t.scrollPane.getPreferredSize().height + 50);
		jf.setMinimumSize(onlySize);
		jf.setMaximumSize(onlySize);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(t);
	}
	
	private class ColumnColorRenderer extends DefaultTableCellRenderer {
		Color backgroundColor, foregroundColor;
		
		public ColumnColorRenderer(Color backgroundColor, Color foregroundColor) {
			super();
			this.backgroundColor = backgroundColor;
			this.foregroundColor = foregroundColor;
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value,
		 boolean isSelected, boolean hasFocus, int row, int column) {
			Component cell =
			 super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			cell.setBackground(backgroundColor);
			cell.setForeground(foregroundColor);
			return cell;
		}
	}
}


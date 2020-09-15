import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class TableViewer extends JFrame{
	public static final JFrame JF_1 = new JFrame();
	private final JPanel panel;
	private final CRUDBuddy crud;
	private JScrollPane scrollPane;
	
	public TableViewer(CRUDBuddy crud) {
		scrollPane = new JScrollPane();
		panel = new JPanel();
		this.crud = crud;
	}
	
	public void setGui(String databaseName, String tableName)
	throws SQLException {
		
		ArrayList<String> temp = crud.readColumnNames(databaseName, tableName);
		String columnNames = (temp + "").substring(1, (temp + "").length() - 1);
		ResultSet rs = crud.query("Select " + columnNames + " from " + tableName);
		//JTable jt;
		int i = 0;
		ArrayList<Object[]> rows = new ArrayList<>();
		while(rs.next()) {
			i++;
			Object product_id = rs.getObject("product_id");
			Object quantity = rs.getInt("quantity");
			Object wholesale_cost = rs.getDouble("wholesale_cost");
			Object sale_price = rs.getDouble("sale_price");
			String supplier_id = rs.getString("supplier_id");
			
			rows.add(new Object[] {
			 product_id, quantity, wholesale_cost, sale_price, supplier_id
			});
		}
		
		Iterator<Object[]> row_it = rows.iterator();
		Object[][] data = new Object[i][6];
		for(
		 int i1 = 0;
		 i1 < data.length; i1++) {
			data[i1] = row_it.next();
		}
		
		TableFormatter tf = new TableFormatter(data, columnNames.split(","), crud);
		JTable jTable = tf.getTable();
		jTable.setModel(new DefaultTableModel(data, columnNames.split(",")));
		for(int j = 0; j < 6; j++) {
			//TableColumn tColumn = table.getColumnModel().getColumn(j);
			//tColumn.setCellRenderer(new ColumnColorRenderer(Color.black, (j == 0 ?
			// Color.yellow : Color.white)));
		}
		tf.setData();
		
		//table.getTableHeader().setBackground(Color.black);
		//table.getTableHeader().setForeground(Color.yellow);
		scrollPane = new JScrollPane();scrollPane.getViewport().add(jTable);
		//scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		//scrollPane.getVerticalScrollBar().setForeground(Color.yellow);
		
		scrollPane.setMinimumSize(tf.getScrollPanelSize());
		scrollPane.setPreferredSize(tf.getScrollPanelSize());
		
		panel.add(scrollPane);
		
		setTitle("Test");
		Dimension onlySize = new Dimension(
		 scrollPane.getPreferredSize().width + 50,
		 scrollPane.getPreferredSize().height + 50);
		setMinimumSize(onlySize);
		setMaximumSize(onlySize);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
	}
}

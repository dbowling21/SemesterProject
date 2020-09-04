import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Database class holds a collection of Table objects for relational operations,
 * and a buffer for deleted entries. A private class class HistoryEntry is used
 * to store the name, and entry. TODO: also store which table each entry in the
 * buffer came from
 */
public class Database {
	// Number of deleted entries to keep in the buffer.
	public static final int MAX_HISTORY_ENTRIES = 400;

	// Private class to facilitate holding on to deleted entry names and entries
	private class HistoryEntry {
		private String name;
		private DataEntry entry;

		public HistoryEntry(String name, DataEntry table) {
			this.name = name;
			this.entry = table;
		}
	}

	// holds a set of tables for future relational operations between different
	// tables
	private HashMap<String, Table> tables;

	// holds an array of MAX_HISTORY_ENTRIES
	private HistoryEntry[] oldEntries;

	// to reflect how many entries large the history buffer is.
	private int bufferIndex;

	public Database() {
		tables = new HashMap<>();
		oldEntries = new HistoryEntry[MAX_HISTORY_ENTRIES];
		bufferIndex = 0;
	}

	/**
	 * create a new table
	 * 
	 * @param name unique name of the table
	 * @return true if the table name is not taken.
	 */
	public boolean createTable(String name) {
		if (!tables.containsKey(name)) {
			Table newTable = new Table(name);
			tables.put(name, newTable);
			return true;
		}
		return false;
	}

	/**
	 * Creates a new entry in the specified table.
	 * 
	 * @param tableName name of an existing table.
	 * @param entry     The new DataEntry object to insert into the specified table.
	 * @return null if the productId is not present in the specified Table.
	 *         Otherwise, the old DataEntry object that was pushed out.
	 */
	public DataEntry createEntry(String tableName, DataEntry entry) {
		DataEntry oldEntry = null;
		if (tables.containsKey(tableName)) {
			Table table = tables.get(tableName);
			oldEntry = table.create(entry.getProductId(), entry);
			dataToHistory(oldEntry);
		}
		return oldEntry;
	}

	/**
	 * Selects and returns the string representation of an entire table
	 * 
	 * @param tableName name of an existing table.
	 * @return string representation the table
	 * @exception NullPointerException if the specified table does not exist.
	 */
	public String readTable(String tableName) {
		if (tables.containsKey(tableName)) {
			return tables.get(tableName).toString();
		}
		System.out.println("Error: The table " + tableName + " does not exist.");
		throw new NullPointerException();
	}

	/**
	 * Selects and returns the string representation of the specified entry in the
	 * specified table.
	 * 
	 * @param tableName name of an existing table.
	 * @param entryName name of an existing entry in the specified table.
	 * @return string representation the entry
	 */
	public String readEntry(String tableName, String entryName) {
		if (tables.containsKey(tableName)) {
			Table table = tables.get(tableName);
			return table.read(entryName).toReadableString();
		}
		System.out.println("Error: The table " + tableName + " does not exist.");
		throw new NullPointerException();
	}

	/**
	 * Generates new .csv files for every table in the Database object.
	 * 
	 * @return an array of File objets to write to the disk.
	 * @throws FileNotFoundException because IntelliJ said so.
	 */
	public File[] updateTables() throws FileNotFoundException {
		File[] files = new File[tables.size()];
		int i = 0;
		for (Table table : tables.values()) {
			files[i++] = table.update(table.getName() + ".csv");
		}
		return files;
	}

	/**
	 * Generates new .csv files for every table in the Database object.
	 * 
	 * @return a File objet to write to the disk.
	 * @throws FileNotFoundException because IntelliJ said so.
	 */
	public File updateTable(Table table) throws FileNotFoundException {
		return table.update(table.getName() + ".csv");
	}

	/**
	 * Looks in all tables to find the entry.
	 * 
	 * @param entry the DataEntry to search for in all tables
	 * @return an ArrayList of the tables that contain the specified entry
	 */
	public ArrayList<Table> find(DataEntry entry) {
		ArrayList<Table> foundTables = new ArrayList<>();
		for (Table table : tables.values()) {
			if (table.contains(entry)) {
				foundTables.add(table);
			}
		}
		return foundTables;
	}

	/**
	 * Removes the specified table from the Database collection of Table objects if
	 * it exists.
	 * 
	 * @param tableName name of a specified table.
	 * @return the table that was removed from the database, if it exists. Otherwise
	 *         null.
	 */
	public Table deleteTable(String tableName) {
		if (tables.containsKey(tableName)) {
			Table table = tables.get(tableName);
			tables.remove(tableName);
			return table;
		}
		return null;
	}

	/**
	 * Deletes the specified entry in the specified table.
	 * 
	 * @param tableName name of an existing table.
	 * @param entryName name of an existing entry in the specified table.
	 * @return string representation the entry
	 */
	public DataEntry deleteEntry(String tableName, String entryName) {
		if (tables.containsKey(tableName)) {
			Table table = tables.get(tableName);
			DataEntry oldEntry = table.delete(entryName);
			dataToHistory(oldEntry);
			return oldEntry;
		}
		return null;
	}

	/**
	 * Stores removed data entries in a buffer, if there is capacity left.
	 * 
	 * @param oldEntry DataEntry object to be stored
	 * @return true if the buffer had space to insert the old entry. Otherwise
	 *         false.
	 */
	private boolean dataToHistory(DataEntry oldEntry) {
		if (oldEntry != null) {
			if (bufferIndex < MAX_HISTORY_ENTRIES) {
				oldEntries[bufferIndex++] = new HistoryEntry(oldEntry.getProductId(), oldEntry);
			}
			return true;
		}
		return false;
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @param <T>
 */
public class Table<T> implements Iterable<T> {
	private TreeMap<String, DataEntry> data;
	private String name;
	
	/**
	 * A new table in the database
	 * @param name name of the table
	 */
	public Table(String name) {
		data = new TreeMap<>();
		this.name = name;
	}
	
	/**
	 * @param productId TODO: finish
	 * @param entry TODO: finish
	 * @return the the previous DataEntry associated with productId, or null if there was no 
	 * mapping for productId
	 * . (A null return can also indicate that the map previously associated null with key.)
	 */
	public DataEntry create(String productId, DataEntry entry) {
		return (data.put(entry.getProductId(), entry));
	}
	
	/**
	 * @param outputPath the directory in which to write the new .csv file
	 * @throws IllegalArgumentException if the file is not .csv
	 * @throws FileNotFoundException    if for some reason the file could not
	 *                                  be located
	 */
	public File update(String outputPath) throws FileNotFoundException, IllegalArgumentException {
		if(!outputPath.endsWith(".csv")) {
			throw new IllegalArgumentException("The file name must end with \".csv\"");
		}
		File outputFile = new File(outputPath);
		PrintWriter writer = new PrintWriter(outputFile);
		writer.write(toString() + "\n");
		return outputFile;
	}
	
	/**
	 * @param entry the entry to attempt to delete
	 * @return true if the entry matches one in the map. False otherwise.
	 */
	public DataEntry delete(String entry) {
		if(data.containsKey(entry)) {
			return null;
		}
		DataEntry temp = data.get(entry);
		data.remove(entry);
		return temp;
	}
	
	/**
	 * @param id key of the entry to return.
	 * @return null if the productID is not associated with a DataEntry.
	 */
	public DataEntry read(String id) {
		return data.get(id);
	}
	
	/**
	 * @return the String representation of the underlying TreeMap
	 */
	@Override public String toString() {
		StringBuilder s = new StringBuilder();
		for(DataEntry entry: data.values()) {
			s.append(entry).append("\n");
		}
		s.setLength(s.length() - 1);
		return s.toString();
	}
	
	/**
	 * @return a TreeMap iterator for enhanced for loops
	 */
	@Override public Iterator<T> iterator() {
		return new Iterator<>() {
			Map.Entry<String, DataEntry> current;
			final Iterator<Map.Entry<String, DataEntry>> iterator = data.entrySet().iterator();
			
			@Override public boolean hasNext() {
				return current != null;
			}
			
			@Override public T next() {
				Map.Entry<String, DataEntry> temp = iterator.next();
				current = iterator.next();
				return (T)temp;
			}
		};
	}
	
	public int size() {
		return data.size();
	}
	
	public String getName() {
		return name;
	}
} 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @param <T>
 */
public class Database<T> implements Iterable<T> {
	private TreeMap<String, DataEntry> data;
	
	public Database() {
		data = new TreeMap<>();
	}
	
	/**
	 * @param
	 * @return the the previous DataEntry associated with productId, or null if there was no mapping for productId
	 * . (A null return can also indicate that the map previously associated null with key.)
	 */
	public DataEntry create(String productId, DataEntry entry) {
		DataEntry oldEntry = (data.put(entry.getProductId(), entry));
		return oldEntry;
	}
	
	/**
	 * @throws IllegalArgumentException if the file is not .csv
	 * 
	 * @throws FileNotFoundException if for some reason the file could not
	 * be located
	 * 
	 * @param outputPath the directory in which to write the new .csv file
	 */
	public File update(String outputPath) throws FileNotFoundException, IllegalArgumentException {
		if(!outputPath.substring(outputPath.length() - 3).equals(".csv")) {
			throw new IllegalArgumentException("The file name must end with \".csv\"");
		}
		File outputFile = new File(outputPath);
		PrintWriter writer = new PrintWriter(outputFile);
		for(T dataEntry: this) {
			writer.println(dataEntry);
		}
		return outputFile;
	}
	
	public boolean delete(DataEntry entry) {
		if(data.containsKey(entry.getProductId())) {
			return false;
		}
		data.remove(entry.getProductId());
		return true;
	}
	
	/**
	 * @param
	 */
	public DataEntry read(String id) {
		return data.get(id);
	}
	
	@Override public String toString() {
		return data.toString();
	}
	
	/**
	 * @param
	 */
	@Override public Iterator<T> iterator() {
		return new Iterator<T>() {
			Map.Entry<String, DataEntry> current;
			final Iterator<Map.Entry<String, DataEntry>> iterator = data.entrySet().iterator();
			
			@Override public boolean hasNext() {
				return iterator != null;
			}
			
			/**
			 *
			 * @param
			 */
			@Override public T next() {
				Map.Entry<String, DataEntry> temp = iterator.next();
				current = iterator.next();
				return (T)temp;
			}
		};
	}
} 

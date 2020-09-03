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
	 */
	public boolean create(String PRODUCT_ID, DataEntry entry) {
		if(data.containsKey(entry.getProductId())) {
			return false;
		}
		data.put(entry.getProductId(), entry);
		return true;
	}
	
	/**
	 * @param
	 */
	public File update(String outputPath) throws FileNotFoundException {
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

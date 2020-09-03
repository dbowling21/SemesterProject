import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
	static DataEntry entry1 = new DataEntry(
	 "1234567890-=",
	 400,
	 34.98,
	 49.99,
	 "12345678"
	);
	
	static DataEntry entry2 = new DataEntry(
	 "=-0987654321",
	 900,
	 383.93,
	 899.99,
	 "87654321"
	);
	static DataEntry entry3 = new DataEntry(
	 "=-0987654321",
	 900,
	 383.93,
	 899.99,
	 "87654321"
	);
	public static final Table DB = new Table("test_table");
	
	//TODO: write some tests...
	
	@Test public void assertDataEntryEqualsTest() {
		DB.create(entry1.getProductId(), entry1);
		DB.create(entry2.getProductId(), entry2);
		assertNotEquals(entry1, entry2);
		assertEquals(entry3, entry2);
	}
	
	@Test public void assertDeletionTest() {
		DB.create(entry1.getProductId(), entry1);
		DB.create(entry2.getProductId(), entry2);
		DB.create(entry1.getProductId(), entry3);
		DB.delete(entry2.getProductId());
		assertEquals(DB.size(), 2);
	}
	
	@Test public void assertDataEntryToString() throws FileNotFoundException {
		DB.create(entry1.getProductId(), entry1);
		DB.create(entry2.getProductId(), entry2);
		DB.create(entry1.getProductId(), entry3);
		System.out.println(DB);
		File testFile = new File("fake.csv");
		PrintWriter writer = new PrintWriter(testFile);
		String lines = "1234567890-=,400,34.98,49.99,12345678\n" +
		"=-0987654321,900,383.93,899.99,87654321";
		writer.write(lines);
		assertEquals(DB.update("fake.csv"), testFile);
	}
}

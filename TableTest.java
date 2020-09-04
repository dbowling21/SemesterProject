import org.junit.Test;

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
	public static final Table TABLE = new Table("test_table");
	
	//TODO: write some tests...
	
	@Test public void assertDataEntryEqualsTest() {
		TABLE.create(entry1.getProductId(), entry1);
		TABLE.create(entry2.getProductId(), entry2);
		assertNotEquals(entry1, entry2);
		assertEquals(entry3, entry2);
	}
	
	@Test public void assertDeltionTest() {
		TABLE.create(entry2.getProductId(), entry1);
		assertEquals(entry3, TABLE.delete(entry2.getProductId()));
		assertNull(TABLE.delete(entry3.getProductId()));
	}
	
	@Test public void assertDeletionTest() {
		TABLE.create(entry1.getProductId(), entry1);
		TABLE.create(entry2.getProductId(), entry2);
		TABLE.create(entry1.getProductId(), entry3);
		TABLE.delete(entry2.getProductId());
		assertEquals(TABLE.size(), 2);
	}
	
	@Test public void assertDataEntryToString() throws FileNotFoundException {
		TABLE.create(entry1.getProductId(), entry1);
		TABLE.create(entry2.getProductId(), entry2);
		TABLE.create(entry1.getProductId(), entry3);
		System.out.println(TABLE);
		File testFile = new File("fake.csv");
		PrintWriter writer = new PrintWriter(testFile);
		String lines = "1234567890-=,400,34.98,49.99,12345678\n" +
		"=-0987654321,900,383.93,899.99,87654321";
		writer.write(lines);
		assertEquals(TABLE.update("fake.csv"), testFile);
	}
}

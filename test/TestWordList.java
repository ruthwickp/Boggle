import java.io.File;
import java.io.IOException;
import org.testng.annotations.*;

/**
 * This class performs basic tests for the WordList class.
 * @author rpathire
 *
 */
public class TestWordList {
	
	/** Test the WordList default constructor */
	@Test(groups = {"basic"})
	public void testDefaultConstructor() {
		WordList list = new WordList();
		assert list.size() == 0;
		assert !list.containsWord("word");
	}
	
	/** 
	 * Tests WordList adding word and contains word functionality with
	 * properly formatted words.
	 */
	@Test(groups = {"basic"})
	public void testAddWord() {
		WordList list = new WordList();
		list.addWord("first");
		list.addWord("second");
		list.addWord("third");
		
		// Makes sure the size matches and the words appear in list
		assert list.size() == 3;
		assert list.containsWord("first");
		assert list.containsWord("second");
		assert list.containsWord("third");
		// These words should not exist
		assert !list.containsWord("none");
		assert !list.containsWord("fourth");
	}
	
	/** Tests the adding word and containing word functionality with
	 * improperly formatted words. 
	 */
	@Test(groups = {"basic"})
	public void testAddWordExtra() {
		WordList list = new WordList();
		list.addWord("FiRst");
		list.addWord("   second");
		list.addWord("third   ");
		list.addWord("  fourth  ");
		
		// Makes sure words are properly formatted
		assert list.containsWord("first");
		assert list.containsWord("second");
		assert list.containsWord("third");
		assert list.containsWord("fourth");
	}
	
	/**
	 * Tests behavior when encountering a missing file.
	 * @throws IOException when file does not exist
	 */
	@Test(groups = {"fileio"}, expectedExceptions = {IOException.class})
	public void testMissingFile() throws IOException {
		File f = new File("missing.txt");
		assert !f.exists();
		WordList list = new WordList(f);
	}
	
	/** 
	 * Tests the WordList file constructor using 
	 * properly formatted words.
	 * @throws IOException when test file does not exist
	 */
	@Test(groups = {"fileio"})
	public void testFileConstructor() throws IOException {
		File f = new File("formattedWordList.txt");
		WordList list = new WordList(f);
		
		// Verifies the content and size of wordlist
		assert list.size() == 3;
		assert list.containsWord("first");
		assert list.containsWord("second");
		assert list.containsWord("third");
	}
	
	/**
	 * Tests the WordList file constructor using 
	 * improperly formatted words.
	 * @throws IOException when test file does not exist
	 */
	@Test(groups = {"fileio"})
	public void testFileConstructorUnformatted() throws IOException {
		File f = new File("unformattedWordList.txt");
		WordList list = new WordList(f);
		
		// Makes sure words are properly formatted
		assert list.containsWord("first");
		assert list.containsWord("second");
		assert list.containsWord("third");
		assert list.containsWord("fourth");
	}
	
	/**
	 * Tests the WordList file constructor using a list of
	 * non distinct words.
	 * @throws IOException when test file does not exist
	 */
	@Test(groups = {"fileio"})
	public void testFileConstructorNonDistinct() throws IOException {
		File f = new File("nonUniqueWordList.txt");
		WordList list = new WordList(f);
		
		// Makes sure contents and size match
		assert list.size() == 3;
		assert list.containsWord("first");
		assert list.containsWord("second");
		assert list.containsWord("third");
	}
	
	/**
	 * Tests the add word list method with all distinct values.
	 */
	@Test(groups = {"basic"})
	public void testAddWordListUnique() {
		// Create two distinct valued WordList
		WordList list1 = new WordList();
		WordList list2 = new WordList();
		list1.addWord("first");
		list1.addWord("second");
		list2.addWord("third");
		list2.addWord("fourth");
		list1.addWordList(list2);
		
		// Checks size and list 1 contents
		assert list1.size() == 4;
		assert list2.size() == 2;
		assert list1.containsWord("first");
		assert list1.containsWord("second");
		assert list1.containsWord("third");
		assert list1.containsWord("fourth");
		
		// Checks list 2 contents
		assert list2.containsWord("third");
		assert list2.containsWord("fourth");
		assert !list2.containsWord("first");
	}
	
	/**
	 * Tests the add word list method using non unique words
	 * between the two lists
	 */
	@Test(groups = {"basic"})
	public void testAddWordListNonUnique() {
		// Creates two WordLists with some common words
		WordList list1 = new WordList();
		WordList list2 = new WordList();
		list1.addWord("first");
		list1.addWord("second");
		list2.addWord("first");
		list2.addWord("third");
		list1.addWordList(list2);
		
		// Checks size and list 1 contents
		assert list1.size() == 3;
		assert list2.size() == 2;
		assert list1.containsWord("first");
		assert list1.containsWord("second");
		assert list1.containsWord("third");
		
		// Checks list 2 contents
		assert list2.containsWord("first");
		assert list2.containsWord("third");
		assert !list2.containsWord("second");
	}
	
	/**
	 * Tests the subtract word list method using distinct words.
	 */
	@Test(groups = {"basic"})
	public void testSubtractDistinct() {
		// Create two distinct valued WordList
		WordList list1 = new WordList();
		WordList list2 = new WordList();
		list1.addWord("first");
		list1.addWord("second");
		list2.addWord("third");
		list2.addWord("fourth");
		list1.subtract(list2);
		
		// Checks size and list 1 contents
		assert list1.size() == 2;
		assert list2.size() == 2;
		assert list1.containsWord("first");
		assert list1.containsWord("second");
		
		// Checks list 2 contents
		assert list2.containsWord("third");
		assert list2.containsWord("fourth");
	}
	
	/**
	 * Tests the subtract word list method using some common words.
	 */
	@Test(groups = {"basic"})
	public void testSubtractCommon() {
		// Create two WordLists with common words
		WordList list1 = new WordList();
		WordList list2 = new WordList();
		list1.addWord("first");
		list1.addWord("second");
		list2.addWord("first");
		list2.addWord("third");
		list1.subtract(list2);
		
		// Checks size and list 1 contents
		assert list1.size() == 1;
		assert list2.size() == 2;
		assert !list1.containsWord("first");
		assert list1.containsWord("second");
		
		// Checks list 2 contents
		assert list2.containsWord("first");
		assert list2.containsWord("third");
	}

}

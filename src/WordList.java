import java.util.HashSet;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * This class consists of methods that operate and manage
 * a list of valid words.
 * @author rpathire
 * 
 */
public class WordList {
	/** Collection to store list of words */
	private HashSet<String> words;
	
	/**
	 * Constructs a new empty word list.
	 */
	public WordList() {
		words = new HashSet<String>();
	}
	
	/**
	 * Constructs a word list from words from the file.
	 * 
	 * @param file File containing list of words in each line, cannot be null
	 * 
	 * @throws IOException if file cannot be opened or issues occur with
	 * file load
	 */
	public WordList(File file) throws IOException {
		words = new HashSet<String>();
		
		// Creates a BufferedReader to read words
		BufferedReader in  = new BufferedReader(new FileReader(file));
		
		// Reads word in each line and adds it to list
		while (true) {
			String word = in.readLine();
			if (word == null)
				break;
			addWord(word);
		}
		// Closes BufferedReader
		in.close();
	}
	
	/**
	 * Returns number of elements in the word list.
	 * 
	 * @return	number of elements in word list
	 */
	public int size() {
		return words.size();
	}
	
	/**
	 * Adds the word to the word list if not already present.
	 * Before inserting, trims leading and trailing space of word and
	 * converts all letters to lowercase. If string is empty, does
	 * nothing.
	 * 
	 * @param word word to be added to the set
	 */
	public void addWord(String word) {
		word = processWord(word);
		if (word.length() != 0) {
			words.add(word);
		}
	}
	
	/**
	 * Returns true if the list contains the specified word.
	 * Before checking, method processes the argument.
	 *  
	 * @param word word to check in list
	 * 
	 * @return whether the word is in the list
	 */
	public boolean containsWord(String word) {
		return words.contains(processWord(word));
	}
	
	/**
	 * Adds all words from another word list to this word list.
	 * 
	 * @param other word list to add words from
	 */
	public void addWordList(WordList other) {
		words.addAll(other.words);
	}
	
	/**
	 * Computes the set difference between the word lists. In other
	 * words, this word list will only contain words not in the
	 * other word list.
	 * 
	 * @param other word list to compute set difference
	 */
	public void subtract(WordList other) {
		for (String w : other.words) {
			// If the word appears in our list, we remove it
			if (containsWord(w)) {
				words.remove(w);
			}
		}
	}
	
	/**
	 * Removes leading and trailing whitespace from word and returns
	 * lower-case version of the word.
	 * 
	 * @param word word to be processed
	 * 
	 * @return new processed string
	 */
	private String processWord(String word) {
		return word.trim().toLowerCase();
	}

}

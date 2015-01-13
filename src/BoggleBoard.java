import java.util.Random;

/**
 * This class represents an NxN Boggle board and contains methods
 * that configures it. The frequency of letters used for the game is
 * based on the letter distribution of Loggle.
 * @author rpathire
 * 
 */
public class BoggleBoard {
	/** Cell grid to store letters */
	private String[][] board;
	
	/** Default Boggle board size */
	public static final int DEFAULT_SIZE = 4;
	
	/**
	 * Constructs a Boggle board of dimension 4x4 and initializes
	 * the board to a random configuration of letters.
	 */
	public BoggleBoard() {
		this(DEFAULT_SIZE);
	}
	
	/**
	 * Constructs a Boggle board of dimension size x size.
	 * Initializes the board to a random configuration of letters.
	 * 
	 * @param size size of square Boggle board
	 */
	public BoggleBoard(int size) {
		board = new String[size][size];
		generateBoard();
	}
	
	/**
	 * Initializes the board to a random configuration of letters.
	 * The letter Q is represented as Qu.
	 */
	private void generateBoard() {
		Random rand = new Random();
		String[] letters = createLetterDistribution();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				// Generates a letter and inserts it into the cell
				board[i][j] = letters[rand.nextInt(letters.length)];
			}
		}
	}
	
	/**
	 * Creates a letter distribution for Boggle and returns
	 * its representation. This representation is based of Loggle
	 * which contains 96 letters.
	 * @return list of letters for boggle game
	 */
	private String[] createLetterDistribution() {
		String[] letters = {"A","A","A","A","A","A","A","A",
				"B","B","B","C","C","C","D","D","D","D","E","E","E",
				"E","E","E","E","E","E","E","F","F","G","G","G","H",
				"H","H","I","I","I","I","I","I","I","J","K","K","L",
				"L","L","L","L","M","M","M","N","N","N","N","N","O",
				"O","O","O","O","O","P","P","P","Qu","R","R","R","R",
				"S","S","S","S","S","T","T","T","T","T","U","U","U",
				"U","V","V","W","W","X","Y","Y","Y","Z"};
		assert letters.length == 96;
		return letters;
	}
	
	/**
	 * Returns size of Boggle board.
	 * @return size of boggle board.
	 */
	public int size() {
		return board.length;
	}
	
	/**
	 * Returns string at Boggle board position (x, y).
	 * @param x row of the cell position
	 * @param y column of the cell position
	 * @return string at the boggle board (x, y)
	 */
	public String getCell(int x, int y) {
		return board[x][y];
	}
	
	

}

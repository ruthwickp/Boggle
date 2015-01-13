import java.util.Arrays;

import org.testng.annotations.*;
/**
 * Tests the Boggle board class functionality
 * @author rpathire
 *
 */
public class TestBoggleBoard {
	/**
	 * Tests the default constructor
	 */
	@Test(groups = {"basic"})
	public void testDefaultConstructor() {
		BoggleBoard board = new BoggleBoard();
		// Checks size of board and makes sure it is valid
		assert board.size() == BoggleBoard.DEFAULT_SIZE;
		assert validBoard(board);
	}
	
	/**
	 * Tests the one argument constructor.
	 */
	@Test(groups = {"basic"})
	public void testOneArgConstructor() {
		BoggleBoard board = new BoggleBoard(10);
		// Checks size of board and makes sure it is valid
		assert board.size() == 10;
		assert validBoard(board);
	}
	
	/**
	 * Checks if all the cells in the Boggle board contains
	 * valid strings.
	 */
	private boolean validBoard(BoggleBoard board) {
		// Stores array of valid strings
		String[] validStr = {"A","B","C","D","E","F","G","H","I","J","K","L","M",
				"N","O","P","Qu","R","S","T","U","V","W","X","Y","Z"};
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				String cell = board.getCell(i, j);
				// If a cell is null, board is invalid
				if (cell == null)
					return false;
				// If cell is not a valid string, board is invalid
				else if (!Arrays.asList(validStr).contains(cell))
					return false;
			}
		}
		return true;
	}

}

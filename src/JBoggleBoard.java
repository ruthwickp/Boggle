import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.GridLayout;

/**
 * The class creates the Boggle UI with a grid of NxN 
 *  boggle buttons, allowing users to create words from
 *  letters on the button.
 * @author rpathire
 * 
 */
public class JBoggleBoard extends JPanel {
	/** Dimension of board */
	private int size;
	
	/** Boggle game board instance */
	private BoggleBoard board;
	
	/** List of selected Boggle buttons */
	private ArrayList<JBoggleButton> selectedButtons;
	
	/** List of all Boggle buttons */
	private ArrayList<JBoggleButton> buttons;
	
	/**
	 * Creates a Boggle board display with the given dimension.
	 * @param size size of the boggle board.
	 */
	public JBoggleBoard(int size) {
		// Makes the panel a grid layout of size x size
		super(new GridLayout(size, size));
		
		// Initializes size and board
		this.size = size;
		board = new BoggleBoard(size);
		
		// Creates empty selected button list
		selectedButtons = new ArrayList<JBoggleButton>();
		// Creates list to contains all buttons
		buttons = new ArrayList<JBoggleButton>();
	}
	
	
	/**
	 * Displays the new Boggle board.
	 * @param b Boggle board to display.
	 * @throws IllegalArgumentException if sizes of boards don't match.
	 */
	public void setBoard(BoggleBoard b) throws IllegalArgumentException {
		// Throws exception if sizes don't match
		if (b.size() != size) 
			throw new IllegalArgumentException();
		
		// Updates all the buttons state
		for (int i = 0; i < b.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				// Creates button and customizes it accordingly
				JBoggleButton button = new JBoggleButton(i, j);
				button.setText(b.getCell(i, j));
				button.setState(JBoggleButton.State.AVAILABLE);
				
				// Provides action handling and displays button
				button.addActionListener(new BoggleButtonHandler());
				add(button);
				
				// Stores buttons in list
				buttons.add(button);
			}
		}
	}
	
	/**
	 * Inner class handles Boggle button presses by changing their
	 * state.
	 * @author rpathire
	 *
	 */
	private class BoggleButtonHandler implements ActionListener {
		
		// Changes the state of the button when pressed
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			// Makes sure a boggle button was pressed
			if (obj instanceof JBoggleButton) {
				JBoggleButton button = (JBoggleButton) obj;
				
				// If button is available, we make it selected 
				// and update display
				if (button.getState() == JBoggleButton.State.AVAILABLE) {
					button.setState(JBoggleButton.State.SELECTED);
					selectedButtons.add(button);
					displayUpdate();
				}
				// If button is selected, we remove all the selected buttons
				// past the current one.
				else if (button.getState() == JBoggleButton.State.SELECTED) {
					int index = selectedButtons.indexOf(button);
					// Find list of buttons to remove and removes them
					selectedButtons.subList(
							index + 1, selectedButtons.size()).clear();;
					
					// Updates display
					displayUpdate();
				}
			}
		}
	}
	
	/**
	 * Updates all the buttons states. If no buttons are selected,
	 * we set all of them available. Else, make buttons only adjacent
	 * to the last selected button available.
	 */
	private void displayUpdate() {
		// If no buttons are selected, we make all of the available
		if (selectedButtons.isEmpty()) {
			for (JBoggleButton b : buttons) {
				b.setState(JBoggleButton.State.AVAILABLE);
			}
		}
		else {
			// First make all the buttons unavailable
			for (JBoggleButton b : buttons) {
				b.setState(JBoggleButton.State.UNAVAILABLE);
			}
			
			// Changes state of selected buttons
			for (JBoggleButton b : selectedButtons) {
				b.setState(JBoggleButton.State.SELECTED);
			}
			
			// Sets neighbors of last button to be available
			JBoggleButton lastButton = 
					selectedButtons.get(selectedButtons.size() - 1);
			for (JBoggleButton b : buttons) {
				// The neighbor must be unavailable and neighbor the last
				// button
				if (b.getState() == JBoggleButton.State.UNAVAILABLE &&
						areNeighbors(lastButton, b)) {
					b.setState(JBoggleButton.State.AVAILABLE);
				}
			}
		}
	}
	
	/**
	 * Returns whether the buttons are neighbors.
	 * @param b1 first button to check if it is a neighbor.
	 * @param b2 second button to check if it is a neighbor.
	 * @return whether the buttons are neighbors.
	 */
	private boolean areNeighbors(JBoggleButton b1, JBoggleButton b2) {
		int x = b1.getBoardX();
		int y = b1.getBoardY();
		int neighborX = b2.getBoardX();
		int neighborY = b2.getBoardY();
		
		// Returns whether the buttons are neighbors
		return (x - 1 <= neighborX && neighborX <= x + 1 &&
				y - 1 <= neighborY && neighborY <= y + 1);
	}
	
	/**
	 * Clears out all selected buttons and updates display.
	 */
	public void clearSelections() {
		selectedButtons.clear();
		displayUpdate();
	}
	
	/**
	 * Iterates through all the selected buttons and returns a
	 * concatenated word from all the letters. If no letters are 
	 * selected, empty string is returned.
	 * 
	 * @return concatenated word from letters selected or empty string if
	 * no words are selected.
	 */
	public String getWord() {
		String word = "";
		for (JBoggleButton but : selectedButtons) {
			word += but.getText();
		}
		return word;
	}
	
	/**
	 * Simple main method to test our GUI.
	 * @param args
	 */
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              JFrame f = new JFrame("BoggleTest");
              f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

              JBoggleBoard boardGUI = new JBoggleBoard(BoggleBoard.DEFAULT_SIZE);
              f.add(boardGUI);

              f.pack();
              f.setVisible(true);
              f.setSize(300, 300);

              boardGUI.setBoard(new BoggleBoard());
            }
        });
    }}

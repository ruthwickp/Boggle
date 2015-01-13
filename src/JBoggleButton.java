import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * This class presents a customized button derived from
 * JButton and is used for playing Boggle.
 * 
 * Class inherits methods from JButton that allows text
 * manipulation for the button such as setText(String) 
 * and getText().
 * @author rpathire 
 *
 */
public class JBoggleButton extends JButton {
	/** Location of button on grid in x direction */
	private int x;
	
	/** Location of button on grid in y direction */
	private int y;
	
	/** Stores state of Boggle button */
	private State buttonState;
	
	/** Possible border states for button */
	private static final int LINE_BORDER_SIZE = 3;
	private static final Border UNAVAILABLE_BORDER =
			BorderFactory.createLineBorder(Color.gray, LINE_BORDER_SIZE);
	private static final Border AVAILABLE_BORDER = 
			BorderFactory.createLineBorder(Color.green, LINE_BORDER_SIZE);
	private static final Border SELECTED_BORDER = 
			BorderFactory.createLineBorder(Color.red, LINE_BORDER_SIZE);
	
	/**
	 * Possible states of Boggle button.
	 * UNAVAILABLE - User cannot select this button
	 * AVAILABLE - Button is not selected and User can choose it
	 * SELECTED - Button is selected and is part of ward
	 * @author rpathire
	 *
	 */
	public enum State {
		UNAVAILABLE, 
		AVAILABLE,
		SELECTED
	}
	
	/**
	 * Constructs a Boggle button with the given x and 
	 * y coordinate. Also redesigns button display 
	 * to make it more readable and makes button default state
	 * AVAILABLE.
	 * @param xCoord the x coordinate location of button
	 * @param yCoord the y coordinate location of button
	 */
	public JBoggleButton(int xCoord, int yCoord) {
		// Location must be nonnegative
		assert xCoord >= 0;
		assert yCoord >= 0;
		x = xCoord;
		y = yCoord;
		
		// Designs button display by changing font
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		setFont(f);
		
		// Sets button state to AVAILABLE
		setState(State.AVAILABLE);
	}
	
	/**
	 * Returns the x coordinate location for the button.
	 * @return x coordinate location for button.
	 */
	public int getBoardX() {
		return x;
	}
	
	/**
	 * Returns the y coordinate location for the button.
	 * @return y coordinate location for button.
	 */
	public int getBoardY() {
		return y;
	}
	
	
	/**
	 * Returns state of button.
	 * @return state of button.
	 */
	public State getState() {
		return buttonState;
	}
	
	/**
	 * Sets the button state to the new state and modifies
	 * buttons display accordingly.
	 * UNAVAILABLE state disables button and creates a gray border
	 * AVAILABLE state enables button and creates a green border
	 * SELECTED state enables button and creates a red border
	 * @param state the new state for the button.
	 */
	public void setState(State state) {
		buttonState = state;
		switch (state) {
			// Disables button
			case UNAVAILABLE:
				setEnabled(false);
				setBorder(UNAVAILABLE_BORDER);
				break;
			// Enables button with green border
			case AVAILABLE:
				setEnabled(true);
				setBorder(AVAILABLE_BORDER);
				break;
			// Enables button with red border
			case SELECTED:
				setEnabled(true);
				setBorder(SELECTED_BORDER);
				break;
			// Should never come here
			default:
				break;
		}
	}
}

package RobotSokoban;
/**
 * This class is creating a teleport point where player goes in
 * @author Serkan delil
 * @version 1.0
 * @since 12.12.2017
 */
public class TeleportIn extends Figure {
	/**
	 * constructor that initialize location and image of object 
	 * @param row the y coordinate of object
	 * @param col the x coordinate of object
	 * @param teleportin is name of image file
	 */
	public TeleportIn(int row, int col, String teleportin) {
		super(row, col, teleportin);
	}
}

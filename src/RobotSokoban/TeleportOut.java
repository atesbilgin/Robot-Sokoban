package RobotSokoban;
/**
 * This class is creating a teleport point where player goes out
 * @author Serkan delil
 * @version 1.0
 * @since 12.12.2017
 */
public class TeleportOut extends Figure {
	/**
	 * constructor that initialize location and image of object 
	 * @param row the y coordinate of object
	 * @param col the x coordinate of object
	 * @param teleportout is name of image file
	 */
	public TeleportOut(int row, int col, String teleportout) {
		super(row, col, teleportout);
	}
}

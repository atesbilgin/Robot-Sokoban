package RobotSokoban;
/**
 * This class is creating ways have just 1 direction
 * @author Serkan delil
 * @version 1.0
 * @since 12.12.2017
 */
public class Oneway extends Figure {
	int direction; // up = 1,down = 2, right = 3,left = 4,
	/**
	 * constructor that initialize location,direction and image of object 
	 * @param row the y coordinate of object
	 * @param col the x coordinate of object
	 * @param oneway is name of image file
	 * @param direction of way
	 */
	public Oneway(int row, int col, String oneway,int direction) {
		super(row, col, oneway);
		this.direction = direction;
	}

}

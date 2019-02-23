/**
 * This class is the character that represents the edges of the map
 * @author Ekin Üstündað
 * @version 1.0
 * @since 12.12.2017
 */
package RobotSokoban;
public class Wall extends Figure {
 
 /**
 * Constructor that initializes the location and the image of the object
 * @param row the y-coordinate of the object
 * @param column the X-coordinate of the object
 * @param filename the image file of the objet
 */
  public Wall(int row, int col, String wall) {
  super(row, col, wall);
 }
}
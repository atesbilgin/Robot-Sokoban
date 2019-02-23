/**This class represents the paths in the game that Robot can walk freely
 * @author Ekin Üstündað
 * @version 1.0
 * @since 12.12.2017
*/
package RobotSokoban;

public class Path extends Figure {
 /**Constructor that initializes the location and the image of the object
 * @param row the y-coordinate of the object
 * @param column the X-coordinate of the object
 * @param filename the image file of the objet
 */
 public Path(int row, int col, String path) {
  super(row, col, path);
 }
}
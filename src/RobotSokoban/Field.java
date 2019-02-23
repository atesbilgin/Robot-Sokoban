/**This class represents the checkpoints in the game that are used to complete the game
 * @author Ekin Üstündað
 * @version 1.0
 * @since 12.12.2017
*/
package RobotSokoban;
public class Field extends Figure {
 
 /**Constructor that initializes the location and the image of the object
 * @param row the y-coordinate of the object
 * @param column the X-coordinate of the object
 * @param filename the image file of the objet
 */
  public Field(int row, int col, String field) {
  super(row, col, field);
 }
}

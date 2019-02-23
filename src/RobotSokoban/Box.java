/**This class represents the boxes in the game that are used to complete the game
 * @author Ekin Ustundag
 * @version 1.0
 * @since 12.12.2017
*/
package RobotSokoban;

public class Box extends Figure {
 
 /**Constructor that initializes the location and the image of the object
 * @param row the y-coordinate of the object
 * @param column the X-coordinate of the object
 * @param filename the image file of the object
 */
 public Box(int row, int col, String filename) {
  super(row, col, filename);
 }
  /** Moves the object according to given parameters
  *@param row the change in Y-coordinate of the object
  *@param col the change in X-coordinate of the object
  */
 public void move(int row, int col){
  int row1 = this.getRow() + row;
  int col1 = this.getCol() + col;
  this.setRow(row1);
  this.setCol(col1);
 }
}
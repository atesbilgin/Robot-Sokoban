/**This abstract class is the super class of all the characters in the game
 * @author Ekin Üstündað
 * @version 1.0
 * @since 12.12.2017
*/
package RobotSokoban;
 import java.awt.*;
 import java.net.*;
 import javax.swing.*;
public abstract class Figure {
 protected int row;
 protected int col;
 protected Image image;
 /**Constructor that initializes the location and the image of the object
 * @param row the y-coordinate of the object
 * @param column the X-coordinate of the object
 * @param filename the image file of the objet
 */
 public Figure(int row,int col,String filename){
  this.row = row;
  this.col = col;
  URL loc = this.getClass().getResource(filename);
  ImageIcon pic = new ImageIcon(loc);
  Image image = pic.getImage();
  this.image = image; 
 }
 /**Returns the image of the object
  *@return image the image of the object
  */
 public Image getImage(){
  return this.image;
 }
 
 //Changes the image of the object
 public void setImage(String image){
  URL loc = this.getClass().getResource(image);
  ImageIcon pic = new ImageIcon(loc);
  Image image1 = pic.getImage();
  this.image = image1;
 }
 /**Returns the row of the object
  * @return row the Y-coordinate of the object
  */
 public int getRow(){
  return this.row;
 }
  /**Returns the column of the object
  * @return this.col the x-coordinate of the object
  */
 public int getCol(){
  return this.col;
 }
 /**
  * Changes the Y-coordinate of the object
  * @param row y coordinate of object
  */
 public void setRow(int row){
  this.row = row;
 }
 /**
  * Changes the Y-coordinate of the object
  * @param col x coordinate of object
  */
 public void setCol(int col){
  this.col = col;
 }
}

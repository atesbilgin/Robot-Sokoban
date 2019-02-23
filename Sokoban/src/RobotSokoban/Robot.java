
package RobotSokoban;
/**
 * This class is creating a player we control
 * @author Serkan delil
 * @version 1.0
 * @since 12.12.2017
 */
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class Robot extends Figure {
	int energy;
	int direction;
	/**
	 * constructor that initialize location and image of object 
	 * @param row the y coordinate of object
	 * @param col the x coordinate of object
	 * @param robot is name of image file
	 */
	public Robot(int row, int col, String robot) {
		super(row, col, robot);
		energy = 200;
	}
	/**
	  * This method plays the sound affects according to the file parameter it take
	  * @param File Sound: File parameter that will be played
	  */
	 public static void PlaySound(File sound)
	 {
	   
	   //catches exception if file cannot found
	  try
	  {
	   //Creates a Clip object to load the sound and play it
	   Clip clip = AudioSystem.getClip();
	   clip.open(AudioSystem.getAudioInputStream(sound));
	   clip.start();
	   
	   //sleeps the program while music is playing
	   Thread.sleep(clip.getMicrosecondLength()/1000);
	  }catch(Exception e)
	  {
	  }
	 }

	/**
	 * setting energy of robot
	 * @param e energy of robot
	 */
	public void setEnergy(int e){
		energy = e;
	}
	/**
	 * getting energy of robot
	 * @return energy of robot
	 */
	public int getEnergy(){
		return energy;
	}
	/**
	 * moves the robot according to parameters also this method changing image of robot according to direction of moves
	 * @param row the y coordinate of object
	 * @param col the x coordinate of object
	 */
	public void move(int row, int col){
		//sound part of move
		
		File Step = new File("sound/stepSound.WAV");
		PlaySound(Step);
		//move part
		int row1 = this.getRow() + row;
		int col1 = this.getCol() + col;
		this.setRow(row1);
		this.setCol(col1);
		this.energy--;
		// put up image of robot
		if(row == 0 && col == -48){
			setImage("uprobot.png");
			direction = 1;
		}
		// put down(normal) image of robot
		else if (row == 0 && col == 48){
			setImage("robot.png");
			direction = 2;
		}
		// put right image of robot
		else if (row == 48 && col == 0){
			setImage("rightrobot.png");
			direction = 3;	
		}
		// put left image of robot
		else if (row == -48 && col == 0){
			setImage("leftrobot.png");
			direction = 4;
		}

	}



}

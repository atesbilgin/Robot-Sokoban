package RobotSokoban;
/**
 * This level class is a child class of the JPanel class.
 * It creates and displays a level panel, which user can choose the level it wants to play.
 * @author Ateþ Bilgin-Ekin Ustundag
 * @version 1.0
 * @since  12.12.2017
 */
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Level extends JPanel implements ActionListener {
	//variables
	protected JButton b1, b2, b3, b4, b5, b6, b7, b8;   
	public static int level;   
	//constructor
	public Level()
	{
		//creates JButton objects
		b1 = new JButton("level 1");
		b2 = new JButton("Level 2");
		b3 = new JButton("Level 3");
		b4 = new JButton("Level 4");
		b5 = new JButton("Level 5");
		b6 = new JButton("Level 6");
		b7 = new JButton("Level 7");
		b8 = new JButton("Level 8");
		
		//adds ActionListener to the buttons
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		
		//sets the background colors of the methods
		b1.setBackground(Color.green);
		b2.setBackground(Color.blue);
		b3.setBackground(Color.CYAN);
		b4.setBackground(Color.pink);
		b5.setBackground(Color.magenta);
		b6.setBackground(Color.ORANGE);
		b7.setBackground(Color.RED);
		b8.setBackground(Color.YELLOW);
		
		//creates two more JPanel objects
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		//adds buttýns to the j1 and j2 panels and adjusts their layouts in Box layout
		p1.setLayout (new BoxLayout (p1, BoxLayout.X_AXIS));
		p1.add(b1);
		p1.add (javax.swing.Box.createRigidArea(new Dimension(20, 0)));
		p1.add(b2);
		p1.add (javax.swing.Box.createRigidArea(new Dimension(20, 0)));
		p1.add(b3);
		p1.add (javax.swing.Box.createRigidArea(new Dimension(20, 0)));
		p1.add(b4);
		p1.add (javax.swing.Box.createRigidArea(new Dimension(20, 0)));

		p2.add(b5);
		p2.add (javax.swing.Box.createRigidArea(new Dimension(20, 0))); 
		p2.add(b6);
		p2.add (javax.swing.Box.createRigidArea(new Dimension(20, 0)));
		p2.add(b7);
		p2.add (javax.swing.Box.createRigidArea(new Dimension(20, 0)));
		p2.add(b8);
		p2.add (javax.swing.Box.createRigidArea(new Dimension(20, 0)));
		p2.setLayout (new BoxLayout (p2, BoxLayout.X_AXIS));
		//sets the background colors of the j1 and j2 and the main panel
		p1.setBackground(Color.green);
		p2.setBackground(Color.green);      
		setBackground(Color.orange);
		//Adjusts the main panels layout and adds j1 and j2 to te main panel
		setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
		add (javax.swing.Box.createRigidArea(new Dimension(20, 20)));
		add(p1);
		add (javax.swing.Box.createRigidArea(new Dimension(20, 20)));
		add(p2);
		add (javax.swing.Box.createRigidArea(new Dimension(20, 20)));
	}
	//Levels, W-wall, P-path, B-box, F-field, R-robot, l-left one way, d-down one way, u-up one way, r-right one way,
	 // I-teleportin, O-teleportout
	 static String[][] level1 = new String[][]{{"W","W","W","W","W","W"," "},
	  {"W","P","B","P","F","W","W"},//25 point
	  {"W","F","P","P","B","P","W"},
	  {"W","P","P","B","R","F","W"},
	  {"W","W","W","W","W","W","W"}};

	 static String[][] level2 = new String[][]{{"W","W","W","W","W","W","W"},//PULL
	   {"W","B","P","P","F","R","W"},//35 point
	   {"W","W","W","W","W","W","W"}};

	 static String[][] level3 = new String[][]{{"W","W","W","W","W","W","W","W","W","W"},//SWÝTCH
	    {"W","R","P","B","B","P","P","F","F","W"},//45
	    {"W","W","W","W","W","W","W","W","W","W"}};

	 static String[][] level4 = new String[][]{{"W","W","W","W","W","W","W","W","W"},
	     {"W","R","P","P","W","P","B","F","W"},//20
	     {"W","F","B","P","r","P","P","P","W"},
	     {"W","P","P","P","W","P","P","P","W"},
	     {"W","W","W","W","W","W","W","d","W"},
	     {"W","F","B","P","l","P","P","P","W"},
	     {"W","P","P","P","W","F","B","P","W"},
	     {"W","W","W","W","W","W","W","W","W"}};

	  static String[][] level5 = new String[][]{{"W","W","W","W","W","W","W","W","W"},//TELEPORT
	      {"W","P","P","B","P","F","W","F","W"},//20
	      {"W","F","B","P","I","W","W","P","W"},
	      {"W","P","P","P","W","W","P","B","W"},
	      {"W","P","P","W","W","W","P","P","W"},
	      {"W","P","W","W","P","P","P","P","W"},
	      {"W","R","W","O","P","P","B","F","W"},
	      {"W","W","W","W","W","W","W","W","W"}};

	  static String[][] level6 = new String[][]{{"W","W","W","W","W","W","W"," "},//90
	       {"W","P","P","P","P","P","W","W"},
	       {"W","P","W","W","B","B","P","W"},
	       {"W","P","W","P","P","W","P","W"},
	       {"W","P","P","P","P","W","P","W"},
	       {"W","F","W","P","P","r","P","W"},
	       {"W","F","P","R","W","W","W","W"},
	       {"W","W","W","W","W"," "," ","W"}};

	   static String[][] level7 = new String[][]{{"W","W","W","W","W","W","W"},//35
	        {"W","R","P","B","F","P","W"},
	        {"W","u","W","W","W","d","W"},
	        {"W","I","W","F","W","P","W"},
	        {"W","P","W","B","W","P","W"},
	        {"W","P","W","O","W","P","W"},
	        {"W","P","W","W","W","P","W"},
	        {"W","P","P","P","l","P","W"},
	        {"W","P","B","P","W","W","W"},
	        {"W","P","P","F","W"," "," "},
	        {"W","W","W","W","W"," "," "}};

	   static String[][] level8 = new String[][]{{"W","W","W","W","W"," ","W","W","W","W","W"},//200
	         {"W","P","P","P","W","W","W","P","P","P","W"},
	         {"W","P","P","P","P","W","W","P","P","P","W"},
	         {"W","W","P","P","P","P","B","B","P","W"," "},
	         {" ","W","P","P","W","P","P","B","P","W"," "},
	         {" ","W","W","W","P","P","W","P","P","W"," "},
	         {" "," "," ","W","P","R","W","W","W","W"," "},
	         {" "," "," ","W","P","P","P","P","W"," "," "},
	         {" ","W","W","W","P","W","W","P","W"," "," "},
	         {" ","W","P","P","P","P","W","P","W"," "," "},
	         {" ","W","F","F","P","F","W","P","W"," "," "},
	         {" ","W","W","P","W","W","W","P","W"," "," "},
	         {" "," ","W","P","P","P","P","P","W"," "," "},
	         {" "," ","W","W","W","W","W","W","W"," "," "}};
	         
	          /*Listens the buttons in the screen and initializes the level
	          *@param event the button pressed event
	          */
	         public void actionPerformed(ActionEvent event) {
	          if (event.getSource() == b1)//First level
	          {     
	           level = 0;
	           RobotSokoban next = new RobotSokoban();
	           next.setVisible(true);
	          }
	          else if (event.getSource() == b2)//Second level
	          {       
	           level = 1;
	           RobotSokoban next = new RobotSokoban();
	           next.setVisible(true);
	          }
	          else if (event.getSource() == b3)//Third level
	          {       
	           level = 2;
	           RobotSokoban next = new RobotSokoban();
	           next.setVisible(true);
	          }
	          else if (event.getSource() == b4)//Fourth level
	          {      
	           level = 3;
	           RobotSokoban next = new RobotSokoban();
	           next.setVisible(true);
	          }
	          else if (event.getSource() == b5)//Fifth level
	          {      
	           level = 4;
	           RobotSokoban next = new RobotSokoban();
	           next.setVisible(true);
	          }
	          else if (event.getSource() == b6)//Sixth level
	          {
	           level = 5;
	           RobotSokoban next = new RobotSokoban();
	           next.setVisible(true);
	          }
	          else if (event.getSource() == b7)//Seventh level
	          {
	           level = 6;
	           RobotSokoban next = new RobotSokoban();
	           next.setVisible(true);
	          }
	          else if (event.getSource() == b8)//Last level
	          {

	           level = 7;
	           RobotSokoban next = new RobotSokoban();
	           next.setVisible(true);
	          }
	         }
	         // Array to store Levels
	         private static String[][][] allmaps = {level1,level2,level3,level4,level5,level6,level7,level8};
	         /*Determines the energy used in the level
	          *return the amount of energy needed in corresponding level
	          */
	         public static int getLevelEnergies(){
	          if(level == 0)
	           return 25;
	          else if(level == 1)
	           return 35;
	          else if(level == 2)
	           return 45;
	          else if(level == 3)
	           return 20;
	          else if(level == 4)
	           return 20;
	          else if(level == 5)
	           return 90;
	          else if(level == 6)
	           return 35;
	          else if(level == 7)
	           return 200;
	          else
	           return 0;
	         }  
	         
	         /*Determines the current level of the game
	          *@return level the current level
	          */
	         public static String[][] getLevel(){
	          return allmaps[level];
	         }
	         
	         //Creates a frame and fills it with level map
	         public static void ShowGraphics() {

	          JFrame frame = new JFrame("RobotSokoban");

	          Level newContentPane = new Level();
	          newContentPane.setOpaque(true);
	          frame.setContentPane(newContentPane);

	          frame.pack();
	          frame.setVisible(true);
	         }
	}
package RobotSokoban;

import javax.swing.JFrame;
/**
 * This RobotSokoban class is a child class of a JFrame class.
 * It creates a Board object, which is a JPanel, and displays Board on itself.
 * @author Ateþ Bilgin
 * @version 1.0
 * @since  12.12.2017
 */
public final class RobotSokoban extends JFrame{
	//variables
	public static RobotSokoban sokoban = new RobotSokoban();
	private final int OFFSET = 39;
	//constructor
	public RobotSokoban() {
		//creates new Board
		Board board = new Board();

		add(board);
		//sets the size of the frame and board, according to the offsets
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(board.getBoardWidth()+(OFFSET/2-3),
				board.getBoardHeight()+OFFSET);
		setLocationRelativeTo(null);         
		setTitle("Robot Sokoban");

	}
	/**
	 * This method disposes the Board,and displays new Menu
	 */
	public void goMainMenu(){
		sokoban.dispose();
		Menu newMenu = new Menu();
		newMenu.createAndShowGUI();
	}
}
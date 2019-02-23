/**
 * This class is creating a board that takes maps from level class and print it out.
 *  Also, it contains listener and methods for many physical part of game
 * @author Serkan delil
 * @version 1.0
 * @since 12.12.2017
 */
package RobotSokoban;
import java.awt.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.net.URL;

import javax.swing.*;

public class Board extends JPanel {
	//part of initializing and creating objects  of board
	private final int CORNERSPACE = 50;
	private final int SPACE = 48;
	public ArrayList<Box> boxes = new ArrayList<Box>();
	public ArrayList<Field> fields = new ArrayList<Field>();
	public ArrayList<Wall> walls = new ArrayList<Wall>();
	public ArrayList<Path> paths = new ArrayList<Path>();
	public ArrayList<Oneway> oneways = new ArrayList<Oneway>();
	public ArrayList<TeleportIn> teleportins=new ArrayList<TeleportIn>();  
	String[][] level = Level.getLevel();   
	public Robot player;
	public TeleportOut to;
	public int width = 0;
	public int height = 0;
	public boolean gameFinished = false;
	ImageIcon background;
	/**
	 * constructor of board
	 */
	public Board() {
		setImage("background.jpg");
		JLabel background = new JLabel(new 
				ImageIcon(this.getClass().getResource("background.jpg")));
		add(background);
		background.setLayout(new FlowLayout());
		addKeyListener(new Listener());
		setFocusable(true);
		createMap(level);
		player.setEnergy(Level.getLevelEnergies());
	}
	public void setImage(String image){
		  URL loc = this.getClass().getResource(image);
		  ImageIcon pic = new ImageIcon(loc);
		  background = pic;
	}
	/**
	 * getting the board width
	 * @return board width
	 */
	public int getBoardWidth(){
		return this.width;
	}
	/**
	 * getting the board height
	 * @return board height
	 */
	public int getBoardHeight(){
		return this.height;
	}
	/**
	 * creates items of map according to level
	 * @param level level of game
	 */
	public final void createMap(String[][] level){
		int row = CORNERSPACE;
		int col = CORNERSPACE;
		Box b;
		Field f;
		Wall w;
		TeleportIn entrance;
		Path p;
		Oneway u;
		Oneway d;
		Oneway r;
		Oneway l;
		for(int i = 0; i < level.length ; i++ ){
			for(int j = 0; j < level[0].length ; j++ ){
				String item = level[i][j];

				if(item.equals("W")){
					w = new Wall(row,col,"wall.png");
					walls.add(w);
					row += SPACE;
				}
				else if(item.equals("P")){
					p = new Path(row,col,"path.png");
					paths.add(p);
					row += SPACE;
				}
				else if(item.equals(" ")){
					row += SPACE;
				}
				else if(item.equals("B")){
					p = new Path(row,col,"path.png");
					paths.add(p);
					b = new Box(row,col,"box.png");
					boxes.add(b);
					row += SPACE;
				}
				else if(item.equals("F")){
					f = new Field(row,col,"field.png");
					fields.add(f);
					row += SPACE;
				}
				else if(item.equals("R")){
					p = new Path(row,col,"path.png");
					paths.add(p);
					player = new Robot(row,col,"robot.png");
					row += SPACE;
				}
				else if(item.equals("I")){
					entrance = new TeleportIn(row,col,"teleportin.png");
					teleportins.add(entrance);
					row += SPACE;
				}
				else if(item.equals("u")){
					u = new Oneway(row,col,"up.png",1);
					oneways.add(u);
					row += SPACE;
				}
				else if(item.equals("d")){
					d = new Oneway(row,col,"down.png",2);
					oneways.add(d);
					row += SPACE;
				}
				else if(item.equals("r")){
					r = new Oneway(row,col,"right.png",3);
					oneways.add(r);
					row += SPACE;
				}
				else if(item.equals("l")){
					l = new Oneway(row,col,"left.png",4);
					oneways.add(l);
					row += SPACE;
				}
				else if(item.equals("O")){
					to = new TeleportOut(row,col,"teleportout.png");
					row += SPACE;
				}
			}
			col += SPACE;
			if(this.width < row){
				this.width = row;
			}
			row = CORNERSPACE;
			this.height = col;
		}
	}
	/**
	 * puts all arraylist into a arraylist
	 * @return arraylist 
	 */
	public ArrayList<Figure> putAllItems(){
		ArrayList<Figure> figures = new ArrayList<Figure>();
		for(int i = 0; i < paths.size();i++){
			if(paths.get(i)!= null)
				figures.add(paths.get(i));
		}
		for(int i = 0; i < oneways.size();i++){
			if(oneways.get(i)!= null)
				figures.add(oneways.get(i));
		}
		for(int i = 0; i < teleportins.size();i++){
			if(teleportins.get(i)!= null)
				figures.add(teleportins.get(i));
		}

		if(to!=null)
			figures.add(to);
		for(int i = 0; i < walls.size();i++){
			if(walls.get(i)!= null)
				figures.add(walls.get(i));
		}
		for(int i = 0; i < fields.size();i++){
			if(fields.get(i)!= null)
				figures.add(fields.get(i));
		}
		for(int i = 0; i < boxes.size();i++){
			if(boxes.get(i)!= null)
				figures.add(boxes.get(i));
		}
		if(player!=null)
			figures.add(player);
		return figures;
	}
	/**
	 * draws all item onto board
	 * @param g is graphic
	 */
	public void drawMap(Graphics g){
		ArrayList<Figure> figures = new ArrayList<Figure>();
		figures = putAllItems();
		for(int i = 0; i < figures.size();i++){
			Figure item = (Figure) figures.get(i);
			g.drawImage(item.getImage(), item.getRow(), item.getCol(), this);
			g.setFont(new Font("TimesRoman",Font.PLAIN,30));
			g.drawString("remaining energy is: "+player.getEnergy(), 30, 45);
			
			if(gameFinished){
				g.setColor(new Color(50,50,50));
				if(player.getEnergy()>=0){
					Wall wal = new Wall(1,1,"congratulations.jpg");
					g.drawImage(wal.getImage(), 20, 50, this.getWidth()-40, 100, this);
				}
				else{
					g.drawString("GAME OVER", 100, 100);
					g.setFont(new Font("TimesRoman",Font.ITALIC,40));
				}
			}
		}
	}
	/**
	 * paints all things
	 *@param graphic
	 */
	public void paint(Graphics g){
		super.paint(g);
		drawMap(g);
	}
	class Listener extends KeyAdapter {
		File teleport = new File("sound/teleportSound.WAV");
		File boxMove = new File("sound/pushboxSound.WAV");
		File Step = new File("sound/stepSound.WAV");
		File Switch = new File("sound/switchSound.WAV");

		public void keyPressed(KeyEvent e) {
			if(gameFinished){
				RobotSokoban newSokoban = new RobotSokoban();
				newSokoban.goMainMenu();
			}
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_LEFT) {
				player.setImage("leftrobot.png");
				player.direction = 4;
				int x = 1;
				int pRow = player.getRow();
				int pCol = player.getCol();
				for(int i=0; i<walls.size();i++){
					for(int j=0; j<boxes.size();j++){
						if(pRow - SPACE ==(walls.get(i)).getRow() && pCol == (walls.get(i)).getCol()){
							x = 2;
						}
						if(pRow - SPACE ==(boxes.get(j)).getRow() && pCol == (boxes.get(j)).getCol()){
							for(int k=0; k<walls.size();k++){
								if(pRow - 2*SPACE ==(walls.get(k)).getRow() &&
										pCol == (walls.get(k)).getCol()){
									x=2;
								}
							}
							for(int l=0; l<boxes.size();l++){
								if(pRow - 2*SPACE ==(boxes.get(l)).getRow() &&
										pCol == (boxes.get(l)).getCol()){
									x=2;
								}
							}
							if(x==1){
								boxes.get(j).move(-SPACE,0);
								PlaySound(boxMove);
								isGameFinished();
							}
						}
					}
				}			
				for(int b=0; b<oneways.size();b++){
					if(pRow -SPACE ==(oneways.get(b)).getRow() && pCol == (oneways.get(b)).getCol()){
						if(oneways.get(b).direction == 1){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()  == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol()-SPACE == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								player.setRow((oneways.get(b).getRow()));
								player.setCol((oneways.get(b).getCol())-SPACE);
								player.move(+SPACE, 0);
							}
						}
						else if(oneways.get(b).direction == 2){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()  == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol()+SPACE == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								player.setRow((oneways.get(b).getRow()));
								player.setCol((oneways.get(b).getCol())+SPACE);
								player.move(+SPACE, 0);
							}
						}
						else if(oneways.get(b).direction == 3){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow() +SPACE == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol() == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								player.setRow((oneways.get(b).getRow())+SPACE);
								player.setCol((oneways.get(b).getCol()));
								player.move(+SPACE, 0);
							}
						}
						else if(oneways.get(b).direction == 4){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()-SPACE == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol() == (boxes.get(c)).getCol()){
									x =2;
									System.out.println("s");
								}
								else{									
									x=2;
									player.setRow((oneways.get(b).getRow())-SPACE);
									player.setCol((oneways.get(b).getCol()));									
								}
							}
						}
					}	
				}
				for(int a=0; a<teleportins.size();a++){
					if(pRow - SPACE ==(teleportins.get(a)).getRow() &&
							pCol == (teleportins.get(a)).getCol()){
						player.setRow(to.getRow());
						player.setCol(to.getCol());
						x = 2;
						PlaySound(teleport);
					}
				}
				if(x==1){
					player.move(-SPACE, 0);
					isGameFinished();
				}
			}
			else if (key == KeyEvent.VK_RIGHT) {
				player.setImage("rightrobot.png");
				player.direction = 3;
				int x = 1;
				int pRow = player.getRow();
				int pCol = player.getCol();
				for(int i=0; i<walls.size();i++){
					for(int j=0; j<boxes.size();j++){
						if(pRow + SPACE ==(walls.get(i)).getRow() && pCol == (walls.get(i)).getCol()){
							x = 2;
						}
						if(pRow + SPACE ==(boxes.get(j)).getRow() && pCol == (boxes.get(j)).getCol()){
							for(int k=0; k<walls.size();k++){
								if(pRow + 2*SPACE ==(walls.get(k)).getRow() && pCol == (walls.get(k)).getCol()){
									x=2;
								}
							}
							for(int l=0; l<boxes.size();l++){
								if(pRow + 2*SPACE ==(boxes.get(l)).getRow() && pCol == (boxes.get(l)).getCol()){
									x=2;
								}
							}
							if(x==1){
								boxes.get(j).move(SPACE,0);
								PlaySound(boxMove);
								isGameFinished();
							}
						}
					}			
				}
				for(int a=0; a<teleportins.size();a++){
					if(pRow + SPACE ==(teleportins.get(a)).getRow() &&
							pCol == (teleportins.get(a)).getCol()){
						player.setRow(to.getRow());
						player.setCol(to.getCol());
						x = 2;
						PlaySound(teleport);
					}
				}
				for(int b=0; b<oneways.size();b++){
					if(pRow +SPACE ==(oneways.get(b)).getRow() && pCol == (oneways.get(b)).getCol()){
						if(oneways.get(b).direction == 1){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()  == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol()-SPACE == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								player.setRow((oneways.get(b).getRow()));
								player.setCol((oneways.get(b).getCol())-SPACE);
								player.move(-SPACE, 0);
							}
						}
						else if(oneways.get(b).direction == 2){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()  == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol()+SPACE == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								player.setRow((oneways.get(b).getRow()));
								player.setCol((oneways.get(b).getCol())+SPACE);
								player.move(-SPACE, 0);
							}

						}
						else if(oneways.get(b).direction == 3){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow() +SPACE == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol() == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								x=2;
								player.setRow((oneways.get(b).getRow())+SPACE);
								player.setCol((oneways.get(b).getCol()));
							}
						}
						else if(oneways.get(b).direction == 4){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()-SPACE == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol() == (boxes.get(c)).getCol()){
									x =2;
								}
								else{
									player.setRow((oneways.get(b).getRow())-SPACE);
									player.setCol((oneways.get(b).getCol()));
									player.move(-SPACE, 0);
								}
							}
						}
					}	
				}
				if(x==1){
					player.move(SPACE, 0);
					isGameFinished();
				}
			}
			else if(key == KeyEvent.VK_DOWN){
				player.setImage("robot.png");
				player.direction = 2;
				int x = 1;
				int pRow = player.getRow();
				int pCol = player.getCol();
				for(int i=0; i<walls.size();i++){
					for(int j=0; j<boxes.size();j++){
						if(pRow  ==(walls.get(i)).getRow() && pCol + SPACE == (walls.get(i)).getCol()){
							x = 2;
						}
						if(pRow  ==(boxes.get(j)).getRow() && pCol + SPACE == (boxes.get(j)).getCol()){
							for(int k=0; k<walls.size();k++){
								if(pRow  ==(walls.get(k)).getRow() && pCol + 2*SPACE == (walls.get(k)).getCol()){
									x=2;
								}
							}
							for(int l=0; l<boxes.size();l++){
								if(pRow  ==(boxes.get(l)).getRow() && pCol + 2*SPACE == (boxes.get(l)).getCol()){
									x=2;
								}
							}
							if(x==1){
								boxes.get(j).move(0, SPACE);
								PlaySound(boxMove);
								isGameFinished();
							}
						}
					}
				}
				for(int b=0; b<oneways.size();b++){
					if(pRow ==(oneways.get(b)).getRow() && pCol+SPACE == (oneways.get(b)).getCol()){
						if(oneways.get(b).direction == 1){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()  == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol()-SPACE == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								player.setRow((oneways.get(b).getRow()));
								player.setCol((oneways.get(b).getCol())-SPACE);
								player.move(0,-SPACE);
							}
						}
						else if(oneways.get(b).direction == 2){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()  == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol()+SPACE == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								x=2;
								player.setRow((oneways.get(b).getRow()));
								player.setCol((oneways.get(b).getCol())+SPACE);
							}
						}
						else if(oneways.get(b).direction == 3){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow() +SPACE == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol() == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								player.setRow((oneways.get(b).getRow())+SPACE);
								player.setCol((oneways.get(b).getCol()));
								player.move(0,-SPACE);
							}
						}
						else if(oneways.get(b).direction == 4){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()-SPACE == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol() == (boxes.get(c)).getCol()){
									x =2;
								}
								else{
									player.setRow((oneways.get(b).getRow())-SPACE);
									player.setCol((oneways.get(b).getCol()));
									player.move(0,-SPACE);
								}
							}
						}
					}	
				}
				for(int a=0; a<teleportins.size();a++){
					if(pRow  ==(teleportins.get(a)).getRow() &&
							pCol + SPACE == (teleportins.get(a)).getCol()){
						player.setRow(to.getRow());
						player.setCol(to.getCol());
						x = 2;
						PlaySound(teleport);
					}
				}
				if(x==1){
					player.move(0,SPACE);
					isGameFinished();
				}
			}
			else if(key == KeyEvent.VK_UP){
				player.setImage("uprobot.png");
				player.direction = 1;
				int x = 1;
				int pRow = player.getRow();
				int pCol = player.getCol();
				for(int i=0; i<walls.size();i++){
					for(int j=0; j<boxes.size();j++){
						if(pRow  ==(walls.get(i)).getRow() && pCol - SPACE == (walls.get(i)).getCol()){
							x = 2;
						}
						if(pRow  ==(boxes.get(j)).getRow() && pCol - SPACE == (boxes.get(j)).getCol()){
							for(int k=0; k<walls.size();k++){
								if(pRow  ==(walls.get(k)).getRow() && pCol - 2*SPACE == (walls.get(k)).getCol()){
									x=2;
								}
							}
							for(int l=0; l<boxes.size();l++){
								if(pRow  ==(boxes.get(l)).getRow() && pCol - 2*SPACE == (boxes.get(l)).getCol()){
									x=2;
								}
							}
							if(x==1){
								boxes.get(j).move(0,- SPACE);
								PlaySound(boxMove);
								isGameFinished();
							}
						}
					}
				}
				for(int b=0; b<oneways.size();b++){
					if(pRow  ==(oneways.get(b)).getRow() && pCol-SPACE == (oneways.get(b)).getCol()){
						if(oneways.get(b).direction == 1){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()  == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol()-SPACE == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								x=2;
								player.setRow((oneways.get(b).getRow()));
								player.setCol((oneways.get(b).getCol())-SPACE);
							}
						}
						else if(oneways.get(b).direction == 2){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()  == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol()+SPACE == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								player.setRow((oneways.get(b).getRow()));
								player.setCol((oneways.get(b).getCol())+SPACE);
								player.move(0,SPACE);
							}

						}
						else if(oneways.get(b).direction == 3){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow() +SPACE == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol() == (boxes.get(c)).getCol()){
									x=2;
								}									
							}
							if(x==1){
								player.setRow((oneways.get(b).getRow())+SPACE);
								player.setCol((oneways.get(b).getCol()));
								player.move(0,SPACE);
							}

						}
						else if(oneways.get(b).direction == 4){
							for(int c=0; c<boxes.size();c++){
								if(oneways.get(b).getRow()-SPACE == (boxes.get(c)).getRow() &&
										oneways.get(b).getCol() == (boxes.get(c)).getCol()){
									x =2;
									System.out.println("s");
								}
								else{
									player.setRow((oneways.get(b).getRow())-SPACE);
									player.setCol((oneways.get(b).getCol()));
									player.move(0,SPACE);
								}
							}
						}
					}	
				}
				for(int a=0; a<teleportins.size();a++){
					if(pRow  ==(teleportins.get(a)).getRow() &&
							pCol - SPACE == (teleportins.get(a)).getCol()){
						player.setRow(to.getRow());
						player.setCol(to.getCol());
						x = 2;
						PlaySound(teleport);
					}
				}
				if(x==1){
					player.move(0,- SPACE);
					isGameFinished();
				}
			}
			/*
			 * if we press S, our robot pull a box according to our direction
			 * also pulling requires 10 energy
			 */
			else if(key == KeyEvent.VK_P){
				if(player.direction == 1){
					int x = 1;
					int pRow = player.getRow();
					int pCol = player.getCol();
					for(int j=0; j<boxes.size();j++){
						for(int i=0; i<walls.size();i++){
							if(pRow  ==(boxes.get(j)).getRow() && pCol - SPACE == (boxes.get(j)).getCol()){
								for(int k=0; k<walls.size();k++){
									if(pRow  ==(walls.get(k)).getRow() && pCol + SPACE == (walls.get(k)).getCol()){
										x=2;
									}
								}
								for(int l=0; l<boxes.size();l++){
									if(pRow  ==(boxes.get(l)).getRow() && pCol +SPACE == (boxes.get(l)).getCol()){
										x=2;
									}
								}
								if(x==1){
									boxes.get(j).move(0,SPACE);
									PlaySound(boxMove);
									player.move(0,SPACE);
									player.setEnergy(player.getEnergy()- 9);
									isGameFinished();
									player.setImage("uprobot.png");
									player.direction = 1;
								}
							}
						}
					}
				}
				else if(player.direction == 2){
					int x = 1;
					int pRow = player.getRow();
					int pCol = player.getCol();
					for(int j=0; j<boxes.size();j++){
						for(int i=0; i<walls.size();i++){
							if(pRow  ==(boxes.get(j)).getRow() && pCol + SPACE == (boxes.get(j)).getCol()){
								for(int k=0; k<walls.size();k++){
									if(pRow  ==(walls.get(k)).getRow() && pCol - SPACE == (walls.get(k)).getCol()){
										x=2;
									}
								}
								for(int l=0; l<boxes.size();l++){
									if(pRow  ==(boxes.get(l)).getRow() && pCol -SPACE == (boxes.get(l)).getCol()){
										x=2;
									}
								}
								if(x==1){
									boxes.get(j).move(0,-SPACE);
									PlaySound(boxMove);
									player.move(0,-SPACE);
									player.setEnergy(player.getEnergy()- 9);
									isGameFinished();
									player.setImage("robot.png");
									player.direction = 2;
								}
							}
						}
					}					
				}
				else if(player.direction == 3){
					int x = 1;
					int pRow = player.getRow();
					int pCol = player.getCol();
					for(int j=0; j<boxes.size();j++){
						for(int i=0; i<walls.size();i++){
							if(pRow + SPACE ==(boxes.get(j)).getRow() && pCol  == (boxes.get(j)).getCol()){
								for(int k=0; k<walls.size();k++){
									if(pRow - SPACE ==(walls.get(k)).getRow() && pCol  == (walls.get(k)).getCol()){
										x=2;
									}
								}
								for(int l=0; l<boxes.size();l++){
									if(pRow -SPACE ==(boxes.get(l)).getRow() && pCol  == (boxes.get(l)).getCol()){
										x=2;
									}
								}
								if(x==1){
									boxes.get(j).move(-SPACE,0);
									PlaySound(boxMove);
									player.move(-SPACE,0);
									player.setEnergy(player.getEnergy()- 9);
									isGameFinished();
									player.setImage("rightrobot.png");
									player.direction = 3;
								}
							}
						}
					}
				}
				else if(player.direction == 4){
					int x = 1;
					int pRow = player.getRow();
					int pCol = player.getCol();
					for(int j=0; j<boxes.size();j++){
						for(int i=0; i<walls.size();i++){
							if(pRow - SPACE ==(boxes.get(j)).getRow() && pCol  == (boxes.get(j)).getCol()){
								for(int k=0; k<walls.size();k++){
									if(pRow + SPACE ==(walls.get(k)).getRow() && pCol  == (walls.get(k)).getCol()){
										x=2;
									}
								}
								for(int l=0; l<boxes.size();l++){
									if(pRow +SPACE ==(boxes.get(l)).getRow() && pCol  == (boxes.get(l)).getCol()){
										x=2;
									}
								}
								if(x==1){
									boxes.get(j).move(SPACE,0);
									PlaySound(boxMove);
									player.move(SPACE,0);
									player.setEnergy(player.getEnergy()- 9);
									isGameFinished();
									player.setImage("leftrobot.png");
									player.direction = 4;
								}
							}
						}
					}
				}
			}
			/*
			 * if we press S, our robot switch with box according to our direction
			 * also switch requires 15 energy
			 */
			else if(key == KeyEvent.VK_S){
				if(player.direction == 1){
					int pRow = player.getRow();
					int pCol = player.getCol();
					for(int j=0; j<boxes.size();j++){
						if(pRow  ==(boxes.get(j)).getRow() && pCol - SPACE == (boxes.get(j)).getCol()){					
							boxes.get(j).move(0,SPACE);
							PlaySound(Switch);
							player.move(0,-SPACE);
							player.setEnergy(player.getEnergy()- 14);
							isGameFinished();
							player.setImage("robot.png");
							player.direction = 2;
						}	
					}
				}
				else if(player.direction == 2){	
					int pRow = player.getRow();
					int pCol = player.getCol();
					for(int j=0; j<boxes.size();j++){						
						if(pRow  ==(boxes.get(j)).getRow() && pCol + SPACE == (boxes.get(j)).getCol()){							
							boxes.get(j).move(0,-SPACE);
							PlaySound(Switch);
							player.move(0,+SPACE);
							player.setEnergy(player.getEnergy()- 14);
							isGameFinished();
							player.setImage("uprobot.png");
							player.direction = 1;								
						}						
					}					
				}
				else if(player.direction == 3){
					int pRow = player.getRow();
					int pCol = player.getCol();
					for(int j=0; j<boxes.size();j++){						
						if(pRow + SPACE ==(boxes.get(j)).getRow() && pCol  == (boxes.get(j)).getCol()){			
							boxes.get(j).move(-SPACE,0);
							PlaySound(Switch);
							player.move(+SPACE,0);
							player.setEnergy(player.getEnergy()- 14);
							isGameFinished();
							player.setImage("leftrobot.png");
							player.direction = 4;								
						}						
					}
				}
				else if(player.direction == 4){
					int pRow = player.getRow();
					int pCol = player.getCol();
					for(int j=0; j<boxes.size();j++){
						for(int i=0; i<walls.size();i++){
							if(pRow - SPACE ==(boxes.get(j)).getRow() && pCol  == (boxes.get(j)).getCol()){								
								boxes.get(j).move(SPACE,0);
								PlaySound(Switch);
								player.move(-SPACE,0);
								player.setEnergy(player.getEnergy()- 14);
								isGameFinished();
								player.setImage("rightrobot.png");
								player.direction = 3;
							}
						}
					}
				}
			}
			/*
			 * if we press ESC, we go to main menu
			 */
			else if (key == KeyEvent.VK_ESCAPE){
				RobotSokoban newSokoban = new RobotSokoban();
				newSokoban.goMainMenu();
			}
			/*
			 * if we press r, game restarts
			 */
			else if (key == KeyEvent.VK_R) {
				restart();
			}			
			repaint();
		}
	}
	/**
	  * This method plays the sound affecrs according to the file parameter it take
	  * @param File Sound: File parameter that will be played
	  */
	 public static void PlaySound(File Sound)
	 {
	   
	   //catches exception if file cannot found
	  try
	  {
	   //Creates a Clip object to load the sound and play it
	   Clip clip = AudioSystem.getClip();
	   clip.open(AudioSystem.getAudioInputStream(Sound));
	   clip.start();
	   
	   //sleeps the program while music is playing
	   Thread.sleep(clip.getMicrosecondLength()/1000);
	  }catch(Exception e)
	  {
	  }
	 }
	/**
	 * checks whether is game finished or not
	 */
	public void isGameFinished(){
		//change image of boxes that are on checkpoints(fields)
		int matched = 0;
		for(int k = 0;k< boxes.size();k++){
			Box box =(Box) boxes.get(k);
			box.setImage("box.png");
			for(int l = 0;l< fields.size();l++){
				Field field =(Field) fields.get(l);
				if(box.getRow() == field.getRow() && box.getCol() == field.getCol()){
					matched++;
					box.setImage("ultrabox.png");
				}
			}
		}
		//check situation that all boxes are on checkpoints(fields)
		if(matched == fields.size()){
			gameFinished = true;		
			repaint();
		}
		//check whether player has energy 
		if(player.getEnergy()<0){
			gameFinished = true;		
			repaint();
		}
	}
	/**
	 * restarts map
	 */
	public void restart(){
		walls.clear();
		boxes.clear();
		fields.clear();
		gameFinished = false;
		createMap(level);
		player.setEnergy(Level.getLevelEnergies());
	}
}

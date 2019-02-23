package RobotSokoban;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.io.*;
import java.net.URL;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Menu {
  
  public static void main(String[] args){
    new Menu();
  }
  public static void createAndShowGUI() {
    
    JFrame frame = new JFrame("Robot Sokoban");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  TestPane menu;
  JFrame frame;
  
  public Menu() {
    EventQueue.invokeLater(new Runnable(){
      @Override
      public void run(){
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
          ex.printStackTrace();
        }       
        menu = new TestPane();        
        frame = new JFrame("Robot Sokoban");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(menu);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }
  
  public class TestPane extends JPanel{
    
    private List<String> menuItems;
    private String selectMenuItem;
    private String focusedItem;   
    private MenuItemPainter painter;
    private Map<String, Rectangle> menuBounds;
    Image backgroundImage;
    public void setImage(String image){
  	  URL loc = this.getClass().getResource(image);
  	  ImageIcon pic = new ImageIcon(loc);
  	  Image image1 = pic.getImage();
  	  backgroundImage = image1;
  	 }
    public TestPane(){
      setBackground(Color.YELLOW);
      painter = new SimpleMenuItemPainter();
      menuItems = new ArrayList<>(25);
      menuItems.add("Start Game");
      menuItems.add("Instructions");
      menuItems.add("About Us");
      selectMenuItem = menuItems.get(0);
      
      MouseAdapter ma = new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
          String newItem = null;
          for (String text : menuItems) {
            Rectangle bounds = menuBounds.get(text);
            if (bounds.contains(e.getPoint())){
              newItem = text;
              break;
            }
          }
          if(newItem == selectMenuItem){
            int index = menuItems.indexOf(selectMenuItem);
            if(index == 0){
              Level.ShowGraphics();
            }
            else if(index == 2){
              JOptionPane.showMessageDialog(null, "This CS102 Project is implemented by the following students of Bilkent University:" + "\n" +
                                            "1.Muhammad Ramish Saeed" + "\n" +
                                            "2.Ates Bilgin" + "\n" +
                                            "3.Taner Durmaz" + "\n" +
                                            "4.Ekin Ustundag" + "\n" +
                                            "5.Serkan Delil" + "\n" +
                                            "Instructor : Ugur Gudukbay ");
            }
            else if(index == 1){
            	JOptionPane.showMessageDialog(null, "1.Some of the floor squares will have certain amount of boxes which have to be pushed to the storage locations. The rest of the area will be empty for the robot to move freely." + "\n" +
                        "2.The robot can move horizontally and vertically to push the boxes but it can never move outside of the wall nor go past the boxes." + "\n" +
                        "3.The boxes can be pushed and pulled back(on condition)." + "\n" +
                        "4.Levels will increase in difficulty." + "\n" +
                        "5.The robot can switch its position with the box and teleport from one marked position on the board to another." + "\n" +
                        "6.If the player decides to switch the robot's position with button S, he will loose 15 points." + "\n" +
                        "7.If player decides to pull the box with button P, he will loose 10 points." +  "\n" +
                        "8.If he presses R, the game will restart and if he presses ESCAPE, game will return to main menu." + "\n" +
                        "9.The player can teleport to other position with a special marked floor and another Oneway floor will be used to go to the next position and not return back." + "\n" +
                        "10.The puzzle will be solved when all the boxes will be pushed to the storage location after which the player will move on to the next level.");
            }           
          }
          if (newItem != null && !newItem.equals(selectMenuItem)){
            selectMenuItem = newItem;
            repaint();
          }
        }
        
        @Override
        public void mouseMoved(MouseEvent e){
          focusedItem = null;
          for (String text : menuItems){
            Rectangle bounds = menuBounds.get(text);
            if (bounds.contains(e.getPoint())){
              focusedItem = text;
              repaint();
              break;
            }
          }
        }       
      };
      
      addMouseListener(ma);
      addMouseMotionListener(ma);
      
      InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
      ActionMap am = getActionMap();
      
      im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "arrowDown");
      im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "arrowUp");
      
      am.put("arrowDown", new MenuAction(1));
      am.put("arrowUp", new MenuAction(-1)); 
      im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
      am.put("enter", new EnterAction());
      
      try{
    	  setImage("download.jpg");
//        backgroundImage = ImageIO.read(new File("\\Users\\serkan\\Desktop\\Sokoban\\bin\\RobotSokoban\\download.jpg"));
      }catch(Exception e)
      {
        System.out.println(e);
        System.out.println("Working Directory = " +
                           System.getProperty("user.dir"));
      }
    }
    
    @Override
    public void invalidate() {
      menuBounds = null;
      super.invalidate();
    }
    
    @Override
    public Dimension getPreferredSize(){
      return new Dimension(500, 500);
    }
    
    @Override
    protected void paintComponent(Graphics g){
      super.paintComponent(g);
      
      g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);      
      Graphics2D g2d = (Graphics2D) g.create();
      if (menuBounds == null){
        menuBounds = new HashMap<>(menuItems.size());
        int width = 0;
        int height = 0;
        for (String text : menuItems){
          Dimension dim = painter.getPreferredSize(g2d, text);
          width = Math.max(width, dim.width);
          height = Math.max(height, dim.height);
        }
        
        int x = (getWidth() - (width + 10)) / 2;
        
        int totalHeight = (height + 10) * menuItems.size();
        totalHeight += 5 * (menuItems.size() - 1);
        
        int y = (getHeight() - totalHeight) / 2;
        
        for (String text : menuItems){
          menuBounds.put(text, new Rectangle(x, y, width + 10, height + 10));
          y += height + 10 + 5;
        }       
      }
      for (String text : menuItems){
        Rectangle bounds = menuBounds.get(text);
        boolean isSelected = text.equals(selectMenuItem);
        boolean isFocused = text.equals(focusedItem);
        painter.paint(g2d, text, bounds, isSelected, isFocused);
      }
      g2d.dispose();
    }
    
    
    public class EnterAction extends AbstractAction{
      @Override
      public void actionPerformed(ActionEvent e){
        int index = menuItems.indexOf(selectMenuItem);
        if(index == 0){
        	Level.ShowGraphics();
        }
      }
    }
    public class MenuAction extends AbstractAction {
      
      private final int delta;
      
      public MenuAction(int delta) {
        this.delta = delta;
      }
      
      @Override
      public void actionPerformed(ActionEvent e){
        
        int index = menuItems.indexOf(selectMenuItem);
        if (index < 0) {
          selectMenuItem = menuItems.get(0);
        }
        index += delta;
        if (index < 0) {
          selectMenuItem = menuItems.get(menuItems.size() - 1);
        } else if (index >= menuItems.size()) {
          selectMenuItem = menuItems.get(0);
        } else {
          selectMenuItem = menuItems.get(index);
        }
        repaint();
      }      
    }    
  }
  
  public interface MenuItemPainter{ 
    public void paint(Graphics2D g2d, String text, Rectangle bounds, boolean isSelected, boolean isFocused);    
    public Dimension getPreferredSize(Graphics2D g2d, String text);   
  }
  
  public class SimpleMenuItemPainter implements MenuItemPainter{    
    public Dimension getPreferredSize(Graphics2D g2d, String text){
      return g2d.getFontMetrics().getStringBounds(text, g2d).getBounds().getSize();
    }
    
    @Override
    public void paint(Graphics2D g2d, String text, Rectangle bounds, boolean isSelected, boolean isFocused) {
      FontMetrics fm = g2d.getFontMetrics();
      if (isSelected) {
        paintBackground(g2d, bounds, Color.BLUE, Color.WHITE);
      } else if (isFocused) {
        paintBackground(g2d, bounds, Color.MAGENTA, Color.BLACK);
      } else {
        paintBackground(g2d, bounds, Color.DARK_GRAY, Color.LIGHT_GRAY);
      }
      int x = bounds.x + ((bounds.width - fm.stringWidth(text)) / 2);
      int y = bounds.y + ((bounds.height - fm.getHeight()) / 2) + fm.getAscent();
      g2d.setColor(isSelected ? Color.WHITE : Color.LIGHT_GRAY);
      g2d.drawString(text, x, y);
    }
    
    protected void paintBackground(Graphics2D g2d, Rectangle bounds, Color background, Color foreground) {
      g2d.setColor(background);
      g2d.fill(bounds);
      g2d.setColor(foreground);
      g2d.draw(bounds);
    }   
  } 
}
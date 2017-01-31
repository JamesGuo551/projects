/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: November 2, 2016
 * File: CosmicSphereController.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program makes cosmic spheres that do a lot of things.
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * This class defines all the variables and runs the methods for each
 * function of the cosmic spheres.
 */
public class CosmicSphereController extends WindowController 
implements MouseListener, MouseMotionListener, ActionListener, ChangeListener {
  //I need these interfaces
  private Line xLine, yLine; //axes lines 
  private static final int CANVAS_WIDTH = 600; //canvas width
  private static final int CANVAS_HEIGHT = 600; //canvas height
  private static final int MAX_SPEED = 100; //max speed setting
  private static final int START_SPEED = 50; //starting speed setting
  int canvasHeight = 600; //stores height before canvas resize
  int canvasWidth = 600; //stores width before canvas resize
  int lineStart = 300; //Since line is in middle of canvas
  int lineEnd = 600; //Line ends at edge
  int checkPress = 0; //Checks which lines are pressed
  int xPressed = 1; //xLine pressed
  int yPressed = 2; //yLine pressed
  int lineMove = 2; //used when moving lines after canvas resize
  int bothPressed = 3; //Both lines pressed
  int limit = 4; //4 pixel boundary of where lines can move
  int speed = START_SPEED; //starting speed setting
  public boolean growth = true; //toggle growth of spheres
  private CosmicSphere sphere; //CosmicSphere object
  int size = 50; //Initial size of sphere
  private JButton start, stop, clear; //initiate jbuttons
  private JSlider speedSlider; //initate a slider
  private JLabel speedLabel; //initiate a label
  int beginCheck = 0; //some stuff only runs after program begins
  //abstract methods since i implemented interfaces
  public void mouseEntered(MouseEvent e){} 
  public void mouseExited(MouseEvent e){}
  public void mouseMoved(MouseEvent e){}

  /**
   * Code runs as soon as canvas is opened.
   */
  public void begin() { //Makes axes
    JPanel northPanel = new JPanel(); //button panel
    JPanel mainPanel = new JPanel(); //so label goes above buttons
    JPanel upperNorthPanel = new JPanel(); //label panel
    start = new JButton("Start"); //start button
    stop = new JButton("Stop"); //stop button
    clear = new JButton("Clear All"); //clear button
    JLabel label = new JLabel("Cosmic Sphere Controls"); //display text
    //so buttons respond to actions
    start.addActionListener(this);
    stop.addActionListener(this);
    clear.addActionListener(this);
    upperNorthPanel.add(label); //add label to label panel
    //add buttons to button panel
    northPanel.add(start);
    northPanel.add(stop);
    northPanel.add(clear);
    mainPanel.setLayout(new BorderLayout()); //to change main panel layout
    mainPanel.add(upperNorthPanel, BorderLayout.NORTH); //label panel on top
    mainPanel.add(northPanel, BorderLayout.SOUTH); //button panel on botton
    Container contentPane = getContentPane(); //so i can add panels to canvas
    contentPane.add(mainPanel, BorderLayout.NORTH); //main panel on top
    contentPane.validate(); //makes it work
    //canvas responds to action events
    canvas.addMouseListener(this);
    canvas.addMouseMotionListener(this);
    
    JPanel southPanel = new JPanel(); //speed panel
    speedSlider = new JSlider(JSlider.HORIZONTAL, 1, MAX_SPEED, START_SPEED);
    speedSlider.addChangeListener(this); //make jslider and recognize events
    speedLabel = new JLabel("The speed is " + speed); //display speed
    southPanel.add(speedLabel); //add text message
    southPanel.add(speedSlider); //and sliding tool
    contentPane.add(southPanel, BorderLayout.SOUTH); //add panel to bottom
    xLine = new Line(0, lineStart, lineEnd, lineStart, canvas);
    yLine = new Line(lineStart, 0, lineStart, lineEnd, canvas);
    //makes two axes
    beginCheck = 1; //sets after begin() is run
  }

  /**
   * Statechange event.
   * @param e is how the slider was changed
   */
  public void stateChanged(ChangeEvent e) {
    speed = speedSlider.getValue(); //find new slider value
    if(sphere != null) { //if a sphere exists
      sphere.setSpeed(speed); //sets sphere's growth rate
    }
    speedLabel.setText("The speed is " + speed); //text displays speed
  }
   
  /**
   * Action performed event.
   * @param e is which button was pressed
   */
  public void actionPerformed(ActionEvent e) {
    if(sphere != null) { //if sphere exists
      if(e.getSource() == start) {
        growth = true; //new spheres will grow
      } else if(e.getSource() == stop) {
        growth = false; //new spheres will stop
      }
    }
  }    
  /**
   * If the lines are pressed.
   * @param e is where the mouse is pressed
   */
  public void mousePressed(MouseEvent e) {
    Location point = new Location(e.getX(), e.getY());
    if(xLine.contains(point) && yLine.contains(point)) { //Both pressed
      checkPress = bothPressed; //state change
    } else if(xLine.contains(point)) { //xLine pressed
      checkPress = xPressed; //state change
    } else if(yLine.contains(point)) { //yLine pressed
      checkPress = yPressed; //state change
    } 
  }

  /**
   * When lines are dragged.
   * @param e is where the mouse is pressed
   */
  public void mouseDragged(MouseEvent e) {
    Location point = new Location(e.getX(), e.getY());
    if(point.getX() < canvas.getWidth()-limit && point.getX() > limit
       && point.getY() < canvas.getHeight()-limit && point.getY() > limit) {
    //Mouse drag only moves lines up to 5 pixels from the boundary
      if(checkPress == bothPressed) { //Moves both lines
        xLine.moveTo(0, point.getY());
        yLine.moveTo(point.getX(), 0);
      } else if(checkPress == xPressed) { //Moves xLine
        xLine.moveTo(0, point.getY());
      } else if(checkPress == yPressed) { //Moves yLine
        yLine.moveTo(point.getX(), 0);
      }
    }
  } 

  /**
   * When the mouse is clicked.
   * @param e is where the mouse is clicked.
   */
  public void mouseClicked(MouseEvent e) {
    Location point = new Location(e.getX(), e.getY());
    sphere = new CosmicSphere(point.getX(), point.getY(), size, canvas, xLine,
		        yLine, start, stop, clear, growth, speed, speedSlider); 
    //Create CosmicSphere object
  }

  /**
   * When mouse is released.
   * @param e is where the mouse is released
   */
  public void mouseReleased(MouseEvent e) {
    checkPress = 0; //After done dragging, the state is reset
  }
 
  /**
   * Repaints the canvas every time it's resized.
   * @param g is a java.awt.Graphics object.
   */
  public void paint(java.awt.Graphics g) {
    if(beginCheck == 1) {
      super.paint(g); 
      xLine.setEnd(canvas.getWidth(), (xLine.getStart()).getY()); //New endpoint
      xLine.move(0, (double)(canvas.getHeight()-canvasHeight)/lineMove);
      yLine.setEnd((yLine.getStart()).getX(), canvas.getHeight());//New endpoint
      yLine.move((double)(canvas.getWidth()-canvasWidth)/lineMove, 0);
      //also move lines to remain proportional in location
      canvasHeight = canvas.getHeight(); //update canvas height
      canvasWidth = canvas.getWidth(); //update canvas width
    }
  } 

  /**
   * Main method runs my program in an Acme frame so it will
   * run as an application that uses object draw.
   */
  public static void main(String[] args) {
    new Acme.MainFrame(new CosmicSphereController(), args, CANVAS_WIDTH,
    CANVAS_HEIGHT);
  }
}

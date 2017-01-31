/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: October 18, 2016
 * File: CosmicSphereController.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program makes cosmic spheres and changes their colors.
 */

import objectdraw.*;
import java.awt.*;

/**
 * This class defines all the variables and runs the methods for each
 * function of the cosmic spheres.
 */
public class CosmicSphereController extends WindowController {
  private Line xLine, yLine; 
  private static final int CANVAS_WIDTH = 600;
  private static final int CANVAS_HEIGHT = 600;
  private static final int lineStart = 300; //Since line is in middle of canvas
  private static final int lineEnd = 600; //Line ends at edge
  int checkPress = 0; //Checks which lines are pressed
  int xPressed = 1; //xLine pressed
  int yPressed = 2; //yLine pressed
  int bothPressed = 3; //Both lines rpessed
  private CosmicSphere sphere; //CosmicSphere object
  int size = 50; //Initial size of sphere
  
  /**
   * Code runs as soon as canvas is opened.
   */
  public void begin() { //Makes axes
    xLine = new Line(0, lineStart, lineEnd, lineStart, canvas);
    yLine = new Line(lineStart, 0, lineStart, lineEnd, canvas);
  }
  
  /**
   * If the lines are pressed.
   * @param point is where the mouse is pressed
   */
  public void onMousePress(Location point) {
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
   * @param point is where the mouse is pressed
   */
  public void onMouseDrag(Location point) {
    if(point.getX() < canvas.getHeight()-4 && point.getX() > 4
       && point.getY() < canvas.getWidth()-4 && point.getY() > 4) {
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
   * @param point is where the mouse is clicked.
   */
  public void onMouseClick(Location point) {
    sphere = new CosmicSphere(point.getX(), point.getY(), size, canvas,
		                    xLine, yLine); //Create CosmicSphere object
  }

  /**
   * When mouse is released.
   * @param point is where the mouse is released
   */
  public void onMouseRelease(Location point) {
    checkPress = 0; //After done dragging, the state is reset
  }
 
  /**
   * Repaints the canvas every time it's resized.
   * @param g is a java.awt.Graphics object.
   */
  public void paint(java.awt.Graphics g) {
    super.paint(g); 
    xLine.setEnd(canvas.getWidth(), (xLine.getStart()).getY()); //New endpoint
    yLine.setEnd((yLine.getStart()).getX(), canvas.getHeight()); //New endpoint
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

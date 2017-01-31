/*
 * Name: Yipeng Guo
 * Login: cs11fadl <<< --- Use your cs11f course-specific account name
 * Date: October 4, 2016
 * File: DraggingBlockH.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program creates an "H" block and allows the user to drag it around.
 */

import java.awt.*;
import objectdraw.*; 

/**
 * This class defines all the variables and runs the methods for each
 * function of the H block
 */
public class DraggingBlockH extends WindowController {
  private Text instr1 = null;
  private Text instr2 = null;

  int Hcounter = 0; //I use this to alter states basically
  //It prevents the nullpointerexception and is simpler to use than boolean
  int dragState = 2; //State of dragging
  int exitState = 3; //State of exit canvas
  
  double leftXdistance = 0; //Distance from left block and mouse point
  double leftYdistance = 0; //Rest have intuitive names
  double centerXdistance = 0;
  double centerYdistance = 0;
  double rightXdistance = 0;
  double rightYdistance = 0;

  private FilledRect leftBar = null; //We will define these objects later
  private FilledRect rightBar = null;
  private FramedRect centerBar = null;

  private static final double INSTR1_X = 50;
  private static final double INSTR1_Y = 50;
  private static final double INSTR2_X = INSTR1_X;
  private static final double INSTR2_Y = INSTR1_Y + 20;

  private static final int FRAME_WIDTH = 750;
  private static final int FRAME_HEIGHT = 750;

  private static final double CENTER_BAR_WIDTH = 50;
  private static final double HALF_CENTER_BAR_WIDTH = CENTER_BAR_WIDTH / 2;
  private static final double CENTER_BAR_HEIGHT = 25;
  private static final double HALF_CENTER_BAR_HEIGHT = CENTER_BAR_HEIGHT / 2;
  
  private static final double SIDE_BARS_WIDTH = 25;
  private static final double SIDE_BARS_HEIGHT = 125;
  private static final double HALF_SIDE_BARS_HEIGHT = SIDE_BARS_HEIGHT / 2;
  
  /**
   * Method initiates by displaying instructions
   */
  public void begin() {
    instr1 = new Text("Click to display a Block H centered at the mouse click.", 
    INSTR1_X, INSTR1_Y, canvas);  
    instr2 = new Text(
    "Mouse press in any part of the image and drag to move image around.", 
    INSTR2_X, INSTR2_Y, canvas);
  }
  
  /**
   * Clicking will hide the instructions and make the H appear
   *
   * @param point this is the location where the mouse click occured
   * @no return
   */  
  public void onMouseClick(Location point) {
    if(Hcounter == 0) { //Only runs when no H block on canvas
      instr1.hide();
      instr2.hide();
      centerBar = new FramedRect(point.getX()-HALF_CENTER_BAR_WIDTH, point.getY()-
      HALF_CENTER_BAR_HEIGHT, CENTER_BAR_WIDTH, CENTER_BAR_HEIGHT, canvas);
      leftBar = new FilledRect(point.getX()-HALF_CENTER_BAR_WIDTH-SIDE_BARS_WIDTH,
      point.getY()-HALF_SIDE_BARS_HEIGHT, SIDE_BARS_WIDTH, SIDE_BARS_HEIGHT, 
      canvas);
      rightBar = new FilledRect(point.getX()+SIDE_BARS_WIDTH, point.getY()-
      HALF_SIDE_BARS_HEIGHT, SIDE_BARS_WIDTH, SIDE_BARS_HEIGHT, canvas);
      Hcounter = 1; //Now the H block exists
    }
  }

  /**
   * Mouse press changes color of H block and allows you to drag
   *
   * @param point this is the location where the mouse was pressed
   * @no return
   */
  public void onMousePress(Location point) {
    if(Hcounter == 1) { //Only runs after H block is made
      if(centerBar.contains(point) || leftBar.contains(point) || 
      rightBar.contains(point)) { //Must press inside block to run
        leftBar.setColor(Color.red);
        centerBar.setColor(Color.green);
        rightBar.setColor(Color.blue);
        Hcounter = dragState; //Change to the state of dragging
        //We find the exact place where the mouse was pressed so the block
        //will move at that spot and won't center itself
        leftXdistance = point.getX()-leftBar.getX();
        leftYdistance = point.getY()-leftBar.getY();
        rightXdistance = point.getX()-rightBar.getX();
        rightYdistance = point.getY()-rightBar.getY();
        centerXdistance = point.getX()-centerBar.getX();
        centerYdistance = point.getY()-centerBar.getY();
      }
    }
  }
  
  /**
   * Mouse drag moves the H block around
   * 
   * @param point this is where the mouse was pressed
   * @no return 
   */
  public void onMouseDrag(Location point) {
    if(Hcounter == dragState) { //only runs after mouse press
      leftBar.moveTo(point.getX()-leftXdistance, point.getY()-leftYdistance);
      rightBar.moveTo(point.getX()-rightXdistance, point.getY()-rightYdistance);
      centerBar.moveTo(point.getX()-centerXdistance, 
                       point.getY()-centerYdistance);
      //This is so the block moves where you pressed it and not the
      //center of the block
    }
  }
  
  /**
   * Mouse release will change the block back to black and change the state
   *
   * @param point this point is the same as onMousePress point
   * @no return
   */
  public void onMouseRelease(Location point) {
    if(Hcounter == dragState) { //only runs after mouse press
      leftBar.setColor(Color.black);
      centerBar.setColor(Color.black);
      rightBar.setColor(Color.black);
      Hcounter = 1; //brings it back to press state so it can drag again
    }
  }

  /** 
   * This method removes the H block and changes to state 3
   *
   * @param point this is the point where the mouse exits the canvas
   * @no return
   */
  public void onMouseExit(Location point) {
    if(Hcounter == 1 || Hcounter == dragState) { 
      //runs in any state except before the H is created
      leftBar.removeFromCanvas();
      centerBar.removeFromCanvas();
      rightBar.removeFromCanvas();
      Hcounter = exitState; //state of being off canvas
    }
  }
  
  /**
   * Mouse entering the canvas makes instructions appear again and changes
   * state to 0 so mouse click will start the process all over
   *
   * @param point this is the location where the mouse reenters the canvas
   * @no return
   */
  public void onMouseEnter(Location point) {
    if(Hcounter == exitState) { //only runs after mouse exits
      instr1.show();
      instr2.show();
      Hcounter = 0; //resets the process
    }
  }
  
  /**
    * main method runs my program in an Acme frame so it will
    * run as an application that uses object draw
    *
    * @no param
    * @no return
    */
  public static void main(String[] args) {
    new Acme.MainFrame(new DraggingBlockH(), args, FRAME_WIDTH, FRAME_HEIGHT);
  }
}
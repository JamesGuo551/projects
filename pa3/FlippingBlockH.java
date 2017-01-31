/*
 * Name: Yipeng Guo
 * Login: cs11fadl <<< --- Use your cs11f course-specific account name
 * Date: October 11, 2016
 * File: FlippingBlockH.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program makes an H block and has many functions for it.
 */

import objectdraw.*;
import java.awt.*;

/**
 * This class defines all the variables and runs the methods for each
 * function of the H block
 */
public class FlippingBlockH extends WindowController {
  private Text instr1, instr2, instr3, instr4;
  private static final int INSTR1_X = 50;
  private static final int INSTR1_Y = 50;
  private static final int INSTR2_X = INSTR1_X;
  private static final int INSTR2_Y = INSTR1_Y + 20;
  private static final int INSTR3_X = INSTR1_X;
  private static final int INSTR3_Y = INSTR2_Y + 20;
  private static final int INSTR4_X = INSTR1_X;
  private static final int INSTR4_Y = INSTR3_Y + 20;
 
  private static final int CANVAS_WIDTH = 750;
  private static final int CANVAS_HEIGHT = 750;
  private static final int FLIP_PRESS_THRESHOLD = 500; // Half a second
  private Timer timer; //timer object
  private BlockH block; //BlockH object
  
  private Location pressPoint; //Starting location where the mouse is pressed
  int Hcounter = 0; //The states of the Hblock
  int dragState = 2; //State while block is being dragged
  boolean flipped = false; //Check if block is already flipped
  boolean inverted = false; //Checks if colors are inverted

  /**
   * Method initiates by displaying instructions
   *
   * @no param
   * @no return
   */  
  public void begin() {
    timer = new Timer(); //create timer object
    instr1 = new Text("Click to display a Block H centered at the mouse click.",
    INSTR1_X, INSTR1_Y, canvas);
    instr2 = new Text(
    "Mouse press in any part of the image and drag to move image around.",
    INSTR2_X, INSTR2_Y, canvas);
    instr3 = new Text("Mouse click in the left or right block of the H" +
    " for more than 0.5 seconds to flip the Block H.",
    INSTR3_X, INSTR3_Y, canvas);
    instr4 = new Text("Mouse click in the center block of the H for more" +
    " than 0.5 seconds to invert the colors.",
    INSTR4_X, INSTR4_Y, canvas);
  }

  /**
   * Clicking will hide the instructions and make the H appear
   *
   * @param point this is the location where the mouse click occured
   * @no return
   */  
  public void onMouseClick(Location point) {
    if(Hcounter == 0) { //Meaning block has not been created yet
      block = new BlockH(point, canvas);
      Hcounter = 1; //Block has been created
      instr1.hide();
      instr2.hide();
      instr3.hide();
      instr4.hide();
    }
  }

  /**
   * Mouse press makes the block light up 
   *
   * @param point this is the location where the mouse press occured
   * @no return
   */  
  public void onMousePress(Location point) {
    if(Hcounter == 1) { //Only runs if block exists
      timer.reset(); //Timer starts after mouse press
      if(block.contains(point)) {
        block.showColors(true); //Only lights up if block is pressed
        pressPoint = point; //This is where the mouse was pressed
      }
    }  
  }

  /**
   * Mouse drag moves the block around
   *
   * @param point this is the location where the mouse is being dragged
   * @no return
   */  
  public void onMouseDrag(Location point) {
    Hcounter = dragState; //So dragging won't flip the block or invert
    block.move(point.getX()-pressPoint.getX(), point.getY()-pressPoint.getY());
    //Block moves according to how far it's dragged from where it was pressed
    pressPoint = point;
    //This method is constantly running so the pressPoint must change with each
    //new drag point
  }

  /**
   * Mouse release, if after pressed long enough, will flip the block
   * or invert it's colors. The block also stops being colorful on release.
   *
   * @param point this is the location where the mouse release occured
   * @no return
   */  
  public void onMouseRelease(Location point) {
    if(Hcounter == 1) { //Only runs after block is created
      if(inverted == false) { //If it's not inverted, block goes black after
        block.showColors(false);
      }
      if(inverted == true) { //If it's inverted, block goes white after
        block.invert(); //Makes block white
      }
      if(timer.elapsedMilliseconds() > 500) { //Pressed for 0.5 seconds
        if(block.inLeftBar(point)) { //Pressed on leftbar
          if(flipped == false) { //If block is not flipped
            block.flipLeft(); //Flips left
            flipped = true; //Block is now flipped
          } else { //If block is flipped
            block.flipLeft2(); 
            //Flips right now because leftBar is currently on the right side
            flipped = false; //Block is back to normal
          }
        } 
        if(block.inRightBar(point)) { //Pressed on rightbar
          if(flipped == false) { //If block is not flipped
            block.flipRight(); //Flips block right
            flipped = true; //Block is now flipped
          } else { //If block is flipped
            block.flipRight2();
            //Flips left now because rightBar is currently on the left side
            flipped = false; //Block is back to normal
          }
        } 
        if(block.inCenterBar(point)) { //Pressed on center bar
          if(inverted == false) { //If not inverted
            block.invert(); //Block becomes white
            ((JDrawingCanvas)canvas).setBackground(Color.black); 
            //Canvas becomes black
            inverted = true; //Canvas is now inverted
          } else { //If inverted
            ((JDrawingCanvas)canvas).setBackground(Color.white);
            //Canvas becomes white again
            block.showColors(false); //Block becomes black again
            inverted = false; //Canvas back to normal
          }
        }
      }
    }
    if(Hcounter == dragState) { //If released while in drag state
      if(inverted == false) { //Not inverted
        block.showColors(false); //Block goes back to black
      } 
      if(inverted == true) { //Inverted 
        block.invert(); //Block goes back to white
      } 
      Hcounter = 1; //After drag state is exited, block goes to normal state
    }
  }
  
  /**
   * Exiting the canvas hides the instructions and removes the block
   * It also resets all the states
   *
   * @param point this is the location where the mouse exit occured
   * @no return
   */  
  public void onMouseExit(Location point) {
    if(Hcounter == 1) {
      block.removeFromCanvas(); //Removes block
      ((JDrawingCanvas)canvas).setBackground(Color.white); //Canvas normal
      inverted = false; //Reset inverted boolean
      flipped = false; //Reset flipped boolean
    }
    instr1.hide();
    instr2.hide();
    instr3.hide();
    instr4.hide();
  }

  /**
   * Mouse enter shows instructions and resets the program
   *
   * @param point this is the location where the mouse enter occured
   * @no return
   */  
  public void onMouseEnter(Location point) {
    instr1.show(); 
    instr2.show();
    instr3.show();
    instr4.show();
    Hcounter = 0; //Block does not exist and program resets
  }

  /**
    * main method runs my program in an Acme frame so it will
    * run as an application that uses object draw
    *
    * @no param
    * @no return
    */
  public static void main(String[] args) {
    new Acme.MainFrame(new FlippingBlockH(), args, CANVAS_WIDTH, CANVAS_HEIGHT);
  }
}
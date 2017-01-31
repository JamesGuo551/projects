/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: October 13, 2016
 * File: BlockH.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program defines the BlockH object.
 */

import objectdraw.*; 
import java.awt.*;

/**
 * This class defines what an HBlock is and what it can do
 */
public class BlockH {
  private FilledRect leftBar, rightBar, centerBar;
  
  // Dimensions of center bar
  private static final double CENTER_BAR_WIDTH = 50;
  private static final double CENTER_BAR_HEIGHT = 25;
  private static final double HALF_CENTER_BAR_HEIGHT = 12.5;
  // Dimensions of side bars
  private static final double SIDE_BARS_WIDTH = 25;
  private static final double SIDE_BARS_HEIGHT = 125;
  private static final double HALF_SIDE_BARS_HEIGHT = 62.5;
  
  /**
   * Creates a BlockH object at location point
   *
   * @param point this is the location where the mouse was clicked
   * @no return
   */
  public BlockH(Location point, DrawingCanvas canvas) {
    centerBar = new FilledRect(point.getX()-CENTER_BAR_HEIGHT, point.getY()-
    HALF_CENTER_BAR_HEIGHT, CENTER_BAR_WIDTH, CENTER_BAR_HEIGHT, canvas);
    leftBar = new FilledRect(point.getX()-CENTER_BAR_HEIGHT-SIDE_BARS_WIDTH,
    point.getY()-HALF_SIDE_BARS_HEIGHT, SIDE_BARS_WIDTH, SIDE_BARS_HEIGHT, 
    canvas);
    rightBar = new FilledRect(point.getX()+SIDE_BARS_WIDTH, point.getY()-
    HALF_SIDE_BARS_HEIGHT, SIDE_BARS_WIDTH, SIDE_BARS_HEIGHT, canvas);
  }

  /**
   * Checks if the block contains a point
   *
   * @param point this is the location where the mouse was pressed
   * @no return
   */
  public boolean contains(Location point) {
    if(centerBar.contains(point) || leftBar.contains(point) ||
    rightBar.contains(point)) {
      return true;
    } else {
      return false;
    }  
  }

  /**
   * Checks if the point is in the left bar
   *
   * @param point this is the location where the mouse was pressed
   * @no return
   */
  public boolean inLeftBar(Location point) {
    if(leftBar.contains(point)) {
      return true;
    } else {
      return false;
    } 
  }

  /**
   * Checks if the point is in the center bar
   *
   * @param point this is the location where the mouse was pressed
   * @no return
   */
  public boolean inCenterBar(Location point) {
    if(centerBar.contains(point)) {
      return true;
    } else {
      return false;
    } 
  }
 

  /**
   * Checks if the point is in the right bar
   *
   * @param point this is the location where the mouse was pressed
   * @no return
   */
  public boolean inRightBar(Location point) {
    if(rightBar.contains(point)) {
      return true;
    } else {
      return false;
    } 
  }

  /**
   * Moves the block
   *
   * @param dx amount to move in the x-direction
   * @param dy amount to move in the y-direction
   * @no return
   */
  public void move(double dx, double dy) {
    leftBar.move(dx, dy);
    centerBar.move(dx, dy);
    rightBar.move(dx, dy);
  }

  /**
   * Removes the block from the canvas
   *
   * @no param
   * @no return
   */
  public void removeFromCanvas() {
    leftBar.removeFromCanvas();
    centerBar.removeFromCanvas();
    rightBar.removeFromCanvas();
  }

  /**
   * Flips the block left
   *
   * @no param
   * @no return
   */
  public void flipLeft() {
    centerBar.move(-(SIDE_BARS_WIDTH+CENTER_BAR_WIDTH), 0);
    rightBar.move(-(CENTER_BAR_WIDTH+CENTER_BAR_WIDTH+CENTER_BAR_WIDTH), 0);
  }

  /**
   * Flips the block right, but only after it is flipped left
   *
   * @no param
   * @no return
   */
  public void flipLeft2() {
    centerBar.move(SIDE_BARS_WIDTH+CENTER_BAR_WIDTH, 0);
    rightBar.move((CENTER_BAR_WIDTH+CENTER_BAR_WIDTH+CENTER_BAR_WIDTH), 0);
  }

  /**
   * Flips the block right
   *
   * @no param
   * @no return
   */
  public void flipRight() {
    centerBar.move(SIDE_BARS_WIDTH+CENTER_BAR_WIDTH, 0);
    leftBar.move(CENTER_BAR_WIDTH+CENTER_BAR_WIDTH+CENTER_BAR_WIDTH, 0);
  }

  /**
   * Flips the block left, but only after it is flipped right
   *
   * @no param
   * @no return
   */
  public void flipRight2() {
    centerBar.move(-(SIDE_BARS_WIDTH+CENTER_BAR_WIDTH), 0);
    leftBar.move(-(CENTER_BAR_WIDTH+CENTER_BAR_WIDTH+CENTER_BAR_WIDTH), 0);
  }

  /**
   * Changes the block to colorful or black
   *
   * @param on true for colorful, false for black
   * @no return
   */
  public void showColors(boolean on) {
    if(on == true) {
      leftBar.setColor(Color.red);
      centerBar.setColor(Color.green);
      rightBar.setColor(Color.blue);
    } else { 
      leftBar.setColor(Color.black);
      centerBar.setColor(Color.black);
      rightBar.setColor(Color.black);
    }  
  }

  /**
   * Makes the block inverted(white)
   *
   * @no param
   * @no return
   */
  public void invert() {
    leftBar.setColor(Color.white);
    centerBar.setColor(Color.white);
    rightBar.setColor(Color.white);
  }
}
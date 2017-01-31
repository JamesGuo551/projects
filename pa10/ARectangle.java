/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: December 1, 2016
 * File: Circle.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program defines the behavior of an ARectangle.
 */

import java.awt.*;
import objectdraw.*;

/**
 * ARectangle is abstract because square and rectangle are ARectanlges
 */
public abstract class ARectangle extends Shape {
  // Upper left corner 
  private Point upperLeft;

  /**
   * ARectangle constructor
   * makes an ARectangle with meaningless values
   */ 
  public ARectangle() {
    setUpperLeft( new Point(0,0) );
  }

  /**
   * ARectangle constructor
   * @param name is the name of the ARectangle
   * @param x is the x coordinate of upperleft corner
   * @param y is the y coordinate of upperleft corner
   */
  public ARectangle( String name, int x, int y ) {
    //new point for deep copy
    setUpperLeft( new Point(x,y) );
  }
  
  /**
   * ARectangle constructor
   * @param name is the name of the ARectangle
   * @param x is the x coordinate of upperleft corner
   * @param y is the y coordinate of upperleft corner
   */
  public ARectangle( String name, Point upperLeft ) {
    //new point for deep copy
    setUpperLeft( new Point( upperLeft ) );
  }

  /**
   * ARectangle constructor
   * @param r is the ARectangle to make a copy of
   */
  public ARectangle( ARectangle r ) {
    //new point for deep copy
    setUpperLeft( new Point( r.upperLeft ) );
  }

  /**
   * move method moves upper left, also moving ARectangle
   * @param xDelta is amount to move in x direction
   * @param yDelta is amount to move in y direction
   */
  public void move( int xDelta, int yDelta ) {
    upperLeft.move(xDelta, yDelta);
  }

  /**
   * to string method
   * prints out the shape name and its attributes
   * @return returns this as a string
   */
  @Override
  public String toString() { 
    return this.getClass().getName() + ": Upper Left Corner: Point: " +
    upperLeft.toString();
  } 

  /**
   * equals method
   * I just check if the toString matches up because that will achieve the 
   * check just fine.
   * @param o is the object to check equality to
   * @return returns true for equality, false for not
   */
  @Override
  public boolean equals( Object o ) { 
    //prevent NPE	  
    if(o != null && this.toString().equals(o.toString())) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * hashcode method
   * @return returns hashcode of my shape
   */
  @Override
  public int hashCode() { 
    return this.toString().hashCode();
  }

  /**
   * set upperLeft method
   * @param p is the point to set upperleft to
   */
  private void setUpperLeft(Point p) {
    this.upperLeft = p;
  }

  /**
   * get upperleft method
   * @return returns upperleft as a point
   */
  public Point getUpperLeft() {
    return this.upperLeft;
  }
}

/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: December 1, 2016
 * File: CSE11_Line.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program defines the behavior of a CSE11 line.
 */

import java.awt.*;
import objectdraw.*;

/**
 * CSE11_Line is an instance of a shape object
 */
public class CSE11_Line extends Shape {
  //start is the starting point of a line
  private Point start;
  //end is the ending point of a line
  private Point end;

  /**
   * CSE11_Line constructor
   * Makes a CSE11_Line with meaningless values
   */
  public CSE11_Line() {
    setStart( new Point(0,0) );
    setEnd( new Point(0,0) ); 
  }

  /**
   * CSE11_Line constructor
   * @param start is the starting point to make a copy of
   * @param end is the ending point to make a copy of
   */
  public CSE11_Line( Point start, Point end ) {
    //new point so deep copy
    setStart( new Point(start) );
    setEnd( new Point(end) );
  }

  /**
   * CSE11_Line constructor
   * @param line is the line to make a copy of
   */
  public CSE11_Line( CSE11_Line line ) { 
    //new point to make a deep copy
    setStart( new Point( line.getStart() ) );
    setEnd( new Point( line.getEnd() ) );
  }

  /**
   * Move method
   * @param xDelta is the amount to move in the x direction
   * @param yDelta is the amount to move in the y direction
   */
  public void move( int xDelta, int yDelta ) {
    start.move(xDelta, yDelta);
    end.move(xDelta, yDelta);
  }

  /**
   * to string method
   * writes out the shape name and its attributes
   * @return returns this as a string
   */
  @Override
  public String toString() {
    return this.getClass().getName() + ": Point: " + this.start.toString() + 
    " to Point: "
    + this.end.toString();
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
    if(o != null && this.toString().equals(o.toString())) {
      return true;
    } else {
      return false;
    }
  }

  /** 
   * hashcode converts string to hashcode
   * @return returns hashcode
   */
  @Override
  public int hashCode() { 
    return this.toString().hashCode();
  }

  /**
   * draw method draws the line
   * @param canvas is what to draw on
   * @param c is the color of the line
   * @param fill is meaningless
   */
  public void draw( DrawingCanvas canvas, Color c, boolean fill ) { 
    Location s = new Location(start.getX(), start.getY());
    Location e = new Location(end.getX(), end.getY());
    Line line = new Line(s, e, canvas);
    line.setColor(c);
    if(c == null) {
      line.setColor(Color.BLACK);
    }
  }

  /**
   * used to get the starting point of the line
   * @return returns it as a point
   */
  public Point getStart() {
    return this.start;
  }

  /**
   * used to get the ending point of the line
   * @return returns it as a point
   */
  public Point getEnd() {
    return this.end;
  }

  /**
   * used to set the starting point of the line
   * @param p is the point to set the start with
   */
  private void setStart(Point p) {
    this.start = p;
  }

  /**
   * used to set the ending point of the line
   * @param p is the point to set the end with
   */
  private void setEnd(Point p) {
    this.end = p;
  }
} 

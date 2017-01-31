/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: December 1, 2016
 * File: Circle.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program defines the behavior of a circle.
 */

import java.awt.*;
import objectdraw.*;

/**
 * Circle class is a shape object
 */
public class Circle extends Shape {
  private Point center; //center of circle
  private int radius; //radius of circle

  /**
   * circle constructor
   * makes a circle with meaningless values.
   */
  public Circle() { 
    setCenter( new Point(0,0) );
    setRadius(0);
  }

  /**
   * circle constructor
   * makes a deep copy circle with these values
   * @param center is the center
   * @param radius is the radius
   */
  public Circle( Point center, int radius ) { 
    //new object for deep copy
    setCenter( new Point(center) );
    setRadius( radius );
  }

  /**
   * circle constructor
   * makes a deep copy of circle c
   * @param c is the circle to copy values from
   */
  public Circle( Circle c ) { 
    //new point for deep copy
    setCenter( new Point( c.getCenter() ) ); 
    //radius is primitive
    setRadius( c.getRadius() ); 
  }

  /** 
   * move method
   * moves the center, which will move the circle
   * @param xDelta is the amount to move in x direction
   * @param yDelta is the amount to move in y direction
   */
  public void move( int xDelta, int yDelta ) { 
    center.move(xDelta, yDelta);
  }

  /**
   * to string method
   * writes out the shape name and its attributes
   * @return returns this as a string
   */
  @Override
  public String toString() {
    return this.getClass().getName() + ": Center: Point: " + center.toString() +
    "; Radius: " + radius;
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
   * draw method draws the circle
   * @param canvas is what to draw on
   * @param c is the color of the circle
   * @param fill is whether or not the circle is filled
   */
  public void draw( DrawingCanvas canvas, Color c, boolean fill ) { 
    //origin is top left
    Location origin = new Location(center.getX()-radius, center.getY()-radius);
    //filled oval only takes width or height arguments
    double diameter = radius+radius;
    //makes a filled oval
    if(fill == true) {
      FilledOval circle = new FilledOval(origin, diameter, diameter, canvas); 
      circle.setColor(c);
    //makes an empty oval      
    } else {
      FramedOval circle = new FramedOval(origin, diameter, diameter, canvas);
      circle.setColor(c);
    }
  }

  /**
   * used to get the center of a circle object
   * @return returns center as a point
   */
  public Point getCenter() {
    return this.center;
  }

  /**
   * used to get the radius of a circle object
   * @return returns the radius
   */
  public int getRadius() {
    return this.radius;
  }

  /**
   * used to set the radius of a circle object
   * @param r is the value to set radius to
   */ 
  private void setRadius(int r) {
    this.radius = r;
  }

  /**
   * used to set the center of a circle object
   * @param p  is the point to set the center to
   */
  private void setCenter(Point p) {
    this.center = p;
  }
}

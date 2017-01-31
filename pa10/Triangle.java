/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: December 1, 2016
 * File: Triangle.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program defines the behavior of a triangle.
 */

import java.awt.*;
import objectdraw.*;

/**
 * Triangle is an instance of a shape object
 */
public class Triangle extends Shape {
  //These are the three endpoints of the triangle
  private Point p1;
  private Point p2;
  private Point p3;

  /**
   * Triangle constructor
   * Makes a triangle with meaningless values
   */
  public Triangle() {
    //new point for deep copies
    setP1( new Point(0,0) );
    setP2( new Point(0,0) );
    setP3( new Point(0,0) );
  }

  /**
   * Triangle constructor
   * @param p1, p2, p3 are the points to make copies of
   */
  public Triangle( Point p1, Point p2, Point p3 ) { 
    //new point for deep copy
    setP1( new Point(p1) );
    setP2( new Point(p2) );
    setP3( new Point(p3) );
  }

  /**
   * Triangle constructor
   * @param tri is the triangle to make a copy of
   */
  public Triangle( Triangle tri ) { 
    //new point for deep copy
    setP1( new Point( tri.getP1() ) );
    setP2( new Point( tri.getP2() ) );
    setP3( new Point( tri.getP3() ) );
  }

  /**
   * move method
   * @param xDelta is amount to move in x direction
   * @param yDelta is amount to move in y direction
   */
  public void move( int xDelta, int yDelta ) { 
    //moving the three points moves the triangle
    p1.move(xDelta, yDelta);
    p2.move(xDelta, yDelta);
    p3.move(xDelta, yDelta);
  }

  /**
   * to string method
   * writes out the shape name and its attributes
   * @returns this as a string
   */
  @Override
  public String toString() {
    return this.getClass().getName() + ": Point: " + p1.toString() + 
    ", Point: " + p2.toString() + ", Point: " + p3.toString();
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
    if(o != null && this.toString().equals( o.toString() ) ) {
      return true;
    } else {
      return false;
    }
  }

  /** 
   * hashcode converts string to hashcode
   * @returns this as an int
   */
  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  /**
   * draw method draws the triangle
   * @param canvas is what to draw on
   * @param c is the color of the triangle
   * @param fill is whether or not the triangle is filled
   */
  public void draw( DrawingCanvas canvas, Color c, boolean fill ) { 
    CSE11_Line edge1 = new CSE11_Line(p1, p2);
    CSE11_Line edge2 = new CSE11_Line(p2, p3);
    CSE11_Line edge3 = new CSE11_Line(p3, p1);
    edge1.draw(canvas, c, true);
    edge2.draw(canvas, c, true);
    edge3.draw(canvas, c, true);
  }

  /**
   * getP1 method gets P1
   * @return returns p1
   */
  public Point getP1() {
    return this.p1;
  }

  /**
   * getP2 method gets p2
   * @return returns p2
   */
  public Point getP2() {
    return this.p2;
  }

  /**
   * getP3 method gets p3
   * @return returns p3
   */
  public Point getP3() {
    return this.p3;
  }

  /**
   * setP1 method sets P1
   * @param p1 is the point to set p1 too
   */
  private void setP1( Point p1 ) {
    this.p1 = p1;
  } 

  /**
   * setP2 method sets P2
   * @param p2 is the point to set p2 to
   */
  private void setP2( Point p2 ) {
    this.p2 = p2;
  } 

  /**
   * setP3 method sets p3
   * @param p3 is the point to set p3 to
   */
  private void setP3( Point p3 ) {
    this.p3 = p3;
  } 
}

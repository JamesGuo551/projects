/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: December 1, 2016
 * File: Rectangle.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program defines the behavior of a Rectangle.
 */

import java.awt.*;
import objectdraw.*;

/**
 * Rectangle is an instance of an ARectangle object
 */
public class Rectangle extends ARectangle {
  //Rectangle width
  private int width;
  //Rectangle height
  private int height;

  /**
   * Rectangle constructor
   * makes a Rectangle with meaningless values
   */
  public Rectangle() {
    //to set upperleft corner
    super();
    setWidth(0);
    setHeight(0);
  }

  /**
   * Rectangle constructor
   * @param x is the x coordinate of upperleft corner
   * @param y is the y coordinate of upperleft corner
   * @param width is the width of the rectangle
   * @param height is the height of the rectangle
   */
  public Rectangle(int x, int y, int width, int height) {
    //set upper left and name
    super("Rectangle", x, y);
    setWidth(width);
    setHeight(height);
  }

  /**
   * Rectangle constructor
   * @param upperleft is the point to make a copy of
   * @param width is the width of the rectangle
   * @param height is the height of the rectangle
   */
  public Rectangle(Point upperLeft, int width, int height) {
    //set upper left and name
    super("Rectangle", upperLeft);
    setWidth(width);
    setHeight(height);
  }

  /**
   * Rectangle constructor
   * @param r is the rectangle to make a copy of
   */
  public Rectangle(Rectangle r) {
    //set upper left and name
    super("Rectangle", r.getUpperLeft() );
    setWidth(r.getWidth());
    setHeight(r.getHeight());
  }

  /**
   * to string method
   * writes out the shape name and its attributes
   * @return returns this as a string
   */
  @Override
  public String toString() { 
    return super.toString() + " Width: " + width + " Height: " + height;
  } 

  /**
   * equals method
   * I just check if the toString matches up because that will achieve the 
   * check just fine.
   * @param o is the object to check equality to
   * @return returns true for equality, false for not
   */
  @Override
  public boolean equals(Object o) { 
    if(o != null && this.toString().equals(o.toString())) {
      return true;
    } else {
      return false;
    }
  } 

  /**
   * draw method draws the rectangle
   * @param canvas is what to draw on
   * @param c is the color of the rectangle
   * @param fill is whether or not the rectangle is filled
   */
  public void draw( DrawingCanvas canvas, Color c, boolean fill ) {
    //make upperleft into a location
    Location upperLeft = new Location(super.getUpperLeft().getX(),
    super.getUpperLeft().getY() );
    if(fill == true) {
      //filled rectangle
      FilledRect rectangle = new FilledRect(upperLeft, width, height, canvas);
      rectangle.setColor(c);
      if(c == null) {
        rectangle.setColor(Color.BLACK);
      }
    } else {
      //framed rectangle
      FramedRect rectangle = new FramedRect(upperLeft, width, height, canvas);
      rectangle.setColor(c);
      if(c == null) {
        rectangle.setColor(Color.BLACK);
      }
    }
  }

  /**
   * used to get the width of a rectangle
   * @return returns width
   */
  public int getWidth() {
    return this.width;
  } 

  /**
   * used to get the height of a rectangle
   * @return returns height
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * used to set the width of a rectangle
   * @param w is the value to set width to
   */
  private void setWidth( int w ) {
    this.width = w;
  }

  /**
   * used to set the height of a rectangle
   * @param h is the value to set height to
   */
  private void setHeight( int h ) {
    this.height = h;
  }
}

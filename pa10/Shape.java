/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: December 1, 2016
 * File: Shape.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program is at the top of my hierarchy for all the objects I'll draw.
 */

import java.awt.*;
import objectdraw.*;

/**
 * Shape is an abstract class so I can make shape objects.
 */
public abstract class Shape {
  //name of the shape
  private String name;
  
  /**
   * makes a shape object with meaningless values.
   */ 
  public Shape() {
    this.setName( new String("") );	  
  }

  /**
   * shape constructor that makes a deep copy
   * @param name is the name of the shape
   */
  public Shape( String name ) {
    this.setName( new String(name) );
  }

  /**
   * to get the name of the shape
   * @return returns it as a string
   */
  public String getName() {
    return this.name;
  }

  /**
   * to set the name of the shape
   * @param name is the string to set name to
   */
  private void setName( String name ) { 
    this.name = name;
  }

  /* Copy this as is in your Shape.java */
  /**
   * equals method checks for shape equality
   * @param o is the object to check equality for
   * @return returns true for equality, false for not
   */
  @Override
  public boolean equals( Object o ) {
    String s = "\n**********************************************************\n"
     + "This should never print. If it does print, then\n"
     + "you did not override equals() properly in class "
     + this.getClass().getName() + "\n"
     + "**********************************************************\n";
    System.out.println( s );
    return this == o;
  }

  /**
   * abstract move method
   * @param xDelta, yDelta are just placeholders
   */
  public abstract void move( int xDelta, int yDelta );

  /**
   * abstract draw method
   * @param canvas, c, fill are just placeholders
   */
  public abstract void draw( DrawingCanvas canvas, Color c, boolean fill );
}

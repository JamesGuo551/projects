/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: October 27, 2016
 * File: PuzzlePiece.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program defines the behavior of a PuzzlePiece object.
 */
import objectdraw.*; 
import java.awt.*;

/**
 * This class defines what a PuzzlePiece is and how it should act.
 */
public class PuzzlePiece implements Piece {
  double centerDistance = 50; //Distance from corner of piece to center
  double framedRectSize = 100; //Border size
  double offCanvas = 5000; //Move pieces off canvas so can't select them
  private FramedRect framedRect1; //Initiate framedrect object
  private int id; //Initiate piece id
  private Location loc = null; //Initiate location object(center of piece)
  private VisibleImage image; //Initiate visible image object

  /**
   * PuzzlePiece constructor
   * @param the parameter functions are explained above
   * @param canvas is the canvas we make this on
   */
  public PuzzlePiece(Image img, int id, Location loc, DrawingCanvas canvas) {
    image = new VisibleImage(img, loc, canvas);
    framedRect1 = new FramedRect(loc, framedRectSize, framedRectSize, canvas);
    this.id = id;
    this.loc = loc;
  }
  
  /**
   * Need this here because piece interface
   */
  public void showHighlight(Color color) {
  }

  /**
   * Need this here because piece interface
   */
  public void hideHighlight() {
  }

  /**
   * Need this here because piece interface
   */
  public void show() {
  }

  /**
   * After PuzzlePiece is in place, we move it off the canvas so that user 
   * can't select it by mistake if it overlaps with a board piece
   */
  public void hide() {
    image.move(offCanvas,offCanvas);
    framedRect1.move(offCanvas,offCanvas);
  }

  /**
   * Moves puzzle piece
   * @param dx amount to move in x direction
   * @param dy amount to move in y direction
   */
  public void move(double dx, double dy) {
    image.move(dx, dy);
    framedRect1.move(dx, dy);
  }
  
  /**
   * Used to check if we pressed on PuzzlePiece
   * @param point to see if point pressed is inside the piece
   * @return true or false to see if piece contains point or not
   */
  public boolean contains(Location point) {
    return image.contains(point);
  }

  /**
   * Doesnt do anything since boardpiece has it
   * @param o is a BoardPiece object
   * @return true because needs to have a return value
   */
  public boolean equals(Object o) {
    return true;
  }
  
  /**
   * Removes puzzle pieces from board
   */
  public void clear() {
    framedRect1.removeFromCanvas();
    image.removeFromCanvas();
  }
  
  /**
   * Get PuzzlePiece ID
   * @return returns ID
   */
  public int getId() {
    return id;
  }

  /**
   * Gets center of PuzzlePiece
   * @return center as a location
   */  
  public Location getCenter() {
    Location center = new Location(image.getLocation().getX()+centerDistance, 
	                  image.getLocation().getY()+centerDistance);
    return center;
  }
}

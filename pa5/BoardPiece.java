/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: October 27, 2016
 * File: BoardPiece.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program defines the behavior of a BoardPiece object.
 */
import objectdraw.*; 
import java.awt.*;

/**
 * This class defines what a BoardPiece is and how it should act.
 */
public class BoardPiece implements Piece {
  double centerDistance = 50; //Distance from corner of piece to center
  double containDistance = 25; //Square in center of piece where center of
                               //puzzle piece can be placed to lock in
  double framedRectSize = 100; //Border size
  double inset = 1; //Makes border thicker
  private FramedRect framedRect1, framedRect2;//Initiates two objects for border
  private int id; //Initiates BoardPiece id
  private Location loc = null; //Initiates location of piece
  private Location center = null; //Initiates center
  private VisibleImage image; //Initiates image

  /**
   * BoardPiece constructor
   * @param the paramater functions are explained
   */
  public BoardPiece(Image img, int id, Location loc, DrawingCanvas canvas) {
    image = new VisibleImage(img, loc, canvas);
    framedRect1 = new FramedRect(loc, framedRectSize, framedRectSize, canvas);
    framedRect2 = new FramedRect(loc, framedRectSize-inset, 
		                 framedRectSize-inset, canvas);
    framedRect2.hide();
    image.hide();
    this.id = id;
    this.loc = loc;
  }

  /**
   * Makes the highlighted border show.
   * @param color is the color that the border will be set to
   */
  public void showHighlight(Color color) {
    framedRect2.show();
    framedRect2.setColor(color);
    framedRect1.hide();
  }

  /**
   * Hides the highlighted border.
   */
  public void hideHighlight() {
    framedRect2.hide();
  }

  /**
   * Shows the boardpiece.
   */
  public void show() {
    image.show();
  }

  /**
   * Removes the boardpiece.
   */
  public void hide() {
    framedRect1.removeFromCanvas();
    framedRect2.removeFromCanvas();
    image.removeFromCanvas();
  }

  /**
   * Moves the boardpiece
   * @param dx is the amount the piece moves in the x direction
   * @param dy is the amount the piece moves in the y direction
   */
  public void move(double dx, double dy) {
    image.move(dx, dy);
    framedRect1.move(dx, dy);
    framedRect2.move(dx, dy);
  }

  /**
   * Checks if point is close enough to center of the boardpiece
   * @param point is a location that I will replace with puzzle piece center
   * @param dy is the amount the piece moves in the y direction
   * @return true if point is close enough to boardpiece
   */
  public boolean contains(Location point) {
    if((getCenter().getX() <= point.getX()+containDistance && 
       getCenter().getX() >= point.getX()-containDistance) &&
       (getCenter().getY() <= point.getY()+containDistance && 
       getCenter().getY() >= point.getY()-containDistance)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the whole piece contains a point after puzzle is complete
   * @param point is the mouse point to check
   * @return returns true if piece contains mouse point
   */
  public boolean victoryContains(Location point) {
    return image.contains(point);
  }

  /**
   * Checks if id of Boardpiece is equal to puzzle piece id
   * @param o is a puzzle piece object
   * @return true if ids are equal
   */
  public boolean equals(Object o) {
    if(this.id == ((PuzzlePiece)o).getId()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets id of boardpiece
   * @return returns id
   */
  public int getId() {
    return id;
  }

  /**
   * Gets center of boardpiece
   * @return returns center as a location
   */
  public Location getCenter() {
    Location center = new Location(image.getLocation().getX()+centerDistance, 
	                           image.getLocation().getY()+centerDistance);
    return center;
  }
}

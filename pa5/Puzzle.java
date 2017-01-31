/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: October 27, 2016
 * File: Puzzle.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program defines the behavior of a Puzzle object.
 */
import objectdraw.*;
import java.awt.*;
import java.util.Random;

/**
 * This class defines what a puzzle is and how it should act.
 */
public class Puzzle extends WindowController {
  private static final int PIECES_PER_COL = 3; 
  // can be changed to expand the puzzle
  private static final int PIECES_PER_ROW = 3;
  // can be changed to expand the puzzle
  private static final int PIECES = 9;
  private static final int PUZZLE_SPACING = 20; 
  // num of px between PuzzlePieces
  private static final int PUZZLE_OFFSET = 355; 
  // offset from left side of canvas
  private static final int BOARD_MARGIN_X = 25; // left margin of the board
  private static final int BOARD_MARGIN_Y = 40; // top margin of the board
  private static final int SIDE_LENGTH = 100; // side length of each Piece
  private static final int CANVAS_WIDTH = 735; //Canvas width
  private static final int CANVAS_HEIGHT = 380; //canvas height
  private static final int MESSAGE_LOC = 3; //has y-value of 4th piece
  private static final int NOT_PRESSED = 100; //Used to reset pressed counter
  private static final int FONT_SIZE = 55; //Size of text
  int randomInt = 0; //replace this with random ints later
  int verify = 9; //Because this is way easier to verify victory lol
  int[] random = new int[PIECES]; //random int array intiator
  private Location pressPoint; //Starting location where the mouse is pressed
  int pressed = 100; //checks which piece is pressed and acts as boolean too
  PuzzlePiece[] puzzlePieces = new PuzzlePiece[PIECES]; //initiate PP array
  BoardPiece[] boardPieces = new BoardPiece[PIECES]; //initiate BP array
  Image[] images = new Image[PIECES]; //initiate visible image array
  Location[] puzzleLocations = new Location[PIECES]; //initiate PL array
  Location[] boardLocations = new Location[PIECES]; //initiate BL array
  Text startingMessage = null; //initiate starting message
  
  /**
   * This method generates a random number and set random array to 1 so that
   * it stops after 9 numbers have been generated.
   * @return returns an int value from 0-8
   */
  public int getRandomPuzzle() {
    Random randomGenerator = new Random();
    while(true) { //runs indefinitely until it generates a position in random
	          //that isnt 1(which means that location is used)
      randomInt = randomGenerator.nextInt(PIECES);
      if(random[randomInt] == 0) {
        random[randomInt] = 1;
        return randomInt;
      }
    }   
  }

  /**
   * This method makes all the stuff at the beginning
   */
  public void begin() {
    startingMessage = new Text("YOU WIN!", (double)(PUZZLE_OFFSET), 
    BOARD_MARGIN_Y + SIDE_LENGTH * (MESSAGE_LOC / PIECES_PER_ROW), canvas);
    startingMessage.setColor(Color.green); //green message
    startingMessage.setBold(true); 
    startingMessage.setFontSize(FONT_SIZE);
    startingMessage.hide(); //hides message at start
    for(int x = 0; x < PIECES; x++) {
      images[x] = getImage("p" + x + ".jpg"); //gets images
      puzzleLocations[x] = new Location(PUZZLE_OFFSET + PUZZLE_SPACING * (x % 
      PIECES_PER_COL + 1) + SIDE_LENGTH * (x % PIECES_PER_COL),
      PUZZLE_SPACING * (x / PIECES_PER_ROW + 1) + SIDE_LENGTH * (x / 
      PIECES_PER_ROW)); //constructs puzzle piece locations     
      boardLocations[x] = new Location(BOARD_MARGIN_X + SIDE_LENGTH * (x % 
      PIECES_PER_ROW), BOARD_MARGIN_Y + SIDE_LENGTH * (x / PIECES_PER_ROW));
      //constructs board piece locations   
    }
    for(int x = 0; x < PIECES; x++) { //constructs boardpieces
      boardPieces[x] = new BoardPiece(images[x], x, boardLocations[x], canvas);
      random[x] = 0; //resets random array before making puzzle pieces 
    }
    for(int x = 0; x < PIECES; x++) { //constructs puzzle pieces
      puzzlePieces[x] = new PuzzlePiece(images[x], x,
      puzzleLocations[getRandomPuzzle()], canvas); //random locations
    }
  }
  
  /**
   * This method runs on mouse press
   * @param point is where the mouse is pressed
   */
  public void onMousePress(Location point) {
    for(int x = 0; x < PIECES; x++) { //goes through all PP to see which pressed
      if(puzzlePieces[x].contains(point)) {
        pressed = x; //x is the id of pressed piece
	pressPoint = point; //store point value
      }
    }
    if(verify == 0) { //does something else when puzzle is complete
      for(int x = 0; x < PIECES; x++) {
        if(boardPieces[x].victoryContains(point)) { 
          pressed = x; //pressed != 100(or true) when ANY piece is pressed
          pressPoint = point; //store point value
        }
      }
    }
  }

  /**
   * This method runs on mouse drag.
   * @param point is where the mouse is dragged
   */
  public void onMouseDrag(Location point) {
    //only runs when a piece was pressed
    //only moves pieces if their centers arent at the boundary
    if(pressed != NOT_PRESSED && puzzlePieces[pressed].getCenter().getX()>0
		  && puzzlePieces[pressed].getCenter().getY()>0
		  && puzzlePieces[pressed].getCenter().getX()<CANVAS_WIDTH
		  && puzzlePieces[pressed].getCenter().getY()<CANVAS_HEIGHT) {
      puzzlePieces[pressed].move(point.getX()-pressPoint.getX(), 
                                 point.getY()-pressPoint.getY());
      pressPoint = point; //since point is always changing
    }
    //In case center is at a boundary
    if(pressed != NOT_PRESSED ) {
      //When center has passed left side
      if(point.getX()-pressPoint.getX()>0 && //Will move again if dx positive 
         puzzlePieces[pressed].getCenter().getX()<=0) {
        puzzlePieces[pressed].move(point.getX()-pressPoint.getX(), 0);
        pressPoint = point; //since point is always changing
      }
      //When center has passed top side
      if(puzzlePieces[pressed].getCenter().getY()<=0 &&
         point.getY()-pressPoint.getY()>0) { //will move again if dy positive
        puzzlePieces[pressed].move(0,point.getY()-pressPoint.getY());
        pressPoint = point; //since point is always changing
      }
      //When center has passed right side
      if(point.getX()-pressPoint.getX()<0 && //Only moves if dx negative
         puzzlePieces[pressed].getCenter().getX()>=CANVAS_WIDTH) {
        puzzlePieces[pressed].move(point.getX()-pressPoint.getX(), 0);
        pressPoint = point; //since point is always changing
      }
      //When center has passed bottom side
      if(puzzlePieces[pressed].getCenter().getY()>=CANVAS_HEIGHT &&
         point.getY()-pressPoint.getY()<0) { //Only moves if dy negative
        puzzlePieces[pressed].move(0,point.getY()-pressPoint.getY());
        pressPoint = point; //since point is always changing
      }
    }
    //runs when piece pressed and puzzle complete
    if(pressed != NOT_PRESSED && verify == 0) {
      //moves all the boardpieces together
      for(int x = 0; x < PIECES; x++) {
        boardPieces[x].move(point.getX()-pressPoint.getX(), 
  		            point.getY()-pressPoint.getY());
      }
      pressPoint = point; //since point always changing
    }
  }
  
  /**
   * This method runs on mouse release
   * @param point is where the mouse was released
   */
  public void onMouseRelease(Location point) {
    if(pressed != NOT_PRESSED) { //if a piece was pressed during mouse press
      //if board piece center is close enough to puzzle piece center
      if(boardPieces[pressed].contains(puzzlePieces[pressed].getCenter())) { 
        //and the IDs match
        if(boardPieces[pressed].equals(puzzlePieces[pressed])) {
          puzzlePieces[pressed].hide(); //moves puzzle piece off canvas
	  boardPieces[pressed].show(); //show board piece
          boardPieces[pressed].showHighlight(Color.green); //show green border
	  verify--; //verify gets closer to 0
	  if(verify == 0) { //puzzle is complete
	    startingMessage.show(); //show victory message
            for(int x = 0; x < PIECES; x++) { //hides highlight of all pieces
              boardPieces[x].hideHighlight(); 
            }
	  }
        }
      }
    }
    pressed = NOT_PRESSED; //resets pressed boolean/counter
  }

  /**
   * This method runs on mouse click
   * @param point is where the mouse is clicked
   */
  public void onMouseClick(Location point) {
    if(verify == 0) { //if puzzle is complete
      for(int a = 0; a < PIECES; a++) { 
	//if any piece contains point
        if(boardPieces[a].victoryContains(point)) {
	  verify = PIECES; //resets verify since puzzle resets
          for(int x = 0; x < PIECES; x++) {
            boardPieces[x].hide(); //actually removes pieces
          }
          for(int x = 0; x < PIECES; x++) { //makes new board pieces
            boardPieces[x] = new BoardPiece(images[x], x, boardLocations[x], 
      			                    canvas); 
          }
          for(int x = PIECES-1; x >= 0; x--) { //8 downwards
            puzzlePieces[x].clear(); //removes end of PP array
            random[x] = 0; //also resets the random array
          }
          for(int x = 0; x < PIECES; x++) { //makes new puzzle pieces
            puzzlePieces[x] = new PuzzlePiece(images[x], x,
            puzzleLocations[getRandomPuzzle()], canvas);
          }
          startingMessage.hide(); //hides victory message again
	}
      }
    }
  }
  
  /**
   * This is my main method and it makes canvas
   * @param String[] args is what main method uses
   */
  public static void main(String[] args) {
    new Acme.MainFrame(new Puzzle(), args, CANVAS_WIDTH, CANVAS_HEIGHT);
  }
}

/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: November 21, 2016
 * File:  MutilpleTurtles.java
 * Sources of Help: 
 *
 * This program writes "CS11FADL JAVA 2016 UC SAN DIEGO on three different
 * lines with multiple turtle threads.
 */
import turtleClasses.*;
import java.awt.Color;
/*
 * This class defines many used variables and runs all my methods.
 */
public class MultipleTurtles extends Turtle implements Runnable{
  //Variables for char widths and heights
  //initiate to edit later
  private char ch; 
  private final static int CHAR_WIDTH = 40;
  private final static int HALF_CHAR_WIDTH = 20;
  private final static int THIRD_CHAR_WIDTH = 14;
  private final static int TWO_THIRD_CHAR_WIDTH = 27;
  private final static int CHAR_HEIGHT = 80;
  private final static int N_LENGTH = 90;
  private final static int HALF_CHAR_HEIGHT = 40;
  private final static int THIRD_CHAR_HEIGHT = 27;
  private final static int TWO_FIFTH_CHAR_HEIGHT = 34;
  private final static int A_LENGTH = 165*CHAR_HEIGHT/160;
  private final static int HALF_A_LENGTH = 165*CHAR_HEIGHT/320;
  private final static int PADDING_BETWEEN_CHARS = 40;
  private final static int PADDING_BETWEEN_LINES = 40;
  private final static int CHAR_SPACING = CHAR_WIDTH + PADDING_BETWEEN_CHARS;
  private final static int DOUBLE_CHAR_SPACING = CHAR_SPACING*2;
  private final static int LINE_SPACING = CHAR_HEIGHT + PADDING_BETWEEN_LINES;
  private final static int STAR_ANGLE = 72;
  private final static int STAR_TURN = 144;

  //variables for specific angle turns
  private final static int HALF_TURN = 45;
  private final static int TWO_THIRD_TURN = 60;
  private final static int THIRD_TURN = 30;
  private final static int LARGE_A_TURN = 76;
  private final static int SMALL_A_TURN = 28;

  //Variables for starting positions, pen style, and world size
  private final static int FIRST_STAR_X = 80;
  private final static int SECOND_STAR_X = 1000;
  private final static int START_X_1 = 240; // starting x offset for line 1
  private final static int START_X_2 = 200; // starting x offset for line 2
  private final static int START_X_3 = 80; // starting x offset for line 3
  private final static int START_Y = 40; // starting y offset
  private final static int INITIAL_START_Y = 80; 
  private final static int PEN_WIDTH = 10;
  private final static Color PEN_COLOR1 = Color.BLUE;
  private final static Color PEN_COLOR2 = Color.RED;
  private final static Color PEN_COLOR3 = Color.GREEN;
  private final static Color PEN_COLOR4 = Color.BLACK;
  private final static Color PEN_COLOR5 = Color.YELLOW;
  private final static Color PEN_COLOR6 = Color.ORANGE;
  private final static int WORLD_WIDTH = 1080;
  private final static int WORLD_HEIGHT = 480;
  
  /*
   * Delay between turtle actions (turns, moves) in milliseconds.
   * 1000 = 1 sec. so 500 = 0.5 sec.
   */
  private final static int DELAY = 500;
  private int x, y; //so I can constantly change x and y and pass it to
  //different methods
 

  /*
   * Constructor uses superclass to create turtle object with delay.
   * @param world w is the world I make
   * @param ch is the char I'm drawing
   * @param x is the x coordinate
   * @param y is the y coordinate
   * @delay is the delay
   */
  public MultipleTurtles(World w, char ch, int x, int y, int delay) {
    super(w, delay); // Invoke superclass's ctor to create this turtle
    this.ch = ch; //ch is updated each time this constructor is called
    //x and y too
    this.x = x;
    this.y = y;
    setPenWidth(PEN_WIDTH);
    new Thread(this).start(); //starts run method
  } // on World w with delay of each turtle's action.


  /*
   * Run method runs all the turtle threads
   */
  public void run() {
    switch(ch) { //break this label each letter
    //1 case for each letter/number
    case 'C': this.drawC(x,y); break;
    case 'S': this.drawS(x,y); break;
    case '1': this.draw1(x,y); break;
    case 'F': this.drawF(x,y); break;
    case 'A': this.drawA(x,y); break;
    case 'D': this.drawD(x,y); break;
    case 'L': this.drawL(x,y); break;	
    case 'J': this.drawJ(x,y); break;
    case 'V': this.drawV(x,y); break;
    case '2': this.draw2(x,y); break;
    case '0': this.draw0(x,y); break;
    case '6': this.draw6(x,y); break;
    case 'U': this.drawU(x,y); break;     
    case 'N': this.drawN(x,y); break;
    case 'I': this.drawI(x,y); break;
    case 'E': this.drawE(x,y); break;
    case 'G': this.drawG(x,y); break;
    case 'O': this.draw0(x,y); break;
    }
  }
 

  /*
   * Draws the letter "c"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawC(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
  }
 

  /*
   * Draws the letter "s"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawS(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnRight();
    forward(HALF_CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
    turnRight();
    forward(HALF_CHAR_HEIGHT );
    turnRight();
    forward(CHAR_WIDTH);
  }
  

  /*
   * Draws the number 1
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void draw1(int x, int y) {
    penUp();
    moveTo(x, y); 
    turnToFace(getXPos() + 1, getYPos()); // face right
    forward(HALF_CHAR_WIDTH); // makes drawing start in middle of "1"
    penDown();
    turn(-HALF_TURN );
    backward(THIRD_CHAR_WIDTH);
    forward(THIRD_CHAR_WIDTH);
    turn(-HALF_TURN );
    backward(CHAR_HEIGHT);
    turnRight();
    forward(HALF_CHAR_WIDTH);
    backward(CHAR_WIDTH);
  }


  /*
   * Draws the letter "f"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawF(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    backward(HALF_CHAR_HEIGHT);
    turnLeft();
    forward(TWO_THIRD_CHAR_WIDTH);
  }


  /*
   * Draws the letter "a"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawA(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    forward(HALF_CHAR_WIDTH); // makes drawing start in middle of "A"
    penDown();
    turn(-LARGE_A_TURN); 
    backward(A_LENGTH); 
    forward(A_LENGTH);
    turn(-SMALL_A_TURN);
    backward(A_LENGTH);
    forward(HALF_A_LENGTH);
    turn(-LARGE_A_TURN);
    forward(HALF_CHAR_WIDTH);
  }


  /*
   * Draws the letter "d"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawD(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(TWO_THIRD_CHAR_WIDTH);
    turn(TWO_THIRD_TURN);
    forward(THIRD_CHAR_HEIGHT);
    turn(THIRD_TURN);
    forward(TWO_FIFTH_CHAR_HEIGHT);
    turn(THIRD_TURN);
    forward(THIRD_CHAR_HEIGHT);
    turn(TWO_THIRD_TURN);
    forward(TWO_THIRD_CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
  }


  /*
   * Draws the letter "l"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawL(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    turnRight();
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
  }


  /*
   * Draws the letter "j"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawJ(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);
    backward(HALF_CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    turnRight();
    forward(HALF_CHAR_WIDTH);
    turnRight();
    forward(HALF_CHAR_WIDTH);
  }


  /*
   * Draws the letter "v"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawV(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown(); 
    turn(LARGE_A_TURN); //"V" has the same angles as "A"
    forward(A_LENGTH); //"V" has the same char length as "A"
    turn(SMALL_A_TURN);
    backward(A_LENGTH); 
  }


  /*
   * Draws the number 2
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void draw2(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH); 
    turnRight();
    forward(HALF_CHAR_HEIGHT); 
    turnRight();
    forward(CHAR_WIDTH); 
    turnLeft();
    forward(HALF_CHAR_HEIGHT); 
    turnLeft();
    forward(CHAR_WIDTH); 
  }
 

  /*
   * Draws the letter "0"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void draw0(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH); 
    turnRight();
    forward(CHAR_HEIGHT); 
    turnRight();
    forward(CHAR_WIDTH); 
    turnRight();
    forward(CHAR_HEIGHT); 
  }


  /*
   * Draws the number 6
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void draw6(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH); 
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT); 
    turnLeft();
    forward(CHAR_WIDTH); 
    turnLeft();
    forward(HALF_CHAR_HEIGHT); 
    turnLeft();
    forward(CHAR_WIDTH); 
  }


  /*
   * Draws the letter "u"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawU(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    turnRight();
    forward(CHAR_HEIGHT); 
    turnLeft();
    forward(CHAR_WIDTH); 
    turnLeft();
    forward(CHAR_HEIGHT); 
  }


  /*
   * Draws the letter "n"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawN(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    turnRight();
    forward(CHAR_HEIGHT);
    backward(CHAR_HEIGHT); 
    turn(-THIRD_TURN);
    forward(N_LENGTH);
    turn(THIRD_TURN);
    backward(CHAR_HEIGHT); 
  }


  /*
   * Draws the letter "i"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawI(int x, int y) {
    penUp();
    moveTo(x, y); 
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH); 
    backward(HALF_CHAR_WIDTH); 
    turnRight();
    forward(CHAR_HEIGHT);
    turnRight();
    forward(HALF_CHAR_WIDTH);
    backward(CHAR_WIDTH);
  }


  /*
   * Draws the letter "e"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawE(int x, int y) {
    penUp();
    moveTo(x, y); 
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH); 
    backward(CHAR_WIDTH); 
    turnRight();
    forward(HALF_CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH); 
    backward(CHAR_WIDTH); 
    turnRight();
    forward(HALF_CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH); 
    backward(CHAR_WIDTH); 
  }


  /*
   * Draws the letter "g"
   * @param x is the x coordinate to start drawing
   * @param y is the y coordinate to start drawing
   */
  private void drawG(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH); 
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT); 
    turnLeft();
    forward(CHAR_WIDTH); 
    turnLeft();
    forward(HALF_CHAR_HEIGHT);
    turnLeft();
    forward(THIRD_CHAR_WIDTH);
  }


  /*
   * main method runs all the other methods I made previously.
   * @param args is the command line argument
   */
  public static void main(String [] args) {
    int startX1 = START_X_1; // starting x offset for line 1
    int startX2 = START_X_2; // starting x offset for line 2
    int startX3 = START_X_3; // starting x offset for line 3
    int startY = START_Y;    // starting y offset
    int x, y;                //local x, y
    World w = new World(WORLD_WIDTH, WORLD_HEIGHT);
    //make a turtle for each letter/number
    new MultipleTurtles(w, 'C', x = startX1, y = INITIAL_START_Y, DELAY); 
    new MultipleTurtles(w, 'S', x += CHAR_SPACING, y, DELAY); 
    new MultipleTurtles(w, '1', x += CHAR_SPACING, y, DELAY); 
    new MultipleTurtles(w, '1', x += CHAR_SPACING, y, DELAY);
    new MultipleTurtles(w, 'F', x += CHAR_SPACING, y, DELAY);
    new MultipleTurtles(w, 'A', x += CHAR_SPACING, y, DELAY); 
    new MultipleTurtles(w, 'D', x += CHAR_SPACING, y, DELAY); 
    new MultipleTurtles(w, 'L', x += CHAR_SPACING, y, DELAY); 
    new MultipleTurtles(w, 'J', x = startX2, y += startY+CHAR_HEIGHT, DELAY);
    new MultipleTurtles(w, 'A', x += CHAR_SPACING, y, DELAY);
    new MultipleTurtles(w, 'V', x += CHAR_SPACING, y, DELAY);
    new MultipleTurtles(w, 'A', x += CHAR_SPACING, y, DELAY);
    new MultipleTurtles(w, '2', x += CHAR_SPACING+CHAR_SPACING, y, DELAY);
    new MultipleTurtles(w, '0', x += CHAR_SPACING, y, DELAY); 
    new MultipleTurtles(w, '1', x += CHAR_SPACING, y, DELAY); 
    new MultipleTurtles(w, '6', x += CHAR_SPACING, y, DELAY);
    new MultipleTurtles(w, 'U', x = startX3, y += startY+CHAR_HEIGHT, DELAY);
    new MultipleTurtles(w, 'C', x += CHAR_SPACING, y, DELAY); 
    new MultipleTurtles(w, 'S', x += CHAR_SPACING+CHAR_SPACING, y, DELAY); 
    new MultipleTurtles(w, 'A', x += CHAR_SPACING, y, DELAY); 
    new MultipleTurtles(w, 'N', x += CHAR_SPACING, y, DELAY); 
    new MultipleTurtles(w, 'D', x += CHAR_SPACING+CHAR_SPACING, y, DELAY);     
    new MultipleTurtles(w, 'I', x += CHAR_SPACING, y, DELAY);
    new MultipleTurtles(w, 'E', x += CHAR_SPACING, y, DELAY);
    new MultipleTurtles(w, 'G', x += CHAR_SPACING, y, DELAY);
    new MultipleTurtles(w, 'O', x += CHAR_SPACING, y, DELAY);
  }
} // End of public class MultipleTurtles extends Turtle

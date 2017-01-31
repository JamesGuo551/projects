import Acme.*;
import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Controller for the Snake game.
 */
public class SnakeController extends WindowController implements
    ActionListener, KeyListener {
    boolean pause = false; //pause toggle
    boolean restart = false; //restart check
    private boolean error = false; //print statements if error at end
    public static final int HALF = 2; //divide by 2
    private static final int SEG_SIZE = 50; //snake segment size
    private static final int FOOD_SIZE = 50; //food size
    private static final int MIN_DIMENSION = 200; //min x dimension
    private static final int MAX_DIMENSION = 800; //min y dimension
    private static final int MIN_DELAY = 50; //minimum delay
    private static final int MAX_DELAY = 600; //max delay
    // Padding to account for GUI components
    private static final int Y_PADDING = 50; //if canvas made wrongly
    //initialize a bunch of stuff
    private JLabel scoreLabel, highScoreLabel, gameOver, win, paused; 
    private JButton newGame;
    Snake snake;
    Location start;
    public int windowWidth, windowHeight, delay;
    double xOffset;
    public int xPositions, yPositions, maxFood, score, highScore, direction;
    //since direction is an int
    int up = 1;
    int right = 2;
    int down = 3;
    int left = 4;
    int argLength = 3; //arg argument length
    // TODO Add more instance variables here
    
    /**
     * Constructor. Parses the command line arguments,
     * and initializes the controller state
     * @param args the string arguments to be parsed
     */
    public SnakeController( String[] args ) {
      if(args.length != argLength) {
	System.out.println(PA8Strings.USAGE); //correct arg use
	System.exit(1); //exit
      }
      //set each arg to a dimension
      windowWidth = Integer.parseInt( args[0] );
      windowHeight = Integer.parseInt( args[1] );
      delay = Integer.parseInt( args[argLength-1] );
      //if window width is the wrong number
      if(windowWidth < MIN_DIMENSION || windowWidth > MAX_DIMENSION) {      
      	System.err.format(PA8Strings.OUT_OF_RANGE, "width", windowWidth, 
		          MIN_DIMENSION, MAX_DIMENSION);
	error = true;
      }
      //if window with not divisible by 50
      if(windowWidth % SEG_SIZE != 0) {
        System.err.format(PA8Strings.NOT_EVENLY_DIVISIBLE, "width", 
			  windowWidth);
        error = true;
      }
      //if window height out of bounds
      if(windowHeight < MIN_DIMENSION || windowHeight > MAX_DELAY) {
        System.err.format(PA8Strings.OUT_OF_RANGE, "height", windowHeight,
 		          MIN_DIMENSION, MAX_DELAY);
	error = true;
      }      
      //if window height not divisble by 50
      if(windowHeight % SEG_SIZE != 0) {
	System.err.format(PA8Strings.NOT_EVENLY_DIVISIBLE, "height", 
			  windowHeight); 
        error = true;
      }
      //if delay out of bounds
      if(delay < MIN_DELAY || delay > MAX_DELAY) {
        System.err.format(PA8Strings.OUT_OF_RANGE, "delay", delay,
 		          MIN_DELAY, MAX_DELAY);
	error = true;
      }
      //if an error turned up anywhere then print this last
      if(error) {
        System.out.println(PA8Strings.USAGE);
	System.exit(1);
      }
    }

    /*
     * show win message
     */
    public void showWin() {
      win.setVisible(true);
    }

    /*
     * show lose message 
     */
    public void showLose() {
      gameOver.setVisible(true);
    }

    /**
     * Window setup.
     */
    public void begin() {
      xOffset = (canvas.getWidth()-windowWidth)/HALF; //if dimensions wrong
      //update x, y positions based on what was entered
      xPositions = (int)windowWidth/SEG_SIZE;
      yPositions = (int)windowHeight/SEG_SIZE;
      maxFood = xPositions * yPositions; //board is basically a grid
      ((JDrawingCanvas)canvas).setBackground( Color.BLACK );
      //initiate labels and buttoms and stuff
      highScoreLabel = new JLabel(String.format(PA8Strings.HIGH_SCORE, 
			          highScore));
      scoreLabel = new JLabel(String.format(PA8Strings.SCORE, score));
      gameOver = new JLabel(PA8Strings.GAME_OVER);
      win = new JLabel(PA8Strings.WIN);
      paused = new JLabel(PA8Strings.PAUSED);
      newGame = new JButton(PA8Strings.NEW_GAME);
      gameOver.setForeground(Color.red);
      paused.setForeground(Color.red);
      win.setForeground(Color.blue);
      //hide labels
      gameOver.setVisible(false);
      paused.setVisible(false);
      win.setVisible(false);
      //make panels
      newGame.addActionListener(this);
      JPanel northPanel = new JPanel();
      JPanel southPanel = new JPanel();
      southPanel.add(newGame);
      southPanel.add(gameOver);
      southPanel.add(win);
      southPanel.add(paused);
      northPanel.setLayout(new GridLayout(0, HALF));
      northPanel.add(scoreLabel);
      northPanel.add(highScoreLabel);
      Container contentPane = getContentPane();
      contentPane.add(northPanel, BorderLayout.NORTH);
      contentPane.add(southPanel, BorderLayout.SOUTH);
      contentPane.validate();

      //snake x and y positions
      double snakeX = ((windowWidth/HALF)/SEG_SIZE) * SEG_SIZE;
      double snakeY =(windowHeight/HALF/SEG_SIZE) * SEG_SIZE;
      start = new Location(snakeX, snakeY);
      snake = new Snake(canvas, start, delay, this, maxFood);
      this.addKeyListener(this);
      requestFocusInWindow();
    }

    /**
     * Program runner.
     * @param args command line string arguments
     */
    public static void main( String[] args ) {
        SnakeController game = new SnakeController( args );
        new Acme.MainFrame( game, game.windowWidth,
                            game.windowHeight + Y_PADDING );
    }

    /**
     * Action listener method to check for button presses.
     * Handles newgame button presses
     * @param evt the event that triggers callback
     */
    public void actionPerformed( ActionEvent evt ) {
      score = 0; //reset score
      scoreLabel.setText(String.format(PA8Strings.SCORE, score)); //and label
      //hide messages
      gameOver.setVisible(false);
      paused.setVisible(false);
      win.setVisible(false);
      restart = true; //game has been restarted
      canvas.clear(); //clear canvas
      direction = 0; //direction reset and make new snake
      snake = new Snake(canvas, start, delay, this, maxFood);
      this.addKeyListener(this);
      requestFocusInWindow();
    }

    /*
     * every time food is eaten, change score
     */
    public void changeScore() {
      //update score label and maybe high score label
      scoreLabel.setText(String.format(PA8Strings.SCORE, score));
      if(score > highScore) {
	highScore = score;
	highScoreLabel.setText(String.format(PA8Strings.HIGH_SCORE, score));
      }
    }

    /**
     * Key Press handler. Manages snake movement
     * @param e the keyboard event that triggers callback
     */
    public void keyPressed( KeyEvent e ) {
      int keyCode = e.getKeyCode();
      //only does stuff if not paused
      if(pause == false) {
	//each of these changes direction
        if(keyCode == KeyEvent.VK_UP) {
          if(direction != down) {
  	    direction = up;
	  }
        }
        if(keyCode == KeyEvent.VK_DOWN) {
          if(direction != up) {
  	    direction = down;
	  }
        }
        if(keyCode == KeyEvent.VK_RIGHT) {
          if(direction != left) {
  	    direction = right;
 	  }
        }
        if(keyCode == KeyEvent.VK_LEFT) {
          if(direction != right) {
  	    direction = left;
	  }
        }
      }
      //space button pauses
      if(keyCode == KeyEvent.VK_SPACE) {
	if(pause) {
	  pause = false;
	  paused.setVisible(false);
	} else {
	  pause = true;
	  paused.setVisible(true);
	}
      }
    }

    /* method stubs for the KeyListener interface */
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

} // end of class SnakeController

/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: November 9, 2016
 * File: Spin100Controller.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program makes a wheel of fortune game.
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 
public class Spin100Controller extends WindowController {
  int score = 0;
  int totalOneScore = 0;
  int totalTwoScore = 0;
  boolean switched = false;
  private static final int[] SCORES = { 50, 85, 30, 65, 10, 45, 70, 25, 90, 5,
  100, 15, 80, 35, 60, 20, 40, 75, 55, 95 };
  private static final int IMAGE_WIDTH = 185;
  private static final int IMAGE_HEIGHT = 99;;
  private static final double LEFT_ARROW_X = 218;
  private static final double LEFT_ARROW_Y = 186;
  private static final double RIGHT_ARROW_X = 638;
  private static final double RIGHT_ARROW_Y = 186;
  private static final double START_ANGLE = -15;
  private static final double ARC_ANGLE = 30;
  private static final int CANVAS_WIDTH = 1000; //canvas width
  private static final int CANVAS_HEIGHT = 660; //canvas height
  private static final double ARC_SIZE = 150;
  private Location leftWheelLoc = new Location(118, 10);
  private Location rightWheelLoc = new Location(538, 10);
  private Location tieLocation = new Location(410, 5);
  private Location p1Winner = new Location(20, 5);
  private Location p2Winner = new Location(740, 5);
  private Location scoreLoc = new Location(355, 130);
  private Text tie, p1Wins, p2Wins, totalScore;
  private JButton spinOne, finishOne, restart, spinTwo, finishTwo, Switch;
  private Spin100Wheel leftWheel, rightWheel;
  private JLabel firstScore, secondScore;
  private FilledArc leftArrow, rightArrow;
  Image[] images = new Image[20];
  /**
   * Code runs as soon as canvas is opened.
   */
  public void begin() { //Makes axes
    JPanel mainPanel = new JPanel(); //button panel
    JPanel northPanel = new JPanel(); //so label goes above buttons
    JPanel southMainPanel = new JPanel(); //label panel
    JPanel southTopPanel = new JPanel();
    JPanel southBottomPanel = new JPanel();
    JLabel spinLabel = new JLabel("Spin100"); //display text
    firstScore = new JLabel("Player 1's score: " + score);
    secondScore = new JLabel("Player 2's score: " + score);
    northPanel.add(spinLabel); //add label to label panel
    southTopPanel.add(firstScore);
    southBottomPanel.add(secondScore);
    southMainPanel.setLayout(new BorderLayout());
    southMainPanel.add(southTopPanel, BorderLayout.NORTH);
    southMainPanel.add(southBottomPanel, BorderLayout.SOUTH);
    mainPanel.setLayout(new BorderLayout()); //to change main panel layout
    mainPanel.add(northPanel, BorderLayout.NORTH); //label panel on top
    mainPanel.add(southMainPanel, BorderLayout.SOUTH); //button panel on botton
    Container contentPane = getContentPane(); //so i can add panels to canvas
    contentPane.add(mainPanel, BorderLayout.NORTH); //main panel on top
    contentPane.validate(); //makes it work

    spinOne = new JButton("Click to Spin P1");
    finishOne = new JButton("Finish Player 1");
    restart = new JButton("Restart");
    Switch = new JButton("Switch");
    spinTwo = new JButton("Click to Spin P2");
    finishTwo = new JButton("Finish Player 2");
    spinOne.addActionListener(new SpinOneButtonEventHandler());
    finishOne.addActionListener(new FinishOneButtonEventHandler());
    restart.addActionListener(new RestartButtonEventHandler());
    spinTwo.addActionListener(new SpinTwoButtonEventHandler());
    finishTwo.addActionListener(new FinishTwoButtonEventHandler());
    Switch.addActionListener(new SwitchButtonEventHandler());
    JPanel southPanel = new JPanel();
    southPanel.add(spinOne);
    southPanel.add(finishOne);
    southPanel.add(restart);
    southPanel.add(Switch);
    southPanel.add(spinTwo);
    southPanel.add(finishTwo);
    finishTwo.setEnabled(false);
    finishOne.setEnabled(false);
    spinTwo.setEnabled(false);
    contentPane.add(southPanel, BorderLayout.SOUTH);
    
    tie = new Text("Tie", tieLocation, canvas);
    totalScore = new Text("Overall Wins P1: " + totalOneScore + ", P2: " +
		 totalTwoScore, scoreLoc, canvas); 
    p1Wins = new Text("P1 Winner", p1Winner, canvas);
    p2Wins = new Text("P2 Winner", p2Winner, canvas);
    tie.setBold(true);
    p1Wins.setBold(true);
    p2Wins.setBold(true);
    tie.setFontSize(16);
    p1Wins.setFontSize(16);
    p2Wins.setFontSize(16);
    totalScore.setColor(Color.RED);
    tie.setColor(Color.BLUE);
    p1Wins.setColor(Color.GREEN);
    p2Wins.setColor(Color.GREEN);
    totalScore.hide();
    tie.hide();
    p1Wins.hide();
    p2Wins.hide();

    for(int x = 0; x < 20; x++) {
      images[x] = getImage("Big_Wheel-" + SCORES[x] + ".png"); //gets images
    }
    leftWheel = new Spin100Wheel(images, leftWheelLoc, spinOne, finishOne, 
		    restart, firstScore, 1, canvas);
    rightWheel = new Spin100Wheel(images, rightWheelLoc, spinTwo, finishTwo, 
		     restart, secondScore, 2, canvas);
    leftArrow = new FilledArc(LEFT_ARROW_X, LEFT_ARROW_Y, ARC_SIZE, 
		          ARC_SIZE, START_ANGLE, ARC_ANGLE, canvas);
    rightArrow = new FilledArc(RIGHT_ARROW_X, RIGHT_ARROW_Y, ARC_SIZE, 
		          ARC_SIZE, START_ANGLE, ARC_ANGLE, canvas);
    leftArrow.setColor(Color.RED);
    rightArrow.setColor(Color.RED);
  }
  
  public void scoreCap(int player) {
    if(player == 1) {
      if(switched == false) {	    
        spinTwo.setEnabled(true);
      } else if(switched) {
	if(leftWheel.score > 100 && rightWheel.score > 100) {
	  tie.show();
	  totalScore.show();
        } else if (leftWheel.score == rightWheel.score) {
	  tie.show();
	  totalScore.show();
        }else if(Math.abs(leftWheel.score-100)<Math.abs(rightWheel.score-100)) {
	  p1Wins.show();
	  totalOneScore++;
	  totalScore.setText("Overall Wins P1: " + totalOneScore + ", P2: " +
		 totalTwoScore);
	  totalScore.show();
        } else {
	  p2Wins.show();
	  totalTwoScore++;
	  totalScore.setText("Overall Wins P1: " + totalOneScore + ", P2: " +
		 totalTwoScore);
	  totalScore.show();
        }
      }
    } else {
      if(switched == false) {
        spinTwo.setEnabled(false);
        finishTwo.setEnabled(false);
        if(leftWheel.score > 100 && rightWheel.score > 100) {
	  tie.show();
	  totalScore.show();
        } else if (leftWheel.score == rightWheel.score) {
	  tie.show();
	  totalScore.show();
        }else if(Math.abs(leftWheel.score-100)<Math.abs(rightWheel.score-100)) {
	  p1Wins.show();
	  totalOneScore++;
	  totalScore.setText("Overall Wins P1: " + totalOneScore + ", P2: " +
		 totalTwoScore);
	  totalScore.show();
        } else {
	  p2Wins.show();
	  totalTwoScore++;
	  totalScore.setText("Overall Wins P1: " + totalOneScore + ", P2: " +
		 totalTwoScore);
	  totalScore.show();
        }
      } else if(switched) {
	spinTwo.setEnabled(false);
        finishTwo.setEnabled(false);
	spinOne.setEnabled(true);
      }
    }
  }

  private class SwitchButtonEventHandler implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if(switched == false) {
        switched = true;
        spinOne.setEnabled(false);
	finishOne.setEnabled(false);
	spinTwo.setEnabled(true);
      } else {
	switched = false;
	spinOne.setEnabled(true);
	spinTwo.setEnabled(false);
      }
    }
  }
  private class SpinOneButtonEventHandler implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      Switch.setEnabled(false);
      finishOne.setEnabled(true);
      leftWheel.setTicks();
      if(leftWheel.spinning == true) {
      	spinOne.setEnabled(false);
        finishOne.setEnabled(false);
	restart.setEnabled(false);
      } 
    }
  }
  private class FinishOneButtonEventHandler implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      spinOne.setEnabled(false);
      finishOne.setEnabled(false);
      if(switched == false) {
        spinTwo.setEnabled(true);
      }
      scoreCap(1);
    }
  }
  private class RestartButtonEventHandler implements ActionListener {
    public void actionPerformed(ActionEvent evt) { 
      Switch.setEnabled(true);
      p1Wins.hide();
      p2Wins.hide();
      tie.hide();
      totalScore.hide();
      firstScore.setText("Player 1's score: " + score);
      secondScore.setText("Player 2's score: " + score);	    
      leftWheel = null;
      rightWheel = null;
      leftWheel = new Spin100Wheel(images, leftWheelLoc, spinOne, finishOne,
		      restart, firstScore, 1, canvas);
      rightWheel = new Spin100Wheel(images, rightWheelLoc, spinTwo, finishTwo,
		       restart, secondScore, 2, canvas);
      leftArrow = null;
      rightArrow = null;
      leftArrow = new FilledArc(LEFT_ARROW_X, LEFT_ARROW_Y, ARC_SIZE, 
		          ARC_SIZE, START_ANGLE, ARC_ANGLE, canvas);
      rightArrow = new FilledArc(RIGHT_ARROW_X, RIGHT_ARROW_Y, ARC_SIZE, 
		          ARC_SIZE, START_ANGLE, ARC_ANGLE, canvas);
      leftArrow.setColor(Color.RED);
      rightArrow.setColor(Color.RED);
      if(switched) {
        spinTwo.setEnabled(true);
	spinOne.setEnabled(false);
	finishTwo.setEnabled(false);
	finishOne.setEnabled(false);
      } else {
	spinOne.setEnabled(true);
	spinTwo.setEnabled(false);
	finishOne.setEnabled(false);
	finishTwo.setEnabled(false);
      }
    }
  }
  private class SpinTwoButtonEventHandler implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      Switch.setEnabled(false);
      finishTwo.setEnabled(true);
      rightWheel.setTicks();
      if(rightWheel.spinning == true) {
      	spinTwo.setEnabled(false);
        finishTwo.setEnabled(false);
	restart.setEnabled(false);
      } 
    }
  }
  private class FinishTwoButtonEventHandler implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      finishTwo.setEnabled(false);
      spinTwo.setEnabled(false);
      scoreCap(2);
    }
  }


  public static void main(String[] args) {
    new Acme.MainFrame(new Spin100Controller(), args, CANVAS_WIDTH,
    CANVAS_HEIGHT);
  }
}

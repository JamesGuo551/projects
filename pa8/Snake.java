/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: November 17, 2016
 * File: Snake.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program defines the behavior of a snake.
 */

import objectdraw.*;
import java.awt.Color;
import java.util.*;
import java.awt.event.*;

/**
 * Represents the Snake object on the canvas.
 */
public class Snake extends ActiveObject {
    int delay, size, maxFood, xRandom, yRandom; //initialize some ints
    int boundary = 50; //boundary size
    int move = 50; //movement amount
    int eat = 10; //increase score
    ArrayList<FilledRect> snake = new ArrayList <>(); //array of filledrect
    public static final int HALF = 2; //divide
    public static final int SEG_SIZE = 50; //each segment size
    SnakeController controller; //for reference
    DrawingCanvas canvas;
    int even = 2; //modulus use
    int up = 1; //direction is an int
    int right = 2; 
    int down = 3;
    int left = 4;
    int crash = 3; //snake must be next to 3rd body or higher to crash
    int crashThreshold = 4; //snake must be at least 5 length to crash
    FilledOval food; //initialize these things
    Location oddLoc, evenLoc, foodLoc;
    boolean alive = true; //for determining snake death
    boolean wall = false;
    boolean noFood = true;
    Random generator = new Random(); //random num generator
    // TODO Add your instance variables here

    /*
     * Makes a snake 
     * @param canvas is the canvas to make on
     * @param start is where the snake starts
     * @param delay is how long between each movement
     * @param controller is for reference
     * @param maxfood is for win condition
     */
    public Snake( DrawingCanvas canvas, Location start, int delay,
                  SnakeController controller, int maxFood) {
      FilledRect head = new FilledRect(start, SEG_SIZE, SEG_SIZE, canvas);
      head.setColor(Color.LIGHT_GRAY); //make snake head
      this.delay = delay; //reference
      this.controller = controller;
      this.canvas = canvas;
      this.maxFood = maxFood;
      size = 1; //increment snake size
      snake.add(head); //add to array
      start(); //start active object
    }

    /*
     * runs like an active object
     */
    public void run() {
      while(alive) { //snake is alive by default
	if(size == maxFood) { //win check
	  controller.showWin();
	  alive = false;
	  return;
	}
	if(noFood) { //if no food
	  placeFood(); //make food object
	}
	grow(); //growth check
	if(snake.get(0).getLocation().getX() == 0 || 
	   snake.get(0).getLocation().getY() == 0 || 
	   snake.get(0).getLocation().getX() == controller.windowWidth
            - boundary || snake.get(0).getLocation().getY() == 
	    controller.windowHeight - boundary) { //wall check
          wall = true; //if next to a wall
	} else {
	  wall = false; //if not next to a wall
	}
	pause(delay);	      
	if(controller.direction == up) {
	  if(snake.get(0).getLocation().getY() == 0 && wall == true && 
	     !controller.pause) { //if moving up into a wall and not paused
	    alive = false; //dead
	    controller.showLose();
	    return;
	  }
	  move(0, -move); //else move head
	  for(int x = crash; x < snake.size(); x++) {
	    if(snake.get(0).getLocation().equals(snake.get(x).getLocation()) && 
	       size > crashThreshold) { //check if snake next to itself
	      alive = false; //snake crashes after moving up
	      controller.showLose();
	      move(0, move); //move head back and end game
	      return;
	    }
	  }
	  bodyMove(); //move body
	} 
	//same as logic for up loop but for when snake moving left
	if(controller.direction == left) { 
	  System.out.print(left);	
	  if(snake.get(0).getLocation().getX() == 0 && wall == true && 
	     !controller.pause) {
	    alive = false;
	    controller.showLose();
	    return;
	  }
	  move(-move, 0);
	  for(int x = crash; x < snake.size(); x++) {
	    if(snake.get(0).getLocation().equals(snake.get(x).getLocation()) && 
	       size > crashThreshold) {
	      alive = false;
	      controller.showLose();
	      move(move, 0);
	      return;
	    }
	  }
	  bodyMove();
	}
	//same as logic for up loop but for when snake moving right
	if(controller.direction == right) {
	  if(snake.get(0).getLocation().getX() == controller.windowWidth
             - boundary && wall == true && !controller.pause) {
	    alive = false;
	    controller.showLose();
	    return;
	  }
	  move(move, 0);
	  for(int x = crash; x < snake.size(); x++) {
	    if(snake.get(0).getLocation().equals(snake.get(x).getLocation()) && 
	       size > crashThreshold) {
	      alive = false;
	      controller.showLose();
	      move(-move, 0);
	      return;
	    }
	  }
	  bodyMove();
	}
	//same as logic for up loop but for when snake moving down
	if(controller.direction == down) {
	  if(snake.get(0).getLocation().getY() == controller.windowHeight
             - boundary && wall == true && !controller.pause) {
	    alive = false;
	    controller.showLose();
	    return;
	  }
	  move(0, move);
	  for(int x = crash; x < snake.size(); x++) {
	    if(snake.get(0).getLocation().equals(snake.get(x).getLocation()) && 
	       size > crashThreshold) {
	      alive = false;
	      controller.showLose();
	      move(0, -move);
	      return;
	    }
	  }
	  bodyMove();
        }
      }
    }
    
    /* 
     * this is the method that places food
     */
    public void placeFood() {
      //generate an x and y index
      xRandom = generator.nextInt(controller.xPositions);
      yRandom = generator.nextInt(controller.yPositions);
      foodLoc = new Location ((double)xRandom * SEG_SIZE, (double)yRandom * 
      SEG_SIZE); //so itll be placed at a multiple of 50 for each dimension
      for(int x = 0; x < snake.size(); x++) {	  
	if(snake.get(x).getLocation().equals(foodLoc)) { //if food overlaps
	  placeFood(); //restart the method and exit this one
	  return;
	}
      }    
      food = new FilledOval(foodLoc, SEG_SIZE, SEG_SIZE, canvas);
      food.setColor(Color.ORANGE);
      noFood = false; //now there is food again
    }
    
    /*
     * move head method
     * @param dx is x amount to move
     * @param dy is y amount to move
     */
    private synchronized void move(double dx, double dy) {
      if(controller.pause) {
        return;
      }	//only moves when unpaused    
      evenLoc = new Location(snake.get(0).getLocation()); //store location
      snake.get(0).move(dx, dy); //move head
    }

    /*
     * move body method
     */
    private void bodyMove() {
      if(controller.pause) {
        return;
      } //only moves when unpaused
      if(alive) { 
        for(int x = 1; x < snake.size(); x++) {
	  if(x % even == 1) { //odd body parts store odd location
	    oddLoc = snake.get(x).getLocation();
	    snake.get(x).moveTo(evenLoc);
  	  } else { //even body parts store even location
      	    evenLoc = snake.get(x).getLocation();
	    snake.get(x).moveTo(oddLoc);
	  }	  
        }
      }
    }

    /*
     * method for snake to grow in size
     */
    public void grow() {
      if(snake.get(0).getLocation().equals(food.getLocation())) {
	food.removeFromCanvas(); //remove food if snake head goes over food
      	FilledRect body = new FilledRect(snake.get(size-1).getLocation(), 
	SEG_SIZE, SEG_SIZE, canvas); //make a body at the end
        body.setColor(Color.WHITE); 
        size++; //increase size
	controller.score += eat; //increase score
	controller.changeScore(); //update score label
        snake.add(body); //add body to array
        noFood = true; //so food can be made again
      }      
    }
    // TODO Add more functionality + helper functions

} // end of class Snake

/*
 * Name: Yipeng Guo
 * Login: cs11fadl <<< --- Use your cs11f course-specific account name
 * Date: October 1, 2016
 * File: PosNeg.java
 * Sources of Help: 
 *
 * This program scans input to find the smallest positive integer and
   largest negative integer in the input.
 */
// Construct a Scanner that produces values scanned from standard input
import java.util.Scanner;

/**
 * Purpose: This class runs my main method.
 */
public class EC_PosNeg{
 /**
  * Name: main method
  * Purpose: Defines the variables and runs the code to make the program work.
  * no @param
  * no @return
  */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a series of integers; EOF to quit");
    int smallestPos = 0;
    int largestNeg = 0;
    int secondPos = 0;
    int secondNeg = 0;
    int number = 0;
    int posCounter = 0; //Used to change states
    int negCounter = 0; //Used to change states
    int zeroCounter = 0; //count zeros

    while (input.hasNext()) { // while there is more input                      
      number = input.nextInt(); // read next integer
      if(number == 0) {
        zeroCounter++;
      } //counts zeros

      //So the first positive number entered will replace smallestPos
      if(number > 0 && posCounter == 0) {
        smallestPos = number;
        posCounter++; //This code should only run once
      }
 
      //Second positive number will replace one of the two variables
      if(number > 0 && posCounter == 1) {
        if(number < smallestPos) {
          secondPos = smallestPos;
          smallestPos = number;
          posCounter++; //runs one time regardless of which it replaces
        } else if(number != smallestPos) {
          secondPos = number;
          posCounter++;
        }
      }

      //Afterwards one of the two positive numbers can be replaced
      if(number > 0 && posCounter == 2) { //runs last
        if(number < smallestPos) {
          secondPos = smallestPos; //So numbers remain distinct
          smallestPos = number;
        } else if (number < secondPos && number != smallestPos) {
          secondPos = number;
        }
      }  

      //So the first negative number entered will replace neg
      if(number < 0 && negCounter == 0) {
        largestNeg = number;
        negCounter++; //This code should only run once
      }

      //Second negative number replaces one of the two variables
      if(number < 0 && negCounter == 1) {
        if(number > largestNeg) {
          secondNeg = largestNeg;
          largestNeg = number;
          negCounter++; //Runs once regardless of which is replaced
        } else if(number != largestNeg) {
          secondNeg = number;
          negCounter++;
        }
      }

      //Afterwards one of the two largest negative numbers will change
      if(number < 0 && negCounter == 2) { //runs last
        if(number > largestNeg) {
          secondNeg = largestNeg; //So numbers stay distinct
          largestNeg = number;
        } else if (number > secondNeg && number != largestNeg) {
          secondNeg = number;
        }
      }
    }

    //Only prints if a positive number was entered
    if(smallestPos != 0) {
      System.out.println("Smallest distinct positive number entered was " + 
      smallestPos);
    }
    //Only prints if there is a second positive number
    if(secondPos != 0) {
      System.out.println(
      "Second smallest distinct positive number entered was " + secondPos);
    }
    
    //Prints number of zeros entered
    if(zeroCounter == 0) { 
      System.out.println("No zeros were entered");
    } else if(zeroCounter > 1) {
      System.out.println("There were " + zeroCounter + " zeros entered");
    } else if(zeroCounter == 1) {
      System.out.println("There was 1 zero entered");
    }

    //Only prints if a negative number was entered
    if(largestNeg != 0) {
      System.out.println("Largest distinct negative number entered was " + 
      largestNeg);
    }
    //Only prints if there is a second largest distinct negative number
    if(secondNeg != 0) {
      System.out.println(
      "Second largest distinct negative number entered was " + secondNeg);
    }
    //If no negative or positive numbers were entered
    if(smallestPos == 0 && largestNeg == 0) {
      System.out.println("No positive or negative numbers entered");
    }
  }
}


/*
 * Name: Yipeng Guo
 * Login: cs11fadl <<< --- Use your cs11f course-specific account name
 * Date: October 4, 2016
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
public class PosNeg {
 /**
  * Name: main method
  * Purpose: Defines the variables and runs the code to make the program work.
  * no @param
  * no @return
  */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a series of integers; EOF to quit");
    int pos = 0;
    int neg = 0;
    int number = 0;
    int poscounter = 0; //Used to change states
    int negcounter = 0; //Used to change states

    while (input.hasNext()) { // while there is more input                      
      number = input.nextInt(); // read next integer
      //So the first positive number entered will replace pos
      if(number > 0 && poscounter == 0) {
        pos = number;
	//This code should only run once
        poscounter++;
      }
      //Afterwards the smallest positive integer will replace pos
      if(number > 0 && poscounter == 1) {
        if(number < pos) {
          pos = number;
        }
      }  
      //So the first negative number entered will replace neg
      if(number < 0 && negcounter == 0) {
        neg = number;
	//This code should only run once
        negcounter++;
      }
      //Afterwards the largest negative integer will replace neg
      if(number < 0 && negcounter == 1) {
        if(number > neg) {
          neg = number;
        }
      }
    }
    //Only prints if a positive number was entered
    if(pos != 0) {
      System.out.println("Smallest positive number entered " + pos);
    }
    //Only prints if a negative number was entered
    if(neg != 0) {
      System.out.println("Largest negative number entered was" + neg);
    }
    //If no negative or positive numbers were entered
    if(pos == 0 && neg == 0) {
      System.out.println("No positive or negative numbers entered");
    }
  }
}


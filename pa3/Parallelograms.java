/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: October 13, 2016
 * File: Parallelograms.java
 * Sources of Help: 
 *
 * This program scans input to create parallelograms of the inputted size.
 */
// Construct a Scanner that produces values scanned from standard input
import java.util.Scanner;

/**
 * Purpose: This class runs my main method.
 */
public class Parallelograms {
 /**
  * Name: main method
  * Purpose: Defines the variables and runs the code to make the program work.
  * no @param
  * no @return
  */
  public static void main(String[] args) {
    int originalSize = 0; // size of parallelograms
    Scanner input = new Scanner(System.in);     
    System.out.print("Enter the size of the parallelograms to display: ");              
    while (input.hasNext()) { 
      originalSize = input.nextInt(); // reads input
      //original size is the number of stars in the largest row
      if(originalSize >= 2 && originalSize <= 20) { //only runs from 2-20
        for(int row=originalSize; row>0; row--) { 
        //row gets smaller as you move down
          System.out.println("");
          for(int space=originalSize-row+1; space>0; space--) {
          //spacing on left gets bigger as row is smaller
            System.out.print(" ");
          }
          for(int size=row; size>0; size--) { //less stars as row is smaller
            System.out.print("*"); //prints out stars
          }
          System.out.print(" "); //one space in between triangles
          for(int diff=originalSize-row+1; diff>0; diff--) {
          //increases as row gets smaller
          //the second triangle gets bigger as the first gets smaller
            System.out.print("*");
          }
          System.out.print(" ");
          for(int space=row-1; space>0; space--) {
          //less spaces as row gets smaller
            System.out.print("  "); //prints two spaces for middle gap
          }
          for(int diff=originalSize-row+1; diff>0; diff--) {
          //should have as many stars in each row as second triangle
            System.out.print("*");
          }
          System.out.print(" ");
          for(int size=row; size>0; size--) {
          //flipped version of the first triangle
            System.out.print("*");
          }
        } 
        return;
      } else { //in case the number entered was invalid
        System.out.println(
        "Parallelogram size must be >= 2 and <= 20; Try again.");
        System.out.println("");
        System.out.print("Enter the size of the parallelograms to display: ");
      }
    }
  }
}
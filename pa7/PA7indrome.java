/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: November 9, 2016
 * File: PA7indrome.java
 * Sources of Help: Object draw website with all the methods
 *
 * This program determines if a list of strings is a palindrome.
 */

import java.util.*;
import java.io.*;
import java.util.Arrays;

/*
 * This class defines how the palindrome program will work.
 */
public class PA7indrome {

  public static final int EXPECTED_ARGS = 1;
  /**
   * Reads from a file specified in the command line into an array, and then
   * checks if the array is palindromic.
   * @param args command line arguments 
   */
  public static void main( String[] args) {
    // DO NOT MODIFY THIS METHOD
    // We only want one filename as an arg
    if (args.length != EXPECTED_ARGS) {
      System.out.println("Expected a file name as the only argument.");
      System.exit(1);
    }

    // Grab the filename from first arg spot
    String filename = args[0];
    ArrayList<String> words = new ArrayList<String>();

    // Open the file, and then read from it into the arrayList
    try {
      FileReader in = new FileReader(filename);
      Scanner scan = new Scanner(in);
      while (scan.hasNext()) {
        String s = scan.nextLine();
        if ( !s.equals("")) {
           words.add(s);
        }
      }
      scan.close();
      in.close();
    }
    catch (IOException e) {
      System.out.println("Error with File IO.");
      System.exit(1);
    }
    if (words.size() == 0) {
      System.out.println("The file is empty of characters.");
      System.exit(1);
    }

    // Convert the arrayList to an array of Strings
    String[] arr = words.toArray(new String[] {""});

    // Print out the array of strings retrieved from the file
    System.out.println( "Input array of strings: " );
    for( int i = 0; i < arr.length; ++i ) {
      System.out.print( arr[i] + ((i != arr.length - 1)? ", " : "\n\n") );
    }

    // Make the call to the palindrome function
    boolean isPalindrome = isPalindrome(arr, 0, arr.length - 1);
    if (isPalindrome) {
      System.out.println("The file is a palindrome.\n");
    }
    else {
      System.out.println("The file is not a palindrome.\n");
    }
  }


  /**
   * Boolean Checker
   * @param words is an array of strings.
   * @param lo is the lowest index in the array of strings.
   * @param hi is the highest index in the array of strings.
   * @return returs true or false depending on if words is a palindrome.
   */
  public static boolean isPalindrome(String[] words, int lo, int hi) {
    char[] temp = new char[words[lo].length()];
    //temp is the word in the lowest index
    char[] temp1 = new char[words[hi].length()];
    //temp1 is the word in the highest index
    for(int x = 0; x < words[lo].length(); x++) {
      temp[x] = Character.toLowerCase(words[lo].charAt(x));
    } //so the comparison won't be case sensitive
    for(int x = 0; x < words[hi].length(); x++) {
      temp1[x] = Character.toLowerCase(words[hi].charAt(x));
    } //so the comparison won't be case sensitive
    if(lo == hi || lo == hi-1) { //base case
      //if we are at the middle of the array and all the ends have been checked
      if(Arrays.equals(temp, reverse(temp1))) {
	return true; //if middle is palindrome, return true
      }
    } else {
      //if the end words are palindrome
      if(Arrays.equals(temp, reverse(temp1))) {	   
        return isPalindrome(words, lo+1, hi-1);
	//recurse towards the middle
      } else {
        return false;
	//if not then false and exit loop
      }
    }
    return false; //because the method must have a return statement
  }


  /**
   * Reverses a character array
   * @param toRev is the character array to reverse
   * @return returns a reversed char array
   */
  public static char[] reverse(char[] toRev) {
    if(toRev.length == 1) { //base case
      //if at middle of array then return it
      return toRev;
    } else {
      //swap the first and last letter
      char temp = toRev[0]; 
      toRev[0] = toRev[toRev.length-1];
      toRev[toRev.length-1] = temp;
      //create middle array
      char[] middleArray = new char[toRev.length-2];
      //middle array is toRev minus the two endpoints
      System.arraycopy(toRev, 1, middleArray, 0, toRev.length-2);
      if(middleArray.length > 0) {
	//doesnt run if middle array is negative length or 0
        reverse(middleArray); //recurse towards middle
	for(int x=0; x<middleArray.length; x++) {
          toRev[x+1] = middleArray[x]; //place middle array back into toRev
	}
      }
      return toRev; //return reversed array
    }
  }
}

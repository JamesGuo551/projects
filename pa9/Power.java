/*
 * Name: Yipeng Guo
 * Login: cs11fadl 
 * Date: November 21, 2016
 * File:  Power.java
 * Sources of Help: 
 *
 * This program takes command line arguments and calculates a number raised to
 * a power.
 */

/**
 * This class defines many used variables and runs all my methods.
 */
public class Power {
  //Used in main method 
  private static final int EXPECTED_ARGS = 2;
  private static final int BASE_INDEX = 0;
  private static final int EXP_INDEX = 1;
  //even used for modulus, divide makes something half
  private static final int EVEN = 2;
  private static final int DIVIDE = 2;
  private static int power1MultCnt;	// # of multiplications for power1()
  private static int power2MultCnt;	// # of multiplications for power2()


  /**
   * Power1 raises base to exponent power
   * @param x is the base
   * @param n is the exponent
   */
  public static double power1( double x, int n ) {
    //recursion
    if(n == 0) {
      return 1;
    } else {
      power1MultCnt++;
      return x * power1(x, n-1);
    } 
  }


  /**
   * Power2 does the same as power1 but in fewer steps.
   * @param x is the base
   * @param n is the exponent
   */
  public static double power2( double x, int n ) {
    //recursion
    if(n == 0) {
      return 1;
    } else if(n % EVEN == 0) {
      double result = power2(x, n/DIVIDE);
      power2MultCnt++;
      return result * result;
    } else {
      power2MultCnt++;	    
      return x * power2(x, n -1);
    }
  }


  /**
   * Case study of the efficiency of two different recursive algorithms used to
   * calculate exponents.
   * @param args Command line arguments (base and exponent used in calculation)
   */
  public static void main( String[] args ) {
    // DO NOT EDIT THIS METHOD (you may edit the style at your own risk)

    // Make sure the correct number of arguments were passed in
    if ( args.length != EXPECTED_ARGS ) {
      System.out.println("Usage: java Power BASE EXPONENT");
      System.exit( 1 );
    }

    // Do not worry about catching bad input
    double base = Double.parseDouble( args[BASE_INDEX] );
    int exp = Integer.parseInt( args[EXP_INDEX] );

    power1MultCnt = 0;
    power2MultCnt = 0;

    // Calculate base^exponent using method 1 and report stats
    double power1Result = power1( base, exp );
    System.out.printf( "power1(%f, %d) = %.6e", base, exp, power1Result );
    System.out.printf( "  # of multiplies = %d\n", power1MultCnt );

    // Calculate base^exponent using method 2 and report stats
    double power2Result = power2( base, exp );
    System.out.printf( "power2(%f, %d) = %.6e", base, exp, power2Result );
    System.out.printf( "  # of multiplies = %d\n", power2MultCnt );
    System.out.println();

  } // end main()

} // end class Power

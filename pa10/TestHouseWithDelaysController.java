/**
 * Helper program to test various Shapes per CSE 11 - Inheritance
 * homework.
 *
 * Basic WindowController (with canvas) to support drawing from the
 * ActiveObject TestHouseWithDelays object.
 *
 * Application invoked from command line
 */

import objectdraw.*;

public class TestHouseWithDelaysController extends WindowController {

  public static void main(String [] args) {
    new Acme.MainFrame(new TestHouseWithDelaysController(), 700, 550 );
  }

  public void begin() {
    new TestHouseWithDelays( canvas );
  }

}

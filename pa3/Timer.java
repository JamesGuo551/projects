public class Timer {
  private double startTime; //Time when Timer started or reset
  //Create timer, initializing startTime with current time
  public Timer() {
    startTime = System.currentTimeMillis();
  }
  //Return number of milliseconds since last reset
  public double elapsedMilliseconds() {
    return System.currentTimeMillis() - startTime;
  }
  //Reset startTime
  public void reset() {
    startTime = System.currentTimeMillis();
  }
}
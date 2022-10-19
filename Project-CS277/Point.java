/**
  * this class creates a Point that keeps track of
  * the Fighter's location.
  * Kiet
**/
public class Point {
  private int x;
  private int y;

  /**
    * Creates a Point object.
    * **/

  public Point() {
    this.x = 0;
    this.y = 0;
  }

  /**
    * Creates a Point object at a specified Point.
    * **/

  public Point(Point p) {
    p.x = this.x;
    p.y = this.y;
  }

  /**
    * Creates a Point object at the given coordinates.
    * @param x the x-coordinate of the Point.
    * @param y the y-coordinate of the Point.
    * **/

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
    * Returns the x-coordinate of the Point object.
    * @return the x-value as an int
    * **/

  public int getX() {
    return this.x;
  }

  /**
    * Returns the y-coordinate of the Point object.
    * @return the y-value as an int
    * **/

  public int getY() {
    return this.y;
  }

  /**
    * Sets the location of the Point object to the 
    * specified Point.
    * @param p the Point to be changed.
    * **/

  public void setLocation(Point p) {
    this.x = p.x;
    this.y = p.y;
  }

  /**
    * Sets the location of the Point object to the 
    * given coordinates.
    * @param x the x-coordinate of the Point.
    * @param y the y-coordinate of the Point.
    * **/

  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
    * Returns a String of the Point's x and y coordinates.
    * @return the point as a String.
    * **/

  public String toString() {
    return this.x + ", " + this.y;
  }
}
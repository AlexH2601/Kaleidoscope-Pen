/** Title: P03 Kaleidoscopic Pen

 * Files: Point ; DriverApplication ; KaleidoscopePen ; TrianglePen ; Point ; Triangle
 * Course: CS 300 ; Fall Semester ; 2019
 * 
 * @author Alex Huang
 * Email: anhuang@wisc.edu
 * Lecturer: Gary Dahl
 */
import processing.core.PApplet; 

public class Point {
  
  private static final int POINT_DIAMETER = 8; // Diameter of all created points
  
  private int x; // X position of point center
  private int y; // Y position of point center
  
  public Point (int x, int y) {
    // Initialization of instance fields 
    this.x = x;
    this.y = y;
  }
  
  // Accessor method for x position
  public int getX () {
    return x;
  }
  
  // Accessor method for y position
  public int getY () {
    return y;
  }
  
  // Mutator method for position
  public void setPosition (int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void draw (PApplet drawTo) {
    // Draws a circle at the current position with fixed diameter
    drawTo.circle(this.x, this.y, POINT_DIAMETER);
  }
  
  public boolean isOver (int x, int y) {
    double radius = POINT_DIAMETER / 2; 
    // Distance formula
    double distance = Math.sqrt((Math.pow(x-this.x, 2) + (Math.pow(y-this.y, 2))));
    // Checks if the given point is within the point
    if (distance < radius) {
      return true;
    }
    else {
      return false;
    }
  }
}
/** Title: P03 Kaleidoscopic Pen

 * Files: Triangle ; DriverApplication ; KaleidoscopePen ; TrianglePen ; Point
 * Course: CS 300 ; Fall Semester ; 2019
 * 
 * @author Alex Huang
 * Email: anhuang@wisc.edu
 * Lecturer: Gary Dahl
 */
import processing.core.PApplet;

public class Triangle {
  private static final int[] COLORS = new int[] { // int packed w/8 bits of ARGB
   // WHITE, RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
      -1, -766643, -752563, -723891, -11668348, -11696908, -8106508, -766476 };
  
  private Point p1;
  private Point p2;
  private Point p3;
  private int colorIndex;
  
  // Constructor used if no color specified (default white used)
  public Triangle (Point p1, Point p2, Point p3) {
    // Initialization of instance fields
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
    this.colorIndex = 0;
  }
  
  // Constructor used if a color is specified 
  public Triangle (Point p1, Point p2, Point p3, int colorIndex) {
    // Initialization of instance fields
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
    this.colorIndex = colorIndex;
  }
  
  public void draw (PApplet drawTo) {
    // Fills triangle with selected color
    drawTo.fill(COLORS[colorIndex]);    
    // Draws triangle
    drawTo.triangle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
  }
  
  public boolean isOver (int x, int y) {
    // Checks if the given coordinates are inside the triangle
    if (isPointInTriangle(x, y, p1.getX(), p1.getY(), 
        p2.getX(), p2.getY(), p3.getX(), p3.getY())) {
      return true;
    }
    else {
      return false;
    }
  }
  
  // Mutator method for colorIndex
  public void setColor(int colorIndex) {
    this.colorIndex = colorIndex;
  } 
  
  private static boolean isPointInTriangle(int px, int py,
      int t1x, int t1y, int t2x, int t2y, int t3x, int t3y) {
      px -= t1x; 
      py -= t1y;
      t2x -= t1x;
      t2y -= t1y;
      t3x -= t1x;
      t3y -= t1y;
      double dotp2 = px*t2x+py*t2y;
      double dotp3 = px*t3x+py*t3y;
      double dot22 = t2x*t2x+t2y*t2y;
      double dot23 = t2x*t3x+t2y*t3y;
      double dot33 = t3x*t3x+t3y*t3y;
      double invDen = 1 / (dot33 * dot22 - dot23 * dot23);
      double a = (dot22 * dotp3 - dot23 * dotp2) * invDen;
      double b = (dot33 * dotp2 - dot23 * dotp3) * invDen;
      return (a >= 0) && (b >= 0) && (a + b < 1);
     }
}
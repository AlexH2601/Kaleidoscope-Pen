/** Title: P03 Kaleidoscopic Pen

 * Files: KaleidoscopePen ; DriverApplication ; TrianglePen ; Point ; Triangle
 * Course: CS 300 ; Fall Semester ; 2019
 * 
 * @author Alex Huang
 * Email: anhuang@wisc.edu
 * Lecturer: Gary Dahl
 */
import processing.core.PApplet;

public class KaleidoscopePen {
  
  private int numberOfTrianglePens;
  private TrianglePen[] trianglePens;
    
  public KaleidoscopePen (PApplet drawTo, int numberOfTrianglePens) {
    // Initialization of instance fields
    this.numberOfTrianglePens = numberOfTrianglePens;
    trianglePens = new TrianglePen[this.numberOfTrianglePens];
    // Creation of zero rotation pen
    trianglePens[0] = new TrianglePen(drawTo, true);
    // Creation of rotated versions of the zero rotation pen
    for (int i = 1 ; i < trianglePens.length ; i++) {
      // "False" parameter makes the points of rotated versions invisible
      trianglePens[i] = new TrianglePen(drawTo, false);
    }
  }
  
  public void update (int mouseX, int mouseY, boolean mousePressed, char keyPressed) {
    // Updates the application with changes/additions to the triangles
    for (int i = 0 ; i < trianglePens.length ; i++) {
      // Calculation of x/y position after being rotated
      int[] rotation = rotate(mouseX, mouseY, (i*(2*(Math.PI) / numberOfTrianglePens)));
      trianglePens[i].update(rotation[0], rotation[1], mousePressed, keyPressed);
    }
  }
  
  /**
  * Rotates a position around the center of an 800x600 screen by the specified
  * amount, and then returns an array containing the resulting position.
  * @param x position of the point to be rotated (0 to 800 pixels)
  * @param y position of the point to be rotated (0 to 600 pixels)
  * @param angle amount of rotation to apply (angle in radians units: 0 to 2*PI)
  * @return the rotated position array: x @ index 0, y @ index 1
  */
  private static int[] rotate(int x, int y, double angle) {
   x -= 400; // Translate center of screen to origin (0,0)
   y -= 300;
   int[] rotatedPosition = new int[] { // rotate around origin
   (int)(x * Math.cos(angle) - y * Math.sin(angle)),
   (int)(x * Math.sin(angle) + y * Math.cos(angle)) };
   rotatedPosition[0] += 400; // Return to center of screen
   rotatedPosition[1] += 300;
   return rotatedPosition;
  }
}

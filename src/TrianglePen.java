/** Title: P03 Kaleidoscopic Pen

 * Files: TrianglePen ; DriverApplication ; KaleidoscopePen ; Point ; Triangle
 * Course: CS 300 ; Fall Semester ; 2019
 * 
 * @author Alex Huang
 * Email: anhuang@wisc.edu
 * Lecturer: Gary Dahl
 */
import processing.core.PApplet;
import java.util.ArrayList;

public class TrianglePen {
  
  private boolean mouseWasPressed; // mousePressed from previous update() call
  private char keyWasPressed; // keyPressed from previous update() call
  
  private PApplet processing; 
  private boolean showPoints; // Whether or not to show points

  private ArrayList<Point> points; // List of all points
  private ArrayList<Triangle> triangles; // List of all triangles
  
  private Point currPoint; // Current point selected by user
  
  public TrianglePen(PApplet processing, boolean showPoints) {
    // Initialization of instance fields
    mouseWasPressed = false;
    keyWasPressed = '\0';
    this.processing = processing;
    this.showPoints = showPoints;
    points = new ArrayList<Point>();
    triangles = new ArrayList<Triangle>();
    currPoint = null;
  }
  
  public void update(int mouseX, int mouseY, boolean mousePressed, char keyPressed) {
    // Process mouse-based user input
    if(mousePressed != mouseWasPressed) {
     if(mousePressed) handleMousePress(mouseX, mouseY);
     else handleMouseRelease(mouseX, mouseY);
    }
    if(mousePressed) handleMouseDrag(mouseX, mouseY);
    mouseWasPressed = mousePressed;
    // Process keyboard-based user input
    if(keyPressed != keyWasPressed) handleKeyPress(mouseX, mouseY, keyPressed);
    keyWasPressed = keyPressed;
    // Draw everything in its current state
    draw();
  }
  
  private void handleMousePress (int mouseX, int mouseY) {
    // Boolean to check if the user is modifying a point
    boolean modify = false;
    // Checks if the mouse is over any points
    for (Point p : points) {
      if (p.isOver(mouseX, mouseY)) {
        currPoint = p;
        modify = true;
      }
    }
    
    // If the user is not modifying a point, they are creating a new one
    if (!modify) {
      points.add(new Point(mouseX, mouseY));
      int pointCount = points.size();
      // Checks if three points have been made (triangle should be created if so)
      if (pointCount % 3 == 0) {
        // Acquiring the last 3 points the user made
        Point p1 = points.get(pointCount-1);
        Point p2 = points.get(pointCount-2);
        Point p3 = points.get(pointCount-3);
        triangles.add(new Triangle(p1, p2, p3));
      }
    }
  }
  
  private void handleMouseRelease (int mouseX, int mouseY) {
    // Once the user releases the mouse click, the point is deselected
    currPoint = null;
  }
  
  private void handleMouseDrag (int mouseX, int mouseY) {
    // Only move the point if the user has selected a point
    if (currPoint != null) {
      currPoint.setPosition(mouseX, mouseY);
    }
  }  
  
  private void handleKeyPress (int mouseX, int mouseY, char keyPressed) {
    // Acquires key pressed by user and converts to int
    int input = processing.key - '0';
    // Checks if the key pressed is a digit key from 0-7
    if (input >= 0 && input <= 7) {
      // Checks if the user cursor is over any triangles
      for (Triangle t : triangles) {
        if (t.isOver(mouseX, mouseY)) {
          // Changes the color index of that triangle
          t.setColor(input);
        }
      }
    }
  }
  
  private void draw () {
    // Only draws defined points if the instance field is true
    if (showPoints) {
      for (Point p : points) {
        p.draw(processing);
      }
    }
    // Draws the defined triangles
    for (Triangle t : triangles) {
        t.draw(processing);
    }
  }
}
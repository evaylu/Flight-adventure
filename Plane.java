/* 
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 11/13/2015
 * Class Plane - An abstract class that provides the basic functions 
 * definitions for a plane
 */

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

public abstract class Plane extends JComponent {
	public abstract String getLabel();
    // return the bounding rectangle of the plane
    public abstract Rectangle getShape();
    // sets whether to display the coordinates with the plane
    public abstract void enableShowCoords(boolean show);
    // draws itself at its x and y coordinates
    // display coordinates if showCoords is set to true
    // light this plane if it selected is set to true
    public abstract void draw(Graphics g);
    // update plane's position for 1/100 second, based on speed and direction
    public abstract void tick();
}
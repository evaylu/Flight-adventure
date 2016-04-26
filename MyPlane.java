/* 
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 10/31/2015
 * Class BasicPlane - An airplane with that when called, draws itself and its
 * altitude, and can also draw its x and y coordinates
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MyPlane extends Plane {
    private static final int MAXSPEED = 500;
    private MyMap map;
    private double x;
    private double y;
    private String direction;
    private String label;
    private String color;
    private double speed;
    private Image img;
    private Rectangle shape;
    private boolean showCoords = false;
    boolean collided = false;
    private Image collisionImg;

    public MyPlane(int x, int y, String direction, String label, String color, 
                    int speed, MyMap map) throws IOException {
    	super();
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.label = label;
        this.color = color;
        if (speed > MAXSPEED)
            this.speed = MAXSPEED;
        else
            this.speed = speed;
        updateIcon();
        this.map = map;
        collisionImg = ImageIO.read(new File("images/collision.png"));
        shape = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }

    // draws itself at its x and y coordinates
    public void draw(Graphics g) {
    	adjustCoords(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, (int)x, (int)y, map);
        Color color = g2.getColor();
        g2.setColor(color.RED);
        g2.drawString("[" + label + "]", (int)x + img.getWidth(null), (int)y + 
                                                           img.getHeight(null));
        g2.setColor(color);
        if (showCoords)
            // draws its x and y coordinates to the screen
            g.drawString("Coordinates: (" + (int)x + ", " + (int)y + ")", 
                            (int)x + img.getWidth(null), 
                            15 + (int)y + img.getHeight(null));
        if (collided) {
        	g2.drawImage(collisionImg, (int)x - 5, (int)y - 5, map);
        }
        
    }
    
    // update plane's position for 1/100 second, based on speed and direction
    public void tick() {
        shape.setBounds((int)this.x, (int)this.y, img.getWidth(null), 
                                                    img.getHeight(null));
        if (direction == "East")
            x += speed/100;
        else if (direction == "West")
            x -= speed/100;
        else if (direction == "South")
            y += speed/100;
        else if (direction == "North")
            y -= speed/100;
    }
    
    public int getX() {
        return (int)x;
    }
    
    public int getY() {
        return (int)y;
    }
    
    public Rectangle getShape() {
        return shape;
    }
    
    public String getLabel() {
        return label;
    }
    
    // check if the rectangle of the other object intersects with this plane's 
    public boolean collide(Rectangle other) {
    	if (shape.intersects(other)) {
        	return true;
        } 
    	return false;
    }
    
    public void enableShowCoords(boolean show) {
        showCoords = show;
    }
    
    // update speed with input "delta"
    public void setSpeed(int s) {
        speed = s;
    }
    
    public void setCollisionStatus(boolean c) {
    	collided = c;
    }
    
    //set the direction of the plane, and update the icon accordingly
    public void setDirection(String direction) {
        this.direction = direction;
        updateIcon();
    }
    
 // turn the plane in the given direction (1: clockwise, 0: counterclockwise)
    public void turn(int d) {
        if (direction.equals("East")) {
            if (d == 1)
                setDirection("South");
            else
                setDirection("North");
        } else if (direction.equals("South")) {
            if (d == 1)
                setDirection("West");
            else
                setDirection("East");
        } else if (direction.equals("West")) {
            if (d == 1)
                setDirection("North");
            else
                setDirection("South");
        } else if (direction.equals("North")) {
            if (d == 1)
                setDirection("East");
            else
                setDirection("West");
        }
    }
        
    public boolean intersect(int x, int y, int w, int h) {
        return shape.intersects(x, y, w, h);
    }
    
 // adjust x and y if they are out of the map area
    private void adjustCoords(Graphics g) {
        int mapWidth = map.getWidth() - img.getWidth(null);
        int mapHeight = map.getHeight() - img.getHeight(null);
        x %= mapWidth;
        y %= mapHeight;
        if (x < 0)
            x = mapWidth + x;
        if (y < 0)
            y = mapHeight + y;
    }
    
    // update the plane icon/img according to the color and direction values
    private void updateIcon() {
        img = Toolkit.getDefaultToolkit().getImage("images/" + direction + "_" 
                                                    + color + ".png");
    }
}
/*
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 11/24/2015
 * Class EnemyPlane - a EnemyPlane with random color, direction and speed
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class EnemyPlane extends Plane {
    private static final int MAXSPEED = 200;
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

    public EnemyPlane(int x, int y, String label, MyMap map){
        this.x = x;
        this.y = y;
        this.map = map;
        this.label = label;
        setColor(Math.random());
        setDirection(Math.random());
        speed = Math.random() * MAXSPEED;
        if (speed < 10)
        	speed = 50;
        img = Toolkit.getDefaultToolkit().getImage("images/" + direction + "_" 
                + color + ".png");
        shape = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }

    // draws itself at its x and y coordinates
    public void draw(Graphics g) {
    	adjustCoords(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, (int)x, (int)y, map);
        if (showCoords)
            // draws its x and y coordinates to the screen
            g.drawString("Coordinates: (" + (int)x + ", " + (int)y + ")", 
                            (int)x + img.getWidth(null), 
                            15 + (int)y + img.getHeight(null));
    }
    
    // update plane's position for 1/100 second, based on speed and direction
    public void tick() {
        if (direction == "East")
            x += speed/100;
        else if (direction == "West")
            x -= speed/100;
        else if (direction == "South")
            y += speed/100;
        else if (direction == "North")
            y -= speed/100;
    }
    
    public String getLabel() {
        return label;
    }
    
    public Rectangle getShape() {
        shape.setBounds((int)this.x, (int)this.y, img.getWidth(null), img.getHeight(null));
        return shape;
    }
    
    public void enableShowCoords(boolean show) {
        showCoords = show;
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
    
    // set the color corresponding to the code
    private void setColor(double code) {
    	if (code <= 0.25)
    		color = "blue";
    	else if (code <= 0.5)
    		color = "green";
    	else if (code <= 0.75)
    		color = "yellow";
    	else
    		color = "red";
    }
    
    // set the direction corresponding to the code
    private void setDirection(double code) {
    	if (code <= 0.25)
    		direction =  "East";
    	else if (code <= 0.5)
    		direction =  "South";
    	else if (code <= 0.75)
    		direction =  "West";
    	else
    		direction =  "North";
    }

}
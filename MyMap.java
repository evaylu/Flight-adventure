/* 
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 10/31/2015
 * Class MyMap - A drawing area that initially draws 2 airplane on it
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MyMap extends JComponent implements ActionListener, MouseListener {
	private final int OBSTACLES = 12;
    private List<Plane> planes;
    private boolean pause;
    private Timer timer;
    private MyPlane player;
    private List<Mountain> mountains;
    private int timerCount;
    private int width;
    private int height;
    private boolean collision;
    private int score;
    private Image destinationImg;
    private boolean win;
    
    MyMap(int w, int h) throws IOException {
    	setPreferredSize(new Dimension(w, h));
        setBorder(new LineBorder(Color.GRAY, 2));
        addMouseListener(this);
        
        width = w;
        height = h;
        destinationImg = ImageIO.read(new File("images/tower1.png"));
        
        init();
    }
    
    private void init() throws IOException {
        // the planes list, initially has only the player's planes in it
        planes = new ArrayList<Plane>();
        player = new MyPlane(0, 0, "East", "MyPlane", "black", 50, this);
        planes.add(player);
        pause = false;
        
        mountains = new ArrayList<Mountain>();
        for (int i = 0; i < OBSTACLES; i++) {
        	mountains.add(new Mountain((int)(Math.random() * width), 
        						   		(int)(Math.random() * height), this));
        	planes.add(new EnemyPlane((int)(Math.random() * width), 
					(int)(Math.random() * height), "Enemy_" + timerCount/500, 
                                                                        this));
        }
        timerCount = 0;
        score = 5000;
        collision = false;
        win = false;
        
        // send actionPerformed event every 1/100 second
        timer = new Timer(10, this);
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
    	// check score
	    if (score <= 0) {
	    	pause = true;
	    	timer.stop();
	    	g.setColor(Color.BLUE);
	    	g.drawString("BOOM!! Grame Over. Click on [New Game] to restart.", 
	    										getWidth()/3, getHeight()/2);
	    	return;
	    }
	    
	    if (win) {
	    	pause = true;
	    	timer.stop();
	        Color color = g.getColor();
	        g.setColor(color.RED);
	    	g.drawString("YOU WIN!!! Score: " + score + 
              ". Click on [New Game] to restart.", getWidth()/4, getHeight()/2);
	        g.setColor(color);
	    	return;
	    }
	   
	    player.setCollisionStatus(collision);
	    
        // change the background color
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(getForeground());
        
        // draw airplanes and mountains and the destination
	    for (Plane plane : planes) {
	    	plane.draw(g);
	    }
	    for (Mountain m : mountains) {
	    	m.draw(g);
	    }
        g.drawImage(destinationImg, getWidth()/2, getHeight()/2, this);
	    
        // print plane count on the bottom-right corner
        g.drawString("Enemies: " + (planes.size() - 1), getWidth() - 115, 
                      getHeight() - 20);
        g.drawString("Total Score: " + score, getWidth() - 115, 
                		getHeight() - 5);
    }

	// used to update the value of pause, which indicates whether the planes'
    // movement should be halted
    public void setPause(boolean pause) {
    	if (pause == true)
    		System.out.println("Paused!");
    	else 
    		System.out.println("Unpaused!");
    	this.pause = pause;
    }
    
    // decides whether to show the planes' coordinates
    public void setShowCoords(boolean showCoords) {
    	for (Plane plane : planes) {
        	plane.enableShowCoords(showCoords);
        }
    }

    // reset the map to its original look
    public void reset() throws IOException {
    	setShowCoords(false);
    	setBackground(null);
    	timer.stop();
    	init();
    	repaint();
    }
    
    public void planeSpeed(int speed) {
    	if (!pause) {
    		player.setSpeed(speed);
    	}
    }
    
    // turn the plane in the given direction (1: clockwise, 0: counterclockwise)
    public void planeTurn(int d) {
    	player.turn(d);
    	repaint();
    }
    
    public void planeDirection(String d) {
    	player.setDirection(d);
    	repaint();
    }
    
 // Listener callback
    public void mousePressed (MouseEvent event) {
    // 	System.out.println ("Mouse pressed at (" + event.getPoint().x + ", " + 
    //                                                 event.getPoint().y + ")");
    }

    // defined by MouseListener
    public void mouseClicked (MouseEvent event) {}
    public void mouseReleased (MouseEvent event) {}
    public void mouseEntered (MouseEvent event) {}
    public void mouseExited (MouseEvent event) {}
    
    // timer actionPerformed callback: update each plane's location
    public void actionPerformed(ActionEvent event) {
        if (!pause) {
        	// add a new enemy plane every 5 seconds
    		//System.out.println(timerCount);
        	if ((timerCount++) % 500 == 0) {
        		planes.add(new EnemyPlane((int)(Math.random() * getWidth()), 
        					(int)(Math.random() * getHeight()), "Enemy_" + 
                                                        timerCount/500, this));
        	}
            for (Plane plane : planes) {
                plane.tick();
            }
            // check collision after game started for 5 seconds
            if (timerCount > 300) {
            	checkCollision();
            	score--;
            }
            player.setCollisionStatus(collision);
            // check if player reached the destination
            win = player.intersect(getWidth()/2, getHeight()/2, 
                                        getWidth()/2 + 50, getHeight()/2 + 50);
            repaint();
        }
    }
    
    // check if there is a collision between two planes
    // two planes collide if their altitudes are the same and both > 0
    private void checkCollision() {
    	collision = false;
        for (int i = 1; i < planes.size(); i++) {
            // check if two planes collide
            if (player.collide(planes.get(i).getShape())) {
//                System.out.println("collision with plane " +
//                                                     planes.get(i).getLabel());
                collision =  true;
                score -= 50;
            }
        }

        for (Mountain m : mountains) {
        	if (player.collide(m.getShape())) {
//                System.out.println("Collision with mountain!");
                collision =  true;
                score -= 100;
        	}
        }
    }
}

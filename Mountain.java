/*
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 11/24/2015
 * Class Mountain - a Mountain that draws itself at the given location in map
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Mountain extends JComponent {
    private MyMap map;
    private int x;
    private int y;
    private Image img;
    private Rectangle shape;

    public Mountain(int x, int y, MyMap map) throws IOException{
        this.x = x;
        this.y = y;
        img = ImageIO.read(new File("images/mountain.png"));
        this.map = map;
        shape = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }

    // draws itself at its x and y coordinates
    public void draw(Graphics g) {
    	adjustCoords(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, x, y, map);
        
    }
    
    public Rectangle getShape() {
    	shape.setBounds((int)this.x, (int)this.y, img.getWidth(null), 
                                                        img.getHeight(null));
        return shape;
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


}
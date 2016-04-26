/*
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 10/25/2015
 * Class Main - deploy a window and several widgets: 
 * a drawing area in the center with a player's plane and a bunch of obstacles
 * in it, in addition a control panel at bottom with some control widgets in it
 */

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class Main extends JFrame {
	public static void main(String[] args) throws IOException {
		new Main();
	}

	public Main() throws IOException {
		// window setup
		setLocation(400, 150);
		setSize(800, 600);
		// exit the program by hitting the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = getContentPane();
		content.setLayout(new BorderLayout());

		MyMap map = new MyMap(600, 475);
		// control panels
		JPanel mainControls = new JPanel();
		mainControls.setLayout(new BorderLayout());
		mainControls.setPreferredSize(new Dimension(800, 125));
		mainControls.add(new PlaneControls(400, 100, map), BorderLayout.WEST);
		mainControls.add(new MainPanel(map), BorderLayout.CENTER);
		
		content.add(map, BorderLayout.CENTER);	
		content.add(mainControls, BorderLayout.SOUTH);

		// display window
		setVisible(true);
		setResizable(false);
	}
}
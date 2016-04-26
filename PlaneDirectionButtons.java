/*
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 11/13/2015
 * Class PlaneDirectionButtons - a pair of buttons that controls the selected
 * plane's direction
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PlaneDirectionButtons extends JPanel {
	private static MyMap map;

	public PlaneDirectionButtons(MyMap m) {
		map = m;
		add(new RotateCounterClockwise());
		add(new RotateClockwise());
	}
	
	static class RotateClockwise extends JButton implements ActionListener {
		RotateClockwise() {
			//setText("⮐");
			setIcon (new ImageIcon ("images/clockwise.png"));
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			map.planeTurn(1);
		}
	}
	
	static class RotateCounterClockwise extends JButton implements 
															ActionListener {
		RotateCounterClockwise() {
			//setText("⮑");
			setIcon (new ImageIcon ("images/counterclockwise.png"));
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			map.planeTurn(0);
		}
	}
	
}
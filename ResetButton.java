
/*
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 10/25/2015
 * Class MyResetButton - a button that when pressed will bring the canvas to 
 * its original look
 */
import java.awt.event.*;
import java.io.IOException;

import javax.swing.JButton;

public class ResetButton extends JButton implements ActionListener {
	private MyMap map;

	public ResetButton(MyMap map) {
		setText("New Game");
		addActionListener(this);
		this.map = map;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			map.reset();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

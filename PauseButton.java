
/* 
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 10/31/2015
 * Class PauseButton - a button to pause and unpause all airplanes
 */

import java.awt.event.*;
import javax.swing.*;

public class PauseButton extends JButton implements ActionListener {
	private MyMap map;
	private String label;

	public PauseButton(MyMap m) {
		map = m;
		label = "Pause";
		setText(label);
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (label == "Pause") { // user pressed "Pause"
			map.setPause(true);
			label = "Unpause";
			setText(label);

		} else { // user pressed "Unpause"
			map.setPause(false);
			label = "Pause";
			setText(label);
		}
	}
}
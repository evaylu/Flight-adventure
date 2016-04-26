
/* 
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 10/31/2015
 * Class MainPanel - a panel that controls the movement of all planes and 
 * the setting of map
 */

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MainPanel extends JPanel {
	public MainPanel(MyMap map) {
		setBorder(new LineBorder(Color.GRAY, 2));
		setLayout(new FlowLayout());

		add(new JLabel("               MyPlane Panel               ")).
										setFont(getFont().deriveFont(20.0f));
		// a list for the option of display planes' coordinates
		add(new ShowCoordsList(map));
		// a combo box to choose map color
		add(new MapColorComBox(map));
		// a button to pause/unpause all planes
		add(new PauseButton(map));
		// a button to reset the settings
		add(new ResetButton(map));
	}
}

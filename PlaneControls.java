/*
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 11/13/2015
 * Class PlaneControls - a control panel that contains widgets that modifies 
 * the selected plane's features
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PlaneControls extends JPanel {
	private MyMap map;

	public PlaneControls(int w, int h, MyMap m) {
		map = m;
		setPreferredSize(new Dimension(w, h));
		setBorder(new LineBorder(Color.GRAY, 2));
		setLayout(new FlowLayout());
		
		add(new JLabel("            MyPlane Controls            ")).
										   setFont(getFont().deriveFont(20.0f));
		add(new JLabel("      Change Speed:      "));
		add(new SpeedScrollBar(map));
		add(new JLabel("      Change Direction:      "));
		add(new PlaneDirectionButtons(map));
	}

}

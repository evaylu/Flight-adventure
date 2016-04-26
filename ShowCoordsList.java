
/*
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 10/25/2015
 * Class ShowCoordsList - lets users select whether they want to display 
 * the airplanes' position coordinates, and sets the canvas accordingly.
 */

import javax.swing.*;
import javax.swing.event.*;

public class ShowCoordsList extends JList<String> implements 
													ListSelectionListener {
	private MyMap map;

	public ShowCoordsList(MyMap map) {
		this.map = map;
		String[] items = { "Show Positions", "Hide Positions" };
		// setListData (map.getPlanes());
		setListData(items);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addListSelectionListener(this);
	}

	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			System.out.println("Plane selected: " + getSelectedValue());
			if (getSelectedValue() == "Show Positions")
				map.setShowCoords(true);
			else
				map.setShowCoords(false);
			map.repaint();
		}
	}
}
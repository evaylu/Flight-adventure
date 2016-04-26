
/*
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 10/25/2015
 * Class MyComboBox - lets users select the background color they want to set
 * for the drawing area
 */

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

// Note: On older versions of Java, use "JComboBox" not "JComboBox<String>"
public class MapColorComBox extends JComboBox<String> implements ItemListener {
	private MyMap map;
	public MapColorComBox(MyMap m) {
		this.map = m;
		addItem("Canvas Color");
		addItem("Red");
		addItem("Yellow");
		addItem("Green");
		addItem("White");
		addItem("Default");
		setSelectedItem("Canvas Color"); // initial
		addItemListener(this);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// System.out.println("Combo: " + e.getItem());
			if (getSelectedItem() == "Red")
				map.setBackground(Color.RED);
			else if (getSelectedItem() == "Yellow")
				map.setBackground(Color.YELLOW);
			else if (getSelectedItem() == "Green")
				map.setBackground(Color.GREEN);
			else if (getSelectedItem() == "White")
				map.setBackground(Color.WHITE);
			else if (getSelectedItem() == "Default")
				map.setBackground(null);
		}
	}
}
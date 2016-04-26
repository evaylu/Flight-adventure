/*
 * Wrote by: Yunjie Lu (luyunjie.eva@gmail.com)
 * Date: 11/13/2015
 * Class SpeedScrollBar - a scroll bar that controls the selected 
 * plane's speed
 */

import java.awt.event.*;
import javax.swing.*;

public class SpeedScrollBar extends JScrollBar implements AdjustmentListener {
	private MyMap map;
    
    public SpeedScrollBar (MyMap m) {
    	map = m;
		setOrientation (HORIZONTAL);
		setMinimum(30);
		setMaximum(210);
		setBlockIncrement(20);
		addAdjustmentListener (this);
    }

    public void adjustmentValueChanged (AdjustmentEvent event) {
    	map.planeSpeed(getValue());
    }
}
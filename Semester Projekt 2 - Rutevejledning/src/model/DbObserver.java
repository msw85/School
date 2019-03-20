package model;

import gui.MobileGUI;
import gui.SaleGUI;

public class DbObserver implements Observer {

	@Override
	public void notifyMe(boolean state) {
		MobileGUI.changeColor(state);
		SaleGUI.changeColor(state);
	}
}

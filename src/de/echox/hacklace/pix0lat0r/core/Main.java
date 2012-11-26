package de.echox.hacklace.pix0lat0r.core;

import de.echox.hacklace.pix0lat0r.gui.GUI;


public class Main {

	public static void main(String[] args) {
	
		GUI gui = new GUI();
		App controller = new App();
		gui.initialize(controller);
	}

}

package de.echox.hacklace.pix0lat0r.gui;

import org.eclipse.swt.widgets.Button;

import de.echox.hacklace.pix0lat0r.data.ModusOptions;

public class ModusByteGUIContainer {
	
	Button speedSlow;
	Button speedMedium;
	Button speedFast;
	
	Button pauseShort;
	Button pauseMedium;
	Button pauseLong;
	
	Button scrollback;

	protected ModusByteGUIContainer(Button speedSlow, Button speedMedium,
			Button speedFast, Button pauseShort, Button pauseMedium,
			Button pauseLong, Button scrollback) {
		this.speedSlow = speedSlow;
		this.speedMedium = speedMedium;
		this.speedFast = speedFast;
		this.pauseShort = pauseShort;
		this.pauseMedium = pauseMedium;
		this.pauseLong = pauseLong;
		this.scrollback = scrollback;
	}
	
	protected short getSpeedValue() {
		
		short speed = 0;
		
		if(this.speedFast.getSelection()) {
			speed += ModusOptions.SPEED_FAST;
		} if (this.speedMedium.getSelection()) {
			speed += ModusOptions.SPEED_MEDIUM;
		} if (this.speedSlow.getSelection()) {
			speed += ModusOptions.SPEED_SLOW;
		}
		
		return speed;
	}
	
	protected short getPauseValue() {

		short pause = 0;
		
		if(this.pauseLong.getSelection()) {
			pause += ModusOptions.PAUSE_LONG;
		} if (this.pauseMedium.getSelection()) {
			pause += ModusOptions.PAUSE_MEDIUM;
		} if (this.pauseShort.getSelection()) {
			pause += ModusOptions.PAUSE_SHORT;
		}
		
		return pause;
	}
	
	protected short getScrollbackValue() {
		if (this.scrollback.getSelection()) {
			return ModusOptions.ANIMATION_SCROLLBACK;
		} else {
			return 0;
		}
	}
}

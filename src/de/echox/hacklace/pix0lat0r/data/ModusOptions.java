package de.echox.hacklace.pix0lat0r.data;

/**
 * This interface contains some constants to create the modus byte
 * 
 * It contains the following info:
 * 
 *  BPPPTSSS
 *  
 *  B: Scrollback bit
 *  P: Pause bit (> = longer)
 *  T: Type (currently only animations are supported)
 *  S: Animation speed bit (> = faster)
 *  
 */
public interface ModusOptions {

	//I would wish for an unsigned byte ;-)
	
	public static final short SPEED_SLOW = 0x01;
	public static final short SPEED_MEDIUM = 0x02;
	public static final short SPEED_FAST = 0x04;
	
	public static final short PAUSE_SHORT = 0x10;
	public static final short PAUSE_MEDIUM = 0x20;
	public static final short PAUSE_LONG = 0x40;
	
	public static final short ANIMATION_SCROLLBACK = 0x80;
	
	public static final short TYPE_ANIMATION = 0x08;
	
}

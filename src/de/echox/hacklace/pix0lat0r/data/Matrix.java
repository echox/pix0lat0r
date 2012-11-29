package de.echox.hacklace.pix0lat0r.data;

public interface Matrix {

	void setPixel(int x, int y);
	void unsetPixel(int x, int y);
	boolean getPixel(int x, int y);
	void togglePixel(int x, int y);
	boolean[][] getData();
	boolean[] getColumn(int column);
	//TODO fix clone interface
	Matrix copy();
}

package de.echox.hacklace.pix0lat0r.data;

public class MatrixImpl implements Matrix {

	boolean[][] matrix;
	
	public MatrixImpl(int width, int height) {
		this.matrix = new boolean[width][height];
	}
	
	@Override
	public void setPixel(int x, int y) {
		matrix[x][y] = true;
	}

	@Override
	public void unsetPixel(int x, int y) {
		matrix[x][y] = false;
	}
	
	@Override
	public boolean getPixel(int x, int y) {
		return matrix[x][y];
	}

	@Override
	public void togglePixel(int x, int y) {
		this.matrix[x][y] = (!getPixel(x, y));
	}

	@Override
	public boolean[][] getData() {
		return this.matrix;
	}

}

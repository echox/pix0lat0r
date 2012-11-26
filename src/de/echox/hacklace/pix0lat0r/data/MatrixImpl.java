package de.echox.hacklace.pix0lat0r.data;

import java.util.Arrays;

public class MatrixImpl implements Matrix {

	private boolean[][] matrix;
	
	public MatrixImpl(int width, int height) {
		this.matrix = new boolean[width][height];
	}
	
	private MatrixImpl(MatrixImpl matrix) {
		this.matrix = copyArray(matrix.getData());
	}
	
	public static boolean[][] copyArray(boolean[][] array) {
	    if (array == null) return null;

	    final boolean[][] result = new boolean[array.length][];
	    for (int i = 0; i < array.length; i++) {
	        result[i] = Arrays.copyOf(array[i], array[i].length);
	    }
	    return result;
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
	
	public Matrix copy() {
		return new MatrixImpl(this);
	}

}

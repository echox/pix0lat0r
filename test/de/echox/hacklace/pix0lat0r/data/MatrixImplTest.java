package de.echox.hacklace.pix0lat0r.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MatrixImplTest {

	public final static int WIDTH = 5;
	public final static int HEIGHT = 7;
	
	private Matrix matrix;
	
	@Before
	public void setup() {
		this.matrix = new MatrixImpl(WIDTH, HEIGHT);
	}
	
	@Test
	public void emptyTest() {
		for(int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				matrix.unsetPixel(x, y);
			}
		}
		
		for(int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				assertFalse(matrix.getPixel(x, y));
			}
		}
		
		for (int x = 0; x < WIDTH; x++) {
			boolean[] column = matrix.getColumn(x);
			for(int y = 0; y < HEIGHT; y++) {
				assertFalse(column[y]);
			}
		}
	}
	
	@Test
	public void fullTest() {
		
		for(int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				matrix.setPixel(x, y);
			}
		}
		
		for(int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				assertTrue(matrix.getPixel(x, y));
			}
		}
		
		for (int x = 0; x < WIDTH; x++) {
			boolean[] column = matrix.getColumn(x);
			for(int y = 0; y < HEIGHT; y++) {
				assertTrue(column[y]);
			}
		}
	}
	
	@Test
	public void circleTest() {
		
		for(int x = 0; x < WIDTH; x++) {
				matrix.setPixel(x, 0);
		}
		
		for(int x = 0; x < WIDTH; x++) {
				matrix.setPixel(x, HEIGHT-1);
		}
		
		for(int y = 0; y < HEIGHT; y++) {
				matrix.setPixel(0, y);
		}
		
		for(int y = 0; y < HEIGHT; y++) {
			matrix.setPixel(WIDTH -1, y);
		}
		
		for(int x = 0; x < WIDTH; x++) {
			assertTrue(matrix.getPixel(x, 0));
		}
		
		for(int x = 0; x < WIDTH; x++) {
			assertTrue(matrix.getPixel(x, HEIGHT-1));
		}
		
		for(int y = 0; y < HEIGHT; y++) {
			assertTrue(matrix.getPixel(0, y));
		}
		
		for(int y = 0; y < HEIGHT; y++) {
			assertTrue(matrix.getPixel(WIDTH -1, y));
		}
		
		for (int x = 1; x < WIDTH-1; x++) {
			boolean[] column = matrix.getColumn(x);
			
			assertTrue(column[0]);
			assertTrue(column[HEIGHT-1]);
			
			for(int y = 1; y < HEIGHT-1; y++) {
				assertFalse(column[y]);
			}
		}

		boolean[] column = matrix.getColumn(0);
		for(int y = 0; y < HEIGHT; y++) {
			assertTrue(column[y]);
		}
		
		column = matrix.getColumn(WIDTH-1);
		for(int y = 0; y < HEIGHT; y++) {
			assertTrue(column[y]);
		}
		
	}
	
}

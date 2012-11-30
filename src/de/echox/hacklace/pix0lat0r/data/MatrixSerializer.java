package de.echox.hacklace.pix0lat0r.data;

import java.util.Iterator;

// TODO class works only for byte size displays
public class MatrixSerializer {
	
	public static byte[] serialize(Iterable<Matrix> matrixes) {

		//TODO finish that :-)
		for (Iterator<Matrix> it = matrixes.iterator(); it.hasNext();) {
			Matrix matrix = it.next();
		}
		return null;
	}
	
	public static byte[] serialize(Matrix matrix) {
		
		byte[] result = new byte[matrix.getSize()];
		int width = matrix.getWidth();
		
		for(int x=0; x < width; x++) {
			result[x] = serialize(matrix.getColumn(x));
		}
		
		return result;
	}

	public static byte serialize(boolean[] row) {
		
		byte result = 0;
		
		if(row[0]) {
			result += 0x01;
		} else if (row[1]) {
			result += 0x02;
		} else if (row[2]) {
			result += 0x04;
		} else if (row[3]) {
			result += 0x08;
		} else if (row[4]) {
			result += 0x10;
		} else if (row[5]) {
			result += 0x20;
		} else if (row[6]) {
			result += 0x40;
		}
		
		return result;
	}

}

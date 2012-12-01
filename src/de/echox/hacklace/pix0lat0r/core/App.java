package de.echox.hacklace.pix0lat0r.core;

import java.util.ArrayList;
import java.util.List;

import de.echox.hacklace.pix0lat0r.data.Matrix;
import de.echox.hacklace.pix0lat0r.data.MatrixImpl;
import de.echox.hacklace.pix0lat0r.data.MatrixSerializer;

public class App {
	
	List<Matrix> pages = new ArrayList<Matrix>();
	
	private int pageIdx = 0;

	public void quit() {
		System.exit(0);
	}

	App() {
		pages.add(new MatrixImpl(5,7));
	}
	
	public Matrix nextPage() {
		pageIdx++;
		if(pageIdx == pages.size()) {
			pages.add(new MatrixImpl(5,7));
		}
		return pages.get(pageIdx);
	}

	public Matrix prevPage() {
		if (pageIdx > 0) {
			pageIdx--;
		}
		return pages.get(pageIdx);
	}

	public int getCurrentPage() {
		return pageIdx + 1;
	}

	public int getPageSize() {
		return this.pages.size();
	}
	
	public Matrix getCurrentPageData() {
		return this.pages.get(pageIdx);
	}

	public Matrix cloneNewPage() {
		Matrix matrix = pages.get(pageIdx).copy();
		pageIdx++;
		pages.add(pageIdx, matrix);
		return matrix;
	}

	public Matrix insertNewPage() {
		Matrix matrix = new MatrixImpl(5, 7);
		pageIdx++;
		pages.add(pageIdx, matrix);
		return matrix;
	}

	public Matrix delete() {
		
		if(pageIdx == 0 && pages.size() == 1) {
			pages.remove(0);
			pages.add(0,new MatrixImpl(5, 7));
		} else {
			if(pages.size() != 1) {
				
				pages.remove(pageIdx);
				
				if(pageIdx != 0) {
					pageIdx--;
				}
				
			}
		}
		
		return pages.get(pageIdx);
	}
	
	//TODO state should be kept in app, not in GUI, change this
	public void serialize(short modus) {
		byte[] result = MatrixSerializer.serialize(this.pages);
		for (int i = 0; i < result.length; i++) {
			System.out.print("0x" + Integer.toHexString(result[i]).toUpperCase() + " ");
		}
		System.out.println();
		System.out.println("Modus: " + Integer.toHexString(modus).toUpperCase());
	}
}

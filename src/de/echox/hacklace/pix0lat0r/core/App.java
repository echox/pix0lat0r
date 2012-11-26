package de.echox.hacklace.pix0lat0r.core;

import java.util.ArrayList;
import java.util.List;

import de.echox.hacklace.pix0lat0r.data.Matrix;
import de.echox.hacklace.pix0lat0r.data.MatrixImpl;

public class App {
	
	List<Matrix> pages = new ArrayList<Matrix>();
	
	private int currentPage;

	public void quit() {
		System.exit(0);
	}

	App() {
		this.currentPage = 1;
		pages.add(new MatrixImpl(5,7));
	}
	
	public Matrix nextPage() {
		if(currentPage >= pages.size()) {
			pages.add(new MatrixImpl(5,7));
			currentPage++;
		}
		return pages.get(currentPage);
	}

	public Matrix prevPage() {
		if (currentPage > 1) {
			currentPage--;
		}
			return pages.get(currentPage);
	}

}

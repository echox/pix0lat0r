package de.echox.hacklace.pix0lat0r.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import de.echox.hacklace.pix0lat0r.core.App;

public class GUI {

	private Display display;
	private Shell shell;
	private App app;
	
	private Drawer drawer;

	public void initialize(App app) {
		
		this.app = app;
		
		display = new Display ();
		shell = new Shell (display);
		
		initializeMenu();
		initializeAnimator();
		
		
		shell.setLayout (new RowLayout ());
		shell.pack();
		shell.open ();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
		
	}

	private void initializeAnimator() {

		// controls
		
		Button left = new Button (shell, SWT.PUSH);
		left.setText(" < ");
		
		Label label = new Label(shell, SWT.VERTICAL);
		label.setText("1/1");
		
		Button right = new Button (shell, SWT.PUSH);
		right.setText(" > ");
		
		// drawer
		Drawer drawer = new Drawer(shell, 0);
		
		left.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				app.prevPage(GUI.this.drawer.getMatrixData());
				GUI.this.drawer.setMatrixData(app.getCurrentPage());
			}
		});
		
		right.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				app.nextPage(GUI.this.drawer.getMatrixData());
				GUI.this.drawer.setMatrixData(app.getCurrentPage());
			}
		});
		
	}

	private void initializeMenu() {
		
		Menu bar = new Menu (shell, SWT.BAR);
		shell.setMenuBar (bar);
		
		MenuItem fileItem = new MenuItem (bar, SWT.CASCADE);
		fileItem.setText ("File");
		Menu submenu = new Menu (shell, SWT.DROP_DOWN);
		fileItem.setMenu (submenu);
		MenuItem item = new MenuItem (submenu, SWT.PUSH);
		
		item.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				app.quit();
			}
		});
		item.setText ("Quit");
		
		MenuItem settings = new MenuItem(bar,SWT.PUSH);
		settings.setText("Settings");
		
	}
	
	
}

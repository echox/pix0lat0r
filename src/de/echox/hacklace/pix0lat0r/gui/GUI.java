package de.echox.hacklace.pix0lat0r.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import de.echox.hacklace.pix0lat0r.core.App;
import de.echox.hacklace.pix0lat0r.data.ModusOptions;

public class GUI {

	private Display display;
	private Shell shell;
	private App app;
	
	private Drawer drawer;
	private Label pageLabel;
	
	private ModusByteGUIContainer modusByte;

	public void initialize(App app) {
		
		this.app = app;
		
		display = new Display ();
		shell = new Shell (display);
		
		GridLayout gridLayout = new GridLayout(1, true);
		shell.setLayout(gridLayout);
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		layoutData.verticalAlignment = SWT.CENTER;
		layoutData.horizontalAlignment = SWT.CENTER;
		shell.setLayoutData(layoutData);
		
		initializeMenu();
		initializeAnimator();
		initializeModusByte();
		
		
		shell.pack();
		shell.open ();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
		
	}

	private void initializeAnimator() {
		
		Composite container = new Composite(shell, SWT.BORDER);
		container.setLayout(new GridLayout());
		container.setLayoutData(GUI.getCenterLayout());

    	Composite control = new Composite(container, SWT.BORDER);
    	control.setLayout(new RowLayout());
		control.setLayoutData(GUI.getCenterLayout());
		

		// controls
		
		Button delete = new Button (control, SWT.PUSH);
		delete.setText(" X ");
    	
		Button left = new Button (control, SWT.PUSH);
		left.setText(" < ");
		
		pageLabel = new Label(control, SWT.VERTICAL);
		
		Button right = new Button (control, SWT.PUSH);
		right.setText(" > ");
		
		Button clone = new Button (control, SWT.PUSH);
		clone.setText(" *> ");
		
		Button insert = new Button (control, SWT.PUSH);
		insert.setText(" +> ");
		
		// drawer
		this.drawer = new Drawer(container, 0);
		drawer.setMatrixData(app.getCurrentPageData());
		drawer.setLayoutData(GUI.getCenterLayout());
		
		left.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				GUI.this.drawer.setMatrixData(app.prevPage());
				GUI.this.drawPaging();
			}
		});
		
		delete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				GUI.this.drawer.setMatrixData(app.delete());
				GUI.this.drawPaging();
			}
		});
		
		right.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				GUI.this.drawer.setMatrixData(app.nextPage());
				GUI.this.drawPaging();
			}
		});
		
		clone.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				GUI.this.drawer.setMatrixData(app.cloneNewPage());
				GUI.this.drawPaging();
			}
		});
		
		insert.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				GUI.this.drawer.setMatrixData(app.insertNewPage());
				GUI.this.drawPaging();
			}
		});
		
		drawPaging();
	}
	
	private void drawPaging() {
		this.pageLabel.setText(" " + app.getCurrentPage() + " / " + app.getPageSize() + " ");
		this.pageLabel.redraw();
	}

	private void initializeMenu() {
		
		//TODO structure menu
		
		Menu bar = new Menu (shell, SWT.BAR);
		shell.setMenuBar (bar);
		
		MenuItem fileItem = new MenuItem (bar, SWT.CASCADE);
		fileItem.setText ("File");
		Menu submenu = new Menu (shell, SWT.DROP_DOWN);
		fileItem.setMenu (submenu);
		
		MenuItem open = new MenuItem (submenu, SWT.PUSH);
		open.setText("Open...");
		MenuItem save = new MenuItem (submenu, SWT.PUSH);
		save.setText("Save");
		MenuItem saveAs = new MenuItem (submenu, SWT.PUSH);
		saveAs.setText("Save as..");
		new MenuItem(submenu, SWT.SEPARATOR);
		MenuItem item = new MenuItem (submenu, SWT.PUSH);
		item.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				app.quit();
			}
		});
		item.setText ("Quit");
		
		MenuItem settings = new MenuItem(bar,SWT.PUSH);
		settings.setText("Settings");
		
		MenuItem export = new MenuItem(bar,SWT.CASCADE);
		export.setText("Export");
		Menu subExport = new Menu(shell, SWT.DROP_DOWN);
		export.setMenu(subExport);
		MenuItem script = new MenuItem(subExport,SWT.CASCADE);
		script.setText("Script");
		final Menu subScript = new Menu(shell, SWT.DROP_DOWN);
		script.setMenu(subScript);
		MenuItem sh = new MenuItem(subScript, SWT.PUSH);
		sh.setText("sh");
		MenuItem perl = new MenuItem(subScript, SWT.PUSH);
		perl.setText("Perl");
		MenuItem python = new MenuItem(subScript, SWT.PUSH);
		python.setText("Python");
		MenuItem toDevice = new MenuItem(subExport,SWT.PUSH);
		toDevice.setText("to hacklace (via serial)");
		toDevice.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				app.serialize(getModus());
			}
		});
		
		MenuItem help = new MenuItem(bar, SWT.PUSH);
		help.setText("?");
		
	}
	
	private void initializeModusByte() {

		Composite container = new Composite(shell, SWT.BORDER);
		container.setLayout(new GridLayout());
		container.setLayoutData(GUI.getCenterLayout());
		
		Composite composite = new Composite(container, SWT.NULL);
		composite.setLayout(new RowLayout());

		Label speed = new Label(composite, SWT.VERTICAL);
		speed.setText("Scroll speed: ");
		Button speedSlow = new Button(composite, SWT.RADIO);
		speedSlow.setText("slow");
		Button speedMedium = new Button(composite, SWT.RADIO);
		speedMedium.setText("medium");
		Button speedFast = new Button(composite, SWT.RADIO);
		speedFast.setText("fast");

		composite = new Composite(container, SWT.NULL);
		composite.setLayout(new RowLayout());

		Label pause = new Label(composite, SWT.VERTICAL);
		pause.setText("Animation pause: ");
		Button pauseShort = new Button(composite, SWT.RADIO);
		pauseShort.setText("short");
		Button pauseMedium = new Button(composite, SWT.RADIO);
		pauseMedium.setText("medium");
		Button pauseLong = new Button(composite, SWT.RADIO);
		pauseLong.setText("long");

		composite = new Composite(container, SWT.NULL);
		composite.setLayout(new RowLayout());
		Label lblScrollback = new Label(composite, SWT.VERTICAL);
		lblScrollback.setText("Animation ");
		Button scrollback = new Button(composite, SWT.CHECK);
		scrollback.setText("scrollback");
		
		this.modusByte = new ModusByteGUIContainer(speedSlow, speedMedium, speedFast, pauseShort, pauseMedium, pauseLong, scrollback);
	}
	
	public static GridData getCenterLayout() {
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		layoutData.verticalAlignment = SWT.CENTER;
		layoutData.horizontalAlignment = SWT.CENTER;
		layoutData.grabExcessHorizontalSpace = true;
		return layoutData;
	}
	
	public short getModus() {
		
		short modus = 0;
		
		modus += this.modusByte.getPauseValue();
		modus += this.modusByte.getSpeedValue();
		modus += this.modusByte.getScrollbackValue();
		
		//add the moment always animations ;-)
		modus += ModusOptions.TYPE_ANIMATION;
		
		return modus;
	}
	
	

	
}

package mvc;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ZoneController {
	
	public final int DEFAULT_MODE = 0, SEARCH_MODE = 1;
	
	public ZoneController() {
		currentMode = DEFAULT_MODE;
		view = new ZoneView();
		model = new ZoneModel(view);
		view.addHeaderMouseListener(new TableHeaderListener());
		view.addSearchListener(new SearchListener());
		view.addKeyboardListener(new KeyboardListener());
		view.addAllListener(new AllListener());
	}
	
	private ZoneModel model;
	private ZoneView view;
	private int currentMode;
	
	class TableHeaderListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent event) {
			Point clickedPoint = event.getPoint();
			int column = view.getTable().columnAtPoint(clickedPoint);
			if(currentMode == DEFAULT_MODE) {
				model.updateZones(column);
			} else {
				model.searchZones(view.getSearchField().getText(), column);
			}
		}
	}
	
	class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			currentMode = SEARCH_MODE;
			model.searchZones(view.getSearchField().getText(), model.PLACE_SORT);
		}
	}
	
	class AllListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			currentMode = DEFAULT_MODE;
			model.updateZones(model.PLACE_SORT);
		}
	}
	
	class KeyboardListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				view.manualClickSearch();
			} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				view.manualClickAll();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}
		
		@Override
		public void keyTyped(KeyEvent e) {}
	}
}
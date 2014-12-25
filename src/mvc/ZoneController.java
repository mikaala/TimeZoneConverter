package mvc;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ZoneController {
	
	public ZoneController() {
		view = new ZoneView();
		model = new ZoneModel(view);
		view.addHeaderMouseListener(new TableHeaderListener());
		view.addSearchListener(new SearchListener());
		view.addKeyboardListener(new KeyboardListener());
		view.addAllListener(new AllListener());
	}
	
	private ZoneModel model;
	private ZoneView view;
	
	class TableHeaderListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent event) {
			Point clickedPoint = event.getPoint();
			int column = view.getTable().columnAtPoint(clickedPoint);
			model.updateZones(column);
		}
	}
	
	class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			model.searchZones(view.getSearchField().getText(), 0);
		}
	}
	
	class AllListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			model.updateZones(0);
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
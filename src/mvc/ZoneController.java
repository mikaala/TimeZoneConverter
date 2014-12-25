package mvc;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.JTextField;

import core.Time;

public class ZoneController {
	
	public ZoneController() {
		view = new ZoneView();
		model = new ZoneModel(view);
		view.addHeaderMouseListener(new TableHeaderListener(view.getTable()));
		view.addSearchListener(new SearchListener(view.getSearchField()));
	}
	
	private ZoneModel model;
	private ZoneView view;
	
	class TableHeaderListener extends MouseAdapter {
		
		public TableHeaderListener(JTable table) {
			this.table = table;
		}
		
		@Override
		public void mouseClicked(MouseEvent event) {
			Point clickedPoint = event.getPoint();
			int column = table.columnAtPoint(clickedPoint);
			model.updateZones(column);
		}
		
		private JTable table;
	}
	
	class SearchListener implements ActionListener {
		
		public SearchListener(JTextField searchField) {
			this.searchField = searchField;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			model.searchZones(searchField.getText(), 0);
		}
		
		private JTextField searchField;
	}
}
package mvc;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

public class ZoneController {
	
	public ZoneController() {
		view = new ZoneView();
		model = new ZoneModel(view);
		view.addHeaderMouseListener(new TableHeaderListener(view.getTable()));
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
}
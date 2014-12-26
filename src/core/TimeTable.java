package core;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

//An AbstractTableModel implementation with very little outside the necessities implemented
public class TimeTable extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	public static final int PLACE_INDEX = 0, TIME_INDEX = 1;
	
	public TimeTable(ArrayList<Time> timeData) {
		this.timeData = timeData;
		columnHeaders = new ArrayList<String>();
		columnHeaders.add(new String("Place"));
		columnHeaders.add(new String("Time"));
	}
	
	@Override
	public String getColumnName(int index) {
		return columnHeaders.get(index);
	}
	
	@Override
	public int getColumnCount() {
		return columnHeaders.size();
	}

	@Override
	public int getRowCount() {
		return timeData.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Time getTime = timeData.get(row);
		switch(column) {
		case PLACE_INDEX:
			return getTime.getPlace();
		case TIME_INDEX:
			return getTime.toString();
		}
		return null;
	}
	
	private ArrayList<String> columnHeaders;
	private ArrayList<Time> timeData;
}

package mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;
import java.util.TimeZone;

import core.Time;

public class ZoneModel extends Observable {
	
	public final int PLACE_SORT = 0, TIME_SORT = 1;
	
	public ZoneModel(Observer obs) {
		addObserver(obs);
		tableData = new ArrayList<Time>();
		updateZones(TIME_SORT);
	}
	
	public void updateZones(int sortMethod) {
		tableData.clear();
		for(String s : TimeZone.getAvailableIDs()) {
			tableData.add(new Time(s));
		}
		if(sortMethod == TIME_SORT) {
			Collections.sort(tableData);
		} else {
			Collections.sort(tableData, new Comparator<Time>() {

				@Override
				public int compare(Time t1, Time t2) {
					return t1.getPlace().compareTo(t2.getPlace());
				}
				
			});
		}
		setChanged();
		notifyObservers(tableData);
	}
	
	private ArrayList<Time> tableData;
}

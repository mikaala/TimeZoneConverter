package mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import java.util.TimeZone;

import core.Time;

public class ZoneModel extends Observable {
	public ZoneModel(Observer obs) {
		addObserver(obs);
		tableData = new ArrayList<Time>();
		updateZones();
	}
	
	public void updateZones() {
		for(String s : TimeZone.getAvailableIDs()) {
			tableData.add(new Time(s));
		}
		Collections.sort(tableData);
		setChanged();
		notifyObservers(tableData);
	}
	
	private ArrayList<Time> tableData;
}

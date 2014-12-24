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
		updateZones(PLACE_SORT);
	}
	
	public void updateZones(int sortMethod) {
		tableData.clear();
		for(String s : TimeZone.getAvailableIDs()) {
			if(s.contains("System")) {
				continue;
			}
			Time newTime = new Time(s);
			boolean duplicate = duplicateData(newTime);
			if(duplicate) {
				continue;
			}
			if(s.contains("PST")) {
				insertCustomTime(s, "America/San Francisco");
				insertCustomTime(s, "America/Las Vegas");
				insertCustomTime(s, "America/San Diego");
			} else if(s.contains("EST")) {
				insertCustomTime(s, "America/Washington DC");
				insertCustomTime(s, "America/Philadelphia");
				insertCustomTime(s, "America/Boston");
				insertCustomTime(s, "America/Atlanta");
			} else if(s.contains("CST")) {
				insertCustomTime(s, "America/Houston");
				insertCustomTime(s, "America/Dallas");
				insertCustomTime(s, "America/Austin");
			} else if(s.contains("Auckland")) {
				insertCustomTime(s, "Pacific/Wellington");
			} else if(s.contains("Hong_Kong")) {
				insertCustomTime(s, "Asia/Beijing/Peking");
				insertCustomTime(s, "Asia/China/CST/China Standard Time");
			}
			tableData.add(new Time(s));
		}
		if(sortMethod == TIME_SORT) {
			Collections.sort(tableData);
		} else {
			Collections.sort(tableData, new Comparator<Time>() {

				@Override
				public int compare(Time t1, Time t2) {
					return t1.getPlace().toLowerCase().compareTo(t2.getPlace().toLowerCase());
				}
				
			});
		}
		setChanged();
		notifyObservers(tableData);
	}
	
	public boolean duplicateData(Time newTime) {
		for(Time t : tableData) {
			if(newTime.equals(t)) {
				return true;
			}
		}
		return false;
	}
	
	public void insertCustomTime(String s, String name) {
		Time customTime = new Time(s, name);
		if(!duplicateData(customTime)) {
			tableData.add(customTime);
		}
	}
	
	public Time findTime(String searchTerm) {
		for(Time t : tableData) {
			if(t.getPlace().toLowerCase().contains(searchTerm.toLowerCase())) {
				return t;
			}
		}
		return null;
	}
	
	private ArrayList<Time> tableData;
}

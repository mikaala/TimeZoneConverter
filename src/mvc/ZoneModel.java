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
		searchData = new ArrayList<Time>();
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
			} else if(s.contains("CET")) {
				insertCustomTime(s, "Europe/Frankfurt");
				insertCustomTime(s, "Europe/Munich/Munchen");
				insertCustomTime(s, "Europe/Cologne/Koln");
				insertCustomTime(s, "Europe/Stuttgart");
				insertCustomTime(s, "Europe/Hamburg");
				insertCustomTime(s, "Europe/Marseille");
				insertCustomTime(s, "Europe/Lyon");
				insertCustomTime(s, "Europe/Toulouse");
				insertCustomTime(s, "Europe/Monaco");
				insertCustomTime(s, "Europe/Milan/Milano");
				insertCustomTime(s, "Europe/Venice/Venezia");
				insertCustomTime(s, "Europe/Naples/Napoli");
				insertCustomTime(s, "Europe/Turin/Torino");
				insertCustomTime(s, "Europe/Barcelona");
				insertCustomTime(s, "Europe/Valencia");
				insertCustomTime(s, "Europe/Krakow");
				insertCustomTime(s, "Europe/Bern");
				//Alternative (native) names for certain large cities
				insertCustomTime(s, "Europe/Praha");
				insertCustomTime(s, "Europe/Roma");
				insertCustomTime(s, "Europe/Wien");
				//Certain central countries in Europe
				insertCustomTime(s, "Germany");
				insertCustomTime(s, "Spain");
				insertCustomTime(s, "Italy");
				insertCustomTime(s, "Denmark");
				insertCustomTime(s, "France");
				insertCustomTime(s, "Netherlands/Holland");
				insertCustomTime(s, "Belgium");
				insertCustomTime(s, "Norway");
				insertCustomTime(s, "Sweden");
				insertCustomTime(s, "Czech Republic");
				insertCustomTime(s, "Slovakia");
				insertCustomTime(s, "Austria");
				insertCustomTime(s, "Hungary");
				insertCustomTime(s, "Slovenia");
				insertCustomTime(s, "Switzerland");
			} else if(s.contains("UTC")) {
				insertCustomTime(s, "Europe/Birmingham");
				insertCustomTime(s, "Europe/Leeds");
				insertCustomTime(s, "Europe/Glasgow");
				insertCustomTime(s, "Europe/Manchester");
				insertCustomTime(s, "Europe/Sheffield");
				insertCustomTime(s, "Europe/Liverpool");
				insertCustomTime(s, "Europe/Edinburgh");
			} else if(s.contains("Moscow")) {
				insertCustomTime(s, "Europe/MSK/Moscow Standard Time");
				insertCustomTime(s, "Europe/Saint Petersburg/St. Petersburg");
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
	
	public void searchZones(String searchTerm, int sortMethod) {
		searchData.clear();
		for(Time t : tableData) {
			if(t.getPlace().toLowerCase().contains(searchTerm.toLowerCase())) {
				searchData.add(t);
			}
		}
		if(sortMethod == TIME_SORT) {
			Collections.sort(searchData);
		} else {
			Collections.sort(searchData, new Comparator<Time>() {

				@Override
				public int compare(Time t1, Time t2) {
					return t1.getPlace().toLowerCase().compareTo(t2.getPlace().toLowerCase());
				}
				
			});
		}
		setChanged();
		notifyObservers(searchData);
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
	
	private ArrayList<Time> tableData;
	private ArrayList<Time> searchData;
}

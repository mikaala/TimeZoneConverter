package core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Time implements Comparable<Time> {
	public Time(String timeZoneID) {
		init(timeZoneID);
	}
	
	//Initializing a time with a time zone but using a name different from the default
	public Time(String timeZoneID, String customName) {
		customInit(timeZoneID, customName);
	}
	
	public void init(String timeZoneID) {
		TimeZone timeZone = TimeZone.getTimeZone(timeZoneID);
		Calendar calendar = Calendar.getInstance(timeZone);
		//24-hour clock and dd-mm-yyyy (most European countries use these)
		SimpleDateFormat format = new SimpleDateFormat("HH:mm dd-MM-yyyy");
		format.setTimeZone(timeZone);
		time = format.format(calendar.getTime());
		String tzPlace = timeZone.getID();
		//Miscellaneous fixes etc. to improve logic and readability when using the program
		if(tzPlace.contains("GMT+")) {
			tzPlace = tzPlace.replace("+", "-");
		} else if(tzPlace.contains("GMT-")) {
			tzPlace = tzPlace.replace("-", "+");
		} 
		if(tzPlace.contains("Etc/")) {
			tzPlace = tzPlace.substring(tzPlace.lastIndexOf("/") + 1);
		}
		if(tzPlace.contains("US")) {
			tzPlace = tzPlace.replace("US", "America/US");
		}
		if(tzPlace.contains("_")) {
			tzPlace = tzPlace.replace("_", " ");
		}
		place = tzPlace;
	}
	
	public void customInit(String timeZoneID, String customName) {
		TimeZone timeZone = TimeZone.getTimeZone(timeZoneID);
		Calendar calendar = Calendar.getInstance(timeZone);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm dd.MM.yyyy");
		format.setTimeZone(timeZone);
		time = format.format(calendar.getTime());
		place = customName;
	}
	
	public String toString() {
		return time;
	}
	
	public String getPlace() {
		return place;
	}
	
	//Compares time. Does not care about minutes as minute differences are very rare between time zones.
	@Override
	public int compareTo(Time otherTime) {
		
		String[] thisSplitTime = time.split(":");
		String[] otherSplitTime = otherTime.time.split(":");
		int thisHours = Integer.parseInt(thisSplitTime[0]);
		int otherHours = Integer.parseInt(otherSplitTime[0]);
		
		if(thisHours < otherHours) {
			return -1;
		} else if (thisHours > otherHours) {
			return 1;
		}
		return 0;
	}
	
	//Checks if the name (place) is the same
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Time)) {
			return false;
		}
		Time other = (Time)obj;
		if(place.equals(other.place)) {
			return true;
		}
		return false;
	}
	
	private String time, place;
}
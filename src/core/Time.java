package core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Time implements Comparable<Time> {
	public Time(String timeZoneID) {
		init(timeZoneID);
	}
	
	public Time(String timeZoneID, String customName) {
		customInit(timeZoneID, customName);
	}
	
	public void init(String timeZoneID) {
		TimeZone timeZone = TimeZone.getTimeZone(timeZoneID);
		Calendar calendar = Calendar.getInstance(timeZone);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm dd.MM.yyyy");
		format.setTimeZone(timeZone);
		time = format.format(calendar.getTime());
		String tzPlace = timeZone.getID();
		if(tzPlace.contains("GMT+")) {
			tzPlace = new String("GMT-" + tzPlace.substring(tzPlace.lastIndexOf("+") + 1));
		} else if(tzPlace.contains("GMT-")) {
			tzPlace = new String("GMT+" + tzPlace.substring(tzPlace.lastIndexOf("-") + 1));
		} else if(tzPlace.contains("Etc/")) {
			tzPlace = new String(tzPlace.substring(tzPlace.lastIndexOf("/") + 1));
		} else if(tzPlace.contains("US")) {
			tzPlace = new String(tzPlace.replace("US", "America/US"));
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
package core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.TimeZone;

public class Time implements Comparable<Time> {
	public Time(String timeZoneID) {
		init(timeZoneID);
	}
	
	public void init(String timeZoneID) {
		TimeZone timeZone = TimeZone.getTimeZone(timeZoneID);
		Calendar calendar = Calendar.getInstance(timeZone);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm dd.MM.yyyy");
		format.setTimeZone(timeZone);
		time = format.format(calendar.getTime());
		place = timeZone.getID();
	}
	
	public String toString() {
		return time;
	}
	
	public String getPlace() {
		return place;
	}
	
	public ArrayList<String> getAbbreviations() {
		return altAbbreviations;
	}
	
	@Override
	public int compareTo(Time otherTime) {
		
		String[] thisSplitTime = time.split(":");
		String[] otherSplitTime = time.split(":");
		int thisHours = Integer.parseInt(thisSplitTime[0]);
		int otherHours = Integer.parseInt(otherSplitTime[0]);
		
		if(thisHours < otherHours) {
			return 1;
		} else if (thisHours > otherHours) {
			return -1;
		}
		return 0;
	}
	
	private String time, place;
	private ArrayList<String> altAbbreviations;
}
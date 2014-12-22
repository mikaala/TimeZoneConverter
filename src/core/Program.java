package core;
import java.io.IOException;
import java.text.ParseException;
import java.util.TimeZone;

import mvc.ZoneController;

public class Program {
	
	public static void main(String[] args) throws IOException, ParseException {
		ZoneController control = new ZoneController();
		/*for(String s : TimeZone.getAvailableIDs()) {
			Time newTime = new Time(s);
			System.out.println(newTime + " " + newTime.getPlace());
		}*/
	}
}
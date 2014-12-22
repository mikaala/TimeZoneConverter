package mvc;

import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoneController {
	
	public ZoneController() {
		view = new ZoneView();
		model = new ZoneModel(view);
	}
	
	private ZoneModel model;
	private ZoneView view;
	
}

class CitySortListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
}

class TimeSortListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}

class AbbrSortListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
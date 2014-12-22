package mvc;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import core.Time;
import core.TimeTable;

public class ZoneView extends JFrame implements Observer {
	
	public ZoneView() {
		InitView();
	}
	public void InitView() {
		setTitle("Time Zone Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setVisible(true);
		table = new JTable();
		scroller = new JScrollPane();
		add(scroller);
	}
	
	@Override
	public void update(Observable observable, Object dataUpdate) {
		table.setModel(new TimeTable((ArrayList<Time>)dataUpdate));
	    table.setPreferredScrollableViewportSize(new Dimension(300, 80)); 
	    table.setFillsViewportHeight(true); 
		scroller.getViewport().add(table);
	}
	
	private JTable table;
	private JScrollPane scroller;
}
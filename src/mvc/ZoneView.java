package mvc;

import java.awt.Dimension;
import java.awt.event.MouseListener;
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
		table = new JTable();
		scroller = new JScrollPane();
		scroller.setPreferredSize(new Dimension(512, 512));
		add(scroller);
		pack();
		setVisible(true);
	}
	
	@Override
	public void update(Observable observable, Object dataUpdate) {
		
		table.setModel(new TimeTable((ArrayList<Time>)dataUpdate));

		scroller.setViewportView(table);
	    scroller.setColumnHeaderView(table.getTableHeader());
	}
	
	public void addHeaderMouseListener(MouseListener listener) {
		table.getTableHeader().addMouseListener(listener);
	}
	
	public JTable getTable() {
		return table;
	}
	
	private JTable table;
	private JScrollPane scroller;
}
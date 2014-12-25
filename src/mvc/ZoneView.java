package mvc;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import core.Time;
import core.TimeTable;

public class ZoneView extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	public static final int searchWidth = 144, searchHeight = 20, windowWidth = 720;
	
	public ZoneView() {
		InitView();
	}
	public void InitView() {
		setTitle("Time Zone Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(windowWidth, windowWidth + searchHeight);
		table = new JTable();
		scroller = new JScrollPane();
		scroller.setBounds(0, searchHeight, windowWidth, windowWidth);
		searchField = new JTextField();
		searchField.setBounds(0, 0, searchWidth, searchHeight);
		search = new JButton("SEARCH");
		search.setBounds(searchWidth, 0, searchWidth, searchHeight);
		all = new JButton("SHOW ALL");
		all.setBounds(searchWidth << 1, 0, (searchWidth * 3), searchHeight);
		add(searchField);
		add(search);
		add(all);
		add(scroller);
		//pack();
		setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable observable, Object dataUpdate) {
		
		table.setModel(new TimeTable((ArrayList<Time>)dataUpdate));

		scroller.setViewportView(table);
	    scroller.setColumnHeaderView(table.getTableHeader());
	}
	
	public void addHeaderMouseListener(MouseListener listener) {
		table.getTableHeader().addMouseListener(listener);
	}
	
	public void addSearchListener(ActionListener listener) {
		search.addActionListener(listener);
	}
	
	public void addAllListener(ActionListener listener) {
		all.addActionListener(listener);
	}
	
	public void addKeyboardListener(KeyListener listener) {
		searchField.addKeyListener(listener);
	}
	
	public JTable getTable() {
		return table;
	}
	
	public JTextField getSearchField() {
		return searchField;
	}
	
	public void manualClickSearch() {
		search.doClick();
	}
	
	public void manualClickAll() {
		all.doClick();
	}
	
	private JTable table;
	private JScrollPane scroller;
	private JTextField searchField;
	private JButton search, all;
}
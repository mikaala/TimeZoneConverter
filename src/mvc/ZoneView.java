package mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	
	public ZoneView() {
		InitView();
	}
	public void InitView() {
		setTitle("Time Zone Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		optionPanel = new JPanel(new FlowLayout());
		table = new JTable();
		scroller = new JScrollPane();
		scroller.setPreferredSize(new Dimension(512, 512));
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(256, 32));
		search = new JButton("SEARCH");
		all = new JButton("SHOW ALL");
		optionPanel.add(searchField);
		optionPanel.add(search);
		optionPanel.add(all);
		add(optionPanel, BorderLayout.NORTH);
		add(scroller, BorderLayout.CENTER);
		pack();
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
	private JPanel optionPanel;
	private JScrollPane scroller;
	private JTextField searchField;
	private JButton search, all;
}
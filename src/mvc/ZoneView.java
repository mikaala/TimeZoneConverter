package mvc;

import java.awt.event.ActionListener;
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
		answerField = new JTextField("ANSWER WILL APPEAR HERE");
		answerField.setBounds(searchWidth << 1, 0, (windowWidth - searchWidth << 1), searchHeight);
		answerField.setEditable(false);
		add(searchField);
		add(search);
		add(answerField);
		add(scroller);
		//pack();
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
	
	public void addSearchListener(ActionListener listener) {
		search.addActionListener(listener);
	}
	
	public JTable getTable() {
		return table;
	}
	
	public JTextField getSearchField() {
		return searchField;
	}
	
	public JTextField getAnswerField() {
		return answerField;
	}
	
	private JTable table;
	private JScrollPane scroller;
	private JTextField searchField, answerField;
	private JButton search;
}
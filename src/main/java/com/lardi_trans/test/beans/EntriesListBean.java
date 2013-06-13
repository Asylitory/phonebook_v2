package com.lardi_trans.test.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lardi_trans.test.classes.Entry;
import com.lardi_trans.test.interfaces.DBConnector;
import com.lardi_trans.test.logic.MySQL_JDBC_Database;

public class EntriesListBean implements Iterable<Entry> {
	private List<Entry> entriesList;
	private DBConnector dbConnector;
	
	private List<Entry> toDeleteList = new ArrayList<Entry>();
	
	public EntriesListBean() {}
	
	public void getEntries() throws SQLException {
		dbConnector = MySQL_JDBC_Database.getInstance();
		entriesList = dbConnector.getEntriesList();
	}
		
	public List<Entry> getEntriesList() {
		return entriesList;
	}
	
	public void setToDelete(Entry entry) {
		toDeleteList.add(entry);
	}
	
	public void deleteEntries() throws SQLException {
		dbConnector = MySQL_JDBC_Database.getInstance();
		for (Entry entry : toDeleteList) {
			dbConnector.deleteEntry(entry);
		}
	}
	
	public List<Entry> getDeletedEntries() {
		return toDeleteList;
	}
	
	@Override
	public Iterator<Entry> iterator() {
		return entriesList.iterator();
	}
}

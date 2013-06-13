package com.lardi_trans.test.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.lardi_trans.test.classes.Entry;

public interface DBConnector {
	public void addEntry(Entry entry) throws SQLException;
	public void editEntry(Entry entry) throws SQLException;
	public void deleteEntry(Entry entry) throws SQLException;
	public Entry getEntry(int entryId) throws SQLException;
	public List<Entry> getEntriesList() throws SQLException;
}

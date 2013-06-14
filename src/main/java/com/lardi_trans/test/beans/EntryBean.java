package com.lardi_trans.test.beans;

import java.sql.SQLException;
import java.util.List;

import com.lardi_trans.test.classes.Entry;
import com.lardi_trans.test.interfaces.DBConnector;
import com.lardi_trans.test.interfaces.Validator;
import com.lardi_trans.test.logic.EntryValidator;
import com.lardi_trans.test.logic.MySQL_JDBC_Database;

public class EntryBean {
	private Entry entry = null;
	
	private List<String> errors = null;
	private DBConnector dbConnector;
	
	public EntryBean() {}
	
	public void setEmptyEntry() {
		entry = new Entry();
	}
	
	public boolean isEntryExists(int entryId) throws SQLException {
		dbConnector = MySQL_JDBC_Database.getInstance();
		
		entry = dbConnector.getEntry(entryId);
		if (null == entry) {
			return false;
		} 
		return true;
	}
	
	public boolean saveEntry() throws SQLException {
		Validator validator = EntryValidator.getInstance();
		errors = validator.validate(entry);
		
		if (errors.size() > 0) {
			return false;
		}
		
		dbConnector = MySQL_JDBC_Database.getInstance();
		if (entry.getId() > 0) {
			dbConnector.editEntry(entry);
		} else {
			dbConnector.addEntry(entry);
		}
		return true;
	}

	public Entry getEntry() {
		return entry;
	}

	public List<String> getErrors() {
		return errors;
	}
}

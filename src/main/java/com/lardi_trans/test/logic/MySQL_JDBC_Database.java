package com.lardi_trans.test.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.lardi_trans.test.classes.Entry;
import com.lardi_trans.test.interfaces.DBConnector;

public class MySQL_JDBC_Database implements DBConnector {
	private static MySQL_JDBC_Database instance = null;
	private static Connection connection;
	
	private final static String PATH = "dbConnection.properties";
	private final static String dbPASSWORDKEY = "db.password";
	private final static String dbUSERNAMEKEY = "db.username";
	private final static String dbPATHKEY = "db.path";
	
	private MySQL_JDBC_Database() {}
	
	public static synchronized DBConnector getInstance() {
		if (null == instance) {
			try {
				Properties props = new Properties();
				FileInputStream fileInputStream = new FileInputStream(PATH);
				
				props.load(fileInputStream);
				
				String password = props.getProperty(dbPASSWORDKEY);
				String username = props.getProperty(dbUSERNAMEKEY);
				String dbPath = props.getProperty(dbPATHKEY);
				
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dbPath, username, password);
			} catch (IOException e) {
				System.err.println("Can't read file");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println("Can`t find MySQL JDBC driver");
				e.printStackTrace();
			} catch (SQLException e) {
				System.err.println("Can`t connect to database");
				e.printStackTrace();
			}
			
			instance = new MySQL_JDBC_Database();
		}
		return instance;
	}

	@Override
	public void addEntry(Entry entry) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(
					"INSERT INTO person " + 
					"(lastname, firstname, patronymic, " + 
					"phone_mobile, phone_home, address, email) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			stmt.setString(1, entry.getLastname());
			stmt.setString(2, entry.getFirstname());
			stmt.setString(3, entry.getPatronymic());
			stmt.setString(4, entry.getPhone_mobile());
			stmt.setString(5, entry.getPhone_home());
			stmt.setString(6, entry.getAddress());
			stmt.setString(7, entry.getEmail());
			
			stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	@Override
	public void editEntry(Entry entry) throws SQLException {
	PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(
					"UPDATE person SET " + 
					"lastname=?, firstname=?, patronymic=?, " + 
					"phone_mobile=?, phone_home=?, address=?, email=? " + 
					"WHERE id=?");
			
			stmt.setString(1, entry.getLastname());
			stmt.setString(2, entry.getFirstname());
			stmt.setString(3, entry.getPatronymic());
			stmt.setString(4, entry.getPhone_mobile());
			stmt.setString(5, entry.getPhone_home());
			stmt.setString(6, entry.getAddress());
			stmt.setString(7, entry.getEmail());
			stmt.setInt(8, entry.getId());
			
			stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}		
	}
	
	@Override
	public void deleteEntry(Entry entry) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(
					"DELETE FROM person " + 
					"WHERE id=?");
			stmt.setInt(1, entry.getId());
			
			stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	@Override
	public Entry getEntry(int entryId) throws SQLException {
		Entry entry = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.prepareStatement(
					"SELECT id, lastname, firstname, patronymic, " +
					"phone_mobile, phone_home, address, email " +
					"FROM person " +
					"WHERE id=?");
			stmt.setInt(1, entryId);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				entry = new Entry();
				
				entry.setId(rs.getInt(1));
				entry.setLastname(rs.getString(2));
				entry.setFirstname(rs.getString(3));
				entry.setPatronymic(rs.getString(4));
				entry.setPhone_mobile(rs.getString(5));
				entry.setPhone_home(rs.getString(6));
				entry.setAddress(rs.getString(7));
				entry.setEmail(rs.getString(8));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return entry;
	}

	@Override
	public List<Entry> getEntriesList() throws SQLException {
		List<Entry> entries = new ArrayList<Entry>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT id, lastname, firstname, patronymic, " +
					"phone_mobile, phone_home, address, email " +
					"FROM person");
			while (rs.next()) {
				Entry entry = new Entry();
				
				entry.setId(rs.getInt(1));
				entry.setLastname(new String(rs.getString(2)));
				entry.setFirstname(rs.getString(3));
				entry.setPatronymic(rs.getString(4));
				entry.setPhone_mobile(rs.getString(5));
				entry.setPhone_home(rs.getString(6));
				entry.setAddress(rs.getString(7));
				entry.setEmail(rs.getString(8));
				
				entries.add(entry);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return entries;
	}
}

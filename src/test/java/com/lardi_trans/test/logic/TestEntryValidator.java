package com.lardi_trans.test.logic;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lardi_trans.test.classes.Entry;
import com.lardi_trans.test.interfaces.Validator;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestEntryValidator {
	private Entry entry;
	private int expected;
	private Validator entryValidator;
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{new String[] {"Иванов","Иван","Иванович","+38(555)7557575","+38(0642)123123",
					"Украина, Луганск, ул. Оборонная 6","te-st@testmail.com"}, 0},
				{new String [] {"Иванов","Иван","Иванович","+38(555)7557575","",
					"Украина, Луганск, ул. Оборонная 6","te.st@testmail.com"}, 0},
				{new String [] {"И","Иван","Иванович","+38(555)7557575","+38(0642)123123",
						"Украина, Луганск, ул. Оборонная 6","test@testmail.com"}, 1},
				{new String [] {"Иванов","И","Иванович","+38(555)7557575","+38(0642)123123",
						"Украина, Луганск, ул. Оборонная 6","test@testmail.com"}, 1},
				{new String [] {"Иванов","Иван","И","+38(555)7557575","+38(0642)123123",
						"Украина, Луганск, ул. Оборонная 6","test@testmail.com"}, 1},
				{new String [] {"Иванов","Иван","Иванович","+385","+38(0642)123123",
						"Украина, Луганск, ул. Оборонная 6","test@testmail.com"}, 1},
				{new String [] {"Иванов","Иван","Иванович","+38(555)7557575","+383123",
						"Украина, Луганск, ул. Оборонная 6","test@testmail.com"}, 1},
				{new String [] {"Иванов","Иван","Иванович","+38(555)7557575","+38(0642)123123",
						"Украина, Луганск, ул. Оборонная 6","test"}, 1},
				{new String [] {"И","И","Иванович","+38(555)7557575","+38(0642)123123",
						"Украина, Луганск, ул. Оборонная 6","test@testmail.com"}, 2},
				{new String [] {"И","И","И","+38(555)7557575","+38(0642)123123",
						"Украина, Луганск, ул. Оборонная 6","test@testmail.com"}, 3},
				{new String [] {"И","И","И","+38","+38(0642)123123",
						"Украина, Луганск, ул. Оборонная 6","test@testmail.com"}, 4},
				{new String [] {"И","И","И","+38","+38",
						"Украина, Луганск, ул. Оборонная 6","test@testmail.com"}, 5},
				{new String [] {"И","И","И","+38","3",
						"Украина, Луганск, ул. Оборонная 6","test"}, 6}
		});
		}
	
	public TestEntryValidator(String[] params, int expected) {
		entry = new Entry();
		
		entry.setLastname(params[0].toString());
		entry.setFirstname(params[1].toString());
		entry.setPatronymic(params[2].toString());
		entry.setPhone_mobile(params[3].toString());
		entry.setPhone_home(params[4].toString());
		entry.setAddress(params[5].toString());
		entry.setEmail(params[6].toString());
		
		this.expected = expected;
		entryValidator = EntryValidator.getInstance();
	}
	
	@Test
	public void TestValidation() {
		assertEquals(entryValidator.validate(entry).size(), expected);
	}
}
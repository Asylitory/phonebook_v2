package com.lardi_trans.test.interfaces;

import java.util.List;

import com.lardi_trans.test.classes.Entry;

public interface Validator {
	public List<String> validate(Entry entry);
}

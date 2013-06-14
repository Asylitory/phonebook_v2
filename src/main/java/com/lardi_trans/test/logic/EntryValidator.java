package com.lardi_trans.test.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lardi_trans.test.classes.Entry;
import com.lardi_trans.test.interfaces.Validator;

public class EntryValidator implements Validator {
	private static EntryValidator instance = null;
	
	private EntryValidator() {}
	
	public static synchronized Validator getInstance() {
		if(null == instance) {
			instance = new EntryValidator();
		}
		return instance;
	}
	
	@Override
	public List<String> validate(Entry entry) {
		List<String> errors = new ArrayList<String>();
		Pattern pattern;
		Matcher matcher;
		
		if (entry.getLastname().length() > 50) {
			errors.add("Фамилия не может содержать более 50 символов");
		}
		pattern = Pattern.compile("[а-яА-Я]{2,}+");
		matcher = pattern.matcher(entry.getLastname());
		if (!matcher.matches()) {
			errors.add("Фамилия должна быть указана на русском языке");
		}
		if (entry.getFirstname().length() > 30) {
			errors.add("Имя не может содержать более 30 символов");
		}
		matcher = pattern.matcher(entry.getFirstname());
		if (!matcher.matches()) {
			errors.add("Имя должно быть указано на русском языке");
		}
		if (entry.getPatronymic().length() > 30) {
			errors.add("Отчество не может быть длиннее 30 символов");
		}
		matcher = pattern.matcher(entry.getPatronymic());
		if (!matcher.matches()) {
			errors.add("Отчество должно быть указано на русском языке");
		}
		pattern = Pattern.compile("\\+38\\([0-9]{1,3}\\)[0-9]{3,7}");
		matcher = pattern.matcher(entry.getPhone_mobile());
		if (!matcher.matches()) {
			errors.add("Мобильный номер должен соответствовать формату '+38(yyy)xxxxxxx");
		}
		if (entry.getPhone_home().length() != 0) {
			pattern = Pattern.compile("\\+38\\([0-9]{1,4}\\)[0-9]{3,7}");
			matcher = pattern.matcher(entry.getPhone_home());
			if (!matcher.matches()) {
				errors.add("Домашний номер должен соответствовать формату '+38(yyyy)xxxxxxx");
			}
		}
		if (entry.getAddress().length() > 100) {
			errors.add("Длина адреса не может превышать 100 символов");
		}
		if (entry.getEmail().length() > 30) {
			errors.add("Длина e-mail не может превышать 30 символов");
		}
		pattern = Pattern.compile("\\w+([-.\\w]+\\w+){0,4}@\\w+.[\\w]{1,3}");
		matcher = pattern.matcher(entry.getEmail());
		if (entry.getEmail().length() != 0 && !matcher.matches()) {
			errors.add("Неверный формат e-mail");
		}
		return errors;
	}
}

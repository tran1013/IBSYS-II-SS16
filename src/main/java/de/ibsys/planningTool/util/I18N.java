package de.ibsys.planningTool.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18N {
	public static final String FORECAST = "FORECAST";
	public static final String DISPOSITION = "DISPOSITION";
	public static final String WELCOME = "WELCOME";
	public static final String CHANGE_LANGUAGE_BUTTON = "CHANGE_LANGUAGE_BUTTON";
	public static final String Export_TAB = "EXPORT_TAB";
	public static final String SELLING = "SELLING";
	public static final String PRODUCTION_PLAN = "PRODUCTION_PLAN";
	public static final String DIRECT_SELLS = "DIRECT_SELLS";
	public static final String PRICE = "PRICE";
	public static final String AMOUNT = "AMOUNT";
	public static final String PUNISHMENT = "PUNISHMENT";
	public static final String SAVE = "SAVE";
	public static final String XML_INPUT = "XML_INPUT";
	public static final String XML_EXPORT = "XML_EXPORT";
	public static final String CAPPLA = "CAPPLA";
	public static final String WORKPLACE = "WORKPLACE";
	public static final String CAPACITY = "CAPACITY";
	public static final String SHIFTS = "SHIFTS";
	public static final String OVERTIME = "OVERTIME";
	public static final String YAXIS = "YAXIS";

	public static ResourceBundle translation(String language, String country) {
		Locale currentLocale;
		ResourceBundle messages;

		currentLocale = new Locale(language, country);

		messages = ResourceBundle.getBundle("I18N/MessagesBundle", currentLocale);

		return messages;
	}
}

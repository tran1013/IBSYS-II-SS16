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

	public static ResourceBundle translation(String language, String country) {
		Locale currentLocale;
		ResourceBundle messages;

		currentLocale = new Locale(language, country);

		messages = ResourceBundle.getBundle("I18N/MessagesBundle", currentLocale);

		return messages;
	}
}

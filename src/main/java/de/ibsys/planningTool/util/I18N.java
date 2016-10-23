package de.ibsys.planningTool.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18N {
    public static final String FORECAST = "FORECAST";
    public static final String DISPOSITION = "DISPOSITION";
    public static final String PRIORITY = "PRIORITY";
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
    public static final String CAPPLA = "CAPPLA";
    public static final String WORKPLACE = "WORKPLACE";
    public static final String CAPACITY = "CAPACITY";
    public static final String SHIFTS = "SHIFTS";
    public static final String OVERTIME = "OVERTIME";
    public static final String YAXIS = "YAXIS";
    public static final String LABEL01 = "LABEL01";
    public static final String LABEL02 = "LABEL02";
    public static final String LABEL03 = "LABEL03";
    public static final String LABEL04 = "LABEL04";
    public static final String LABEL05 = "LABEL05";
    public static final String XML_INPUT_ERROR_NO_XML = "XML_INPUT_ERROR_NO_XML";
    public static final String XML_INPUT_FAILURE_PARSING = "XML_INPUT_FAILURE_PARSING";
    public static final String XML_INPUT_ERROR_WRONG_XML_STRUCTURE = "XML_INPUT_ERROR_WRONG_XML_STRUCTURE";
    public static final String FORECAST_INPUT_ERROR = "FORECAST_INPUT_ERROR";
    public static final String XML_EXPORT_SAVED_TITLE = "XML_EXPORT_SAVED_TITLE";
    public static final String SPLIT_PRIORITY_DIALOG_TITLE = "SPLIT_PRIORITY_DIALOG_TITLE";
    public static final String SPLIT_PRIORITY_DIALOG_MESSAGE = "SPLIT_PRIORITY_DIALOG_MESSAGE";
    public static final String SPLIT_PRIORITY_DIALOG_CONTENT = "SPLIT_PRIORITY_DIALOG_CONTENT";
    public static final String DIALOG_MESSAGE_ERROR_WHOLE_NUMBER = "DIALOG_MESSAGE_ERROR_WHOLE_NUMBER";
    public static final String DIALOG_MESSAGE_ERROR_BIG_NUMBER = "DIALOG_MESSAGE_ERROR_BIG_NUMBER";
    public static final String DIALOG_MESSAGE_ERROR_ONLY_NUMBER = "DIALOG_MESSAGE_ERROR_ONLY_NUMBER";
    public static final String DIALOG_MESSAGE_ERROR_TITLE = "DIALOG_MESSAGE_ERROR_TITLE";
    public static final String DIALOG_MESSAGE_ERROR_MESSAGE = "DIALOG_MESSAGE_ERROR_MESSAGE";
    public static final String LANGUAGE = "LANGUAGE";
    public static final String OK = "OK";
    public static final String CANCEL = "CANCEL";
    public static final String DIGIT_SMALLER_ZERO = "DIGIT_SMALLER_ZERO";
    public static final String ORDER = "ORDER";
    public static final String AVGLABEL = "AVGLABEL";
    public static final String STOCKLABEL = "STOCKLABEL";
    public static final String OPTIONLABEL = "OPTIONLABEL";
    public static final String DISCONTLABEL = "DISCONTLABEL";
    public static final String QUANTITYLABEL = "QUANTITYLABEL";
    public static final String NRCOLUMN = "NRCOLUMN";
    public static final String NEWBTN = "NEWBTN";
    public static final String CHANGEBTN = "CHANGEBTN";
    public static final String DELETEBTN = "DELETEBTN";
    public static final String QUANTITYL = "QUANTITYL";
    public static final String STOCKL = "STOCKL";
    public static final String AVGL = "AVGL";
    public static final String ALERT_TITLE_ERROR_NEW_ORDER = "ALERT_TITLE_ERROR_NEW_ORDER";
    public static final String ALERT_HEAD_NEW_ORDER = "ALERT_HEAD_NEW_ORDER";
    public static final String ALERT_TEXT_NEW_ORDER = "ALERT_TEXT_NEW_ORDER";
    public static final String ORDERS = "ORDERS";
    public static final String QUEQUE = "QUEQUE";
    public static final String IN_PROCESS = "IN_PROCESS";
    public static final String PRODUCTIONS_ORDERS = "PRODUCTION_ORDER";
    public static final String SAFETYSTOCK = "SAFETYSTOCK";
    public static final String CHILD_BIKE = "CHILD_BIKE";
    public static final String WOMAN_BIKE = "WOMAN_BIKE";
    public static final String MEN_BIKE = "MEN_BIKE";
    public static final String CALC_N_SAVE = "CALC_N_SAVE";
    public static final String PRIORITY_TIP = "PRIORITY_TIP";
    public static final String CAP_WARNING = "CAP_WARNING";

    public static ResourceBundle translation(String language, String country) {
        Locale currentLocale;
        ResourceBundle messages;

        currentLocale = new Locale(language, country);

        messages = ResourceBundle.getBundle("I18N/MessagesBundle", currentLocale);

        return messages;
    }
}

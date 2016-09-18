package de.ibsys.planningTool.model;

/**
 * Created by minhnguyen on 13.07.16.
 */
public class Constants {

    //Warehousestocks
    public static final String TOTALSTOCKVALUE = "totalstockvalue";
    public static final String ID = "id";
    public static final String AMOUNT = "amount";
    public static final String PCT = "pct";
    public static final String PRICE = "price";
    public static final String STOCKVALUE = "stockvalue";

    //FUTUREINWARDSTOCKMOVEMENT
    public static final String ARTICLE = "article";
    public static final String MODE = "mode";
    public static final String ORDER_PERIODE = "orderperiod";

    //OrderInWorkplace
    public static final String ORDER = "order";
    public static final String ITEM_ID = "item";
    public static final String TIME_NEED = "timeneed";
    public static final String PERIOD = "period";

    //WAITINGLIST

    //DELIVER OPTION
    public static final int FAST_DELIVERY = 5;
    public static final int NORMAL_DELIVERY = 4;

    //ORDER
    public  static final double REPLACEMENT_TIME = 1.0;

    //XML EXPORT ELEMENT VAR
    public static final String SELLWISH = "sellwish";
    public static final String SELLDIRECT = "selldirect";
    public static final String ORDER_LIST = "orderlist";
    public static final String PRODUCTION_LIST = "productionlist";
    public static final String PRODUCTION = "production";
    public static final String WORKTIME_LIST = "worktimelist";

    //XML EXPORT ATTRIBUTE VAR
    public static final String QUANTITY = "quantity";
    public static final String PENALTY = "penalty";
    public static final String MODUS = "modus";
    public static final String WORKINGTIME = "workingtime";
    public static final String WORK_STATION = "station";
    public static final String SHIFT = "shift";
    public static final String OVER_TIME = "overtime";

    //DATABASEPATH
    public static final String DATABASEPATH = "jdbc:sqlite:src/main/resources/database/Database.db";

    //WOKRPLACE CAPACITY
    public static final int WORKPLACE_CAPACITY = 2400;




}

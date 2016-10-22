package de.ibsys.planningTool.model;

import de.ibsys.planningTool.model.xmlInputModel.*;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

import static de.ibsys.planningTool.model.Constants.*;

/**
 * XML Parser because of yolo !!
 * Created by minhnguyen on 12.07.16.
 */
public class XmlInputData {

    private Logger logger = Logger.getLogger(XmlInputData.class);

    // The reason to use map and hashmaps are that it increase the performace
    // by quering the right articleID
    private Map<String, Article> wareHouseArticles = new HashMap<>();
    private Map<String, FutureInComingOrder> futureInComingOrderMap = new HashMap<>();
    private Map<String, WaitingListMissingParts> stringWaitingListMissingPartsMap = new HashMap<>();

    //Search by the Workplace ID
    private Map<String, OrdersInWork> ordersInWorkMap = new HashMap<>();
    private Map<String, WaitingListWorkPlace> waitingListWorkPlaceMap = new HashMap<>();

    private int period;

    public boolean parseXML(File file) throws IOException, ParserConfigurationException {
    	
    	boolean parseFine = false;

        Document document;
        try {
            document = new SAXBuilder().build(file);
        } catch (JDOMException e) {
            logger.error(e);
            throw new IOException("Something went wrong during parsing!");
        }


        Element root = document.getRootElement();
        Element wareHouseStock = root.getChild("warehousestock");
        Element futureInwardStockMovement = root.getChild("futureinwardstockmovement");
        Element ordersInWork = root.getChild("ordersinwork");
        Element waitingListWorkStations = root.getChild("waitinglistworkstations");
        Element waitingListStock = root.getChild("waitingliststock");

        //TODO Import this things for later versions
        //like dashboard data
        //incoming stuff are this period so shit happens
//        Element inwardStockMovement = root.getChild("inwardstockmovement");
//        Element cycleTimes = root.getChild("cycletimes");
//        Element completedOrders = root.getChild("completedorders");
//        Element idleTimeCosts = root.getChild("idletimecosts");

        extractPeriod(root);
        try {
            extractWarehouseStockArticles(wareHouseStock);
            extractFutureInWardMovements(futureInwardStockMovement);
            extractOrdersInWorkFormWorkplaces(ordersInWork);
            extractWaitingListWorkStations(waitingListWorkStations);
            extractMissingParts(waitingListStock);
            parseFine = true;
        } catch (ParseException e) {
            logger.info(e);
        }
        return parseFine;
    }

    /**
     * @param file
     * @return boolean if xml file contains the root element results
     * @throws IOException
     */
    public boolean checkXMLFile(File file) throws IOException {
        boolean isDocumentOK = true;
        Document document;
        try {
            document = new SAXBuilder().build(file);
        } catch (JDOMException e) {
            logger.info(e);
            throw new IOException("Incorrect Xml File");
        }
        Element root = document.getRootElement();
        if (!root.getName().equals("results")) {
            isDocumentOK = false;
        }
        return isDocumentOK;
    }

    private void extractPeriod(Element result) {
        period = Integer.parseInt(result.getAttributeValue("period"));
    }

    /***
     *
     * @param wareHouseStock
     * @throws ParseException
     */
    private void extractWarehouseStockArticles(Element wareHouseStock) throws ParseException {

        for (int i = 0; i < wareHouseStock.getChildren().size(); i++) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);

            if (getFirstChildElement(wareHouseStock, i).getName().equals(TOTALSTOCKVALUE)) {
                continue;
            }
            String id = getFirstChildElement(wareHouseStock, i).getAttribute(ID).getValue();
            int amount = Integer.parseInt(getFirstChildElement(wareHouseStock, i).getAttribute(AMOUNT).getValue());
            double pct = numberFormat.parse(getFirstChildElement(wareHouseStock, i).getAttribute(PCT).getValue()).doubleValue();
            double price = numberFormat.parse(getFirstChildElement(wareHouseStock, i).getAttribute(PRICE).getValue()).doubleValue();
            double stockValue = numberFormat.parse(getFirstChildElement(wareHouseStock, i).getAttribute(STOCKVALUE).getValue()).doubleValue();
            // set default reserve
            int reserve = 50;
            switch (id) {
                case "16":
                case "17":
                case "26":
                    reserve = reserve * 3;
                    break;
            }
            wareHouseArticles.put(id, new Article(Integer.parseInt(id), amount, reserve, pct, price, stockValue));
        }
    }

    /**
     * eingehende bestellungen
     *
     * @param futureInWardMovements
     */
    private void extractFutureInWardMovements(Element futureInWardMovements) {
        for (int i = 0; i < futureInWardMovements.getChildren().size(); i++) {
            String id = getFirstChildElement(futureInWardMovements, i).getAttribute(ID).getValue();
            String articleId = getFirstChildElement(futureInWardMovements, i).getAttribute(ARTICLE).getValue();
            int mode = Integer.parseInt(getFirstChildElement(futureInWardMovements, i).getAttribute(MODE).getValue());
            int orderPeriod = Integer.parseInt(getFirstChildElement(futureInWardMovements, i).getAttribute(ORDER_PERIODE).getValue());
            int amount = Integer.parseInt(getFirstChildElement(futureInWardMovements, i).getAttribute(AMOUNT).getValue());

            FutureInComingOrder futureInComingOrder = new FutureInComingOrder(id, articleId, mode, orderPeriod, amount);
            futureInComingOrderMap.put(articleId, futureInComingOrder);
        }
    }

    /**
     * auftraege in bearbeitung auf der maschine
     *
     * @param orderInWork
     */
    private void extractOrdersInWorkFormWorkplaces(Element orderInWork) {
        for (int i = 0; i < orderInWork.getChildren().size(); i++) {

            String workplaceId = getFirstChildElement(orderInWork, i).getAttribute(ID).getValue();
            String articleId = getFirstChildElement(orderInWork, i).getAttribute(ITEM_ID).getValue();
            int amount = Integer.parseInt(getFirstChildElement(orderInWork, i).getAttribute(AMOUNT).getValue());
            int timeNeed = Integer.parseInt(getFirstChildElement(orderInWork, i).getAttribute(TIME_NEED).getValue());
            //optional parameters
            int period = Integer.parseInt(getFirstChildElement(orderInWork, i).getAttribute(PERIOD).getValue());
            int order = Integer.parseInt(getFirstChildElement(orderInWork, i).getAttribute(ORDER).getValue());

            ordersInWorkMap.put(workplaceId, new OrdersInWork(workplaceId, articleId, period, order, amount, timeNeed));
        }
    }

    /**
     * warteschlangen
     *
     * @param waitingListWorkStations
     */
    private void extractWaitingListWorkStations(Element waitingListWorkStations) {
        for (int index = 0; index < waitingListWorkStations.getChildren().size(); index++) {
            List<WaitingList> waitingLists = new ArrayList<>();
            String workplaceId = getFirstChildElement(waitingListWorkStations, index).getAttribute(ID).getValue();
            int timeNeed = Integer.parseInt(getFirstChildElement(waitingListWorkStations, index).getAttribute(TIME_NEED).getValue());

            if (getFirstChildElement(waitingListWorkStations, index).getChildren() != null) {
                for (int secondIndex = 0; secondIndex < waitingListWorkStations.getChildren().get(index).getChildren().size(); secondIndex++) {
                    String articleId = getFirstChildElement(waitingListWorkStations, index).getChildren().get(secondIndex).getAttribute(ITEM_ID).getValue();
                    Integer amount = Integer.parseInt(getFirstChildElement(waitingListWorkStations, index).getChildren().get(secondIndex).getAttribute(AMOUNT).getValue());
                    Integer timeNeedWaitingList = Integer.parseInt(getFirstChildElement(waitingListWorkStations, index).getChildren().get(secondIndex).getAttribute(TIME_NEED).getValue());
                    waitingLists.add(new WaitingList(articleId, amount, timeNeedWaitingList));
                }
            }
            waitingListWorkPlaceMap.put(workplaceId, new WaitingListWorkPlace(workplaceId, timeNeed, waitingLists));
        }
    }

    /**
     * @param waitingListStock
     */
    private void extractMissingParts(Element waitingListStock) {
        for (int index = 0; index < waitingListStock.getChildren().size(); index++) {
            List<WaitingList> waitingLists = new ArrayList<>();
            String id = getFirstChildElement(waitingListStock, index).getAttribute(ID).getValue();

            if (waitingListStock.getChildren().get(index).getChildren() != null) {
                for (int secondIndex = 0; secondIndex < getFirstChildElement(waitingListStock, index).getChildren().size(); secondIndex++) {
                    int amount = Integer.parseInt(getFirstChildElement(waitingListStock, index).getChildren().get(secondIndex).getAttribute(AMOUNT).getValue());
                    String itemId = getFirstChildElement(waitingListStock, index).getChildren().get(secondIndex).getAttribute(ITEM_ID).getValue();
                    //Zero because we must wait that the maschines finish the parts
                    waitingLists.add(new WaitingList(itemId, amount, 0));
                }
            }
            stringWaitingListMissingPartsMap.put(id, new WaitingListMissingParts(id, waitingLists));
        }
    }

    private Element getFirstChildElement(Element element, int index) {
        return element.getChildren().get(index);
    }

    public Map<String, Article> getWareHouseArticles() {
        return wareHouseArticles;
    }

    public Map<String, FutureInComingOrder> getFutureInComingOrderMap() {
        return futureInComingOrderMap;
    }

    public Map<String, WaitingListMissingParts> getStringWaitingListMissingPartsMap() {
        return stringWaitingListMissingPartsMap;
    }

    public Map<String, OrdersInWork> getOrdersInWorkMap() {
        return ordersInWorkMap;
    }

    public Map<String, WaitingListWorkPlace> getWaitingListWorkPlaceMap() {
        return waitingListWorkPlaceMap;
    }

    public int getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "XmlInputData{" +
                "wareHouseArticles=" + wareHouseArticles +
                ", futureInComingOrderMap=" + futureInComingOrderMap +
                ", stringWaitingListMissingPartsMap=" + stringWaitingListMissingPartsMap +
                ", ordersInWorkMap=" + ordersInWorkMap +
                ", waitingListWorkPlaceMap=" + waitingListWorkPlaceMap +
                ", period=" + period +
                '}';
    }
}

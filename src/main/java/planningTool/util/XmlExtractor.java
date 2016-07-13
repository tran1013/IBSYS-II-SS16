package planningTool.util;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import planningTool.model.Article;
import planningTool.model.FutureInComingOrder;
import planningTool.model.OrdersInWork;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

import static planningTool.model.Constants.*;

/**
 * XML Parser
 * TODO Maybe Store the Data in a Database
 * Created by minhnguyen on 12.07.16.
 */
public class XmlExtractor {

    // The reason to use map and hashmaps are that it increase the performace
    // by quering the right articleID
    private Map<String, Article> wareHouseArticles = new HashMap<>();
    private Map<String, FutureInComingOrder> futureInComingOrderMap = new HashMap<>();

    //Search by the Workplace ID
    private Map<String, OrdersInWork> ordersInWorkMap = new HashMap<>();

    private int periode;

    public void parseXML(File file) throws IOException, ParserConfigurationException {

        Document document;
        try {
            document = new SAXBuilder().build(file);
        } catch (JDOMException e) {
            throw new IOException("Something went wrong during parsing!");
        }


        Element root = document.getRootElement();
        Element wareHouseStock = root.getChild("warehousestock");
        Element futureInwardStockMovement = root.getChild("futureinwardstockmovement");
        Element ordersInWork = root.getChild("ordersinwork");
        Element waitingListStock = root.getChild("waitingliststock");
        Element waitingListWorkStations = root.getChild("waitinglistworkstations");

        //TODO Import this things for later versions
        //like dashboard data
        //incoming stuff are this periode so shit happens
//        Element inwardStockMovement = root.getChild("inwardstockmovement");
//        Element cycleTimes = root.getChild("cycletimes");
//        Element completedOrders = root.getChild("completedorders");
//        Element idleTimeCosts = root.getChild("idletimecosts");
        extractPeriod(root);
        try {
            extractWarehouseStockArticles(wareHouseStock);
            extractFutureInWardMovements(futureInwardStockMovement);
            extractOrdersInWorkFormWorkplaces(ordersInWork);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param file
     * @return boolean if xml file contains the root element results
     * @throws IOException
     */
    public boolean checkXMLFile(File file ) throws IOException{
        boolean isDocumentOK = true;
        Document document;
        try {
            document = new SAXBuilder().build(file);
        } catch (JDOMException e) {
            throw new IOException("Incorrect Xml File");
        }
        Element root = document.getRootElement();
        if(!root.getName().equals("results")) {
            isDocumentOK = false;
        }
        return isDocumentOK;
    }

    private void extractPeriod(Element result) {
        periode = Integer.valueOf(result.getAttributeValue("period"));
    }

    /***
     *
     * @param wareHouseStock
     * @throws ParseException
     */
    private void extractWarehouseStockArticles(Element wareHouseStock) throws ParseException {

        for (int i = 0; i < wareHouseStock.getChildren().size(); i++) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);

            if (wareHouseStock.getChildren().get(i).getName().equals(TOTALSTOCKVALUE)) {
                continue;
            }
            String id = wareHouseStock.getChildren().get(i).getAttribute(ID).getValue();
            int amount = Integer.valueOf(wareHouseStock.getChildren().get(i).getAttribute(AMOUNT).getValue());
            double pct = numberFormat.parse(wareHouseStock.getChildren().get(i).getAttribute(PCT).getValue()).doubleValue();
            double price = numberFormat.parse(wareHouseStock.getChildren().get(i).getAttribute(PRICE).getValue()).doubleValue();
            double stockValue = numberFormat.parse(wareHouseStock.getChildren().get(i).getAttribute(STOCKVALUE).getValue()).doubleValue();
            int reserve = 50;
            switch (id) {
                case "16":
                case "17":
                case "26":
                    reserve = reserve * 3;
                    break;
            }
            Article article = new Article(Integer.valueOf(id), amount, reserve, pct, price, stockValue);
            wareHouseArticles.put(id, article);
        }
    }

    /**
     * eingehende bestellungen
     * @param futureInWardMovements
     */
    private void extractFutureInWardMovements(Element futureInWardMovements) {
        for(int i = 0; i < futureInWardMovements.getChildren().size(); i++) {
            String id = futureInWardMovements.getChildren().get(i).getAttribute(ID).getValue();
            String articleId = futureInWardMovements.getChildren().get(i).getAttribute(ARTICLE).getValue();
            int mode = Integer.valueOf(futureInWardMovements.getChildren().get(i).getAttribute(MODE).getValue());
            int orderPeriod = Integer.valueOf(futureInWardMovements.getChildren().get(i).getAttribute(ORDER_PERIODE).getValue());
            int amount = Integer.valueOf(futureInWardMovements.getChildren().get(i).getAttribute(AMOUNT).getValue());

            FutureInComingOrder futureInComingOrder = new FutureInComingOrder(id, articleId, mode, orderPeriod, amount);
            futureInComingOrderMap.put(articleId, futureInComingOrder);
        }
    }

    /**
     * auftraege in bearbeitung auf der maschine
     * @param orderInWork
     */
    private void extractOrdersInWorkFormWorkplaces(Element orderInWork) {
        for (int i = 0; i < orderInWork.getChildren().size(); i++) {

            String workplaceId = orderInWork.getChildren().get(i).getAttribute(ID).getValue();
            String articleId = orderInWork.getChildren().get(i).getAttribute(ITEM_ID).getValue();
            int amount = Integer.valueOf(orderInWork.getChildren().get(i).getAttribute(AMOUNT).getValue());
            int timeNeed = Integer.valueOf(orderInWork.getChildren().get(i).getAttribute(TIME_NEED).getValue());
            //optional parameters
            int period = Integer.valueOf(orderInWork.getChildren().get(i).getAttribute(PERIOD).getValue());
            int order = Integer.valueOf(orderInWork.getChildren().get(i).getAttribute(ORDER).getValue());

            OrdersInWork workplace = new OrdersInWork(workplaceId, articleId, period, order, amount, timeNeed);
            System.out.println(workplace.toString());
            ordersInWorkMap.put(workplaceId, workplace);
        }
    }
}

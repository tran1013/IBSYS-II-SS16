package planningTool.util;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import planningTool.model.Article;
import planningTool.model.FutureInComingOrder;

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
        Element futureinwardstockmovement = root.getChild("futureinwardstockmovement");
        Element waitinglistworkstations = root.getChild("waitinglistworkstations");
        Element waitingliststock = root.getChild("waitingliststock");
        Element ordersinwork = root.getChild("ordersinwork");

        //TODO Import this things for later versions
        //like dashboard data
        //incoming stuff are this periode so shit happens 
        Element inwardstockmovement = root.getChild("inwardstockmovement");
        Element cycletimes = root.getChild("cycletimes");
        Element completedorders = root.getChild("completedorders");
        Element idletimecosts = root.getChild("idletimecosts");

        extractPeriod(root);

        System.out.println("size " + wareHouseStock.getChildren().size());

        try {
            extractWarehouseStockArticles(wareHouseStock);
            extractFutureInWardMovements(futureinwardstockmovement);
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
     *
     * @param futureInWardMovements
     */
    private void extractFutureInWardMovements(Element futureInWardMovements) {
        for(int i = 0; i < futureInWardMovements.getChildren().size(); i++) {
            String id = futureInWardMovements.getChildren().get(i).getAttribute(ID).getValue();
            String articelId = futureInWardMovements.getChildren().get(i).getAttribute(ARTICLE).getValue();
            int mode = Integer.valueOf(futureInWardMovements.getChildren().get(i).getAttribute(MODE).getValue());
            int orderPeriode = Integer.valueOf(futureInWardMovements.getChildren().get(i).getAttribute(ORDER_PERIODE).getValue());
            int amount = Integer.valueOf(futureInWardMovements.getChildren().get(i).getAttribute(AMOUNT).getValue());

            FutureInComingOrder futureInComingOrder = new FutureInComingOrder(id, articelId, mode, orderPeriode, amount);
            futureInComingOrderMap.put(articelId, futureInComingOrder);
        }
    }

    private void extractOrdersInWorkFormWorkplaces(Element orderInWork) {

    }
}

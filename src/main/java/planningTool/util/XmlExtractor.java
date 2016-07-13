package planningTool.util;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import planningTool.model.Article;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

import static planningTool.model.Constants.*;

/**
 * XML Parser
 * Created by minhnguyen on 12.07.16.
 */
public class XmlExtractor {

    private List <Article> articleList = new ArrayList<>();

    private Map<String, Article> wareHouseArticles = new HashMap<>();

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
        Element inwardstockmovement = root.getChild("inwardstockmovement");
        Element futureinwardstockmovement = root.getChild("futureinwardstockmovement");
        Element idletimecosts = root.getChild("idletimecosts");
        Element waitinglistworkstations = root.getChild("waitinglistworkstations");
        Element waitingliststock = root.getChild("waitingliststock");
        Element ordersinwork = root.getChild("ordersinwork");
        Element completedorders = root.getChild("completedorders");
        Element cycletimes = root.getChild("cycletimes");


        extractPeriod(root);

        System.out.println("size " + wareHouseStock.getChildren().size());

        try {
            extractWarehouseStockArticles(wareHouseStock);
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
            NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);

            if (wareHouseStock.getChildren().get(i).getName().equals(TOTALSTOCKVALUE)) {
                continue;
            }
            String id = wareHouseStock.getChildren().get(i).getAttribute(ID).getValue();
            int amount = Integer.valueOf(wareHouseStock.getChildren().get(i).getAttribute(AMOUNT).getValue());
            double pct = nf.parse(wareHouseStock.getChildren().get(i).getAttribute(PCT).getValue()).doubleValue();
            double price = nf.parse(wareHouseStock.getChildren().get(i).getAttribute(PRICE).getValue()).doubleValue();
            double stockValue = nf.parse(wareHouseStock.getChildren().get(i).getAttribute(STOCKVALUE).getValue()).doubleValue();
            int reserve = 50;
            switch (id) {
                case "16":
                case "17":
                case "26":
                    reserve = reserve * 3;
                    break;
            }
            Article article = new Article(Integer.valueOf(id), amount, reserve, pct, price, stockValue);
            System.out.println(article.toString());
            articleList.add(article);
        }
    }

    private void extractWaitingListStock(Element waitingListStock) {
    }
}

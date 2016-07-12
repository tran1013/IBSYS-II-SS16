package planningTool.util;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * XML Parser
 * Created by minhnguyen on 12.07.16.
 */
public class XmlExtractor {

    private int periode;

    public void parseXML(File file) throws IOException, ParserConfigurationException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

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

            if (wareHouseStock.getChildren().get(i).getName().equals("totalstockvalue")) {
                continue;
            }
            String id = wareHouseStock.getChildren().get(i).getAttribute("id").getValue();
            Integer amount = Integer.valueOf(wareHouseStock.getChildren().get(i).getAttribute("amount").getValue());
            Double pct = nf.parse(wareHouseStock.getChildren().get(i).getAttribute("pct").getValue()).doubleValue();
            Double price = nf.parse(wareHouseStock.getChildren().get(i).getAttribute("price").getValue()).doubleValue();
            Double stockValue = nf.parse(wareHouseStock.getChildren().get(i).getAttribute("stockvalue").getValue()).doubleValue();


            System.out.println(MessageFormat.format("something id {0} amount {1} price {2} stockvalue {3} ptc {4}", id, amount, price, stockValue, pct));
        }
    }
}

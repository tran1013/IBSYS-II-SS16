package de.ibsys.planningTool.model;

import de.ibsys.planningTool.model.xmlExportModel.DirectSell;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlExportModel.WorkTime;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.ibsys.planningTool.model.Constants.*;

/**
 * Created by minhnguyen on 18.07.16.
 */
public class XmlExport {

    private List<Item> sellWishList = new ArrayList<>();
    private List<DirectSell> directSellList = new ArrayList<>();

    public void exportXmlInputData(List<Item> sellWishList, List<DirectSell> directSellList, String path) {
        Document document = new Document();
        Element inputElement = new Element("input");
        document.setRootElement(inputElement);

        // Qualitycontrol
        Element qualityControl = new Element("qualitycontrol");
        qualityControl.setAttribute("type", "no");
        qualityControl.setAttribute("losequantity", "0");
        qualityControl.setAttribute("delay", "0");
        inputElement.addContent(qualityControl);

        Element sellWish = new Element(SELLWISH);
        Element sellDirect = new Element(SELLDIRECT);
        Element orderList = new Element(ORDER_LIST);
        Element productionList = new Element(PRODUCTION_LIST);
        Element workingTimeList = new Element(WORKTIME_LIST);

        sellWishList.forEach((Item item) -> {
            Element information = new Element(ITEM_ID);
            information.setAttribute(ARTICLE, item.getArticleId());
            information.setAttribute(QUANTITY, String.valueOf(item.getQuantity()));
            sellWish.addContent(information);
        });
        inputElement.addContent(sellWish);

        directSellList.forEach((DirectSell directSell) -> {
            Element information = new Element(ITEM_ID);
            information.setAttribute(ARTICLE, directSell.getArticleId());
            information.setAttribute(QUANTITY, String.valueOf(directSell.getQuantity()));
            information.setAttribute(PRICE, String.valueOf(directSell.getPrice()));
            information.setAttribute(PENALTY, String.valueOf(directSell.getPenalty()));
            sellDirect.addContent(information);
        });
        inputElement.addContent(sellDirect);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());

        try {
            xmlOutputter.output(document, new FileWriter(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

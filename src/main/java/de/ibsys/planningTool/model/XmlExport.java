package de.ibsys.planningTool.model;

import static de.ibsys.planningTool.model.Constants.ARTICLE;
import static de.ibsys.planningTool.model.Constants.ITEM_ID;
import static de.ibsys.planningTool.model.Constants.MODE;
import static de.ibsys.planningTool.model.Constants.ORDER;
import static de.ibsys.planningTool.model.Constants.ORDER_LIST;
import static de.ibsys.planningTool.model.Constants.OVER_TIME;
import static de.ibsys.planningTool.model.Constants.PENALTY;
import static de.ibsys.planningTool.model.Constants.PRICE;
import static de.ibsys.planningTool.model.Constants.PRODUCTION;
import static de.ibsys.planningTool.model.Constants.PRODUCTION_LIST;
import static de.ibsys.planningTool.model.Constants.QUANTITY;
import static de.ibsys.planningTool.model.Constants.SELLDIRECT;
import static de.ibsys.planningTool.model.Constants.SELLWISH;
import static de.ibsys.planningTool.model.Constants.SHIFT;
import static de.ibsys.planningTool.model.Constants.WORKINGTIME;
import static de.ibsys.planningTool.model.Constants.WORKTIME_LIST;
import static de.ibsys.planningTool.model.Constants.WORK_STATION;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import de.ibsys.planningTool.model.xmlExportModel.DirectSell;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlExportModel.Order;
import de.ibsys.planningTool.model.xmlExportModel.WorkTime;

/**
 * TODO IF this class will execute in a webserver, create a outputstream 
 * Created by minhnguyen on 18.07.16.
 */
public class XmlExport {

    public void exportXmlInputData(List<Item> sellWishList,
                                   List<DirectSell> directSellList,
                                   List<Order> purchaseList,
                                   List<Item> productionListInformation,
                                   List<WorkTime> workTimeList,
                                   String path) {
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

        purchaseList.forEach((Order order) -> {
            Element information = new Element(ORDER);
            information.setAttribute(ARTICLE, order.getArticleId());
            information.setAttribute(QUANTITY, String.valueOf(order.getQuantity()));
            information.setAttribute(MODE, String.valueOf(order.getModus()));
            orderList.addContent(information);
        });
        inputElement.addContent(orderList);

        productionListInformation.forEach((Item productionElement) -> {
            Element production = new Element(PRODUCTION);
            production.setAttribute(ARTICLE, productionElement.getArticleId());
            production.setAttribute(QUANTITY, String.valueOf(productionElement.getQuantity()));
            productionList.addContent(production);
        });
        inputElement.addContent(productionList);

        workTimeList.forEach((WorkTime worktime) -> {
            Element work = new Element(WORKINGTIME);
            work.setAttribute(WORK_STATION, String.valueOf(worktime.getStation()));
            work.setAttribute(SHIFT, String.valueOf(worktime.getShift()));
            work.setAttribute(OVER_TIME, String.valueOf(worktime.getOvertime()));
            workingTimeList.addContent(work);
        });
        inputElement.addContent(workingTimeList);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());

        try {
            xmlOutputter.output(document, new FileWriter(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

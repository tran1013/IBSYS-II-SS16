package de.ibsys.planningTool.model;

import de.ibsys.planningTool.model.xmlExportModel.DirectSell;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlExportModel.WorkTime;
import org.jdom2.Document;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

import static de.ibsys.planningTool.model.Constants.*;

/**
 * Created by minhnguyen on 18.07.16.
 */
public class XmlExport {

    private List<Item> sellWishList = new ArrayList<>();
    private List<DirectSell> directSellList = new ArrayList<>();

    public void exportXmlInputData() {
        Document document = new Document();
        Element inputElement = new Element("input");
        document.setRootElement(inputElement);

        Element sellWish = new Element(SELLWISH);
        Element sellDirect = new Element(SELLDIRECT);
        Element orderList = new Element(ORDER_LIST);
        Element productionList = new Element(PRODUCTION_LIST);
        Element workingTimeList = new Element(WORKTIME_LIST);
    }
}

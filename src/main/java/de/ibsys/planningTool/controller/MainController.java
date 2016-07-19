package de.ibsys.planningTool.controller;

import de.ibsys.planningTool.controller.tab.ExportController;
import de.ibsys.planningTool.controller.tab.ForeCastController;
import de.ibsys.planningTool.controller.tab.XmlInputController;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnguyen on 17.07.16.
 */
public class MainController {

    private XmlInputData xmlInputData;

    private List<Item> sellWish;

    @FXML
    private ForeCastController foreCastController;

    @FXML
    private XmlInputController xmlInputController;

    @FXML
    private ExportController exportController;

    @FXML
    public void initialize() {
        System.out.println("Start Application");
        foreCastController.init(this);
        xmlInputController.init(this);
        exportController.init(this);
        xmlInputData = new XmlInputData();
        sellWish = new ArrayList<>();
    }

    public XmlInputData getXmlInputData() {
        return xmlInputData;
    }

    public void setXmlInputData(XmlInputData xmlInputData) {
        this.xmlInputData = xmlInputData;
    }

    public List<Item> getSellWish() {
        return sellWish;
    }

    public void setSellWish(List<Item> sellWish) {
        this.sellWish = sellWish;
    }

    public void deleteSellWish() {
        sellWish.clear();
    }
}

package de.ibsys.planningTool.controller;

import de.ibsys.planningTool.controller.tab.ExportController;
import de.ibsys.planningTool.controller.tab.ForeCastController;
import de.ibsys.planningTool.controller.tab.XmlInputController;
import de.ibsys.planningTool.model.XmlInputData;
import javafx.fxml.FXML;

/**
 * Created by minhnguyen on 17.07.16.
 */
public class MainController {

    private XmlInputData xmlInputData;

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
    }

    public XmlInputData getXmlInputData() {
        return xmlInputData;
    }

    public void setXmlInputData(XmlInputData xmlInputData) {
        this.xmlInputData = xmlInputData;
    }
}

package de.ibsys.planningTool.controller.tab.productionOrderTab;

import java.util.Arrays;
import java.util.List;

import com.jfoenix.controls.JFXTextField;

import de.ibsys.planningTool.controller.tab.ProductionController;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Child View Created by minhnguyen on 22.09.16.
 */
public class ChildBikeController extends BaseProductionTabController {

    @FXML
    Label orders, safetystock, stock, queue, process, productionorder;

    @FXML
    JFXTextField orderP1, orderE26, orderE7, orderE51, orderE16, orderE17, orderE50, orderE4, orderE10, orderE49,
            orderE13, orderE18;

    @FXML
    JFXTextField helpE26, helpE51, helpE16, helpE7, helpE17, helpE50, helpE4, helpE10, helpE49, helpE13, helpE18;

    @FXML
    JFXTextField safetyP1, safetyE26, safetyE51, safetyE7, safetyE16, safetyE17, safetyE50, safetyE4, safetyE10,
            safetyE49, safetyE13, safetyE18;

    @FXML
    JFXTextField stockP1, stockE7, stockE26, stockE51, stockE16, stockE17, stockE50, stockE4, stockE10, stockE49,
            stockE13, stockE18;

    @FXML
    JFXTextField queueP1, queueE26, queueE7, queueE51, queueE16, queueE17, queueE50, queueE4, queueE10, queueE49,
            queueE13, queueE18;

    @FXML
    JFXTextField processP1, processE26, processE51, processE7, processE16, processE17, processE50, processE4,
            processE10, processE49, processE13, processE18;

    @FXML
    JFXTextField productionP1, productionE26, productionE51, productionE7, productionE16, productionE17, productionE50,
            productionE4, productionE10, productionE49, productionE13, productionE18;

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @Override
    public void init(ProductionController productionOrderController) {
        super.init(productionOrderController);
        initUIComponents();
    }

    public void initUIComponents() {
        try {
            orderP1.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockP1.setText(String.valueOf(getStockValue("1")));
            if (safetyP1.getText().equals("")) {
                safetyP1.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("1").getReserve()));
            } else {
                safetyP1.getText();
            }
            queueP1.setText(String.valueOf(getQueueValue("1")));
            processP1.setText(String.valueOf(getProcessValue("1")));
            productionP1.setText(String.valueOf(getProductionValuePParts("1", Integer.parseInt(safetyP1.getText()))));

            orderE26.setText(String.valueOf(productionP1.getText()));
            stockE26.setText(String.valueOf(getStockValue("26")));
            helpE26.setText(String.valueOf(getQueueValue("1")));
            if (safetyE26.getText().equals("")) {
                safetyE26.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("26").getReserve() / 3));
            } else {
                safetyE26.getText();
            }
            queueE26.setText(String.valueOf(getQueueValue("26")));
            processE26.setText(String.valueOf(getProcessValue("26")));
            productionE26.setText(String.valueOf(
                    getProductionValueEParts("26", Integer.parseInt(productionP1.getText()), getQueueValue("1"),
                            Integer.parseInt(safetyE26.getText()), Integer.parseInt(stockE26.getText()),
                            Integer.parseInt(queueE26.getText()), Integer.parseInt(processE26.getText()))));

            orderE51.setText(String.valueOf(productionP1.getText()));
            stockE51.setText(String.valueOf(getStockValue("51")));
            helpE51.setText(String.valueOf(getQueueValue("1")));
            if (safetyE51.getText().equals("")) {
                safetyE51.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("51").getReserve()));
            } else {
                safetyE51.getText();
            }
            queueE51.setText(String.valueOf(getQueueValue("51")));
            processE51.setText(String.valueOf(getProcessValue("51")));
            productionE51.setText(String.valueOf(
                    getProductionValueEParts("51", Integer.parseInt(productionP1.getText()), getQueueValue("1"),
                            Integer.parseInt(safetyE51.getText()), Integer.parseInt(stockE51.getText()),
                            Integer.parseInt(queueE51.getText()), Integer.parseInt(processE51.getText()))));

            orderE16.setText(String.valueOf(productionE51.getText()));
            stockE16.setText(String.valueOf(getStockValue("16")));
            helpE16.setText(String.valueOf(getQueueValue("51")));
            if (safetyE16.getText().equals("")) {
                safetyE16.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("16").getReserve() / 3));
            } else {
                safetyE16.getText();
            }
            queueE16.setText(String.valueOf(getQueueValue("16")));
            processE16.setText(String.valueOf(getProcessValue("16")));
            productionE16.setText(String.valueOf(
                    getProductionValueEParts("16", Integer.valueOf(productionE51.getText()), getQueueValue("51"),
                            Integer.parseInt(safetyE16.getText()), Integer.parseInt(stockE16.getText()),
                            Integer.parseInt(queueE16.getText()), Integer.parseInt(processE16.getText()))));

            orderE17.setText(String.valueOf(productionE51.getText()));
            stockE17.setText(String.valueOf(getStockValue("17")));
            helpE17.setText(String.valueOf(getQueueValue("51")));
            if (safetyE17.getText().equals("")) {
                safetyE17.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("17").getReserve() / 3));
            } else {
                safetyE17.getText();
            }
            queueE17.setText(String.valueOf(getQueueValue("17")));
            processE17.setText(String.valueOf(getProcessValue("17")));
            productionE17.setText(String.valueOf(
                    getProductionValueEParts("17", Integer.valueOf(productionE51.getText()), getQueueValue("51"),
                            Integer.parseInt(safetyE17.getText()), Integer.parseInt(stockE17.getText()),
                            Integer.parseInt(queueE17.getText()), Integer.parseInt(processE17.getText()))));

            orderE50.setText(String.valueOf(productionE51.getText()));
            stockE50.setText(String.valueOf(getStockValue("50")));
            helpE50.setText(String.valueOf(getQueueValue("51")));
            if (safetyE50.getText().equals("")) {
                safetyE50.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("50").getReserve()));
            } else {
                safetyE50.getText();
            }
            queueE50.setText(String.valueOf(getQueueValue("50")));
            processE50.setText(String.valueOf(getProcessValue("50")));
            productionE50.setText(String.valueOf(
                    getProductionValueEParts("50", Integer.valueOf(productionE51.getText()), getQueueValue("51"),
                            Integer.parseInt(safetyE50.getText()), Integer.parseInt(stockE50.getText()),
                            Integer.parseInt(queueE50.getText()), Integer.parseInt(processE50.getText()))));

            orderE4.setText(String.valueOf(productionE50.getText()));
            stockE4.setText(String.valueOf(getStockValue("4")));
            helpE4.setText(String.valueOf(getQueueValue("50")));
            if (safetyE4.getText().equals("")) {
                safetyE4.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("4").getReserve()));
            } else {
                safetyE4.getText();
            }
            queueE4.setText(String.valueOf(getQueueValue("4")));
            processE4.setText(String.valueOf(getProcessValue("4")));
            productionE4.setText(String.valueOf(getProductionValueEParts("4", Integer.valueOf(productionE50.getText()),
                    getQueueValue("50"), Integer.parseInt(safetyE4.getText()), Integer.parseInt(stockE4.getText()),
                    Integer.parseInt(queueE4.getText()), Integer.parseInt(processE4.getText()))));

            orderE10.setText(String.valueOf(productionE50.getText()));
            stockE10.setText(String.valueOf(getStockValue("10")));
            helpE10.setText(String.valueOf(getQueueValue("50")));
            if (safetyE10.getText().equals("")) {
                safetyE10.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("10").getReserve()));
            } else {
                safetyE10.getText();
            }
            queueE10.setText(String.valueOf(getQueueValue("10")));
            processE10.setText(String.valueOf(getProcessValue("10")));
            productionE10.setText(String.valueOf(
                    getProductionValueEParts("10", Integer.valueOf(productionE50.getText()), getQueueValue("50"),
                            Integer.parseInt(safetyE10.getText()), Integer.parseInt(stockE10.getText()),
                            Integer.parseInt(queueE10.getText()), Integer.parseInt(processE10.getText()))));

            orderE49.setText(String.valueOf(productionE50.getText()));
            stockE49.setText(String.valueOf(getStockValue("49")));
            helpE49.setText(String.valueOf(getQueueValue("50")));
            if (safetyE49.getText().equals("")) {
                safetyE49.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("49").getReserve()));
            } else {
                safetyE49.getText();
            }
            queueE49.setText(String.valueOf(getQueueValue("49")));
            processE49.setText(String.valueOf(getProcessValue("94")));
            productionE49.setText(String.valueOf(
                    getProductionValueEParts("49", Integer.valueOf(productionE50.getText()), getQueueValue("50"),
                            Integer.parseInt(safetyE49.getText()), Integer.parseInt(stockE49.getText()),
                            Integer.parseInt(queueE49.getText()), Integer.parseInt(processE49.getText()))));

            orderE7.setText(String.valueOf(productionE49.getText()));
            stockE7.setText(String.valueOf(getStockValue("7")));
            helpE7.setText(String.valueOf(getQueueValue("49")));
            if (safetyE7.getText().equals("")) {
                safetyE7.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("7").getReserve()));
            } else {
                safetyE7.getText();
            }
            queueE7.setText(String.valueOf(getQueueValue("7")));
            processE7.setText(String.valueOf(getProcessValue("7")));
            productionE7.setText(String.valueOf(getProductionValueEParts("7", Integer.valueOf(productionE49.getText()),
                    getQueueValue("49"), Integer.parseInt(safetyE7.getText()), Integer.parseInt(stockE7.getText()),
                    Integer.parseInt(queueE7.getText()), Integer.parseInt(processE7.getText()))));

            orderE13.setText(String.valueOf(productionE49.getText()));
            stockE13.setText(String.valueOf(getStockValue("13")));
            helpE13.setText(String.valueOf(getQueueValue("49")));
            if (safetyE13.getText().equals("")) {
                safetyE13.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("13").getReserve()));
            } else {
                safetyE13.getText();
            }
            queueE13.setText(String.valueOf(getQueueValue("13")));
            processE13.setText(String.valueOf(getProcessValue("13")));
            productionE13.setText(String.valueOf(
                    getProductionValueEParts("13", Integer.valueOf(productionE49.getText()), getQueueValue("49"),
                            Integer.parseInt(safetyE13.getText()), Integer.parseInt(stockE13.getText()),
                            Integer.parseInt(queueE13.getText()), Integer.parseInt(processE13.getText()))));

            orderE18.setText(String.valueOf(productionE49.getText()));
            stockE18.setText(String.valueOf(getStockValue("18")));
            helpE18.setText(String.valueOf(getQueueValue("49")));
            if (safetyE18.getText().equals("")) {
                safetyE18.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("18").getReserve()));
            } else {
                safetyE18.getText();
            }
            queueE18.setText(String.valueOf(getQueueValue("18")));
            processE18.setText(String.valueOf(getProcessValue("18")));
            productionE18.setText(String.valueOf(
                    getProductionValueEParts("18", Integer.valueOf(productionE49.getText()), getQueueValue("49"),
                            Integer.parseInt(safetyE18.getText()), Integer.parseInt(stockE18.getText()),
                            Integer.parseInt(queueE18.getText()), Integer.parseInt(processE18.getText()))));
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }
    
    public void  storeNewReserve() {
        getXmlInputData().getWareHouseArticles().get("1").setReserve(Integer.parseInt(safetyP1.getText()));
        getXmlInputData().getWareHouseArticles().get("26").setReserve(Integer.parseInt(safetyE26.getText()));
        getXmlInputData().getWareHouseArticles().get("51").setReserve(Integer.parseInt(safetyE51.getText()));
        getXmlInputData().getWareHouseArticles().get("16").setReserve(Integer.parseInt(safetyE16.getText()));
        getXmlInputData().getWareHouseArticles().get("17").setReserve(Integer.parseInt(safetyE17.getText()));
        getXmlInputData().getWareHouseArticles().get("50").setReserve(Integer.parseInt(safetyE50.getText()));
        getXmlInputData().getWareHouseArticles().get("4").setReserve(Integer.parseInt(safetyE4.getText()));
        getXmlInputData().getWareHouseArticles().get("10").setReserve(Integer.parseInt(safetyE10.getText()));
        getXmlInputData().getWareHouseArticles().get("49").setReserve(Integer.parseInt(safetyE49.getText()));
        getXmlInputData().getWareHouseArticles().get("7").setReserve(Integer.parseInt(safetyE7.getText()));
        getXmlInputData().getWareHouseArticles().get("13").setReserve(Integer.parseInt(safetyE13.getText()));
        getXmlInputData().getWareHouseArticles().get("18").setReserve(Integer.parseInt(safetyE18.getText()));
    }

    public List<Item> setList() {
        List<Item> productionResultChild = setMainProductionList("1",
                Arrays.asList(productionP1, productionE26, productionE51, productionE16, productionE17, productionE50,
                        productionE4, productionE10, productionE49, productionE7, productionE13, productionE18));
        return productionResultChild;
    }
}

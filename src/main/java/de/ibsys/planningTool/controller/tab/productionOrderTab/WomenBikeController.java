package de.ibsys.planningTool.controller.tab.productionOrderTab;

import java.util.Arrays;
import java.util.List;

import com.jfoenix.controls.JFXTextField;

import de.ibsys.planningTool.controller.tab.ProductionController;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.util.I18N;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by minhnguyen on 22.09.16.
 */
public class WomenBikeController extends BaseProductionTabController {

    @FXML
    Label orders, safetystock, stock, queue, process, productionorder;

    @FXML
    JFXTextField orderP2, orderE26, orderE8, orderE56, orderE16, orderE17, orderE5, orderE55, orderE11, orderE54,
            orderE14, orderE19;

    @FXML
    JFXTextField helpE26, helpE56, helpE16, helpE8, helpE17, helpE5, helpE55, helpE11, helpE54, helpE14, helpE19;

    @FXML
    JFXTextField safetyP2, safetyE26, safetyE56, safetyE8, safetyE16, safetyE17, safetyE5, safetyE55, safetyE11,
            safetyE54, safetyE14, safetyE19;

    @FXML
    JFXTextField stockP2, stockE8, stockE26, stockE56, stockE16, stockE17, stockE5, stockE55, stockE11, stockE54,
            stockE14, stockE19;

    @FXML
    JFXTextField queueP2, queueE26, queueE8, queueE56, queueE16, queueE17, queueE5, queueE55, queueE11, queueE54,
            queueE14, queueE19;

    @FXML
    JFXTextField processP2, processE26, processE56, processE8, processE16, processE17, processE5, processE55,
            processE11, processE54, processE14, processE19;

    @FXML
    JFXTextField productionP2, productionE26, productionE56, productionE8, productionE16, productionE17, productionE5,
            productionE55, productionE11, productionE54, productionE14, productionE19;

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @Override
    public void init(ProductionController productionOrderController) {
        super.init(productionOrderController);
        initUIComponents();
        initUI();
    }

    public void initUI() {
        orders.setText(productionOrderController.getTranslation().getString(I18N.ORDERS));
        safetystock.setText(productionOrderController.getTranslation().getString(I18N.SAFETYSTOCK));
        productionorder.setText(productionOrderController.getTranslation().getString(I18N.PRODUCTIONS_ORDERS));
        stock.setText(productionOrderController.getTranslation().getString(I18N.STOCKLABEL));
        process.setText(productionOrderController.getTranslation().getString(I18N.IN_PROCESS));
        queue.setText(productionOrderController.getTranslation().getString(I18N.QUEQUE));
    }

    public void initUIComponents() {
        try {
            orderP2.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockP2.setText(String.valueOf(getStockValue("2")));
            if (safetyP2.getText().equals("")) {
                safetyP2.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("2").getReserve()));
            } else {
                safetyP2.getText();
            }
            queueP2.setText(String.valueOf(getQueueValue("2")));
            processP2.setText(String.valueOf(getProcessValue("2")));
            productionP2.setText(String.valueOf(getProductionValuePParts("2", Integer.parseInt(safetyP2.getText()))));

            orderE26.setText(String.valueOf(productionP2.getText()));
            stockE26.setText(/* String.valueOf(getStockValue("26")) */String.valueOf(0));
            helpE26.setText(String.valueOf(/* getQueueValue("2") */0));
            safetyE26.setText(
                    String.valueOf(/*
                                    * getXmlInputData().getWareHouseArticles().
                                    * get("26").getReserve() / 3
                                    */0));
            queueE26.setText(String.valueOf(/* getQueueValue("26") */0));
            processE26.setText(String.valueOf(/* getProcessValue("26")) */0));
            productionE26.setText(String.valueOf(getProductionValueEParts("26",
                    Integer.parseInt(productionP2.getText()), getQueueValue("2"),
                    Integer.parseInt(safetyE26.getText()), Integer.parseInt(stockE26.getText()),
                    Integer.parseInt(queueE26.getText()), Integer.parseInt(processE26.getText()))));

            orderE56.setText(String.valueOf(checkIsNegative(Integer.parseInt(productionP2.getText()))));
            stockE56.setText(String.valueOf(getStockValue("56")));
            helpE56.setText(String.valueOf(getQueueValue("2")));
            if (safetyE56.getText().equals("")) {
                safetyE56.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("56").getReserve()));
            } else {
                safetyE56.getText();
            }
            queueE56.setText(String.valueOf(getQueueValue("56")));
            processE56.setText(String.valueOf(getProcessValue("56")));
            productionE56.setText(String.valueOf(
                    getProductionValueEParts("56", Integer.parseInt(productionP2.getText()), getQueueValue("2"),
                            Integer.parseInt(safetyE56.getText()), Integer.parseInt(stockE56.getText()),
                            Integer.parseInt(queueE56.getText()), Integer.parseInt(processE56.getText()))));

            orderE16.setText(String.valueOf(productionE56.getText()));
            stockE16.setText(String.valueOf(/* getStockValue("16") */0));
            helpE16.setText(String.valueOf(getQueueValue("56")));
            safetyE16.setText(
                    String.valueOf(/*
                                    * getXmlInputData().getWareHouseArticles().
                                    * get("16").getReserve() / 3
                                    */0));
            queueE16.setText(String.valueOf(0));
            processE16.setText(String.valueOf(0));
            productionE16.setText(String.valueOf(getProductionValueEParts("16",
                    Integer.valueOf(productionE56.getText()), Integer.parseInt(queueE16.getText()),
                    Integer.parseInt(safetyE16.getText()), Integer.parseInt(stockE16.getText()),
                    Integer.parseInt(queueE16.getText()), Integer.parseInt(processE16.getText()))));

            orderE17.setText(String.valueOf(productionE56.getText()));
            stockE17.setText(String.valueOf(/* getStockValue("17") */0));
            helpE17.setText(String.valueOf(getQueueValue("56")));
            safetyE17.setText(
                    String.valueOf(/*
                                    * getXmlInputData().getWareHouseArticles().
                                    * get("17").getReserve() / 3
                                    */0));
            queueE17.setText(String.valueOf(0));
            processE17.setText(String.valueOf(0));
            productionE17.setText(String.valueOf(getProductionValueEParts("17",
                    Integer.valueOf(productionE56.getText()), Integer.parseInt(queueE17.getText()),
                    Integer.parseInt(safetyE17.getText()), Integer.parseInt(stockE17.getText()),
                    Integer.parseInt(queueE17.getText()), Integer.parseInt(processE17.getText()))));

            orderE55.setText(String.valueOf(checkIsNegative(Integer.parseInt(productionE56.getText()))));
            stockE55.setText(String.valueOf(getStockValue("55")));
            helpE55.setText(String.valueOf(getQueueValue("56")));
            if (safetyE55.getText().equals("")) {
                safetyE55.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("55").getReserve()));
            } else {
                safetyE55.getText();
            }
            queueE55.setText(String.valueOf(getQueueValue("55")));
            processE55.setText(String.valueOf(getProcessValue("55")));
            productionE55.setText(String.valueOf(
                    getProductionValueEParts("55", Integer.valueOf(productionE56.getText()), getQueueValue("56"),
                            Integer.parseInt(safetyE55.getText()), Integer.parseInt(stockE55.getText()),
                            Integer.parseInt(queueE55.getText()), Integer.parseInt(processE55.getText()))));

            orderE5.setText(String.valueOf(checkIsNegative(Integer.parseInt(productionE55.getText()))));
            stockE5.setText(String.valueOf(getStockValue("5")));
            helpE5.setText(String.valueOf(getQueueValue("55")));
            if (safetyE5.getText().equals("")) {
                safetyE5.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("5").getReserve()));
            } else {
                safetyE5.getText();
            }
            queueE5.setText(String.valueOf(getQueueValue("5")));
            processE5.setText(String.valueOf(getProcessValue("5")));
            productionE5.setText(String.valueOf(getProductionValueEParts("5", Integer.valueOf(productionE55.getText()),
                    getQueueValue("55"), Integer.parseInt(safetyE5.getText()), Integer.parseInt(stockE5.getText()),
                    Integer.parseInt(queueE5.getText()), Integer.parseInt(processE5.getText()))));

            orderE11.setText(String.valueOf(checkIsNegative(Integer.parseInt(productionE55.getText()))));
            stockE11.setText(String.valueOf(getStockValue("11")));
            helpE11.setText(String.valueOf(getQueueValue("55")));
            if (safetyE11.getText().equals("")) {
                safetyE11.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("11").getReserve()));
            } else {
                safetyE11.getText();
            }
            queueE11.setText(String.valueOf(getQueueValue("11")));
            processE11.setText(String.valueOf(getProcessValue("11")));
            productionE11.setText(String.valueOf(
                    getProductionValueEParts("11", Integer.valueOf(productionE55.getText()), getQueueValue("55"),
                            Integer.parseInt(safetyE11.getText()), Integer.parseInt(stockE11.getText()),
                            Integer.parseInt(queueE11.getText()), Integer.parseInt(processE11.getText()))));

            orderE54.setText(String.valueOf(checkIsNegative(Integer.parseInt(productionE55.getText()))));
            stockE54.setText(String.valueOf(getStockValue("54")));
            helpE54.setText(String.valueOf(getQueueValue("55")));
            if (safetyE54.getText().equals("")) {
                safetyE54.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("54").getReserve()));
            } else {
                safetyE54.getText();
            }
            queueE54.setText(String.valueOf(getQueueValue("54")));
            processE54.setText(String.valueOf(getProcessValue("54")));
            productionE54.setText(String.valueOf(
                    getProductionValueEParts("54", Integer.valueOf(productionE55.getText()), getQueueValue("55"),
                            Integer.parseInt(safetyE54.getText()), Integer.parseInt(stockE54.getText()),
                            Integer.parseInt(queueE54.getText()), Integer.parseInt(processE54.getText()))));

            orderE8.setText(String.valueOf(checkIsNegative(Integer.parseInt(productionE54.getText()))));
            stockE8.setText(String.valueOf(getStockValue("8")));
            helpE8.setText(String.valueOf(getQueueValue("54")));
            if (safetyE8.getText().equals("")) {
                safetyE8.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("8").getReserve()));
            } else {
                safetyE8.getText();
            }
            queueE8.setText(String.valueOf(getQueueValue("8")));
            processE8.setText(String.valueOf(getProcessValue("8")));
            productionE8.setText(String.valueOf(getProductionValueEParts("8", Integer.valueOf(productionE54.getText()),
                    getQueueValue("54"), Integer.parseInt(safetyE8.getText()), Integer.parseInt(stockE8.getText()),
                    Integer.parseInt(queueE8.getText()), Integer.parseInt(processE8.getText()))));

            orderE14.setText(String.valueOf(checkIsNegative(Integer.parseInt(productionE54.getText()))));
            stockE14.setText(String.valueOf(getStockValue("14")));
            helpE14.setText(String.valueOf(getQueueValue("54")));
            if (safetyE14.getText().equals("")) {
                safetyE14.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("14").getReserve()));
            } else {
                safetyE14.getText();
            }
            queueE14.setText(String.valueOf(getQueueValue("14")));
            processE14.setText(String.valueOf(getProcessValue("14")));
            productionE14.setText(String.valueOf(
                    getProductionValueEParts("14", Integer.valueOf(productionE54.getText()), getQueueValue("54"),
                            Integer.parseInt(safetyE14.getText()), Integer.parseInt(stockE14.getText()),
                            Integer.parseInt(queueE14.getText()), Integer.parseInt(processE14.getText()))));

            orderE19.setText(String.valueOf(checkIsNegative(Integer.parseInt(productionE54.getText()))));
            stockE19.setText(String.valueOf(getStockValue("19")));
            helpE19.setText(String.valueOf(getQueueValue("54")));
            if (safetyE19.getText().equals("")) {
                safetyE19.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("19").getReserve()));
            } else {
                safetyE19.getText();
            }
            queueE19.setText(String.valueOf(getQueueValue("19")));
            processE19.setText(String.valueOf(getProcessValue("19")));
            productionE19.setText(String.valueOf(
                    getProductionValueEParts("19", Integer.valueOf(productionE54.getText()), getQueueValue("54"),
                            Integer.parseInt(safetyE19.getText()), Integer.parseInt(stockE19.getText()),
                            Integer.parseInt(queueE19.getText()), Integer.parseInt(processE19.getText()))));

            /*
             * setMainProductionList("2", Arrays.asList(productionP2,
             * productionE26, productionE56, productionE16, productionE17,
             * productionE55, productionE5, productionE11, productionE54,
             * productionE8, productionE14, productionE19));
             */

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

//    Label orders, safetystock, stock, queue, process, productionorder;
    public void storeNewReserve() {
        getXmlInputData().getWareHouseArticles().get("2").setReserve(Integer.parseInt(safetyP2.getText()));
        getXmlInputData().getWareHouseArticles().get("56").setReserve(Integer.parseInt(safetyE56.getText()));
        getXmlInputData().getWareHouseArticles().get("55").setReserve(Integer.parseInt(safetyE55.getText()));
        getXmlInputData().getWareHouseArticles().get("5").setReserve(Integer.parseInt(safetyE5.getText()));
        getXmlInputData().getWareHouseArticles().get("11").setReserve(Integer.parseInt(safetyE11.getText()));
        getXmlInputData().getWareHouseArticles().get("54").setReserve(Integer.parseInt(safetyE54.getText()));
        getXmlInputData().getWareHouseArticles().get("8").setReserve(Integer.parseInt(safetyE8.getText()));
        getXmlInputData().getWareHouseArticles().get("14").setReserve(Integer.parseInt(safetyE14.getText()));
        getXmlInputData().getWareHouseArticles().get("19").setReserve(Integer.parseInt(safetyE19.getText()));
    }

    public List<Item> setList() {
        List<Item> productionResultChild = setMainProductionList("2",
                Arrays.asList(productionP2, productionE26, productionE56, productionE16, productionE17, productionE55,
                        productionE5, productionE11, productionE54, productionE8, productionE14, productionE19));
        return productionResultChild;
    }
}

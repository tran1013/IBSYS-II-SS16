package de.ibsys.planningTool.controller.tab.productionOrderTab;

import com.jfoenix.controls.JFXTextField;

import de.ibsys.planningTool.controller.tab.ProductionController;
import de.ibsys.planningTool.model.ProductionResult;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Child View
 * Created by minhnguyen on 22.09.16.
 */
public class ChildBikeController extends BaseProductionTabController {

    @FXML
    Label orders, safetystock, stock, queue, process, productionorder;

    @FXML
    JFXTextField orderP1, orderE26, orderE7, orderE51, orderE16, orderE17, orderE50, orderE4, orderE10, orderE49, orderE13, orderE18;

    @FXML
    JFXTextField helpE26, helpE51, helpE16, helpE7, helpE17, helpE50, helpE4, helpE10, helpE49, helpE13, helpE18;

    @FXML
    JFXTextField safetyP1, safetyE26, safetyE51, safetyE7, safetyE16, safetyE17, safetyE50, safetyE4, safetyE10, safetyE49, safetyE13, safetyE18;

    @FXML
    JFXTextField stockP1, stockE7, stockE26, stockE51, stockE16, stockE17, stockE50, stockE4, stockE10, stockE49, stockE13, stockE18;

    @FXML
    JFXTextField queueP1, queueE26, queueE7, queueE51, queueE16, queueE17, queueE50, queueE4, queueE10, queueE49, queueE13, queueE18;

    @FXML
    JFXTextField processP1, processE26, processE51, processE7, processE16, processE17, processE50, processE4, processE10, processE49, processE13, processE18;

    @FXML
    JFXTextField productionP1, productionE26, productionE51, productionE7, productionE16, productionE17, productionE50, productionE4, productionE10, productionE49, productionE13, productionE18;

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @Override
    public void init(ProductionController productionOrderController) {
        super.init(productionOrderController);
        initUIThingsRandom();
    }

    public void initUIThingsRandom() {
        try {
            orderP1.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockP1.setText(String.valueOf(getStockValue("1")));
            safetyP1.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("1").getReserve()));
            queueP1.setText(String.valueOf(getQueueValue("1")));
            processP1.setText(String.valueOf(getProcessValue("1")));
            productionP1.setText(String.valueOf(getProductionValuePParts("1")));


            orderE26.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockE26.setText(String.valueOf(getStockValue("26")));
            helpE26.setText(String.valueOf(getQueueValue("1")));
            safetyE26.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("26").getReserve()));
            queueE26.setText(String.valueOf(getQueueValue("26")));
            processE26.setText(String.valueOf(getProcessValue("26")));
            productionE26.setText(String.valueOf(getProductionValueEParts("26", getForeCastInformationProductLine("p1n"), getQueueValue("1"))));



            orderE51.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockE51.setText(String.valueOf(getStockValue("51")));
            helpE51.setText(String.valueOf(getQueueValue("1")));
            safetyE51.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("51").getReserve()));
            queueE51.setText(String.valueOf(getQueueValue("51")));
            processE51.setText(String.valueOf(getProcessValue("51")));
            productionE51.setText(String.valueOf(getProductionValueEParts("51", getForeCastInformationProductLine("p1n"), getQueueValue("1"))));


            orderE16.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockE16.setText(String.valueOf(getStockValue("16")));
            helpE16.setText(String.valueOf(getQueueValue("51")));
            safetyE16.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("16").getReserve()));
            queueE16.setText(String.valueOf(getQueueValue("16")));
            processE16.setText(String.valueOf(getProcessValue("16")));
            productionE16.setText(String.valueOf(getProductionValueEParts("16", Integer.valueOf(productionE51.getText()), getQueueValue("51"))));


            orderE17.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockE17.setText(String.valueOf(getStockValue("17")));
            helpE17.setText(String.valueOf(getQueueValue("51")));
            safetyE17.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("17").getReserve()));
            queueE17.setText(String.valueOf(getQueueValue("17")));
            processE17.setText(String.valueOf(getProcessValue("17")));
            productionE17.setText(String.valueOf(getProductionValueEParts("17", Integer.valueOf(productionE51.getText()), getQueueValue("51"))));


            orderE50.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockE50.setText(String.valueOf(getStockValue("50")));
            helpE50.setText(String.valueOf(getQueueValue("51")));
            safetyE50.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("50").getReserve()));
            queueE50.setText(String.valueOf(getQueueValue("50")));
            processE50.setText(String.valueOf(getProcessValue("50")));
            productionE50.setText(String.valueOf(getProductionValueEParts("50", Integer.valueOf(productionE51.getText()), getQueueValue("51"))));


            orderE4.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockE4.setText(String.valueOf(getStockValue("4")));
            helpE4.setText(String.valueOf(getQueueValue("50")));
            safetyE4.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("4").getReserve()));
            queueE4.setText(String.valueOf(getQueueValue("4")));
            processE4.setText(String.valueOf(getProcessValue("4")));
            productionE4.setText(String.valueOf(getProductionValueEParts("4", Integer.valueOf(productionE50.getText()), getQueueValue("50"))));


            orderE10.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockE10.setText(String.valueOf(getStockValue("10")));
            helpE10.setText(String.valueOf(getQueueValue("50")));
            safetyE10.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("10").getReserve()));
            queueE10.setText(String.valueOf(getQueueValue("10")));
            processE10.setText(String.valueOf(getProcessValue("10")));
            productionE10.setText(String.valueOf(getProductionValueEParts("10", Integer.valueOf(productionE50.getText()), getQueueValue("50"))));


            orderE49.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockE49.setText(String.valueOf(getStockValue("49")));
            helpE49.setText(String.valueOf(getQueueValue("50")));
            safetyE49.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("49").getReserve()));
            queueE49.setText(String.valueOf(getQueueValue("49")));
            processE49.setText(String.valueOf(getProcessValue("94")));
            productionE49.setText(String.valueOf(getProductionValueEParts("49", Integer.valueOf(productionE50.getText()), getQueueValue("50"))));


            orderE7.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockE7.setText(String.valueOf(getStockValue("7")));
            helpE7.setText(String.valueOf(getQueueValue("49")));
            safetyE7.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("7").getReserve()));
            queueE7.setText(String.valueOf(getQueueValue("7")));
            processE7.setText(String.valueOf(getProcessValue("7")));
            productionE7.setText(String.valueOf(getProductionValueEParts("7", Integer.valueOf(productionE49.getText()), getQueueValue("49"))));


            orderE13.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockE13.setText(String.valueOf(getStockValue("13")));
            helpE13.setText(String.valueOf(getQueueValue("49")));
            safetyE13.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("13").getReserve()));
            queueE13.setText(String.valueOf(getQueueValue("13")));
            processE13.setText(String.valueOf(getProcessValue("13")));
            productionE13.setText(String.valueOf(getProductionValueEParts("13", Integer.valueOf(productionE49.getText()), getQueueValue("49"))));


            orderE18.setText(String.valueOf(getForeCastInformationProductLine("p1n")));
            stockE18.setText(String.valueOf(getStockValue("18")));
            helpE18.setText(String.valueOf(getQueueValue("49")));
            safetyE18.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("18").getReserve()));
            queueE18.setText(String.valueOf(getQueueValue("18")));
            processE18.setText(String.valueOf(getProcessValue("18")));
            productionE18.setText(String.valueOf(getProductionValueEParts("18", Integer.valueOf(productionE49.getText()), getQueueValue("49"))));


            //productionP1.setText(String.valueOf(getProductionValuePParts("1")));
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    public List<ProductionResult> getProductionResultForChild() {
        List<ProductionResult> productionResultList = new ArrayList<>();
        List<String> parts = Arrays.asList("P1", "E26", "E51", "E16", "E17", "E50", "E4", "E10", "E49", "E7", "E13", "E18");
        List<JFXTextField> textFields = Arrays.asList(productionP1, productionE26, productionE51, productionE16, productionE17, productionE50, productionE4, productionE10, productionE49, productionE7, productionE13, productionE18);

        for (Integer i = 0; i < parts.size(); i++) {
            productionResultList.add(new ProductionResult(parts.get(i), Integer.parseInt(textFields.get(i).getText())));
        }

        System.out.println(productionResultList);
        return productionResultList;
    }

    @FXML
    public void saveNewReserve() {
        //logger.info(getWaitingListPartsAmount("3"));
        //getXmlInputData().getStringWaitingListMissingPartsMap().entrySet().parallelStream().forEach(System.out::println);
        List<ProductionResult> productionResultList = getProductionResultForChild();
    }
}

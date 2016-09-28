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
 * Created by minhnguyen on 22.09.16.
 */
public class WomenBikeController extends BaseProductionTabController {

    @FXML
    Label orders, safetystock, stock, queue, process, productionorder;

    @FXML
    JFXTextField orderP2, orderE26, orderE8, orderE56, orderE16, orderE17, orderE5, orderE55, orderE11, orderE54, orderE14, orderE19;

    @FXML
    JFXTextField helpE26, helpE56, helpE16, helpE8, helpE17, helpE5, helpE55, helpE11, helpE54, helpE14, helpE19;

    @FXML
    JFXTextField safetyP2, safetyE26, safetyE56, safetyE8, safetyE16, safetyE17, safetyE5, safetyE55, safetyE11, safetyE54, safetyE14, safetyE19;

    @FXML
    JFXTextField stockP2, stockE8, stockE26, stockE56, stockE16, stockE17, stockE5, stockE55, stockE11, stockE54, stockE14, stockE19;

    @FXML
    JFXTextField queueP2, queueE26, queueE8, queueE56, queueE16, queueE17, queueE5, queueE55, queueE11, queueE54, queueE14, queueE19;

    @FXML
    JFXTextField processP2, processE26, processE56, processE8, processE16, processE17, processE5, processE55, processE11, processE54, processE14, processE19;

    @FXML
    JFXTextField productionP2, productionE26, productionE56, productionE8, productionE16, productionE17, productionE5, productionE55, productionE11, productionE54, productionE14, productionE19;


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
            orderP2.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockP2.setText(String.valueOf(getStockValue("2")));
            safetyP2.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("1").getReserve()));
            queueP2.setText(String.valueOf(getQueueValue("2")));
            processP2.setText(String.valueOf(getProcessValue("2")));
            productionP2.setText(String.valueOf(getProductionValuePParts("2")));


            orderE26.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockE26.setText(String.valueOf(getStockValue("26")));
            helpE26.setText(String.valueOf(getQueueValue("2")));
            safetyE26.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("26").getReserve()));
            queueE26.setText(String.valueOf(getQueueValue("26")));
            processE26.setText(String.valueOf(getProcessValue("26")));
            productionE26.setText(String.valueOf(getProductionValueEParts("26", getForeCastInformationProductLine("p2n"), getQueueValue("2"))));


            orderE56.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockE56.setText(String.valueOf(getStockValue("56")));
            helpE56.setText(String.valueOf(getQueueValue("2")));
            safetyE56.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("56").getReserve()));
            queueE56.setText(String.valueOf(getQueueValue("56")));
            processE56.setText(String.valueOf(getProcessValue("56")));
            productionE56.setText(String.valueOf(getProductionValueEParts("56", getForeCastInformationProductLine("p2n"), getQueueValue("2"))));


            orderE16.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockE16.setText(String.valueOf(getStockValue("16")));
            helpE16.setText(String.valueOf(getQueueValue("56")));
            safetyE16.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("16").getReserve()));
            queueE16.setText(String.valueOf(getQueueValue("16")));
            processE16.setText(String.valueOf(getProcessValue("16")));
            productionE16.setText(String.valueOf(getProductionValueEParts("16", Integer.valueOf(productionE56.getText()), getQueueValue("56"))));


            orderE17.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockE17.setText(String.valueOf(getStockValue("17")));
            helpE17.setText(String.valueOf(getQueueValue("56")));
            safetyE17.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("17").getReserve()));
            queueE17.setText(String.valueOf(getQueueValue("17")));
            processE17.setText(String.valueOf(getProcessValue("17")));
            productionE17.setText(String.valueOf(getProductionValueEParts("17", Integer.valueOf(productionE56.getText()), getQueueValue("56"))));


            orderE55.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockE55.setText(String.valueOf(getStockValue("55")));
            helpE55.setText(String.valueOf(getQueueValue("56")));
            safetyE55.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("55").getReserve()));
            queueE55.setText(String.valueOf(getQueueValue("55")));
            processE55.setText(String.valueOf(getProcessValue("55")));
            productionE55.setText(String.valueOf(getProductionValueEParts("55", Integer.valueOf(productionE56.getText()), getQueueValue("56"))));


            orderE5.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockE5.setText(String.valueOf(getStockValue("5")));
            helpE5.setText(String.valueOf(getQueueValue("55")));
            safetyE5.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("5").getReserve()));
            queueE5.setText(String.valueOf(getQueueValue("5")));
            processE5.setText(String.valueOf(getProcessValue("5")));
            productionE5.setText(String.valueOf(getProductionValueEParts("5", Integer.valueOf(productionE55.getText()), getQueueValue("55"))));


            orderE11.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockE11.setText(String.valueOf(getStockValue("11")));
            helpE11.setText(String.valueOf(getQueueValue("55")));
            safetyE11.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("11").getReserve()));
            queueE11.setText(String.valueOf(getQueueValue("11")));
            processE11.setText(String.valueOf(getProcessValue("11")));
            productionE11.setText(String.valueOf(getProductionValueEParts("11", Integer.valueOf(productionE55.getText()), getQueueValue("55"))));


            orderE54.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockE54.setText(String.valueOf(getStockValue("54")));
            helpE54.setText(String.valueOf(getQueueValue("55")));
            safetyE54.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("54").getReserve()));
            queueE54.setText(String.valueOf(getQueueValue("54")));
            processE54.setText(String.valueOf(getProcessValue("54")));
            productionE54.setText(String.valueOf(getProductionValueEParts("54", Integer.valueOf(productionE55.getText()), getQueueValue("55"))));


            orderE8.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockE8.setText(String.valueOf(getStockValue("8")));
            helpE8.setText(String.valueOf(getQueueValue("54")));
            safetyE8.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("8").getReserve()));
            queueE8.setText(String.valueOf(getQueueValue("8")));
            processE8.setText(String.valueOf(getProcessValue("8")));
            productionE8.setText(String.valueOf(getProductionValueEParts("8", Integer.valueOf(productionE54.getText()), getQueueValue("54"))));


            orderE14.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockE14.setText(String.valueOf(getStockValue("14")));
            helpE14.setText(String.valueOf(getQueueValue("54")));
            safetyE14.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("14").getReserve()));
            queueE14.setText(String.valueOf(getQueueValue("14")));
            processE14.setText(String.valueOf(getProcessValue("14")));
            productionE14.setText(String.valueOf(getProductionValueEParts("14", Integer.valueOf(productionE54.getText()), getQueueValue("54"))));


            orderE19.setText(String.valueOf(getForeCastInformationProductLine("p2n")));
            stockE19.setText(String.valueOf(getStockValue("19")));
            helpE19.setText(String.valueOf(getQueueValue("54")));
            safetyE19.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("19").getReserve()));
            queueE19.setText(String.valueOf(getQueueValue("19")));
            processE19.setText(String.valueOf(getProcessValue("19")));
            productionE19.setText(String.valueOf(getProductionValueEParts("19", Integer.valueOf(productionE54.getText()), getQueueValue("54"))));


            //productionP2.setText(String.valueOf(getProductionValuePParts("2")));

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    public List<ProductionResult> getProductionResultForWoman() {
        List<ProductionResult> productionResultList = new ArrayList<>();
        List<String> parts = Arrays.asList("P2", "E26", "E56", "E16", "E17", "E55", "E5", "E11", "E54", "E8", "E14", "E19");
        List<JFXTextField> textFields = Arrays.asList(productionP2, productionE26, productionE56, productionE16, productionE17, productionE55, productionE5, productionE11, productionE54, productionE8, productionE14, productionE19);

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
        List<ProductionResult> productionResultList = getProductionResultForWoman();
    }
}

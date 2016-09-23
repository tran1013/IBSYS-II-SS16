package de.ibsys.planningTool.controller.tab.productionOrderTab;

import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.controller.tab.BaseTabController;
import de.ibsys.planningTool.controller.tab.ProductionController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by minhnguyen on 22.09.16.
 */
public class WomenBikeController extends BaseProductionTabController{

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
    }

    public void initUIThingsRandom() {
        try {
            orderP1.setText(getForeCastInformationProductLine("p1n"));
            stockP1.setText(String.valueOf(getStockValue("1")));
            safetyP1.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("1").getReserve()));
            queueP1.setText(String.valueOf(getQueueValue("1")));
            processP1.setText(String.valueOf(getProcessValue("1")));


            orderE26.setText(getForeCastInformationProductLine("p1n"));
            stockE26.setText(String.valueOf(getStockValue("26")));
            safetyE26.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("26").getReserve()));
            queueE26.setText(String.valueOf(getQueueValue("26")));
            processE26.setText(String.valueOf(getProcessValue("26")));

            orderE51.setText(getForeCastInformationProductLine("p1n"));
            stockE51.setText(String.valueOf(getStockValue("51")));
            safetyE51.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("51").getReserve()));
            queueE51.setText(String.valueOf(getQueueValue("51")));
            processE51.setText(String.valueOf(getProcessValue("51")));

            orderE16.setText(getForeCastInformationProductLine("p1n"));
            stockE16.setText(String.valueOf(getStockValue("16")));
            safetyE16.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("16").getReserve()));
            queueE16.setText(String.valueOf(getQueueValue("16")));
            processE16.setText(String.valueOf(getProcessValue("16")));

            orderE17.setText(getForeCastInformationProductLine("p1n"));
            stockE17.setText(String.valueOf(getStockValue("17")));
            safetyE17.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("17").getReserve()));
            queueE17.setText(String.valueOf(getQueueValue("17")));
            processE17.setText(String.valueOf(getProcessValue("17")));

            orderE4.setText(getForeCastInformationProductLine("p1n"));
            stockE4.setText(String.valueOf(getStockValue("4")));
            safetyE4.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("4").getReserve()));
            queueE4.setText(String.valueOf(getQueueValue("4")));
            processE4.setText(String.valueOf(getProcessValue("4")));

            orderE10.setText(getForeCastInformationProductLine("p1n"));
            stockE10.setText(String.valueOf(getStockValue("10")));
            safetyE10.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("10").getReserve()));
            queueE10.setText(String.valueOf(getQueueValue("10")));
            processE10.setText(String.valueOf(getProcessValue("10")));

            orderE49.setText(getForeCastInformationProductLine("p1n"));
            stockE49.setText(String.valueOf(getStockValue("49")));
            safetyE49.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("49").getReserve()));
            queueE49.setText(String.valueOf(getQueueValue("49")));
            processE49.setText(String.valueOf(getProcessValue("94")));

            orderE7.setText(getForeCastInformationProductLine("p1n"));
            stockE7.setText(String.valueOf(getStockValue("7")));
            safetyE7.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("7").getReserve()));
            queueE7.setText(String.valueOf(getQueueValue("7")));
            processE7.setText(String.valueOf(getProcessValue("7")));

            orderE13.setText(getForeCastInformationProductLine("p1n"));
            stockE13.setText(String.valueOf(getStockValue("13")));
            safetyE13.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("13").getReserve()));
            queueE13.setText(String.valueOf(getQueueValue("13")));
            processE13.setText(String.valueOf(getProcessValue("13")));

            orderE18.setText(getForeCastInformationProductLine("p1n"));
            stockE18.setText(String.valueOf(getStockValue("18")));
            safetyE18.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("18").getReserve()));
            queueE18.setText(String.valueOf(getQueueValue("18")));
            processE18.setText(String.valueOf(getProcessValue("18")));

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }
}

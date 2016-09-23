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
            orderP2.setText(getForeCastInformationProductLine("p2n"));
            stockP2.setText(String.valueOf(getStockValue("2")));
            safetyP2.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("1").getReserve()));
            queueP2.setText(String.valueOf(getQueueValue("2")));
            processP2.setText(String.valueOf(getProcessValue("2")));


            orderE26.setText(getForeCastInformationProductLine("p2n"));
            stockE26.setText(String.valueOf(getStockValue("26")));
            safetyE26.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("26").getReserve()));
            queueE26.setText(String.valueOf(getQueueValue("26")));
            processE26.setText(String.valueOf(getProcessValue("26")));

            orderE56.setText(getForeCastInformationProductLine("p2n"));
            stockE56.setText(String.valueOf(getStockValue("56")));
            safetyE56.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("56").getReserve()));
            queueE56.setText(String.valueOf(getQueueValue("56")));
            processE56.setText(String.valueOf(getProcessValue("56")));

            orderE16.setText(getForeCastInformationProductLine("p2n"));
            stockE16.setText(String.valueOf(getStockValue("16")));
            safetyE16.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("16").getReserve()));
            queueE16.setText(String.valueOf(getQueueValue("16")));
            processE16.setText(String.valueOf(getProcessValue("16")));

            orderE17.setText(getForeCastInformationProductLine("p2n"));
            stockE17.setText(String.valueOf(getStockValue("17")));
            safetyE17.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("17").getReserve()));
            queueE17.setText(String.valueOf(getQueueValue("17")));
            processE17.setText(String.valueOf(getProcessValue("17")));

            orderE55.setText(getForeCastInformationProductLine("p2n"));
            stockE55.setText(String.valueOf(getStockValue("55")));
            safetyE55.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("55").getReserve()));
            queueE55.setText(String.valueOf(getQueueValue("55")));
            processE55.setText(String.valueOf(getProcessValue("55")));
            
            orderE5.setText(getForeCastInformationProductLine("p2n"));
            stockE5.setText(String.valueOf(getStockValue("5")));
            safetyE5.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("5").getReserve()));
            queueE5.setText(String.valueOf(getQueueValue("5")));
            processE5.setText(String.valueOf(getProcessValue("5")));

            orderE11.setText(getForeCastInformationProductLine("p2n"));
            stockE11.setText(String.valueOf(getStockValue("11")));
            safetyE11.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("11").getReserve()));

            queueE11.setText(String.valueOf(getQueueValue("11")));
            processE11.setText(String.valueOf(getProcessValue("11")));

            orderE54.setText(getForeCastInformationProductLine("p2n"));
            stockE54.setText(String.valueOf(getStockValue("54")));
            safetyE54.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("54").getReserve()));
            queueE54.setText(String.valueOf(getQueueValue("54")));
            processE54.setText(String.valueOf(getProcessValue("54")));

            orderE8.setText(getForeCastInformationProductLine("p2n"));
            stockE8.setText(String.valueOf(getStockValue("8")));
            safetyE8.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("8").getReserve()));
            queueE8.setText(String.valueOf(getQueueValue("8")));
            processE8.setText(String.valueOf(getProcessValue("8")));

            orderE14.setText(getForeCastInformationProductLine("p2n"));
            stockE14.setText(String.valueOf(getStockValue("14")));
            safetyE14.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("14").getReserve()));
            queueE14.setText(String.valueOf(getQueueValue("14")));
            processE14.setText(String.valueOf(getProcessValue("14")));

            orderE19.setText(getForeCastInformationProductLine("p2n"));
            stockE19.setText(String.valueOf(getStockValue("19")));
            safetyE19.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("19").getReserve()));
            queueE19.setText(String.valueOf(getQueueValue("19")));
            processE19.setText(String.valueOf(getProcessValue("19")));

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }
}

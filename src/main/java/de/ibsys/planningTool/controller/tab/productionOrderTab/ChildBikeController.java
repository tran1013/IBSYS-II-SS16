package de.ibsys.planningTool.controller.tab.productionOrderTab;

import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.controller.tab.ProductionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Random;

/**
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
        orderP1.setText(String.valueOf(new Random().nextInt(500)));
        safetyP1.setText(String.valueOf(new Random().nextInt(500)));
        stockP1.setText(String.valueOf(new Random().nextInt(500)));
        queueP1.setText(String.valueOf(new Random().nextInt(500)));
        processP1.setText(String.valueOf(new Random().nextInt(500)));
        productionP1.setText(String.valueOf(new Random().nextInt(500)));
    }

    public void doShit() {
        try {
            orderP1.setText(getForeCastInformationProductLine("p1n"));
            stockE18.setText(String.valueOf(productionOrderController.getMainController().getXmlInputData().getWareHouseArticles().get("18").getAmount()));
        } catch (Exception e) {
            logger.error(e);
        }
    }
}

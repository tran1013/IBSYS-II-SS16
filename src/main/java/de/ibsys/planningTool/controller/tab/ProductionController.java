package de.ibsys.planningTool.controller.tab;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.ChildBikeController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.MenBikeController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.WomenBikeController;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * Created by minhnguyen on 22.09.16.
 */
public class ProductionController extends BaseTabController {

    @FXML
    ChildBikeController childBikeController;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void init(MainController main) {
        super.init(main);
        childBikeController.init(this);
    }

    public ResourceBundle getTranslation() {
        return main.getTranslation();
    }
}
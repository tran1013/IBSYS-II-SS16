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

    private MainController mainController;

    @FXML
    ChildBikeController childBikeController;

    @FXML
    MenBikeController menBikeController;

    @FXML
    WomenBikeController womenBikeController;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void init(MainController main) {
        super.init(main);
        mainController = main;
        childBikeController.init(this);
        menBikeController.init(this);
        womenBikeController.init(this);
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public ResourceBundle getTranslation() {
        return main.getTranslation();
    }
}
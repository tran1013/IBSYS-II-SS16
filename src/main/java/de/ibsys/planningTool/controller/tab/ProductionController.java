package de.ibsys.planningTool.controller.tab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.ChildBikeController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.MenBikeController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.WomenBikeController;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import javafx.fxml.FXML;
import javafx.stage.Stage;

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

    @FXML
    JFXButton saveBtn;

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

    @FXML
    public void saveBtnPressed() {
        try {
            childBikeController.storeNewReserve();
            childBikeController.initUIComponents();
            menBikeController.initUIComponents();
            womenBikeController.initUIComponents();
            
            List<Item> child = childBikeController.setList();
            List<Item> men = menBikeController.setList();
            List<Item> women = womenBikeController.setList();
            List<Item> result = new ArrayList<>();
            result.clear();
            
            result.addAll(child);
            result.addAll(women);
            result.addAll(men);

            getMainController().setProductionList(result);
            main.initWorkThings();
            logger.info("save new stuff successfull");
           
            mainController.getProductionList().forEach(System.out::println);
        } catch (NullPointerException e) {
            logger.info(e);
        }
    }
}
package de.ibsys.planningTool.controller.tab;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jfoenix.controls.JFXButton;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.ChildBikeController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.MenBikeController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.WomenBikeController;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.service.Dispo;
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
        initProductionControllerCalculation();
    }
    
    public void initProductionControllerCalculation () {
        try {
            childBikeController.storeNewReserve();
            menBikeController.storeNewReserve();
            womenBikeController.storeNewReserve();
            childBikeController.initUIComponents();
            menBikeController.initUIComponents();
            womenBikeController.initUIComponents();

            List<Item> result = new ArrayList<>();

            result.clear();

            result = new Dispo().calculate( // do calculation and reduce 
                    Stream.concat(childBikeController.setList().parallelStream(), //
                            Stream.concat(menBikeController.setList().parallelStream(), //
                                    womenBikeController.setList().parallelStream() //
                            ).collect(Collectors.toList()).parallelStream()) // get all list from men and women in one list
                            .collect(Collectors.toList())) // get all list form views together
                    .stream()
                    .sorted((item1, item2) -> Integer.valueOf(item1.getArticleId()) // sorted things 
                            .compareTo(Integer.valueOf(item2.getArticleId()))) //
                    .collect(Collectors.toList()); // return a list

            getMainController().setProductionList(result);
            main.initWorkThings();
            logger.info("save new stuff successfull");
        } catch (NullPointerException e) {
            logger.info(e);
        }
    }
}
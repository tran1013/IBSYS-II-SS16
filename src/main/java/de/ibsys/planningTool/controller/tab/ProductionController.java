package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXButton;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.ChildBikeController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.MenBikeController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.WomenBikeController;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.*;

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

        childBikeController.initUIThingsRandom();
        menBikeController.initUIThingsRandom();
        womenBikeController.initUIThingsRandom();
        List<Item> child = childBikeController.setList();
        List<Item> men = menBikeController.setList();
        List<Item> women = womenBikeController.setList();
        List<Item> result = new ArrayList<>();
        result.clear();
        result.addAll(child);


        int quantity = 0;

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getArticleId().equals("16") || result.get(i).getArticleId().equals("17") || result.get(i).getArticleId().equals("26")) {
                quantity = result.get(i).getQuantity();
                quantity += men.get(i).getQuantity() + women.get(i).getQuantity();
                result.set(i, new Item(result.get(i).getArticleId(), quantity));
            }
        }

        for (int i = 0; i < men.size(); i++) {
            if (!(men.get(i).getArticleId().equals("16") || men.get(i).getArticleId().equals("17") || men.get(i).getArticleId().equals("26")))
                result.add(men.get(i));
        }

        for (int i = 0; i < women.size(); i++) {
            if (!(women.get(i).getArticleId().equals("16") || women.get(i).getArticleId().equals("17") || women.get(i).getArticleId().equals("26")))
                result.add(women.get(i));
        }

        Collections.sort(result, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return Integer.parseInt(o1.getArticleId()) - Integer.parseInt(o2.getArticleId());
            }
        });


        getMainController().setProductionList(result);
        main.initWorkThings();
        System.out.println("Result:");
        System.out.println(result);
    }
}
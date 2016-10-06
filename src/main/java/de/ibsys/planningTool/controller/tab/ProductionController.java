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

            int quantity = 0;


            for (int i = 0; i < men.size(); i++) {
                if (!(men.get(i).getArticleId().equals("16") || men.get(i).getArticleId().equals("17")
                        || men.get(i).getArticleId().equals("26")))
                    result.add(men.get(i));
            }

            for (int i = 0; i < women.size(); i++) {
                if (!(women.get(i).getArticleId().equals("16") || women.get(i).getArticleId().equals("17")
                        || women.get(i).getArticleId().equals("26")))
                    result.add(women.get(i));
            }
            
            // Logischer Fehler ? wenns ganz oben steht kommt an sich nicht genugend information rein 
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).getArticleId().equals("16") || result.get(i).getArticleId().equals("17")
                        || result.get(i).getArticleId().equals("26")) {
                    quantity = result.get(i).getQuantity();
                    quantity += men.get(i).getQuantity() + women.get(i).getQuantity();
                    result.set(i, new Item(result.get(i).getArticleId(), quantity));
                }
            }
            logger.info(result);

            Collections.sort(result, new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    return Integer.parseInt(o1.getArticleId()) - Integer.parseInt(o2.getArticleId());
                }
            });

            getMainController().setProductionList(result);
            main.initWorkThings();
            logger.info("save new stuff successfull");
            /*
             * System.out.println("Result:"); System.out.println(result);
             */
        } catch (NullPointerException e) {
            logger.info(e);
        }
    }
}
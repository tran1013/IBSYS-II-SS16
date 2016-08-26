package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXListView;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.util.MockObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by minhnguyen on 26.08.16.
 */
public class ProductionPriorityController extends BaseTabController {

    @FXML
    private JFXListView listView;

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @Override
    public void init(MainController main) {
        super.init(main);
        ObservableList<Item> productionList = FXCollections.observableList(new MockObject().productionListMockData());

        listView.setItems(productionList);
    }
}

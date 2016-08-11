package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXTreeTableView;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.model.ProductionSteps;
import de.ibsys.planningTool.service.CapPla;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

/**
 * Created by Duc on 11.08.16.
 */
public class CapPlaController extends BaseTabController {

    @FXML
    private ListView<String> listView;

    @FXML
    private BarChart barChart;

    @FXML
    private Label label;

    @FXML
    private TreeItem<String> workplaceID;

    @FXML
    private TreeItem<String> capTime;

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    ObservableList<String> list = FXCollections.observableArrayList("Mark","Tom","Daniel");

    @FXML
    public void getList(){
        CapPla cap = new CapPla();

        Map<String, Integer> result = new HashMap<>();
        List<String> workplace = new ArrayList<>();
        List<Integer> time = new ArrayList<>();

        result = cap.getCapResult();

        for(Map.Entry<String, Integer> entry : result.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            workplace.add(key);
            time.add(value);
        }


    }


}

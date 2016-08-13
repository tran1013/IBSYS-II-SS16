package de.ibsys.planningTool.controller.tab;


import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlInputModel.OrdersInWork;
import de.ibsys.planningTool.service.CapPla;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.*;

/**
 * Created by Duc on 11.08.16.
 */
public class CapPlaController extends BaseTabController {

    @FXML
    private TableView<String> tableView;

    @FXML
    private TableColumn workplaceCol;

    @FXML
    private TableColumn capacityCol;

    @FXML
    private TableColumn shiftsCol;

    @FXML
    private TableColumn overtimeCol;

    @FXML
    private TableColumn infoCol;

    @FXML
    private BarChart barChart;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }


    XmlInputData input = new XmlInputData();

    @FXML
    public void getColumns() {

/*
        Map<String, Integer> result;

        result = cap.getCapResult();

        Map<String, Integer> treeMap = new TreeMap<String, Integer>(result);
        List<String> myKeys = new ArrayList<>();

        ObservableList<String> myStrings = FXCollections.observableArrayList(myKeys);

        for (Map.Entry<String, Integer> map : treeMap.entrySet()) {
            String key = map.getKey();
            //Integer key = Integer.parseInt(map.getKey());
            Integer value = map.getValue();

            myKeys.add(key);

            System.out.println("Key: " + key + " " + " Value: " + value);
        }*/


        Map<String, OrdersInWork> result;
        result = input.getOrdersInWorkMap();
        System.out.println(result);


    }


}

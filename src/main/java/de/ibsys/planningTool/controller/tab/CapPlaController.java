package de.ibsys.planningTool.controller.tab;


import de.ibsys.planningTool.database.capPlaDB;
import de.ibsys.planningTool.mock.sellData;
import de.ibsys.planningTool.model.CapPlaResult;
import de.ibsys.planningTool.model.Constants;
import de.ibsys.planningTool.model.ProductionSteps;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlInputModel.OrdersInWork;
import de.ibsys.planningTool.model.xmlInputModel.WaitingList;
import de.ibsys.planningTool.model.xmlInputModel.WaitingListWorkPlace;
import de.ibsys.planningTool.service.CapPla;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.*;


/**
 * Created by Duc on 11.08.16.
 * TODO: Clean up mess in class
 * TODO: Test class or take a deep breath and get to it?
 */
public class CapPlaController extends BaseTabController {

    @FXML
    private TableView<CapPlaResult> tableView;

    @FXML
    private TableColumn<CapPlaResult, Integer> workplaceCol;

    @FXML
    private TableColumn<CapPlaResult, Integer> capacityCol;

    @FXML
    private TableColumn<CapPlaResult, Integer> shiftsCol;

    @FXML
    private TableColumn<CapPlaResult, Integer> overtimeCol;

    @FXML
    private BarChart<Number, String> barChart;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }


    /**
     * Start get CapPla results from here
     */
    @FXML
    public List<CapPlaResult> getMasterResult() {
        CapPla cap = new CapPla();
        List<CapPlaResult> result;
        Map<String, OrdersInWork> ordersInWorkMap;
        Map<String, WaitingListWorkPlace> waitingListWorkPlaceMap;

        ordersInWorkMap = main.getXmlInputData().getOrdersInWorkMap();
        waitingListWorkPlaceMap = main.getXmlInputData().getWaitingListWorkPlaceMap();
        result = cap.calculateCap(ordersInWorkMap, waitingListWorkPlaceMap);

        System.out.println("Print list");
        System.out.println(result);

        return result;
    }


    /**
     * Get ObservableList with datas to print them on the table view
     *
     * @return
     */
    public ObservableList<CapPlaResult> getResults() {
        ObservableList<CapPlaResult> results = FXCollections.observableArrayList();
        List<CapPlaResult> capPlaList = this.getMasterResult();

        Integer workplaceId;
        Integer reqCapacity;
        Integer shifts;
        Integer overtime;

        for (CapPlaResult cap : capPlaList) {
            workplaceId = cap.getWorkplaceId();
            reqCapacity = cap.getReqCapacity();
            shifts = cap.getShifts();
            overtime = cap.getOvertime();

            results.add(new CapPlaResult(workplaceId, reqCapacity, shifts, overtime));
        }

        return results;
    }

    /**
     * Get all datas to fill the bar chart
     *
     * @return
     */
    public XYChart.Series getBarChartData() {
        XYChart.Series dataSet = new XYChart.Series();
        List<CapPlaResult> capPlaList = this.getMasterResult();

        Integer workplaceId;
        Integer reqCapacity;


        for (CapPlaResult cap : capPlaList) {
            workplaceId = cap.getWorkplaceId();
            reqCapacity = cap.getReqCapacity();

            dataSet.getData().add(new XYChart.Data(workplaceId.toString(), reqCapacity));
        }
        dataSet.setName("Workplace");

        return dataSet;
    }

    /**
     * Build the CapPla UI
     * TODO: Delete build CapPla GUI by click on button and automate this if dispo datas are available
     */
    @FXML
    private void getCapPlaUI() {
        XYChart.Series dataSet = new XYChart.Series();

        workplaceCol.setCellValueFactory(new PropertyValueFactory<>("workplaceId"));
        capacityCol.setCellValueFactory(new PropertyValueFactory<>("reqCapacity"));
        shiftsCol.setCellValueFactory(new PropertyValueFactory<>("shifts"));
        overtimeCol.setCellValueFactory(new PropertyValueFactory<>("overtime"));

        tableView.setItems(getResults());
        barChart.getData().add(getBarChartData());
    }


}




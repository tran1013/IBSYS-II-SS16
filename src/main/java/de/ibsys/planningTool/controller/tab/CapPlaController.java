package de.ibsys.planningTool.controller.tab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.model.CapPlaResult;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlExportModel.WorkTime;
import de.ibsys.planningTool.model.xmlInputModel.OrdersInWork;
import de.ibsys.planningTool.model.xmlInputModel.WaitingListWorkPlace;
import de.ibsys.planningTool.service.CapPla;
import de.ibsys.planningTool.util.I18N;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Created by Duc on 11.08.16. TODO: Clean up mess in class TODO: Test class or
 * take a deep breath and get to it?
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

    @FXML
    private NumberAxis yAxis;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private Label label_01;

    @FXML
    private Label label_02;

    @FXML
    private Label label_03;

    @FXML
    private Label label_04;

    @FXML
    private Label label_05;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void init(MainController main) {
        super.init(main);
        this.getCapPlaUI();
    }
    
    public List<CapPlaResult> getMasterResult() {
        CapPla cap = new CapPla();
        List<CapPlaResult> result;
        Map<String, OrdersInWork> ordersInWorkMap;
        Map<String, WaitingListWorkPlace> waitingListWorkPlaceMap;
        List<Item> productionResult = main.getProductionList();

        ordersInWorkMap = main.getXmlInputData().getOrdersInWorkMap();
        waitingListWorkPlaceMap = main.getXmlInputData().getWaitingListWorkPlaceMap();
        result = cap.calculateCap(ordersInWorkMap, waitingListWorkPlaceMap, productionResult);

        return result;
    }

    /**
     * Get ObservableList with datas to print them on the table view
     *
     * @return
     */
    public ObservableList<CapPlaResult> getTableData() {
        ObservableList<CapPlaResult> results = FXCollections.observableArrayList();
        List<CapPlaResult> capPlaList = this.getMasterResult();

        Integer workplaceId, reqCapacity, shifts, overtime;

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

        Integer workplaceId, reqCapacity;

        for (CapPlaResult cap : capPlaList) {
            workplaceId = cap.getWorkplaceId();
            reqCapacity = cap.getReqCapacity();

            dataSet.getData().add(new XYChart.Data(workplaceId.toString(), reqCapacity));
        }

        return dataSet;
    }

    /**
     * Build the CapPla UI TODO: Delete build CapPla GUI by click on button and
     * automate this if dispo datas are available
     */
    private void getCapPlaUI() {
        try {
            XYChart.Series dataSet;
            workplaceCol.setCellValueFactory(new PropertyValueFactory<>("workplaceId"));
            capacityCol.setCellValueFactory(new PropertyValueFactory<>("reqCapacity"));
            shiftsCol.setCellValueFactory(new PropertyValueFactory<>("shifts"));
            overtimeCol.setCellValueFactory(new PropertyValueFactory<>("overtime"));
            this.storeData();
            tableView.setItems(getTableData());
            dataSet = this.getBarChartData();
            barChart.getData().clear();
            barChart.getData().add(dataSet);
            barChart.setLegendVisible(false);
        } catch (NullPointerException e) {
            logger.info(e);
        }
    }

    /**
     * Store data for xml export
     */
    private void storeData() {

        List<CapPlaResult> capPlaResults;
        List<WorkTime> workTimeList = new ArrayList<>();

        capPlaResults = this.getMasterResult();

        for (CapPlaResult cap : capPlaResults) {
            workTimeList.add(new WorkTime(cap.getWorkplaceId(), cap.getShifts(), cap.getOvertime()));
        }
        main.setWorkTimeList(workTimeList);
    }

    public void initUIComponents() {
        workplaceCol.setText(main.getTranslation().getString(I18N.WORKPLACE));
        capacityCol.setText(main.getTranslation().getString(I18N.CAPACITY));
        shiftsCol.setText(main.getTranslation().getString(I18N.SHIFTS));
        overtimeCol.setText(main.getTranslation().getString(I18N.OVERTIME));
        xAxis.setLabel(main.getTranslation().getString(I18N.WORKPLACE));
        yAxis.setLabel(main.getTranslation().getString(I18N.YAXIS));
        label_01.setText(main.getTranslation().getString(I18N.LABEL01));
        label_02.setText(main.getTranslation().getString(I18N.LABEL02));
        label_03.setText(main.getTranslation().getString(I18N.LABEL03));
        label_04.setText(main.getTranslation().getString(I18N.LABEL04));
        label_05.setText(main.getTranslation().getString(I18N.LABEL05));
    }

}

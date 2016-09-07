package de.ibsys.planningTool.controller.tab;


import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.database.ItemDB;
import de.ibsys.planningTool.mock.MockProductionResult;
import de.ibsys.planningTool.model.*;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlInputModel.FutureInComingOrder;
import de.ibsys.planningTool.model.xmlInputModel.WaitingList;
import de.ibsys.planningTool.service.OrderService;
import de.ibsys.planningTool.util.I18N;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Che on 18.08.2016.
 */
public class OrderController extends BaseTabController{

    @FXML
    private TableView<OrderResult> orderTableView;

    @FXML
    private TableColumn<OrderResult, String> nrColumn;

    @FXML
    private TableColumn<OrderResult, Integer> quantityColumn;

    @FXML
    private TableColumn<OrderResult, String> optionColumn;

    @FXML
    private Label nrLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Label optionLabel;

    @FXML
    private Label stockLabel;

    @FXML
    private Label avgLabel;

    @FXML
    private Label discontLabel;

    @FXML
    private JFXTextField quantatityTF;

    @FXML
    private Label bestandLabel;

    @FXML
    private Label verbrauchLabel;

    @FXML
    private Label discontL;

    @FXML
    private Label nrL;

    @FXML
    private JFXCheckBox expressCB;

    @FXML
    private JFXButton newBtn;

    @FXML
    private JFXButton changeBtn;

    @FXML
    private JFXButton deleteBtn;

    OrderService orderService = new OrderService();
    ItemDB itemDB = new ItemDB();

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    /*
    @FXML
    public List<OrderResult> getOrderList() {
        List<OrderResult> orderResults = new ArrayList<>();
        Map<String, Item> forecastProductionList = main.getForecastProductionList();
        //TODO change mockdata with real data from production
        MockProductionResult mockProductionResult = new MockProductionResult();
        mockProductionResult.setProductionResultList();

        return orderResults;
    }

    public ObservableList<OrderResult> getOrderData() {
        ObservableList<OrderResult> results = FXCollections.observableArrayList();
        List<OrderResult> orderList = this.getOrderList();

        for (OrderResult orderResult : orderList) {

            String itemConfigId = orderResult.getItemConfigId();
            int quantity = orderResult.getQuantity();
            int orderMode = orderResult.getOrderingMode();
            int deliveryCosts = orderResult.getDeliveryCosts();
            double time = orderResult.getDeliveryTime();
            double variance = orderResult.getVariance();
            int discont = orderResult.getDiscountQuantity();

            results.add(new OrderResult(itemConfigId, quantity, orderMode, deliveryCosts, discont, time, variance));
        }
        return results;
    }

    public void initUIComponents() {
        nrLabel.setText(main.getTranslation().getString(I18N.NRLABEL));
        quantityLabel.setText(main.getTranslation().getString(I18N.QUANTITYLABEL));
        stockLabel.setText(main.getTranslation().getString(I18N.STOCKLABEL));
        avgLabel.setText(main.getTranslation().getString(I18N.AVGLABEL));
        optionLabel.setText(main.getTranslation().getString(I18N.OPTIONLABEL));
        discontLabel.setText(main.getTranslation().getString(I18N.DISCONTLABEL));
}
  */
}

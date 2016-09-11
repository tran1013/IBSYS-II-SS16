package de.ibsys.planningTool.controller.tab;


import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.database.ItemDB;
import de.ibsys.planningTool.database.OrderDB;
import de.ibsys.planningTool.mock.MockProductionResult;
import de.ibsys.planningTool.model.*;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlExportModel.Order;
import de.ibsys.planningTool.model.xmlInputModel.Article;
import de.ibsys.planningTool.model.xmlInputModel.FutureInComingOrder;
import de.ibsys.planningTool.model.xmlInputModel.WaitingList;
import de.ibsys.planningTool.model.xmlInputModel.WaitingListMissingParts;
import de.ibsys.planningTool.service.OrderService;
import de.ibsys.planningTool.util.I18N;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.ibsys.planningTool.model.Constants.FAST_DELIVERY;
import static de.ibsys.planningTool.model.Constants.NORMAL_DELIVERY;
import static de.ibsys.planningTool.model.Constants.REPLACEMENT_TIME;
import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;

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
    private Label stockL;

    @FXML
    private Label avgL;

    @FXML
    private Label discontL;

    @FXML
    private Label nrL;

    @FXML
    private JFXCheckBox expressCB;

    @FXML
    private Label quantityL;

    @FXML
    private JFXButton newBtn;

    @FXML
    private JFXButton changeBtn;

    @FXML
    private JFXButton deleteBtn;

    OrderService orderService = new OrderService();
    OrderDB orderDB = new OrderDB();
    MockProductionResult mockProductionResult = new MockProductionResult();

    List<OrderResult> orderResults = new ArrayList<>();
    ObservableList<OrderResult> results = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void init(MainController main) {
        super.init(main);
        newBtn.getStyleClass().add("button-raised");
        changeBtn.getStyleClass().add("button-raised");
        deleteBtn.getStyleClass().add("button-raised");
        mockProductionResult.setProductionResultList();


    }



    /*
    orderResult is stored in Controller
     */
    @FXML
    public List<OrderResult> getOrderResults() {
        //List<OrderResult> orderResults = new ArrayList<>();
        Map<String, Item> forecastProductionList = main.getForecastProductionList();

        //TODO change mockdata with real data from production

        List<ProductionResult> productionResults = mockProductionResult.getProductionResultList();

        orderResults = calculateOrders(productionResults, forecastProductionList);

        return orderResults;
    }
    /*
    Change List<OrderResults> in ObservableList
     */
    public ObservableList<OrderResult> getOrderData() {
        //ObservableList<OrderResult> results = FXCollections.observableArrayList();
        List<OrderResult> orderList = this.getOrderResults();

        for (OrderResult orderResult : orderList) {

            String itemConfigId = orderResult.getItemConfigId();
            int quantity = orderResult.getQuantity();
            int orderMode = orderResult.getOrderingMode();
            int deliveryCosts = orderResult.getDeliveryCosts();
            double time = orderResult.getDeliveryTime();
            double variance = orderResult.getVariance();
            int discount = orderResult.getDiscountQuantity();

            results.add(new OrderResult(itemConfigId, quantity, orderMode, deliveryCosts, discount, time, variance));
        }
        return results;
    }

    @FXML
    private void getData() {

        nrColumn.setCellValueFactory(new PropertyValueFactory<>("itemConfigId"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        optionColumn.setCellValueFactory(new PropertyValueFactory<>("orderingMode"));

        orderTableView.setItems(getOrderData());
        String itemConfigId = "K21";
        showOrderDetails(null);

        orderTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> showOrderDetails(newValue));

    }

    @FXML
    public void handleChangeOrder() {

    }
    //maybe updateData for xml input ??
    @FXML
    public void handleDeleteOrder() {
        int selectedIndex = orderTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            orderTableView.getItems().remove(selectedIndex);
            System.out.println("INDEX "+selectedIndex);
            //Delete selected Item from orderResults
            orderResults.remove(selectedIndex);

            for(OrderResult orderResult : orderResults) {
                System.out.println(orderResult.getItemConfigId());
            }

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(getI18NText(I18N.ALERT_TITLE_ERROR_NOT_SELECTED));
            alert.setHeaderText(getI18NText(I18N.ALERT_HEAD_NO_ORDER_SELECTED));
            alert.setContentText(getI18NText(I18N.ALERT_TEXT_NO_ORDER_SELECTED));

            alert.showAndWait();
        }
    }

    @FXML
    public void handleNewOrder() {
        OrderResult tmpOrder = new OrderResult();
        /*
        tmpOrder.setItemConfigId("K21");
        tmpOrder.setDiscountQuantity(1200);
        tmpOrder.setQuantity(100);
        tmpOrder.setDeliveryTime(1.2);
        tmpOrder.setVariance(0.2);
        tmpOrder.setOrderingMode(5);
        tmpOrder.setDeliveryCosts(50);
        */
        results.add(tmpOrder);
        orderTableView.setItems(results);
        nrColumn.setEditable(true);
    }

    private void showOrderDetails(OrderResult orderResult) {
        if(orderResult != null) {
            nrL.setText(orderResult.getItemConfigId());
            quantityL.setText(String.valueOf(orderResult.getQuantity()));
            stockL.setText(getStockAmount(orderResult.getItemConfigId()));
            discontL.setText(String.valueOf(orderResult.getDiscountQuantity()));
            if(orderResult.getOrderingMode()==FAST_DELIVERY) {
                expressCB.setSelected(true);
            }
            else expressCB.setSelected(false);
        }
        else {
            nrL.setText("");
            quantityL.setText("");
            stockL.setText("");
            discontL.setText("");
        }
    }

    private String getStockAmount(String itemConfigId) {
        String stock = "";
        itemConfigId = itemConfigId.substring(1);
        stock = String.valueOf(main.getXmlInputData().getWareHouseArticles().get(itemConfigId).getAmount());
        return stock;
    }

    //TODO Store Data for xml export
    private void storeData() {
        //List<OrderResult> orderResults;
        List<Order> orderList = new ArrayList<>();

        for(OrderResult orderResult : orderResults) {
            orderList.add(new Order(orderResult.getItemConfigId(), orderResult.getQuantity(), orderResult.getOrderingMode()));
        }
        main.setOrderList(orderList);
    }


    public void initUIComponents() {
        nrLabel.setText(main.getTranslation().getString(I18N.NRCOLUMN));
        quantityLabel.setText(main.getTranslation().getString(I18N.QUANTITYLABEL));
        stockLabel.setText(main.getTranslation().getString(I18N.STOCKLABEL));
        avgLabel.setText(main.getTranslation().getString(I18N.AVGLABEL));
        optionLabel.setText(main.getTranslation().getString(I18N.OPTIONLABEL));
        discontLabel.setText(main.getTranslation().getString(I18N.DISCONTLABEL));
        nrColumn.setText(main.getTranslation().getString(I18N.NRCOLUMN));
        quantityColumn.setText(main.getTranslation().getString(I18N.QUANTITYLABEL));
        optionColumn.setText(main.getTranslation().getString(I18N.OPTIONLABEL));
        newBtn.setText(main.getTranslation().getString(I18N.NEWBTN));
        changeBtn.setText(main.getTranslation().getString(I18N.CHANGEBTN));
        deleteBtn.setText(main.getTranslation().getString(I18N.DELETEBTN));
        quantityL.setText(main.getTranslation().getString(I18N.QUANTITYL));
        stockL.setText(main.getTranslation().getString(I18N.STOCKL));
        avgL.setText(main.getTranslation().getString(I18N.AVGL));
        discontL.setText(main.getTranslation().getString(I18N.AVGL));
    }

    public List<OrderResult> calculateOrders(List<ProductionResult> productionResults, Map<String, Item> forecastProductionList) {

        List<OrderResult> orderResults = new ArrayList<>();

        List<Map<String, Integer>> kUsageList = orderService.calculateConsumption(orderService.calculateProgramm(productionResults, forecastProductionList));

        try {
            List<TermsOfSaleData> terms = orderDB.findAll();

            for(TermsOfSaleData term : terms) {
                String itemConfigId = term.getItemConfigId();

                double avg = orderService.calculateAverage(kUsageList, itemConfigId);
                double max = orderService.calculateMaxUsage(kUsageList, itemConfigId);
                double orderpoint = avg * (term.getDeliveryTime() + term.getVariance() + REPLACEMENT_TIME);

                String itemConfigSub = itemConfigId.substring(1);
                double stock = main.getXmlInputData().getWareHouseArticles().get(itemConfigSub).getAmount();

                double stockRange = Math.round(stock/avg);
                if(stock <= orderpoint) {
                    double maxDeliveryTime = term.getVariance() + term.getDeliveryTime();
                    //int orderQuantity = (int) Math.round((avg* term.getDeliveryTime() + max * maxDeliveryTime)/2);
                    int orderQuantity = (int) Math.round((avg* maxDeliveryTime + max * maxDeliveryTime)/2);

                    int orderMode;
                    if (stockRange/maxDeliveryTime<=1) {
                        orderMode = FAST_DELIVERY;
                    } else {
                        orderMode = NORMAL_DELIVERY;
                    }

                    double deliveryTime = term.getDeliveryTime();
                    int discont = term.getDiscountQuantity();
                    int orderingCosts = term.getOrderingCosts();
                    double variance = term.getVariance();

                    orderResults.add(new OrderResult(itemConfigId, orderQuantity, orderMode, orderingCosts, discont, deliveryTime, variance));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        for(OrderResult res : orderResults){
            System.out.println("ID "+res.getItemConfigId());
            System.out.println("Q "+res.getQuantity());
            System.out.println("MODE "+res.getOrderingMode());
        }
        */
        return orderResults;
    }

}
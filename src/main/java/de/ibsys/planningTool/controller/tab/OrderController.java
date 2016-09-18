package de.ibsys.planningTool.controller.tab;


import de.ibsys.planningTool.controller.MainController;
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
import de.ibsys.planningTool.util.Dialogs.DialogMessages;
import de.ibsys.planningTool.util.I18N;
import javafx.application.Platform;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;


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
    private TableColumn<OrderResult, Integer> optionColumn;

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

    private List<OrderResult> orderResults = new ArrayList<>();
    private ObservableList<OrderResult> results = FXCollections.observableArrayList();

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

        orderTableView.setEditable(true);
        //TODO I1N8

        /*
        Callback<TableColumn<OrderResult, String>,
                TableCell<OrderResult, String>> cellFactory
                = (TableColumn<OrderResult, String> cell) -> new EditingCell();

        nrColumn.setCellFactory(cellFactory);
        nrColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<OrderResult, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<OrderResult, String> event) {
                        ((OrderResult) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setItemConfigId(event.getNewValue());
                    }
                }
        );
  */
        Callback<TableColumn<OrderResult, Integer>,
                TableCell<OrderResult, Integer>> cellIntFactory
                = (TableColumn<OrderResult, Integer> cell) -> new IntegerEditingCell();

        quantityColumn.setCellFactory(cellIntFactory);

        //quantityColumn.setCellFactory(TextFieldTableCell.<OrderResult, Integer>forTableColumn(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<OrderResult, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<OrderResult, Integer> event) {
                        ((OrderResult) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setQuantity(event.getNewValue());

                    }
                }
        );
        /*
        Callback<TableColumn<OrderResult, Integer>,
                TableCell<OrderResult, Integer>> cellIntNrFactory
                = (TableColumn<OrderResult, Integer> cell) -> new IntegerNrEditinCell();
        */
        optionColumn.setCellFactory(TextFieldTableCell.<OrderResult, Integer>forTableColumn(new IntegerStringConverter()));
        //optionColumn.setCellFactory(cellIntNrFactory);
        optionColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<OrderResult, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<OrderResult, Integer> event) {
                        ((OrderResult) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setOrderingMode(event.getNewValue());
                    }
                }
        );
    }

    /*
    orderResult is stored in Controller
     */
    @FXML
    public List<OrderResult> getOrderResults() {
        //List<OrderResult> orderResults = new ArrayList<>();
        try {
            Map<String, Item> forecastProductionList = main.getForecastProductionList();

            //TODO change mockdata with real data from production

            List<ProductionResult> productionResults = mockProductionResult.getProductionResultList();

            orderResults = calculateOrders(productionResults, forecastProductionList);
            }
        catch(Exception e) {
            e.printStackTrace();
        }
        return orderResults;
    }
    /*
    Change List<OrderResults> in ObservableList
     */
    public ObservableList<OrderResult> getOrderData() {
        ObservableList<OrderResult> results = FXCollections.observableArrayList();
        List<OrderResult> orderList = this.getOrderResults();

        for (OrderResult orderResult : orderList) {
            if(orderResult.getQuantity() != 0) {
                String itemConfigId = orderResult.getItemConfigId();
                int quantity = orderResult.getQuantity();
                int orderMode = orderResult.getOrderingMode();
                int deliveryCosts = orderResult.getDeliveryCosts();
                double time = orderResult.getDeliveryTime();
                double variance = orderResult.getVariance();
                int discount = orderResult.getDiscountQuantity();

                results.add(new OrderResult(itemConfigId, quantity, orderMode, deliveryCosts, discount, time, variance));
            }
        }
        return results;
    }
    /**
     * fill data in tableView
     */
    @FXML
    private void getData() {

        nrColumn.setCellValueFactory(new PropertyValueFactory<>("itemConfigId"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        optionColumn.setCellValueFactory(new PropertyValueFactory<>("orderingMode"));
        results = getOrderData();
        orderTableView.setItems(results);
        showOrderDetails(null);

        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                orderTableView.requestFocus();
                orderTableView.getSelectionModel().select(0);
                orderTableView.getFocusModel().focus(0);
            }
        });

        orderTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> showOrderDetails(newValue));

        orderTableView.setRowFactory(new Callback<TableView<OrderResult>, TableRow<OrderResult>>() {
            @Override
            public TableRow<OrderResult> call(TableView<OrderResult> param) {
                final  TableRow<OrderResult> row = new TableRow<OrderResult>();
                final ContextMenu menu = new ContextMenu();
                final MenuItem removeItem = new MenuItem(main.getTranslation().getString(I18N.DELETEBTN));
                final MenuItem addItem = new MenuItem(main.getTranslation().getString(I18N.NEWBTN));
                removeItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        orderTableView.getItems().remove(row.getItem());
                        String itemConfigId = row.getItem().getItemConfigId();

                        for(OrderResult order : orderResults) {
                            if(order.getItemConfigId().equals(itemConfigId)) {
                                order.setQuantity(0);
                                order.setOrderingMode(0);
                            }
                        }
                        storeData();
                        /*
                        for(OrderResult or : results) {

                            System.out.println("Nach Löschen RESULT "+or.getItemConfigId() + " " + or.getQuantity());
                        }
                        for(OrderResult order : orderResults) {

                            System.out.println("Nach LÖSCHEN ORDERRESULT "+order.getItemConfigId() + " " + order.getQuantity() );
                        }
                        */
                    }
                });

                addItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        handleNewOrder();
                    }
                });


                menu.getItems().add(removeItem);
                menu.getItems().add(addItem);

                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                        .then((ContextMenu)null)
                        .otherwise(menu)
                );

                return row;
            }
        });
        storeData();
    }

    @FXML
    void handleDeleteOrder(ActionEvent event) {
        /*
        for(OrderResult or : results) {
            System.out.println("VOR LÖSCHEN RESULT "+or.getItemConfigId() + " " + or.getQuantity() );
        }
        for(OrderResult order : orderResults) {
            System.out.println("VOR LÖSCHEN ORDERRESULT "+order.getItemConfigId() + " " + order.getQuantity() );
        }
        */
        // Get selected row and delete
        int ix = orderTableView.getSelectionModel().getSelectedIndex();
        OrderResult oneOrder = (OrderResult) orderTableView.getSelectionModel().getSelectedItem();
        String itemConfigId = orderTableView.getSelectionModel().getSelectedItem().getItemConfigId();

        results.remove(ix);
        oneOrder.setQuantity(0);
        oneOrder.setOrderingMode(0);

        System.out.println("ID "+itemConfigId);

        for(OrderResult order : orderResults) {
            if(order.getItemConfigId().equals(itemConfigId)) {
                order.setQuantity(0);
                order.setOrderingMode(0);
            }
        }

        //System.out.println(orderResults.get(ix).getItemConfigId() + " " + orderResults.get(ix).getQuantity());
        /*
        for(OrderResult or : results) {

            System.out.println("Nach Löschen RESULT "+or.getItemConfigId() + " " + or.getQuantity());
        }
        for(OrderResult order : orderResults) {

            System.out.println("Nach LÖSCHEN ORDERRESULT "+order.getItemConfigId() + " " + order.getQuantity() );
        }
        */

        if (orderTableView.getItems().size() == 0) {

            System.out.println("No data in table !");
            return;
        }
        if (ix != 0) {

            ix = ix -1;
        }
        orderTableView.requestFocus();
        orderTableView.getSelectionModel().select(ix);
        orderTableView.getFocusModel().focus(ix);
    }
    /*
    //maybe updateData for xml input ??
    @FXML
    public void handleDeleteOrder() {
        int selectedIndex = orderTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            orderTableView.getItems().remove(selectedIndex);
            System.out.println("INDEX "+selectedIndex);
            //Delete selected Item from orderResults
            //OrderResult order = (OrderResult) orderTableView.getSelectionModel().getSelectedItem();
            orderResults.remove(selectedIndex);
            //results.remove(selectedIndex);
            orderTableView.setItems(results);

            for(OrderResult orderResult : orderResults) {
                System.out.println(orderResult.getItemConfigId() +  " " + orderResult.getQuantity());
            }

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(getI18NText(I18N.ALERT_TITLE_ERROR_NOT_SELECTED));
            alert.setHeaderText(getI18NText(I18N.ALERT_HEAD_NO_ORDER_SELECTED));
            alert.setContentText(getI18NText(I18N.ALERT_TEXT_NO_ORDER_SELECTED));

            alert.showAndWait();
        }
    }
    */

    @FXML
    public void handleNewOrder() {

        ChoiceDialog<String> dialog = new ChoiceDialog<>("-", setCombobox());
        dialog.setTitle("New Order");
        dialog.setHeaderText("New Order");
        dialog.setContentText("Choose your item:");

        Optional<String> result = dialog.showAndWait();

        OrderResult tmpOrder = new OrderResult();
        String itemConfigId = "";

        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
            itemConfigId = result.get();

            for(OrderResult order : orderResults) {
                if (order.getItemConfigId().equals(itemConfigId)) {

                    results.add(order);
                }
            }
            //results.add(tmpOrder);
        }
        else {
         //   DialogMessages.ErrorDialog("Keine Auswahl!");
        }
        for(OrderResult orderResult : orderResults) {
            System.out.println(orderResult.getItemConfigId() + " " + orderResult.getQuantity());
        }
        //refresh results
        orderTableView.setItems(results);

        }

        protected List<String> setCombobox() {
        List<String> choices = new ArrayList<>();

        for(OrderResult order : orderResults) {
            if(order.getQuantity()==0 && order.getOrderingMode() == 0) {
               choices.add(order.getItemConfigId());
            }
        }
        return choices;
    }

    private void showOrderDetails(OrderResult orderResult) {
        if(orderResult != null) {
            nrL.setText(orderResult.getItemConfigId());
            quantityL.setText(String.valueOf(orderResult.getQuantity()));
            stockL.setText(getStockAmount(orderResult.getItemConfigId()));
            discontL.setText(String.valueOf(orderResult.getDiscountQuantity()));
            avgL.setText(String.valueOf(orderService.calculateOrderCosts(orderResult))+"€");
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
        String itemConfigId = "";

        for(OrderResult orderResult : results) {
            itemConfigId = orderResult.getItemConfigId().substring(1);

            orderList.add(new Order(itemConfigId, orderResult.getQuantity(), orderResult.getOrderingMode()));
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
                double deliveryTime = term.getDeliveryTime();
                int discont = term.getDiscountQuantity();
                int orderingCosts = term.getOrderingCosts();
                double variance = term.getVariance();
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
                    /*
                    double deliveryTime = term.getDeliveryTime();
                    int discont = term.getDiscountQuantity();
                    int orderingCosts = term.getOrderingCosts();
                    double variance = term.getVariance();
                    */
                    orderResults.add(new OrderResult(itemConfigId, orderQuantity, orderMode, orderingCosts, discont, deliveryTime, variance));
                }
                else orderResults.add(new OrderResult(itemConfigId, 0, 0, orderingCosts, discont, deliveryTime, variance));
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


    public class IntegerEditingCell extends TableCell<OrderResult, Integer> {

        private final TextField textField = new TextField();
        private final Pattern intPattern = Pattern.compile("-?\\d+");

        public IntegerEditingCell() {
            textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (! isNowFocused) {
                    processEdit();
                }
            });
            textField.setOnAction(event -> processEdit());
        }

        private void processEdit() {
            String text = textField.getText();
            if (intPattern.matcher(text).matches()) {
                commitEdit(Integer.parseInt(text));
            } else {
                cancelEdit();
            }
        }

        @Override
        public void updateItem(Integer value, boolean empty) {
            super.updateItem(value, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else if (isEditing()) {
                setText(null);
                textField.setText(value.toString());
                setGraphic(textField);
            } else {
                setText(value.toString());
                setGraphic(null);
            }
        }

        @Override
        public void startEdit() {
            super.startEdit();
            Integer value = getItem();
            if (value != null) {
                    textField.setText(value.toString());
                    setGraphic(textField);
                    setText(null);}
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getItem().toString());
            setGraphic(null);
        }

        // This seems necessary to persist the edit on loss of focus; not sure why:
        @Override
        public void commitEdit(Integer value) {
            if(value >= 0) {
                super.commitEdit(value);
                ((OrderResult) this.getTableRow().getItem()).setQuantity(value.intValue());
                storeData();
            }
            else {
                textField.setStyle("-fx-border-color: red;");
                DialogMessages.ErrorDialog("Die Bestellmenge muss größer 0 sein!");
            }
        }
    }
    /*
    public class IntegerNrEditinCell extends IntegerEditingCell {

        @Override
        public void commitEdit(Integer value) {
            if(value == 0 || value == 4 || value == 5) {
                super.commitEdit(value);
                ((OrderResult) this.getTableRow().getItem()).setOrderingMode(value.intValue());
            }
            else {
                DialogMessages.ErrorDialog("G");
            }
        }
    }
    */
}
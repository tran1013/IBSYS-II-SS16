package de.ibsys.planningTool.controller.tab;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.database.OrderDB;
import de.ibsys.planningTool.model.*;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlExportModel.Order;
import de.ibsys.planningTool.model.xmlInputModel.FutureInComingOrder;
import de.ibsys.planningTool.service.OrderService;
import de.ibsys.planningTool.util.Dialogs.DialogMessages;
import de.ibsys.planningTool.util.I18N;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;


import static de.ibsys.planningTool.model.Constants.FAST_DELIVERY;
import static de.ibsys.planningTool.model.Constants.NORMAL_DELIVERY;
import static de.ibsys.planningTool.model.Constants.REPLACEMENT_TIME;

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
    private TableColumn<OrderResult, Boolean> checkBoxOptionColumn;

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
    private JFXButton deleteBtn;

    OrderService orderService = new OrderService();
    OrderDB orderDB = new OrderDB();

    private List<OrderResult> orderResults = new ArrayList<>();
    public ObservableList<OrderResult> results = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @Override
    public void init(MainController main) {
        super.init(main);
        newBtn.getStyleClass().add("button-raised");
        deleteBtn.getStyleClass().add("button-raised");

        orderTableView.setEditable(true);

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

        //checkBoxOptionColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxOptionColumn));
        Callback<TableColumn<OrderResult, Boolean>,
                TableCell<OrderResult, Boolean>> cellBoolFactory
                = (TableColumn<OrderResult, Boolean> cell) -> new BooleanCheckBoxCell();

        checkBoxOptionColumn.setCellFactory(cellBoolFactory);

    }

    /*
    orderResult is stored in Controller
     */
    @FXML
    private List<OrderResult> getOrderResults() {
        //List<OrderResult> orderResults = new ArrayList<>();
        try {
            Map<String, Item> forecastProductionList = main.getForecastProductionList();

            List<Item> items = main.getProductionList();

            orderResults = calculateOrders(items, forecastProductionList);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return orderResults;
    }
    /*
    Change List<OrderResults> in ObservableList
     */
    private ObservableList<OrderResult> getOrderData() {
        ObservableList<OrderResult> results = FXCollections.observableArrayList();
        List<OrderResult> orderList = this.getOrderResults();

        for (OrderResult orderResult : orderList) {
            if(orderResult.getQuantity() != 0) {
                String itemConfigId = orderResult.getItemConfigId();
                int quantity = orderResult.getQuantity();
                boolean orderMode = orderResult.isDeliveryMode();
                int deliveryCosts = orderResult.getDeliveryCosts();
                double time = orderResult.getDeliveryTime();
                double variance = orderResult.getVariance();
                int discount = orderResult.getDiscountQuantity();
                //boolean deliveryMode;
                /*
                if(orderMode==FAST_DELIVERY) {
                    deliveryMode = true;
                }
                else {
                    deliveryMode = false;
                }
                */
                results.add(new OrderResult(itemConfigId, quantity, deliveryCosts, discount, time, variance, orderMode));
            }
        }

        return results;
    }
    /**
     * fill data in tableView
     */
    @FXML
    public void getData() {

        nrColumn.setCellValueFactory(new PropertyValueFactory<>("itemConfigId"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        checkBoxOptionColumn.setCellValueFactory(new PropertyValueFactory<OrderResult, Boolean>("deliveryMode"));

        results = getOrderData();
        orderTableView.setItems(results);
        showOrderDetails(null);
        //focus and select the first row of the table
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

        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem addItem = new MenuItem(main.getTranslation().getString(I18N.NEWBTN));
        contextMenu.getItems().add(addItem);
        orderTableView.setContextMenu(contextMenu);

        addItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleNewOrder();
            }
        });

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
                                //order.setOrderingMode(0);
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
            System.out.println("VOR LÖSCHEN RESULT "+or.getItemConfigId() + " " + or.getQuantity() + " " + or.isDeliveryMode());
        }
        for(OrderResult order : orderResults) {
            System.out.println("VOR LÖSCHEN ORDERRESULT "+order.getItemConfigId() + " " + order.getQuantity() + " " + order.isDeliveryMode() );
        }
        */
        // Get selected row and delete
        int ix = orderTableView.getSelectionModel().getSelectedIndex();
        OrderResult oneOrder = (OrderResult) orderTableView.getSelectionModel().getSelectedItem();
        String itemConfigId = orderTableView.getSelectionModel().getSelectedItem().getItemConfigId();

        results.remove(ix);
        oneOrder.setQuantity(0);
        //oneOrder.setOrderingMode(0);
        oneOrder.setDeliveryMode(false);

        //System.out.println("ID "+itemConfigId);

        for(OrderResult order : orderResults) {
            if(order.getItemConfigId().equals(itemConfigId)) {
                order.setQuantity(0);
                //order.setOrderingMode(0);
                order.setDeliveryMode(false);
            }
        }

        storeData();

        //System.out.println(orderResults.get(ix).getItemConfigId() + " " + orderResults.get(ix).getQuantity());
        /*
        for(OrderResult or : results) {

            System.out.println("Nach Löschen RESULT "+or.getItemConfigId() + " " + or.getQuantity() + " " + or.isDeliveryMode() );
        }
        for(OrderResult order : orderResults) {

            System.out.println("Nach LÖSCHEN ORDERRESULT "+order.getItemConfigId() + " " + order.getQuantity() +  " " + order.isDeliveryMode());
        }


        if (orderTableView.getItems().size() == 0) {

            System.out.println("No data in table !");
            return;
        }
        */
        if (ix != 0) {

            ix = ix -1;
        }

        orderTableView.requestFocus();
        orderTableView.getSelectionModel().select(ix);
        orderTableView.getFocusModel().focus(ix);
    }

    @FXML
    public void handleNewOrder() {

        ChoiceDialog<String> dialog = new ChoiceDialog<>("-", setCombobox());
        dialog.setTitle(main.getTranslation().getString(I18N.ALERT_TITLE_ERROR_NEW_ORDER));
        dialog.setHeaderText(main.getTranslation().getString(I18N.ALERT_HEAD_NEW_ORDER));
        dialog.setContentText(main.getTranslation().getString(I18N.ALERT_TEXT_NEW_ORDER));

        Optional<String> result = dialog.showAndWait();

        OrderResult tmpOrder = new OrderResult();
        String itemConfigId = "";

        if (result.isPresent()){
            //System.out.println("Your choice: " + result.get());
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
        } /*
        for(OrderResult orderResult : orderResults) {
            System.out.println(orderResult.getItemConfigId() + " " + orderResult.getQuantity() +  " " + orderResult.isDeliveryMode());
        } */
        //refresh results
        orderTableView.setItems(results);
        storeData();

    }

    protected List<String> setCombobox() {
        List<String> choices = new ArrayList<>();

        for(OrderResult order : orderResults) {
            if(order.getQuantity()==0) {
                choices.add(order.getItemConfigId());
            }
        }
        return choices;
    }

    private void showOrderDetails(OrderResult orderResult) {
        if (orderResult != null) {
            nrL.setText(orderResult.getItemConfigId());
            quantityL.setText(String.valueOf(orderResult.getQuantity()));
            stockL.setText(getStockAmount(orderResult.getItemConfigId()));
            discontL.setText(String.valueOf(orderResult.getDiscountQuantity()));
            avgL.setText(String.valueOf(orderService.calculateOrderCosts(orderResult)) + "€");

            if(orderResult.isDeliveryMode()==true) {
                expressCB.setSelected(true);
            }
            else expressCB.setSelected(false);
        }
        /*
        else{
                nrL.setText("");
                quantityL.setText("");
                stockL.setText("");
                discontL.setText("");
            }
            */
    }

    private String getStockAmount(String itemConfigId) {
        String stock = "";
        itemConfigId = itemConfigId.substring(1);
        stock = String.valueOf(main.getXmlInputData().getWareHouseArticles().get(itemConfigId).getAmount());
        return stock;
    }

    public double calculateStockRange(List<Map<String, Integer>> kUsageList, TermsOfSaleData terms) {
        double stockRange = 0.0;
        String itemConfigId = terms.getItemConfigId();

        double avg = orderService.calculateAverage(kUsageList, terms.getItemConfigId());
        double stock = main.getXmlInputData().getWareHouseArticles().get(itemConfigId).getAmount() + getFutureInComingOrderAmount(itemConfigId);

        stockRange = Math.round(stock/avg);
        //System.out.println("STOCKRANGE" + stockRange);

        return stockRange;
    }

    public int getFutureInComingOrderAmount(String itemConfigId) {
        int incomingAmount = 0;
        Map<String, FutureInComingOrder> futureInComingOrderMap = main.getXmlInputData().getFutureInComingOrderMap();
        if(futureInComingOrderMap == null ||futureInComingOrderMap.isEmpty()) {
            return 0;
        }
        else {
            for(Map.Entry<String, FutureInComingOrder> entry : futureInComingOrderMap.entrySet()) {
                if(entry.getKey().equals(itemConfigId.substring(1))) {
                    int period = main.getXmlInputData().getPeriod();
                    int orderPeriod = entry.getValue().getOrderPeriode();
                    incomingAmount += entry.getValue().getAmount();
                    double variance = 0.0;
                    double time = 0.0;
                    for(OrderResult order : results) {
                        if(order.getItemConfigId().equals(itemConfigId.substring(1))) {
                            variance = order.getVariance();
                            time = order.getDeliveryTime();
                        }
                    }
                    if(entry.getValue().getOrderPeriode() == 5) {
                        if(period >= orderPeriod+time) {
                            incomingAmount += entry.getValue().getAmount();
                        }
                    }
                    else {
                        if(period >= orderPeriod+(time/2)) {
                            incomingAmount += entry.getValue().getAmount();
                        }
                    }
                    /*
                    System.out.println(incomingAmount + " " + entry.getValue().getArticleId() + " " + entry.getValue().getOrderPeriode()
                            + " " + entry.getValue().getMode() );
                    */
                }
            }
            return incomingAmount;
        }
    }

    //TODO Store Data for xml export
    public void storeData() {
        //List<OrderResult> orderResults;
        List<Order> orderList = new ArrayList<>();
        String itemConfigId = "";

        for(OrderResult orderResult : results) {
            itemConfigId = orderResult.getItemConfigId().substring(1);
            int mode;
            if(orderResult.isDeliveryMode() == true) {
                mode = FAST_DELIVERY;
            }
            else {
                mode = NORMAL_DELIVERY;
            }
            orderList.add(new Order(itemConfigId, orderResult.getQuantity(), mode));
        }
        main.setOrderList(orderList);
    }

    public void synchroResults() {
        for(OrderResult result : results) {
            for(OrderResult order : orderResults) {
                if(result.getItemConfigId().equals(order.getItemConfigId())) {
                    order.setDeliveryMode(result.isDeliveryMode());
                }
            }

        }
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
        newBtn.setText(main.getTranslation().getString(I18N.NEWBTN));
        deleteBtn.setText(main.getTranslation().getString(I18N.DELETEBTN));
        quantityL.setText(main.getTranslation().getString(I18N.QUANTITYL));
        stockL.setText(main.getTranslation().getString(I18N.STOCKL));
        avgL.setText(main.getTranslation().getString(I18N.AVGL));
        discontL.setText(main.getTranslation().getString(I18N.AVGL));
        checkBoxOptionColumn.setText(main.getTranslation().getString(I18N.OPTIONLABEL));
    }

    public List<OrderResult> calculateOrders(List<Item> productionResults, Map<String, Item> forecastProductionList) {

        List<OrderResult> orderResults = new ArrayList<>();

        List<Map<String, Integer>> kUsageList = orderService.calculateConsumption(orderService.calculateProgrammNew(productionResults, forecastProductionList));

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
                double stock = main.getXmlInputData().getWareHouseArticles().get(itemConfigSub).getAmount() + getFutureInComingOrderAmount(itemConfigId);

                double stockRange = Math.round(stock/avg);
                if(stock <= orderpoint) {
                    double maxDeliveryTime = term.getVariance() + term.getDeliveryTime();
                    //int orderQuantity = (int) Math.round((avg* term.getDeliveryTime() + max * maxDeliveryTime)/2);
                    int orderQuantity = (int) Math.round((avg* maxDeliveryTime + max * maxDeliveryTime)/2);

                    boolean deliveryMode;
                    int orderMode;
                    if (stockRange/maxDeliveryTime<=1) {

                        deliveryMode = true;
                    } else {
                        deliveryMode = false;
                    }
                    /*
                    double deliveryTime = term.getDeliveryTime();
                    int discont = term.getDiscountQuantity();
                    int orderingCosts = term.getOrderingCosts();
                    double variance = term.getVariance();
                    */
                    orderResults.add(new OrderResult(itemConfigId, orderQuantity, orderingCosts, discont, deliveryTime, variance, deliveryMode));
                }
                else orderResults.add(new OrderResult(itemConfigId, 0, orderingCosts, discont, deliveryTime, variance, false));
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

        for(OrderResult order : orderResults) {
            System.out.println(order.getItemConfigId()+" "  + order.isDeliveryMode() + "" );
        }
    */
        return orderResults;
    }

    public List<ProductionResult> mapToProductionResults(List<Item> items) {

        List<ProductionResult> res = new ArrayList<>();

        for(Item item : items) {
            if(item.getArticleId().equals("1") || item.getArticleId().equals("2") || item.getArticleId().equals("1") || item.getArticleId()=="1") {
                String itemConfigId = "P" + item.getArticleId();
                System.out.println(itemConfigId);
                res.add(new ProductionResult(itemConfigId, item.getQuantity()));
            }
            else {
                String itemConfidId = "E" + item.getArticleId();
                System.out.println(itemConfidId);
                res.add(new ProductionResult(itemConfidId, item.getQuantity()));
            }
        }

        for(ProductionResult re : res) {
            System.out.println(re.getItemConfigId() + " " + re.getQuantity());
        }

        return  res;
    }

    public List<ProductionResult> getMappedList(List<Item> productionResult) {
        List<ProductionResult> List = new ArrayList<>();

        for (Integer i = 0; i < productionResult.size(); i++) {
            if (productionResult.get(i).getArticleId().equals("1") || productionResult.get(i).getArticleId().equals("2") || productionResult.get(i).getArticleId().equals("3")) {
                String itemID = "P" + productionResult.get(i).getArticleId();
                List.add(i, new ProductionResult(itemID, productionResult.get(i).getQuantity()));
            } else {
                String itemID = "E" + productionResult.get(i).getArticleId();
                List.add(i, new ProductionResult(itemID, productionResult.get(i).getQuantity()));
            }

        }
        System.out.println(List);
        return List;
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

        @Override
        public void commitEdit(Integer value) {
            if(value >= 0) {
                super.commitEdit(value);
                ((OrderResult) this.getTableRow().getItem()).setQuantity(value.intValue());
                storeData();
            }
            else {
                textField.setStyle("-fx-border-color: red;");
                DialogMessages.ErrorDialog(main.getTranslation().getString(I18N.DIGIT_SMALLER_ZERO));
            }
        }
    }

    public class BooleanCheckBoxCell extends CheckBoxTableCell<OrderResult, Boolean> {

        private final CheckBox checkbox = new CheckBox();

        private  BooleanCheckBoxCell() {
        }

        @Override
        public void startEdit() {
            super.startEdit();
            Boolean item = getItem();
            if(item != null) {
                checkbox.setSelected(item);
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setGraphic(null);
        }

        @Override
        public void updateItem(Boolean item ,boolean empty) {
            super.updateItem(item, empty);
            synchroResults();
            storeData();

            if (empty) {

            } else {
                if (isEditing()) {
                    if (checkBoxOptionColumn != null) {
                        //checkbox.setValue(getTyp());
                        checkbox.setSelected(item.booleanValue());
                        synchroResults();
                        storeData();
                    }
                    setGraphic(checkbox);
                } else {

                }
            }

        }

    }
}
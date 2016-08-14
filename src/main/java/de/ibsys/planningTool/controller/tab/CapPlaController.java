package de.ibsys.planningTool.controller.tab;


import de.ibsys.planningTool.database.capPlaDB;
import de.ibsys.planningTool.mock.sellData;
import de.ibsys.planningTool.model.ProductionSteps;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlExportModel.Order;
import de.ibsys.planningTool.model.xmlInputModel.OrdersInWork;
import de.ibsys.planningTool.model.xmlInputModel.WaitingList;
import de.ibsys.planningTool.model.xmlInputModel.WaitingListWorkPlace;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
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


    XmlInputData input;
    XmlInputController inputCon = new XmlInputController();

    @FXML
    public void getColumns() {

        System.out.println(main.getXmlInputData().getOrdersInWorkMap());

    }

    capPlaDB prod = new capPlaDB();

    @FXML
    public void getTable() {

        List<Integer> workplaceIDs;
        sellData mock = new sellData();
        Map<String, Integer> capResult = new HashMap<>();
        Map<String, Integer> setupTimeList = new HashMap<>();
        Map<String, Integer> ordersInWorkTime = new HashMap<>();


        try {
            workplaceIDs = prod.findWorkplaceID();
            mock.setProdSteps();
            List<ProductionSteps> prodSteps = mock.getProdSteps();


            System.out.println("ScheduledTime:");
            for (int i : workplaceIDs) {
                //workplaceNumber begins by 1
                int capacity = 0;
                int setupTime = 0;
                int scheduledTimeOrdersInWork = 0;
                Integer workplace = null;
                Integer result;
                Integer productionTime = null;

                for (ProductionSteps ps : prod.findByWorkplacID(i)) {

                    workplace = ps.getWorkplaceID();
                    String Item = ps.getItemConfigID();
                    productionTime = ps.getProductionTime();
                    Integer setup = ps.getSetupTime();

                    //   System.out.println("Workplace: " + workplace + " - Item: " + Item + " - ProductionTime: " + productionTime);

                    //TODO: Replace mock datas with real from dispo
                    for (ProductionSteps cap : prodSteps) {

                        String item = cap.getItemConfigID();

                        if (item.equals(Item) && cap.getProductionTime() >= 0) {
                            Integer needTime = cap.getProductionTime();

                            setupTime += setup;
                            capacity += (productionTime * needTime);
                            //    System.out.println("Needtime: " + needTime);
                            //   System.out.println("ItemID: " + item);
                        }
                    }
                    //   System.out.println("Workplace: " + workplace + " - Item: " + Item + " - ProdutionTime: " + productionTime);
                    //  System.out.println("Workplace: " + workplace + " - Capacity: " + capacity + " - SetupTime: " + setupTime);

                    capResult.put(workplace.toString(), capacity);
                    setupTimeList.put(workplace.toString(), setupTime);
                }

                // System.out.println("Finish Workplace: " + i);

                result = this.getAllScheduledTime(workplace);
                result = result*productionTime;
                System.out.println("WorkplaceID: " + workplace + " - " + "Result:" + result);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        System.out.println("SETUPTIME: ");
        System.out.println(Arrays.toString(setupTimeList.entrySet().toArray()));

    }

    @FXML
    public int getScheduledTimesOrdersinWork(Integer workplace) {

        Integer workplaceID = null;
        Integer amount = 0;

        Map<String, OrdersInWork> ordersInWorkMap;
        ordersInWorkMap = main.getXmlInputData().getOrdersInWorkMap();

        if (ordersInWorkMap.size() <= 0) {
            System.out.println("NO XML FILE LOADED!!!");
        } else {
            Map<String, OrdersInWork> order = new TreeMap<>(ordersInWorkMap);

            // System.out.println("OrderInWork:");
            for (Map.Entry<String, OrdersInWork> orderMap : order.entrySet()) {
                //Check workplaceID to get amount
                if (orderMap.getValue().getId().equals(workplace.toString())) {
                    workplaceID = Integer.parseInt(orderMap.getValue().getId());
                    String articleID = orderMap.getValue().getArticleId();
                    amount = orderMap.getValue().getAmount();

                    //   System.out.println("WorkplaceID: " + workplaceID + " ArticleID: " + articleID + " Amount: " + amount);
                }
            }


        }
        return amount;
    }


    public int getWaitinglistAmount(Integer workplace) {

        Integer workplaceID = null;
        Integer amount = 0;

        Map<String, WaitingListWorkPlace> waitingListWorkPlaceMap;
        waitingListWorkPlaceMap = main.getXmlInputData().getWaitingListWorkPlaceMap();

        if (waitingListWorkPlaceMap.size() <= 0) {
            System.out.println("NO XML FILE LOADED!!!");
        } else {
            Map<String, WaitingListWorkPlace> wait = new TreeMap<>(waitingListWorkPlaceMap);

            //  System.out.println("WaitingLists");
            for (WaitingListWorkPlace waitPlace : wait.values()) {
                //Check workplaceID to get amount
                if (waitPlace.getWorkplaceId().equals(workplace.toString())) {
                    workplaceID = Integer.parseInt(waitPlace.getWorkplaceId());
                    for (WaitingList list : waitPlace.getWaitingLists()) {
                        String articleID = list.getArticleId();
                        amount = list.getAmount();

                        //    System.out.println("workplaceID: " + workplaceID + " articleID: " + articleID + " Amount: " + amount);
                    }
                }

            }
        }
        return amount;
    }

    public int getAllScheduledTime(Integer workplace) {
        Integer result = null;
        Integer scheduledOrdersInWork = this.getScheduledTimesOrdersinWork(workplace);
        Integer waitinglistAmount = this.getWaitinglistAmount(workplace);

        result = scheduledOrdersInWork + waitinglistAmount;

        return result;
    }
}




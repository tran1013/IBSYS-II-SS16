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
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
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
    capPlaDB prod = new capPlaDB();

    /**
     * God Method to calculate CapPla shit
     * TODO: return Object from CapPlaResult
     */
    @FXML
    public List<CapPlaResult> calculateCap() {

        List<Integer> workplaceIDs;
        Map<String, Integer> capResult = new HashMap<>();
        Map<String, Integer> setupTimeList = new HashMap<>();
        Map<String, Integer> ordersInWorkTime = new HashMap<>();
        Integer setupTimeLastPeriod = 0;

        List<CapPlaResult> resultCapPla = new ArrayList<>();

        //TODO: Replace mock datas with real from dispo (1/2)
        sellData mock = new sellData();


        try {
            workplaceIDs = prod.findWorkplaceID();

            //TODO: Replace mock datas with real from dispo (2/3)
            mock.setProdSteps();
            List<ProductionSteps> prodSteps = mock.getProdSteps();

            System.out.println();
            System.out.println("Start CAPLA BOSS METHOD!!!");
            System.out.println();

            for (Integer i : workplaceIDs) {
                //workplaceNumber begins by 1
                Integer capacity = 0;
                Integer CapSetupTime = 0;
                Integer workplace = 0;
                Integer result = 0;
                Integer productionTime = 0;
                Integer setup = 0;
                Integer requirePeriod = 0;
                Integer shifts = 0;
                Integer overtime = 0;

                for (ProductionSteps ps : prod.findByWorkplacID(i)) {

                    workplace = ps.getWorkplaceID();
                    String Item = ps.getItemConfigID();
                    productionTime = ps.getProductionTime();
                    setup = ps.getSetupTime();

                    //   System.out.println("Workplace: " + workplace + " - Item: " + Item + " - ProductionTime: " + productionTime);

                    //TODO: Replace mock datas with real from dispo (3/3)
                    for (ProductionSteps cap : prodSteps) {

                        String item = cap.getItemConfigID();

                        if (item.equals(Item) && cap.getProductionTime() >= 0) {
                            Integer needTime = cap.getProductionTime();

                            CapSetupTime += setup;
                            capacity += (productionTime * needTime);
                            //    System.out.println("Needtime: " + needTime);
                            //   System.out.println("ItemID: " + item);
                        }
                    }
                    /*   System.out.println("Workplace: " + workplace + " - Item: " + Item + " - ProdutionTime: " + productionTime);
                     System.out.println("Workplace: " + workplace + " - Capacity: " + capacity + " - SetupTime: " + CapSetupTime);*/

                    capResult.put(workplace.toString(), capacity);
                    setupTimeList.put(workplace.toString(), CapSetupTime);
                }

                // System.out.println("Finish Workplace: " + i);
                System.out.println("Workplace: " + workplace);
                System.out.println("Cap. require: " + capResult.get(workplace.toString()));
                System.out.println("Setup Time: " + setupTimeList.get(workplace.toString()));

                result = this.getAllScheduledTime(workplace);
                result = result * productionTime;
                System.out.println("Cap. require last period: " + result);
                ordersInWorkTime.put(workplace.toString(), result);

                setupTimeLastPeriod = this.getPieceAmountOrdersInWork(workplace);
                setupTimeLastPeriod = setupTimeLastPeriod * setup;
                System.out.println("SetupTime last period: " + setupTimeLastPeriod);
                System.out.println("_________________________");

                requirePeriod = capResult.get(workplace.toString()) + setupTimeList.get(workplace.toString()) + result + setupTimeLastPeriod;
                shifts = this.calculateShifts(requirePeriod);
                overtime = this.calculateOvertime(requirePeriod, shifts);

                resultCapPla.add(new CapPlaResult(workplace, requirePeriod, shifts, overtime));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        /*System.out.println("SETUPTIME: ");
        System.out.println(Arrays.toString(setupTimeList.entrySet().toArray()));*/


        return resultCapPla;
    }

    /**
     * Get the amount of article per workplace in OrdersInWork XML
     *
     * @param workplace
     * @return
     */
    @FXML
    public Integer getOrdersinWorkAmount(Integer workplace) {

        Integer workplaceID = null;
        Integer amount = 0;
        Integer piece = 0;

        Map<String, OrdersInWork> ordersInWorkMap;
        ordersInWorkMap = main.getXmlInputData().getOrdersInWorkMap();
        Map<String, OrdersInWork> order = new TreeMap<>(ordersInWorkMap);

        // System.out.println("OrderInWork:");
        for (Map.Entry<String, OrdersInWork> orderMap : order.entrySet()) {
            //Check workplaceID to get amount
            if (orderMap.getValue().getId().equals(workplace.toString())) {
                piece++;
                workplaceID = Integer.parseInt(orderMap.getValue().getId());
                String articleID = orderMap.getValue().getArticleId();
                amount = orderMap.getValue().getAmount();
                //   System.out.println("WorkplaceID: " + workplaceID + " ArticleID: " + articleID + " Amount: " + amount);
            }
        }



        /*System.out.println("Workplace: " + workplace);
        System.out.println("Piece OrdersInWork: " + piece + " " + "Amount: " + amount);*/
        return amount;
    }

    /**
     * Get the amount of article per workplace in WaitingListWorkstations XML
     *
     * @param workplace
     * @return
     */
    public Integer getWaitinglistAmount(Integer workplace) {

        Integer workplaceID = null;
        Integer amount = 0;

        Map<String, WaitingListWorkPlace> waitingListWorkPlaceMap;
        waitingListWorkPlaceMap = main.getXmlInputData().getWaitingListWorkPlaceMap();
        Map<String, WaitingListWorkPlace> wait = new TreeMap<>(waitingListWorkPlaceMap);

        //  System.out.println("WaitingLists");
        for (WaitingListWorkPlace waitPlace : wait.values()) {
            //Check workplaceID to get amount
            if (waitPlace.getWorkplaceId().equals(workplace.toString()) && waitPlace.getNecessaryTime() > 0) {
                workplaceID = Integer.parseInt(waitPlace.getWorkplaceId());
                for (WaitingList list : waitPlace.getWaitingLists()) {
                    String articleID = list.getArticleId();
                    amount = list.getAmount();
                    //    System.out.println("workplaceID: " + workplaceID + " articleID: " + articleID + " Amount: " + amount);
                }
            }

        }

        /*System.out.println("Piece WaitingList: " + piece + " Amount: " + amount);
        System.out.println("__________________________");
        System.out.println();*/
        return amount;
    }

    /**
     * Get the numbers of pieces from ordersInWork to calculate scheduled setup time from last period
     *
     * @param workplace
     * @return
     */
    public Integer getPieceAmountOrdersInWork(Integer workplace) {
        Integer piece = 0;
        Integer workplaceID = null;
        Map<String, OrdersInWork> ordersInWorkMap;
        ordersInWorkMap = main.getXmlInputData().getOrdersInWorkMap();

        Map<String, OrdersInWork> order = new TreeMap<>(ordersInWorkMap);

        //System.out.println("Amount OrdersInWorK:");
        for (Map.Entry<String, OrdersInWork> orderMap : order.entrySet()) {
            //Check workplaceID to get amount
            if (orderMap.getValue().getId().equals(workplace.toString())) {
                piece++;
            }
        }
        //System.out.println("Workplace: " + workplace + " - Piece: " + piece);
        return piece;
    }

    /**
     * !!!DON'T DELETE THIS METHOD!!!
     * Not clear whether this method is important, because it's enough to get the amount from getPieceAmountOrdersInWork()
     * Get the numbers of pieces from WaitingListWorkstations XML to calculate scheduled setup time from last period
     * @param workplace
     * @return
     */
/*    public Integer getPieceAmountWaitingList(Integer workplace){
        Integer piece = 0;

        Map<String, WaitingListWorkPlace> waitingListWorkPlaceMap;
        waitingListWorkPlaceMap = main.getXmlInputData().getWaitingListWorkPlaceMap();
        Map<String, WaitingListWorkPlace> wait = new TreeMap<>(waitingListWorkPlaceMap);

        //System.out.println("Amount WaitingList");
        for (WaitingListWorkPlace waitPlace : wait.values()) {
            //Check workplaceID to get amount
            if (waitPlace.getWorkplaceId().equals(workplace.toString()) && waitPlace.getNecessaryTime() > 0) {
                for (WaitingList list : waitPlace.getWaitingLists()) {
                    piece++;
                }
            }

        }
        System.out.println("Workplace: " + workplace + " - Piece: " + piece);
        return piece;
    }*/

    /**
     * Get all needed times back to calculate capacity requirement of last period
     *
     * @param workplace
     * @return
     */
    public Integer getAllScheduledTime(Integer workplace) {
        Integer result = null;
        Integer scheduledOrdersInWork = this.getOrdersinWorkAmount(workplace);
        Integer waitinglistAmount = this.getWaitinglistAmount(workplace);

        result = scheduledOrdersInWork + waitinglistAmount;

        return result;
    }

    /**
     * Start get CapPla results from here
     */
    @FXML
    public void getMasterResult() {

        List<CapPlaResult> result;

        result = this.calculateCap();

        System.out.println("Print list");
        System.out.println(result);

    }

    /**
     * Calculate shifts per workplace
     * @param requirePeriod
     * @return
     */
    public Integer calculateShifts(Integer requirePeriod) {

        if (requirePeriod <= 0) {
            return 0;
        }
        if (requirePeriod >= 1 && requirePeriod <= 3600) {
            return 1;
        }
        if (requirePeriod >= 3601 && requirePeriod <= 6000) {
            return 2;
        }
        if (requirePeriod >= 6001) {
            return 3;
        }

        return -1;
    }

    /**
     * Calculate overtime per workplace
     * @param reqCapacity
     * @param shifts
     * @return
     */
    protected int calculateOvertime(int reqCapacity, int shifts) {
        if (shifts == 3) {
            return 0;
        }

        int overtime = reqCapacity - (shifts * Constants.WORKPLACE_CAPACITY);
        if (overtime <= 0) {
            return 0;
        }
        return overtime;
    }


}




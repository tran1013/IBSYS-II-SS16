package de.ibsys.planningTool.service;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.database.capPlaDB;
import de.ibsys.planningTool.mock.sellData;
import de.ibsys.planningTool.model.CapPlaResult;
import de.ibsys.planningTool.model.Constants;
import de.ibsys.planningTool.model.ProductionResult;
import de.ibsys.planningTool.model.ProductionSteps;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlInputModel.OrdersInWork;
import de.ibsys.planningTool.model.xmlInputModel.WaitingList;
import de.ibsys.planningTool.model.xmlInputModel.WaitingListWorkPlace;
import javafx.fxml.FXML;

import java.sql.SQLException;
import java.util.*;

/**
 * TODO: Clean up mess in class
 * TODO: Test class or take a deep breath and get to it?
 * Created by Duc on 17.08.16.
 */
public class CapPla {

    capPlaDB prod = new capPlaDB();
    MainController main = new MainController();

    /**
     * God Method to calculate CapPla shit
     * TODO: return Object from CapPlaResult
     */
    @FXML
    public List<CapPlaResult> calculateCap(Map<String, OrdersInWork> ordersInWorkMap, Map<String, WaitingListWorkPlace> waitingListWorkPlaceMap, List<Item> productionResult) {

        List<Integer> workplaceIDs;
        List<CapPlaResult> resultCapPla = new ArrayList<>();
        Map<String, Integer> capResult = new HashMap<>();
        Map<String, Integer> setupTimeList = new HashMap<>();
        Map<String, Integer> ordersInWorkTime = new HashMap<>();
        Integer setupTimeLastPeriod = 0;


        try {
            workplaceIDs = prod.findWorkplaceID();

            List<ProductionSteps> prodSteps = this.getMappedList(productionResult);

            for (Integer i : workplaceIDs) {

                Integer capacity = 0, CapSetupTime = 0, workplace = 0, productionTime = 0, setup = 0;
                Integer result, requirePeriod, shifts, overtime;

                for (ProductionSteps ps : prod.findByWorkplacID(i)) {

                    workplace = ps.getWorkplaceID();
                    String Item = ps.getItemConfigID();
                    productionTime = ps.getProductionTime();
                    setup = ps.getSetupTime();


                    for (ProductionSteps cap : prodSteps) {

                        String item = cap.getItemConfigID();

                        if (item.equals(Item) && cap.getProductionTime() >= 0) {
                            Integer needTime = cap.getProductionTime();

                            CapSetupTime += setup;
                            capacity += (productionTime * needTime);
                        }
                    }
                    capResult.put(workplace.toString(), capacity);
                    setupTimeList.put(workplace.toString(), CapSetupTime);
                }
                /*System.out.println("Workplace: " + workplace);
                System.out.println("Cap. require: " + capResult.get(workplace.toString()));
                System.out.println("Setup Time: " + setupTimeList.get(workplace.toString()));*/

                result = this.getAllScheduledTime(workplace, ordersInWorkMap, waitingListWorkPlaceMap);
                result = result * productionTime;
                //System.out.println("Cap. require last period: " + result);
                ordersInWorkTime.put(workplace.toString(), result);

                setupTimeLastPeriod = this.getPieceAmountOrdersInWork(workplace, ordersInWorkMap);
                setupTimeLastPeriod = setupTimeLastPeriod * setup;
                /*System.out.println("SetupTime last period: " + setupTimeLastPeriod);
                System.out.println("_________________________");*/

                requirePeriod = capResult.get(workplace.toString()) + setupTimeList.get(workplace.toString()) + result + setupTimeLastPeriod;
                shifts = this.calculateShifts(requirePeriod);
                overtime = this.calculateOvertime(requirePeriod, shifts);

                resultCapPla.add(new CapPlaResult(workplace, requirePeriod, shifts, overtime));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return resultCapPla;
    }

    /**
     * Get the amount of article per workplace in OrdersInWork XML
     *
     * @param workplace
     * @return
     */
    @FXML
    public Integer getOrdersInWorkAmount(Integer workplace, Map<String, OrdersInWork> ordersInWorkMap) {

        Integer amount = 0;
        Map<String, OrdersInWork> order = new TreeMap<>(ordersInWorkMap);

        for (Map.Entry<String, OrdersInWork> orderMap : order.entrySet()) {
            if (orderMap.getValue().getId().equals(workplace.toString())) {
                amount = orderMap.getValue().getAmount();
            }
        }

        return amount;
    }

    /**
     * Get the amount of article per workplace in WaitingListWorkstations XML
     *
     * @param workplace
     * @return
     */
    public Integer getWaitinglistAmount(Integer workplace, Map<String, WaitingListWorkPlace> waitingListWorkPlaceMap) {

        Integer amount = 0;
        Map<String, WaitingListWorkPlace> wait = new TreeMap<>(waitingListWorkPlaceMap);

        for (WaitingListWorkPlace waitPlace : wait.values()) {
            if (waitPlace.getWorkplaceId().equals(workplace.toString()) && waitPlace.getNecessaryTime() > 0) {
                for (WaitingList list : waitPlace.getWaitingLists()) {
                    amount = list.getAmount();
                }
            }
        }

        return amount;
    }

    /**
     * Get the numbers of pieces from ordersInWork to calculate scheduled setup time from last period
     *
     * @param workplace
     * @return
     */
    public Integer getPieceAmountOrdersInWork(Integer workplace, Map<String, OrdersInWork> ordersInWorkMap) {

        Integer piece = 0;
        Map<String, OrdersInWork> order = new TreeMap<>(ordersInWorkMap);

        for (Map.Entry<String, OrdersInWork> orderMap : order.entrySet()) {
            if (orderMap.getValue().getId().equals(workplace.toString())) {
                piece++;
            }
        }

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
    public Integer getAllScheduledTime(Integer workplace, Map<String, OrdersInWork> ordersInWorkMap, Map<String, WaitingListWorkPlace> waitingListWorkPlaceMap) {
        Integer result;
        Integer scheduledOrdersInWork = this.getOrdersInWorkAmount(workplace, ordersInWorkMap);
        Integer waitinglistAmount = this.getWaitinglistAmount(workplace, waitingListWorkPlaceMap);

        result = scheduledOrdersInWork + waitinglistAmount;

        return result;
    }

    /**
     * Calculate shifts per workplace
     *
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
     *
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

    public List<ProductionSteps> getMappedList(List<Item> productionResult) {
        List<ProductionSteps> psList = new ArrayList<>();

        for (Integer i = 0; i < productionResult.size(); i++) {
            if (productionResult.get(i).getArticleId().equals("1") || productionResult.get(i).getArticleId().equals("2") || productionResult.get(i).getArticleId().equals("3")) {
                String itemID = "P" + productionResult.get(i).getArticleId();
                psList.add(i, new ProductionSteps(i + 1, 0, itemID, productionResult.get(i).getQuantity(), 0));
            } else {
                String itemID = "E" + productionResult.get(i).getArticleId();
                psList.add(i, new ProductionSteps(i + 1, 0, itemID, productionResult.get(i).getQuantity(), 0));
            }

        }

        //System.out.println(psList);
        return psList;
    }
}

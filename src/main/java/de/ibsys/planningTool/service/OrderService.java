package de.ibsys.planningTool.service;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.controller.tab.ForeCastController;
import de.ibsys.planningTool.database.ItemDB;
import de.ibsys.planningTool.database.OrderDB;
import de.ibsys.planningTool.mock.MockProductionResult;
import de.ibsys.planningTool.model.*;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlInputModel.Article;
import de.ibsys.planningTool.model.xmlInputModel.FutureInComingOrder;
import de.ibsys.planningTool.model.xmlInputModel.WaitingList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.ibsys.planningTool.model.Constants.FAST_DELIVERY;
import static de.ibsys.planningTool.model.Constants.NORMAL_DELIVERY;
import static de.ibsys.planningTool.model.Constants.REPLACEMENT_TIME;

/**
 * Created by Che on 20.08.2016.
 */

/*
TODO Clean up unused imports
TODO Delete comments... System.out.prints

 */
public class OrderService {

    public final static int[][] purchase_parts = {{1,0,0},{0,1,0},{0,0,1},{7,7,7},
            {4,4,4},{2,2,2},{4,5,6},{3,3,3},{0,0,2},
            {0,0,72},{4,4,4},{1,1,1},{1,1,1},{1,1,1},
            {2,2,2},{1,1,1},{1,1,1},{2,2,2},{1,1,1},
            {3,3,3},{1,1,1},{1,1,1},{1,1,1},{2,2,2},
            {2,0,0},{72,0,0},{0,2,0},{0,72,0},{2,2,2}};

    OrderDB orderDB = new OrderDB();

    ItemDB itemDB = new ItemDB();

    XmlInputData inputData;

    ForeCastController foreCastController = new ForeCastController();

    MainController main = new MainController();

    ItemComponents itemComponents = new ItemComponents();

    /*
    public List<OrderResult> calculateOrders(List<ProductionResult> productionResults, Map<String, Item> forecastProductionList) {

        List<OrderResult> orderResults = new ArrayList<>();

        List<Map<String, Integer>> kUsageList = calculateConsumption(calculateProgramm(productionResults, forecastProductionList));

        try {
            List<TermsOfSaleData> terms = orderDB.findAll();

            for(TermsOfSaleData term : terms) {
                String itemConfigId = term.getItemConfigId();

                double avg = calculateAverage(kUsageList, itemConfigId);
                double max = calculateMaxUsage(kUsageList, itemConfigId);
                double orderpoint = avg * (term.getDeliveryTime() + term.getVariance() + REPLACEMENT_TIME);
                String itemConfigSub = itemConfigId.substring(1);
                double stock = main.getXmlInputData().getWareHouseArticles().get(itemConfigSub).getAmount();
                double stockRange = Math.round(stock/avg);
                if(stock <= orderpoint) {
                    double maxDeliveryTime = term.getVariance() + term.getDeliveryTime();
                    int orderQuantity = (int) Math.round((avg* term.getDeliveryTime() + max * maxDeliveryTime)/2);

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

        for(OrderResult res : orderResults){
            System.out.println("ID "+res.getItemConfigId());
            System.out.println("Q "+res.getQuantity());
            System.out.println("MODE "+res.getOrderingMode());

        }

        return orderResults;
    }
    */

    public double calculateAverage(List<Map<String, Integer>> kUsageList, String itemConfigId) {

        double avg = 0.0;
        for(Map<String, Integer> entry : kUsageList) {
            avg += entry.get(itemConfigId);
        }
        //System.out.println("AVG " + Math.round(avg/4.0));
        return Math.round(avg/4.0);
    }

    public int getStockAmount(String itemConfigId) {
        Map<String, Article> warehouse = main.getXmlInputData().getWareHouseArticles();
        itemConfigId = itemConfigId.substring(1);
        int stock = 0;
        System.out.println("sub " + itemConfigId);
        for(Map.Entry<String, Article> entry : warehouse.entrySet()) {
            if(entry.getKey().equals(itemConfigId)) {
                stock = entry.getValue().getAmount();
            }
        }
        return stock;
    }

    public double calculateWeightedAverage(List<Map<String, Integer>> kUsageList, String itemConfigId, double g1, double g2, double g3, double g4) {

        double p1 = 0.0;
        double p2 = 0.0;
        double p3 = 0.0;
        double p4 = 0.0;

        int z = 0;
        for(Map<String, Integer> entry : kUsageList) {
            if(z==0) {
                p1 = entry.get(itemConfigId);
            }
            else if(z==1) {
                p2 = entry.get(itemConfigId);
            }
            else if(z ==2) {
                p3 = entry.get(itemConfigId);
            }
            else p4 = entry.get(itemConfigId);
            ++z;
        }


        System.out.println(Math.round(p1*0.5+p2*0.2+p3*0.2+p4+0.1));
        //return Math.round(p1*g1+p2*g2+p3*g3+p4+g4);
        return Math.round((p1*0.5+p2*0.2+p3*0.2+p4+0.1));

    }


    public double calculateMaxUsage(List<Map<String, Integer>> kUsageList, String itemConfigId) {
        double maxUsage = 0.0;
        for (Map<String, Integer> item : kUsageList) {
            Integer req = item.get(itemConfigId);
            if (maxUsage < req) {
                maxUsage = req;
            }
        }
        //System.out.println(Math.round(maxUsage * 100.0) / 100.0);
        return Math.round(maxUsage * 100.0) / 100.0;

    }

    public double calculateMaxDeliveryTime(String itemConfigId) {

        double maxTime = 0.0;
        try {
            List<TermsOfSaleData> terms = new ArrayList<>();
            terms = orderDB.findAll();

            for(TermsOfSaleData data : terms) {
                if(data.getItemConfigId().equals(itemConfigId)) {
                    maxTime = data.getVariance() + data.getDeliveryTime();
                }
                else continue;

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println(maxTime);
        return maxTime;
    }

    public double getDiscontQuantity(String itemConfigId) {
        double discont = 0.0;
         try {
             List<TermsOfSaleData> terms = orderDB.findAll();

             for(TermsOfSaleData data : terms) {
                 if(data.getItemConfigId().equals(itemConfigId)) {
                    discont = data.getDiscountQuantity();
                 }
             }

         }
         catch (SQLException e) {
             e.printStackTrace();
         }
        return discont;
    }

    // TODO Check access to XmlInputData
    public double calculateStockRange(List<Map<String, Integer>> kUsageList, TermsOfSaleData terms) {
        double stockRange = 0.0;
        String itemConfigId = terms.getItemConfigId();

        double avg = calculateAverage(kUsageList, terms.getItemConfigId());
        //double stock = inputData.getWareHouseArticles().get(itemConfigId).getAmount();
        double stock = main.getXmlInputData().getWareHouseArticles().get(itemConfigId).getAmount();
        //TODO Test if it works
        double futureIncomingAmount = getFutureInComingOrderAmount(itemConfigId);


        stockRange = Math.round(stock/avg);
        System.out.println("STOCKRANGE" + stockRange);

        return stockRange;

    }

    public double calculateOrderCosts(OrderResult orderResult) {
        double orderCosts = 0.0;
        try {
            List<TermsOfSaleData> terms = orderDB.findAll();
            for(TermsOfSaleData term : terms) {
                if(orderResult.getItemConfigId().equals(term.getItemConfigId())) {
                    int orderFixCost = term.getOrderingCosts();
                    int discount = term.getDiscountQuantity();
                    int quantity = orderResult.getQuantity();
                    double partValue = 0.1;
                    //term.getPartyValue();

                    if(orderResult.getOrderingMode()==FAST_DELIVERY){
                        orderCosts = orderFixCost*10;
                        if(orderResult.getQuantity() >= orderResult.getDiscountQuantity()) {
                            orderCosts += (partValue*orderResult.getQuantity())*0.9;
                        }
                        else
                            orderCosts += partValue*orderResult.getQuantity();

                    }
                    else {
                        if (orderResult.getQuantity() >= orderResult.getDiscountQuantity()) {
                            orderCosts = (partValue * orderResult.getQuantity()) * 0.9 + orderCosts;
                        } else
                            orderCosts = partValue * orderResult.getQuantity() + orderCosts;
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return runden(orderCosts, 2);

    }

    public static double runden(double wert, int stellen)
    {
        double gerundet = Math.round(wert * Math.pow(10d, stellen));
        return gerundet / Math.pow(10d, stellen);
    }

    public double getFutureInComingOrderAmount(String itemConfigId) {
        double incomingAmount = 0.0;
        Map<String, FutureInComingOrder> futureInComingOrderMap = main.getXmlInputData().getFutureInComingOrderMap();

        for(Map.Entry<String, FutureInComingOrder> entry : futureInComingOrderMap.entrySet()) {
            if(entry.getKey().equals(itemConfigId)) {
                incomingAmount += entry.getValue().getAmount();
            }
        }
        return incomingAmount;
    }


    public List<Map<String, Integer>> calculateProgramm(List<ProductionResult> productionResults, Map<String, Item> forecastProductionList) {

        List<Map<String, Integer>> productionProgram = new ArrayList<>();
        Map<String, Integer> productionMap = new HashMap<>();

        for (ProductionResult productionResult : productionResults) {
            String itemConfigId = productionResult.getItemConfigId();

            //System.out.println("TEST ID "+itemConfigId);

            if (itemConfigId.equals("P1") || itemConfigId.equals("P2") || itemConfigId.equals("P3")) {

                ItemComponents itemComponent;

                try {
                    itemComponent = itemDB.findById(itemConfigId);
                    System.out.println(itemComponent);
                    /*
                   List<WaitingList> waitingLists = main.getXmlInputData().getStringWaitingListMissingPartsMap().get(itemConfigId).getWaitingLists();

                    int amountofWaitingList = 0;

                    for(WaitingList list : waitingLists){
                        if(list.getArticleId().equals(itemConfigId)){
                            amountofWaitingList = list.getAmount();
                        }

                    }

                    int orderInMapAmount = main.getXmlInputData().getOrdersInWorkMap().get(itemConfigId).getAmount();

                    int quantity1 = productionResult.getQuantity() + orderInMapAmount + amountofWaitingList;
                    */
                    int quantity = productionResult.getQuantity();


                    //System.out.println("MENGE " + quantity + " ID " + itemConfigId);

                    productionMap.put(itemConfigId, quantity);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        /*
        for(Map.Entry<String, Integer> mapentry : productionMap.entrySet()) {
            System.out.println("ProductionMap " + mapentry);
        }
        */
        productionProgram.add(productionMap);


        Map<String, Integer> forecastMap1 = new HashMap<String, Integer>();
        Map<String, Integer> forecastMap2 = new HashMap<String, Integer>();
        Map<String, Integer> forecastMap3 = new HashMap<String, Integer>();

        for(Map.Entry<String, Item> itemEntry: forecastProductionList.entrySet()) {

            if(itemEntry.getKey().endsWith("n")) {
                //map.put(itemEntry.getKey(), itemEntry.getValue().getQuantity());
                continue;
            }

            else if(itemEntry.getKey().endsWith("n1")) {
                forecastMap1.put(itemEntry.getKey(), itemEntry.getValue().getQuantity());
            }
            else if(itemEntry.getKey().endsWith("n2")) {
                forecastMap2.put(itemEntry.getKey(), itemEntry.getValue().getQuantity());
            }
            else if(itemEntry.getKey().endsWith("n3")) {
                forecastMap3.put(itemEntry.getKey(), itemEntry.getValue().getQuantity());
            }
            else continue;
        }

        productionProgram.add(forecastMap1);
        productionProgram.add(forecastMap2);
        productionProgram.add(forecastMap3);

        for(Map<String, Integer> entry : productionProgram) {
            System.out.println("Einträge in productionProgram "+entry);
        }

        return productionProgram;

    }

    public List<Map<String, Integer>> calculateConsumption(List<Map<String, Integer>> productionProgram) {
        List<Map<String, Integer>> kUsageList = new ArrayList<Map<String, Integer>>();

        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, Integer> map_n1 = new HashMap<String, Integer>();
        Map<String, Integer> map_n2 = new HashMap<String, Integer>();
        Map<String, Integer> map_n3 = new HashMap<String, Integer>();

        final String[] zahlenreihe = {"K21", "K22", "K23", "K24",
                "K25", "K27", "K28", "K32", "K33", "K34", "K35", "K36" , "K37",
                "K38", "K39", "K40", "K41", "K42", "K43", "K44", "K45", "K46",
                "K47", "K48", "K52", "K53", "K57", "K58", "K59"};

        Integer wert = 0;

        for(Map<String, Integer> one_map : productionProgram) {
		/*
		for(Map.Entry<String, Integer> m_entry : one_map.entrySet()) {
			System.out.println("in der Mappa xD "+ m_entry.getValue() + " " + m_entry.getKey());
		}*/
            //System.out.println(one_map);

            //one_map.get("p1n1")*i[0]+one_map.get("p2n1")*i[1]+one_map.get("p3n1")*i[2]

            for(int[] i : purchase_parts) {
                //System.out.println("WERT "+wert + " "+ zahlenreihe[wert]);
                if(one_map.containsKey("P1")) {
                    map.put(zahlenreihe[wert], one_map.get("P1")*i[0]+one_map.get("P2")*i[1]+one_map.get("P3")*i[2]);
                }
                else if(one_map.containsKey("p1n1")) {
                    //System.out.println(one_map.get("p1n1")*i[0]+one_map.get("p2n1")*i[1]+one_map.get("p3n1")*i[2]);
                    map_n1.put(zahlenreihe[wert], one_map.get("p1n1")*i[0]+one_map.get("p2n1")*i[1]+one_map.get("p3n1")*i[2]);

                }
                else if(one_map.containsKey("p1n2")) {
                    //System.out.println(one_map.get("p1n2")*i[0]+one_map.get("p2n2")*i[1]+one_map.get("p3n2")*i[2]);
                    map_n2.put(zahlenreihe[wert], one_map.get("p1n2")*i[0]+one_map.get("p2n2")*i[1]+one_map.get("p3n2")*i[2]);
                }
                else if(one_map.containsKey("p1n3")) {
                    //System.out.println(one_map.get("p1n3")*i[0]+one_map.get("p2n3")*i[1]+one_map.get("p3n3")*i[2]);
                    map_n3.put(zahlenreihe[wert], one_map.get("p1n3")*i[0]+one_map.get("p2n3")*i[1]+one_map.get("p3n3")*i[2]);
                }

                wert++;

                if(wert==29) {
                    wert = 0;
                }
            }
        }
        kUsageList.add(map);
        kUsageList.add(map_n1);
        kUsageList.add(map_n2);
        kUsageList.add(map_n3);
        // to test output of the list
        for(Map<String, Integer> entry : kUsageList) {
            System.out.println("ENTRIES in der kUsageList" + entry);
        }
        return kUsageList;
    }
}

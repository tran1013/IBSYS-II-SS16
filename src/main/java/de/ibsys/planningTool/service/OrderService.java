package de.ibsys.planningTool.service;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.controller.tab.ForeCastController;
import de.ibsys.planningTool.database.ItemDB;
import de.ibsys.planningTool.database.OrderDB;
import de.ibsys.planningTool.mock.MockProductionResult;
import de.ibsys.planningTool.model.ItemComponents;
import de.ibsys.planningTool.model.ProductionResult;
import de.ibsys.planningTool.model.TermsOfSaleData;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlExportModel.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Che on 20.08.2016.
 */

/*
TODO Clean up unused imports
TODO Delete comments... System.out.prints
TODO calculateStockRange
TODO Access to InputData;
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

    //public ItemDB itemDB;

    XmlInputData inputData;

    ForeCastController foreCastController = new ForeCastController();

    MainController mainController = new MainController();

    ItemComponents itemComponents = new ItemComponents();

    //Map<String, OrdersInWork> ordersInWorkMap = inputData.getOrdersInWorkMap();

    //Map<String, WaitingListMissingParts> stringWaitingListMissingPart = inputData.getStringWaitingListMissingPartsMap();

    Map<String, Item> forecastProductionList = mainController.getForecastProductionList();

    MockProductionResult mockProductionResult = new MockProductionResult();


    public List<ProductionResult> calculateOrders() {

        List<ProductionResult> productionResults = new ArrayList<>();

        return productionResults;
    }

    public double calculateAverage(List<Map<String, Integer>> kUsageList, String itemConfigId) {

        double avg = 0.0;
        for(Map<String, Integer> entry : kUsageList) {
            avg += entry.get(itemConfigId);
        }
        System.out.println("AVG " + Math.round(avg/4.0));
        return Math.round(avg/4.0);
    }

    public double calculateMaxUsage(List<Map<String, Integer>> kUsageList, String itemConfigId) {
        double maxUsage = 0.0;
        for (Map<String, Integer> item : kUsageList) {
            Integer req = item.get(itemConfigId);
            if (maxUsage < req) {
                maxUsage = req;
            }
        }
        System.out.println(Math.round(maxUsage * 100.0) / 100.0);
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
/*
    public double calculateStockRange(List<Map<String, Integer>> kUsageList, TermsOfSaleData terms) {
        double stockRange = 0.0;
        String itemConfigId = terms.getItemConfigId();
        //double maxTime = calculateMaxDeliveryTime(terms.getItemConfigId());
        double avg = calculateAverage(kUsageList, terms.getItemConfigId());
        double stock = mainController.getXmlInputData().getWareHouseArticles().get(itemConfigId).getStockValue();

        stockRange = Math.round(stock/avg);
        System.out.println(stockRange);
        return stockRange;
    }
    */



    // Map<String, Item> forecastProductionList input
    //TODO Add OrderInWork and WaitingList
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

                    int quantity = productionResult.getQuantity();
                    //System.out.println("MENGE " + quantity + " ID " + itemConfigId);
                    productionMap.put(itemConfigId, quantity);


                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }

           /*
            if(itemConfigId.startsWith("P")) {
                ItemComponents itemComponent;
                try {
                    itemComponent = itemDB.findById(itemConfigId);

                    List<WaitingList> waitingLists = stringWaitingListMissingPart.get(itemConfigId).getWaitingLists();

                    Integer amountofWaitingList = 0;
                    /*
                    get amount of an article in the waiting list
                     */
           /*
                    for(WaitingList list : waitingLists){
                        if(list.getArticleId().equals(itemComponent)){
                            amountofWaitingList = list.getAmount();
                        }
                    }

                    int quantity = productionResult.getQuantity() + ordersInWorkMap.get(itemComponent).getAmount() + amountofWaitingList;
                    System.out.print("Menge "+quantity);
                    map.put(itemConfigId, quantity);
                    productionProgram.add(map);
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }*/
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
            //System.out.print("ITEMENTRY "+itemEntry);

        }


        productionProgram.add(forecastMap1);
        productionProgram.add(forecastMap2);
        productionProgram.add(forecastMap3);

        for(Map<String, Integer> entry : productionProgram) {
            System.out.println("Einträge in productionProgram "+entry);
        }

        return productionProgram;

    }

    public List<Map<String, Integer>> calculateConsumption(List<Map<String, Integer>> productionProgram, int[][] purchase_parts) {
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

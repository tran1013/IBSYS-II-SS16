package de.ibsys.planningTool.service;


//import com.sun.tools.corba.se.idl.InterfaceGen;
import de.ibsys.planningTool.mock.sellData;
import de.ibsys.planningTool.model.ProductionSteps;
import de.ibsys.planningTool.database.capPlaDB;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlInputModel.OrdersInWork;
import org.jdom2.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Duc on 11.07.16.
 */
public class CapPla {


    capPlaDB prod = new capPlaDB();

    @Autowired
    XmlInputData input = new XmlInputData();


    /**
     * Method to get CapReq
     * TODO: delete unused println
     * @return
     */
    public Map<String, Integer> getCapResult() {

        List<Integer> workplaceIDs;
        sellData mock = new sellData();
        Map<String, Integer> capResult = new HashMap<>();
        Map<String, Integer> setupTimeList = new HashMap<>();


        try {
            workplaceIDs = prod.findWorkplaceID();
            mock.setProdSteps();
            List<ProductionSteps> prodSteps = mock.getProdSteps();


            System.out.println("GO!");

            for (int i : workplaceIDs) {
                //workplaceNumber begins by 1
                int capacity = 0;
                int setupTime = 0;
                Integer workplace;

                for (ProductionSteps ps : prod.findByWorkplacID(i)) {

                    workplace = ps.getWorkplaceID();
                    String Item = ps.getItemConfigID();
                    Integer productionTime = ps.getProductionTime();
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
                        }

                    }
                    //  System.out.println("Workplace: " + workplace + " - Item: " + Item + " - ProdutionTime: " + productionTime);
                    // System.out.println("Workplace: " + workplace + " - Capacity: " + capacity + " - SetupTime: " + setupTime);
                    capResult.put(workplace.toString(), capacity);
                    setupTimeList.put(workplace.toString(), setupTime);
                }

                // System.out.println("Finish Workplace: " + i);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
      //  System.out.println("SETUPTIME: ");
      //  System.out.println(Arrays.toString(setupTimeList.entrySet().toArray()));
        return capResult;
    }


    public void getXML(){


        Map<String, OrdersInWork> myList = new HashMap<>();

        myList = input.getOrdersInWorkMap();

        System.out.println("GET XML");
        System.out.println(myList);

    }



}



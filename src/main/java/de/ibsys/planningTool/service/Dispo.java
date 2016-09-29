package de.ibsys.planningTool.service;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.model.ProductionResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duc on 28.09.16.
 */
public class Dispo {

    MainController main = new MainController();

    public void calculate() {
        Integer quantity = 0;
        List<ProductionResult> childList = main.getChildList();
        List<ProductionResult> menList = main.getMenList();
        List<ProductionResult> womenList = main.getWomenList();
        List<ProductionResult> productionResultList = new ArrayList<>();
        productionResultList.addAll(childList);

        for (Integer i = 0; i < productionResultList.size(); i++) {
            String itemConfigID = productionResultList.get(i).getItemConfigId();
            if (itemConfigID.equals("E26") || itemConfigID.equals("E16") || itemConfigID.equals("E16")) {
                quantity += menList.get(i).getQuantity() + womenList.get(i).getQuantity();
                productionResultList.set(i, new ProductionResult(itemConfigID, quantity));
            } else {
                productionResultList.add(menList.get(i));
                productionResultList.add(womenList.get(i));
            }
        }
        System.out.println(productionResultList);
        main.setProductionResultList(productionResultList);
    }

}

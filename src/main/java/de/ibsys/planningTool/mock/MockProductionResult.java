package de.ibsys.planningTool.mock;

import de.ibsys.planningTool.model.ProductionResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Che on 30.08.2016.
 */
public class MockProductionResult {

    List<ProductionResult> productionResultList = new ArrayList<>();

    public List<ProductionResult> getProductionResultList() {
        return productionResultList;
    }

    public void setProductionResultList() {

        productionResultList.add(new ProductionResult("P1",150));
        productionResultList.add(new ProductionResult("P2",200));
        productionResultList.add(new ProductionResult("P3",250));
        productionResultList.add(new ProductionResult("E4",50));
        productionResultList.add(new ProductionResult("E5",60));
        productionResultList.add(new ProductionResult("E6",100));
        productionResultList.add(new ProductionResult("E7",100));
        productionResultList.add(new ProductionResult("E8",100));
        productionResultList.add(new ProductionResult("E9",100));
        productionResultList.add(new ProductionResult("E10",100));
        productionResultList.add(new ProductionResult("E11",100));
        productionResultList.add(new ProductionResult("E12",100));
        productionResultList.add(new ProductionResult("E13",100));
        productionResultList.add(new ProductionResult("E14",100));
        productionResultList.add(new ProductionResult("E15",100));
        productionResultList.add(new ProductionResult("E16",100));
        productionResultList.add(new ProductionResult("E17",100));
        productionResultList.add(new ProductionResult("E18",100));
        productionResultList.add(new ProductionResult("E19",100));
        productionResultList.add(new ProductionResult("E20",100));
        productionResultList.add(new ProductionResult("E26",600));
        productionResultList.add(new ProductionResult("E29",100));
        productionResultList.add(new ProductionResult("E30",100));
        productionResultList.add(new ProductionResult("E31",100));
        productionResultList.add(new ProductionResult("E49",100));
        productionResultList.add(new ProductionResult("E50",100));
        productionResultList.add(new ProductionResult("E51",100));
        productionResultList.add(new ProductionResult("E54",100));
        productionResultList.add(new ProductionResult("E55",100));
        productionResultList.add(new ProductionResult("E56",100));

    }


}

package de.ibsys.planningTool.mock;

import de.ibsys.planningTool.model.ProductionSteps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duc on 24.07.16.
 */
public class sellData {


    List<ProductionSteps> prodSteps = new ArrayList<>();


    public List<ProductionSteps> getProdSteps() {
        return prodSteps;
    }

    public void setProdSteps() {
        prodSteps.add(new ProductionSteps(1, 0, "P1", 200, 0));
        prodSteps.add(new ProductionSteps(2, 0, "P2", 100, 0));
        prodSteps.add(new ProductionSteps(3, 0, "P3", 100, 0));
        prodSteps.add(new ProductionSteps(4, 0, "E4", 180, 0));
        prodSteps.add(new ProductionSteps(5, 1, "E5", 100, 0));
        prodSteps.add(new ProductionSteps(6, 0, "E6", 100, 0));
        prodSteps.add(new ProductionSteps(7, 0, "E7", 180, 0));
        prodSteps.add(new ProductionSteps(8, 0, "E8", 50, 0));
        prodSteps.add(new ProductionSteps(9, 0, "E9", 110, 0));
        prodSteps.add(new ProductionSteps(10, 0, "E10", 180, 0));
        prodSteps.add(new ProductionSteps(11, 0, "E11", 100, 0));
        prodSteps.add(new ProductionSteps(12, 0, "E12", 100, 0));
        prodSteps.add(new ProductionSteps(13, 0, "E13", 180, 0));
        prodSteps.add(new ProductionSteps(14, 0, "E14", 50, 0));
        prodSteps.add(new ProductionSteps(15, 0, "E15", 110, 0));
        prodSteps.add(new ProductionSteps(16, 0, "E16", -70, 0));
        prodSteps.add(new ProductionSteps(17, 0, "E17", 230, 0));
        prodSteps.add(new ProductionSteps(18, 0, "E18", 180, 0));
        prodSteps.add(new ProductionSteps(19, 0, "E19", 50, 0));
        prodSteps.add(new ProductionSteps(20, 0, "E20", 110, 0));
        prodSteps.add(new ProductionSteps(21, 0, "E26", -70, 0));
        prodSteps.add(new ProductionSteps(22, 0, "E29", 110, 0));
        prodSteps.add(new ProductionSteps(23, 0, "E30", 100, 0));
        prodSteps.add(new ProductionSteps(24, 0, "E31", 100, 0));
        prodSteps.add(new ProductionSteps(25, 0, "E49", 180, 0));
        prodSteps.add(new ProductionSteps(26, 0, "E50", 180, 0));
        prodSteps.add(new ProductionSteps(27, 0, "E51", 180, 0));
        prodSteps.add(new ProductionSteps(28, 0, "E54", 100, 0));
        prodSteps.add(new ProductionSteps(29, 0, "E55", 100, 0));
        prodSteps.add(new ProductionSteps(30, 0, "E56", 100, 0));
    }

}

package de.ibsys.planningTool.controller.tab.productionOrderTab;

import de.ibsys.planningTool.controller.tab.ProductionController;
import javafx.application.Application;
import org.apache.log4j.Logger;

/**
 * Created by minhnguyen on 22.09.16.
 */
public abstract class BaseProductionTabController extends Application {

    public Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    protected ProductionController productionOrderController;

    public void init(ProductionController productionOrderController) {
        this.productionOrderController = productionOrderController;
    }

}

package de.ibsys.planningTool.controller.tab.productionOrderTab;

import de.ibsys.planningTool.controller.tab.ProductionController;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlInputModel.WaitingList;
import javafx.application.Application;
import org.apache.log4j.Logger;

/**
 *
 * Created by minhnguyen on 22.09.16.
 */
public abstract class BaseProductionTabController extends Application {

    public Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    protected ProductionController productionOrderController;

    public void init(ProductionController productionOrderController) {
        this.productionOrderController = productionOrderController;
    }

    protected String getI18NText(String i18n) {
        return productionOrderController.getTranslation().getString(i18n);
    }

    protected String getForeCastInformationProductLine(String code) {
        return String.valueOf(productionOrderController.getMainController().getForecastProductionList().get(code).getQuantity());
    }

    protected XmlInputData getXmlInputData() {
        return productionOrderController.getMainController().getXmlInputData();
    }

    protected String getStockValue(String code) {
        return String.valueOf(getXmlInputData().getWareHouseArticles().get(code).getAmount());
    }

    protected int getQueueValue(String code) {
        return getXmlInputData()
                .getWaitingListWorkPlaceMap()
                .entrySet()
                .parallelStream()
                .mapToInt(stringWaitingListWorkPlaceEntry ->
                        stringWaitingListWorkPlaceEntry.getValue().getWaitingLists()
                                .parallelStream()
                                .filter(waitingList -> waitingList.getArticleId().equals(code))
                                .mapToInt(WaitingList::getAmount).sum())
                .sum();
    }

    protected int getProcessValue(String code) {
        return getXmlInputData()
                .getOrdersInWorkMap()
                .entrySet()
                .parallelStream()
                .filter(stringOrdersInWorkEntry -> stringOrdersInWorkEntry.getValue().getArticleId().equals(code))
                .mapToInt(value ->
                        value.getValue().getAmount()
                ).sum();
    }
}

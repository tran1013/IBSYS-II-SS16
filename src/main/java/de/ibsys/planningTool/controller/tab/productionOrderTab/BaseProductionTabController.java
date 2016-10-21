package de.ibsys.planningTool.controller.tab.productionOrderTab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.ibsys.planningTool.controller.MainController;
import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXTextField;

import de.ibsys.planningTool.controller.tab.ProductionController;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlInputModel.WaitingList;
import javafx.application.Application;

/**
 * Created by minhnguyen on 22.09.16.
 */
public abstract class BaseProductionTabController extends Application {

    protected MainController main;


    public Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    protected ProductionController productionOrderController;

    public void init(ProductionController productionOrderController) {
        this.productionOrderController = productionOrderController;
    }

    protected String getI18NText(String i18n) {
        return productionOrderController.getTranslation().getString(i18n);
    }

    protected int getForeCastInformationProductLine(String code) {
        return productionOrderController.getMainController().getForecastProductionList().get(code).getQuantity();
    }

    protected XmlInputData getXmlInputData() {
        return productionOrderController.getMainController().getXmlInputData();
    }

    protected int getStockValue(String code) {
        return getXmlInputData().getWareHouseArticles().get(code).getAmount();
    }

    protected int getQueueValue(String code) {
        return getXmlInputData().getWaitingListWorkPlaceMap().entrySet().parallelStream()
                .mapToInt(
                        stringWaitingListWorkPlaceEntry -> stringWaitingListWorkPlaceEntry.getValue().getWaitingLists()
                                .parallelStream().filter(waitingList -> waitingList.getArticleId().equals(code))
                                .mapToInt(WaitingList::getAmount).sum())
                .sum();
    }

    protected int getProcessValue(String code) {
        return getXmlInputData().getOrdersInWorkMap().entrySet().parallelStream()
                .filter(stringOrdersInWorkEntry -> stringOrdersInWorkEntry.getValue().getArticleId().equals(code))
                .mapToInt(value -> value.getValue().getAmount()).sum();
    }

    // get Production list which are on the machines
    protected int getWaitingListPartsAmount(String code) {
        return getXmlInputData().getStringWaitingListMissingPartsMap().entrySet().parallelStream()
                .filter(predicate -> predicate.getValue().getMissingPartsId().equals(code))
                .mapToInt(waitingList -> waitingList.getValue().getWaitingLists().parallelStream()
                        .mapToInt(WaitingList::getAmount).sum())
                .sum();
    }

    protected int getProductionValuePParts(String code, int sicherheitsbestand) {
        int vertriebwunsch = productionOrderController.getMainController().getForecastProductionList()
                .get("p" + code + "n").getQuantity();
        int lagerBestand = getStockValue(code);
        int warteschlange = getQueueValue(code);
        int bearbeitung = getProcessValue(code);
        // TODO maybe ?? need feedback with get WaitingLIstPartsAmount on
        // Machines ?
        // how to handle it

        return vertriebwunsch + sicherheitsbestand - lagerBestand - warteschlange - bearbeitung;
    }

    protected int getProductionValueEParts(String code, int vertriebwunsch, int hilfszahl, int sicherheitsbestand,
                                           int lagerBestand, int warteschlange, int bearbeitung) {

        return vertriebwunsch + hilfszahl + sicherheitsbestand - lagerBestand - warteschlange - bearbeitung;

    }

    public int checkIsNegative(int production) {
        if (production < 0) {
            return 0;
        } else {
            return production;
        }
    }


    public List<Item> setMainProductionList(String product, List<JFXTextField> textfields) {
        List<Item> productionResultList = new ArrayList<>();
        List<String> parts = new ArrayList<String>();
        switch (product) {
            case "1":
                parts = Arrays.asList("1", "26", "51", "16", "17", "50", "4", "10", "49", "7", "13", "18");
                break;
            case "2":
                parts = Arrays.asList("2", "26", "56", "16", "17", "55", "5", "11", "54", "8", "14", "19");
                break;
            case "3":
                parts = Arrays.asList("3", "26", "31", "16", "17", "30", "6", "12", "29", "9", "15", "20");
                break;
        }
        for (Integer i = 0; i < parts.size(); i++) {
            productionResultList.add(new Item(parts.get(i), Integer.parseInt(textfields.get(i).getText())));
        }
        return productionResultList;
    }

}

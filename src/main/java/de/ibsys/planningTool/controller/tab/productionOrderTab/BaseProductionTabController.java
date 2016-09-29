package de.ibsys.planningTool.controller.tab.productionOrderTab;

import de.ibsys.planningTool.model.ProductionResult;
import de.ibsys.planningTool.service.Dispo;
import org.apache.log4j.Logger;

import de.ibsys.planningTool.controller.tab.ProductionController;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlInputModel.WaitingList;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.List;

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

	protected int getProductionValuePParts(String code) {
		int vertriebwunsch = productionOrderController.getMainController().getForecastProductionList()
				.get("p" + code + "n").getQuantity();
		int sicherheitsbestand = getXmlInputData().getWareHouseArticles().get(code).getReserve();
		int lagerBestand = getStockValue(code);
		int warteschlange = getQueueValue(code);
		int bearbeitung = getProcessValue(code);
		// TODO maybe ?? need feedback with get WaitingLIstPartsAmount on
		// Machines ?
		// how to handle it
		int ergebnis = vertriebwunsch + sicherheitsbestand - lagerBestand - warteschlange - bearbeitung;

		return ergebnis;
	}

	protected int getProductionValueEParts(String code, int vertriebwunsch, int hilfszahl) {
		int sicherheitsbestand = getXmlInputData().getWareHouseArticles().get(code).getReserve();
		int lagerBestand = getStockValue(code);
		int warteschlange = getQueueValue(code);
		int bearbeitung = getProcessValue(code);
		// TODO maybe ?? need feedback with get WaitingLIstPartsAmount on
		// Machines ?
		// how to handle it
		int ergebnis = vertriebwunsch + hilfszahl + sicherheitsbestand - lagerBestand - warteschlange - bearbeitung
				- getWaitingListPartsAmount(code);
		logger.info(getWaitingListPartsAmount(code));

		return ergebnis;
	}




}

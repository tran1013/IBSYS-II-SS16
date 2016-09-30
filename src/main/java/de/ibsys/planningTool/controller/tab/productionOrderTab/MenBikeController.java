package de.ibsys.planningTool.controller.tab.productionOrderTab;

import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.controller.tab.ProductionController;
import de.ibsys.planningTool.model.ProductionResult;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by minhnguyen on 22.09.16.
 */
public class MenBikeController extends BaseProductionTabController {

	@FXML
	Label orders, safetystock, stock, queue, process, productionorder;

	@FXML
	JFXTextField orderP3, orderE26, orderE9, orderE31, orderE16, orderE17, orderE30, orderE6, orderE12, orderE29,
			orderE15, orderE20;

	@FXML
	JFXTextField helpE26, helpE31, helpE16, helpE9, helpE17, helpE30, helpE6, helpE12, helpE29, helpE15, helpE20;

	@FXML
	JFXTextField safetyP3, safetyE26, safetyE31, safetyE9, safetyE16, safetyE17, safetyE30, safetyE6, safetyE12,
			safetyE29, safetyE15, safetyE20;

	@FXML
	JFXTextField stockP3, stockE9, stockE26, stockE31, stockE16, stockE17, stockE30, stockE6, stockE12, stockE29,
			stockE15, stockE20;

	@FXML
	JFXTextField queueP3, queueE26, queueE9, queueE31, queueE16, queueE17, queueE30, queueE6, queueE12, queueE29,
			queueE15, queueE20;

	@FXML
	JFXTextField processP3, processE26, processE31, processE9, processE16, processE17, processE30, processE6,
			processE12, processE29, processE15, processE20;

	@FXML
	JFXTextField productionP3, productionE26, productionE31, productionE9, productionE16, productionE17, productionE30,
			productionE6, productionE12, productionE29, productionE15, productionE20;

	@Override
	public void start(Stage primaryStage) throws Exception {

	}

	@Override
	public void init(ProductionController productionOrderController) {
		super.init(productionOrderController);
		initUIThingsRandom();
	}

	public void initUIThingsRandom() {
		try {
			orderP3.setText(String.valueOf(getForeCastInformationProductLine("p3n")));
			stockP3.setText(String.valueOf(getStockValue("3")));
			safetyP3.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("3").getReserve()));
			queueP3.setText(String.valueOf(getQueueValue("3")));
			processP3.setText(String.valueOf(getProcessValue("3")));
			productionP3.setText(String.valueOf(getProductionValuePParts("3", Integer.parseInt(safetyP3.getText()))));

			orderE26.setText(productionP3.getText());
			stockE26.setText(String.valueOf(getStockValue("26")));
			helpE26.setText(String.valueOf(getQueueValue("3")));
			safetyE26.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("26").getReserve()/3));
			queueE26.setText(String.valueOf(getQueueValue("26")));
			processE26.setText(String.valueOf(getProcessValue("26")));
			productionE26.setText(String.valueOf(
					getProductionValueEParts("26", getForeCastInformationProductLine("p3n"), getQueueValue("3"), Integer.parseInt(safetyE26.getText()))));

			orderE31.setText(productionP3.getText());
			stockE31.setText(String.valueOf(getStockValue("31")));
			helpE31.setText(String.valueOf(getQueueValue("3")));
			safetyE31.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("31").getReserve()));
			queueE31.setText(String.valueOf(getQueueValue("31")));
			processE31.setText(String.valueOf(getProcessValue("31")));
			productionE31.setText(String.valueOf(
					getProductionValueEParts("31", getForeCastInformationProductLine("p3n"), getQueueValue("3"), Integer.parseInt(safetyE26.getText()))));

			orderE16.setText(productionE31.getText());
			stockE16.setText(String.valueOf(getStockValue("16")));
			helpE16.setText(String.valueOf(getQueueValue("31")));
			safetyE16.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("16").getReserve()/3));
			queueE16.setText(String.valueOf(getQueueValue("16")));
			processE16.setText(String.valueOf(getProcessValue("16")));
			productionE16.setText(String.valueOf(
					getProductionValueEParts("16", Integer.valueOf(productionE31.getText()), getQueueValue("31"), Integer.parseInt(safetyE16.getText()))));

			orderE17.setText(productionE31.getText());
			stockE17.setText(String.valueOf(getStockValue("17")));
			helpE17.setText(String.valueOf(getQueueValue("31")));
			safetyE17.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("17").getReserve()/3));
			queueE17.setText(String.valueOf(getQueueValue("17")));
			processE17.setText(String.valueOf(getProcessValue("17")));
			productionE17.setText(String.valueOf(
					getProductionValueEParts("17", Integer.valueOf(productionE31.getText()), getQueueValue("31"), Integer.parseInt(safetyE17.getText()))));

			orderE30.setText(productionE31.getText());
			stockE30.setText(String.valueOf(getStockValue("30")));
			helpE30.setText(String.valueOf(getQueueValue("31")));
			safetyE30.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("30").getReserve()));
			queueE30.setText(String.valueOf(getQueueValue("30")));
			processE30.setText(String.valueOf(getProcessValue("30")));
			productionE30.setText(String.valueOf(
					getProductionValueEParts("30", Integer.valueOf(productionE31.getText()), getQueueValue("31"), Integer.parseInt(safetyE30.getText()))));

			orderE6.setText(productionE30.getText());
			stockE6.setText(String.valueOf(getStockValue("6")));
			helpE6.setText(String.valueOf(getQueueValue("30")));
			safetyE6.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("6").getReserve()));
			queueE6.setText(String.valueOf(getQueueValue("6")));
			processE6.setText(String.valueOf(getProcessValue("6")));
			productionE6.setText(String.valueOf(
					getProductionValueEParts("6", Integer.valueOf(productionE30.getText()), getQueueValue("30"), Integer.parseInt(safetyE6.getText()))));

			orderE12.setText(productionE30.getText());
			stockE12.setText(String.valueOf(getStockValue("12")));
			helpE12.setText(String.valueOf(getQueueValue("30")));
			safetyE12.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("12").getReserve()));
			queueE12.setText(String.valueOf(getQueueValue("12")));
			processE12.setText(String.valueOf(getProcessValue("12")));
			productionE12.setText(String.valueOf(
					getProductionValueEParts("12", Integer.valueOf(productionE30.getText()), getQueueValue("30"), Integer.parseInt(safetyE12.getText()))));

			orderE29.setText(productionE30.getText());
			stockE29.setText(String.valueOf(getStockValue("29")));
			helpE29.setText(String.valueOf(getQueueValue("30")));
			safetyE29.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("29").getReserve()));
			queueE29.setText(String.valueOf(getQueueValue("29")));
			processE29.setText(String.valueOf(getProcessValue("29")));
			productionE29.setText(String.valueOf(
					getProductionValueEParts("29", Integer.valueOf(productionE30.getText()), getQueueValue("30"), Integer.parseInt(safetyE29.getText()))));

			orderE9.setText(productionE29.getText());
			stockE9.setText(String.valueOf(getStockValue("9")));
			helpE9.setText(String.valueOf(getQueueValue("29")));
			safetyE9.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("9").getReserve()));
			queueE9.setText(String.valueOf(getQueueValue("9")));
			processE9.setText(String.valueOf(getProcessValue("9")));
			productionE9.setText(String.valueOf(
					getProductionValueEParts("9", Integer.valueOf(productionE29.getText()), getQueueValue("29"), Integer.parseInt(safetyE9.getText()))));

			orderE15.setText(productionE29.getText());
			stockE15.setText(String.valueOf(getStockValue("15")));
			helpE15.setText(String.valueOf(getQueueValue("29")));
			safetyE15.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("15").getReserve()));
			queueE15.setText(String.valueOf(getQueueValue("15")));
			processE15.setText(String.valueOf(getProcessValue("15")));
			productionE15.setText(String.valueOf(
					getProductionValueEParts("15", Integer.valueOf(productionE29.getText()), getQueueValue("29"), Integer.parseInt(safetyE15.getText()))));

			orderE20.setText(productionE29.getText());
			stockE20.setText(String.valueOf(getStockValue("20")));
			helpE20.setText(String.valueOf(getQueueValue("29")));
			safetyE20.setText(String.valueOf(getXmlInputData().getWareHouseArticles().get("20").getReserve()));
			queueE20.setText(String.valueOf(getQueueValue("20")));
			processE20.setText(String.valueOf(getProcessValue("20")));
			productionE20.setText(String.valueOf(
					getProductionValueEParts("20", Integer.valueOf(productionE29.getText()), getQueueValue("29"), Integer.parseInt(safetyE20.getText()))));

			setMainProductionList("3",
					Arrays.asList(productionP3, productionE26, productionE31, productionE16, productionE17,
							productionE30, productionE6, productionE12, productionE29, productionE9, productionE15,
							productionE20));
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	@FXML
	public void saveNewReserve() {
		
	}
}
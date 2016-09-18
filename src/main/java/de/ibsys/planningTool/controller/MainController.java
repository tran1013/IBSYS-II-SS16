package de.ibsys.planningTool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToggleButton;
import de.ibsys.planningTool.controller.tab.*;
import de.ibsys.planningTool.model.XmlExport;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlExportModel.DirectSell;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlExportModel.Order;
import de.ibsys.planningTool.model.xmlExportModel.WorkTime;
import de.ibsys.planningTool.util.Dialogs.DialogMessages;
import de.ibsys.planningTool.util.I18N;
import de.ibsys.planningTool.util.MockObject;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

import static de.ibsys.planningTool.util.I18N.*;

/**
 * God Class !!
 * Created by minhnguyen on 17.07.16.
 */
public class MainController extends BaseTabController {

    private Stage savedStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.savedStage = primaryStage;
    }

    private String language = "de";
	private String country = "DE";

	private ResourceBundle translation;

	// Export Button
	@FXML
	public JFXButton exportButton;

	// Init Tabs

    @FXML
    public JFXTabPane mainTabPane;

	@FXML
	public Tab forecast;

	@FXML
	public Tab disposition;

	@FXML
	public Tab settingsTab;

    @FXML
    public Tab productionpriorityTab;

	@FXML
	public Tab cappla;

	@FXML
	public Tab orderTab;

	@FXML
	public JFXToggleButton langBtn;

	private XmlInputData xmlInputData;

	private List<Item> sellWish;

	private List<DirectSell> directSellList;

	private List<Order> orderList;

	private List<Item> productionList;

	private List<WorkTime> workTimeList;

	// Program Production plan
	private Map<String, Item> forecastProductionList;

	@FXML
	private CapPlaController capPlaController;

	@FXML
	private ForeCastController foreCastController;

	@FXML
	private  OrderController orderController;

	@FXML
	private SettingsController settingsController;

    @FXML
    private ProductionPriorityController productionPriorityController;

	@FXML
	public void initialize() {
	    logger.info("Start Application");

		foreCastController.init(this);
		settingsController.init(this);
		capPlaController.init(this);
        productionPriorityController.init(this);
		orderController.init(this);

		xmlInputData = null;
		sellWish = new ArrayList<>();
		directSellList = new ArrayList<>();
		orderList = new ArrayList<>();
		// TODO Replace this this again with real data (setter is there)
		productionList = new ArrayList<>();
		workTimeList = new ArrayList<>();
		forecastProductionList = new HashMap<>();

        exportButton.setVisible(false);

		changeUILanguage();

        mainTabPane.getStyleClass().add("jfx-tab-pane");
        new DialogMessages().setMainController(this);
	}

	public XmlInputData getXmlInputData() {
		return xmlInputData;
	}

	public void setXmlInputData(XmlInputData xmlInputData) {
		this.xmlInputData = xmlInputData;
	}

	public List<Item> getSellWish() {
		return sellWish;
	}

	public void setSellWish(List<Item> sellWish) {
		this.sellWish = sellWish;
	}

	public List<DirectSell> getDirectSellList() {
		return directSellList;
	}

	public void setDirectSellList(List<DirectSell> directSellList) {
		this.directSellList = directSellList;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<Item> getProductionList() {
		return productionList;
	}

	public void setProductionList(List<Item> productionList) {
		this.productionList = productionList;
	}

	public List<WorkTime> getWorkTimeList() {
		return workTimeList;
	}

	public void setWorkTimeList(List<WorkTime> workTimeList) {
		this.workTimeList = workTimeList;
	}

	public Map<String, Item> getForecastProductionList() {
		return forecastProductionList;
	}

	public void setForecastProductionList(Map<String, Item> forecastProductionList) {
		this.forecastProductionList = forecastProductionList;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void deleteSellLists() {
		sellWish.clear();
		directSellList.clear();
		forecastProductionList.clear();
	}

	public ResourceBundle getTranslation() {
		return translation;
	}

	public void changeUILanguage() {
		translation = I18N.translation(language, country);

		forecast.setText(translation.getString(FORECAST));
		disposition.setText(translation.getString(DISPOSITION));
		settingsTab.setText(translation.getString(Export_TAB));
		cappla.setText(translation.getString(CAPPLA));
		orderTab.setText(translation.getString(ORDER));
        productionpriorityTab.setText(translation.getString(I18N.PRIORITY));
		langBtn.setText(translation.getString(I18N.LANGUAGE));

		foreCastController.initUIComponents();
		settingsController.initUIComponents();
		capPlaController.initUIComponents();
		orderController.initUIComponents();
	}

	@FXML
    public void exportButtonTapped() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(translation.getString(I18N.XML_EXPORT_SAVED_TITLE));
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML Files", "*.xml");
        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showSaveDialog(savedStage);

        //TODO Adding the other list in here
        if (file != null) {
            new XmlExport().exportXmlInputData(sellWish
                    , directSellList
                    //, new MockObject().orderListMockData()
					, orderList
                    , productionList
                    , workTimeList
                    , file.getPath());
        }
    }

	@FXML
	public void changeLanguageButtonTapped() {
		if (this.getLanguage().equals("de")) {
			this.setLanguage("en");
			this.setCountry("US");
		} else {
			this.setLanguage("de");
			this.setCountry("DE");
		}
		this.changeUILanguage();
	}
}

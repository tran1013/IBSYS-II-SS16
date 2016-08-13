package de.ibsys.planningTool.controller;

import java.util.*;

import de.ibsys.planningTool.controller.tab.ExportController;
import de.ibsys.planningTool.controller.tab.ForeCastController;
import de.ibsys.planningTool.controller.tab.XmlInputController;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlExportModel.DirectSell;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlExportModel.Order;
import de.ibsys.planningTool.model.xmlExportModel.WorkTime;
import de.ibsys.planningTool.util.I18N;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

import static de.ibsys.planningTool.util.I18N.*;

/**
 * Created by minhnguyen on 17.07.16.
 */
public class MainController {

	private String language = "de";
	private String country = "DE";

	private ResourceBundle translation;

	// Init Tabs
	@FXML
	public Tab welcome;

	@FXML
	public Tab forecast;

	@FXML
	public Tab disposition;

	@FXML
	public Tab exportTab;

	private XmlInputData xmlInputData;

	private List<Item> sellWish;

	private List<DirectSell> directSellList;

	private List<Order> orderList;

	private List<Item> productionList;

	private List<WorkTime> workTimeList;

	// Program Production plan
	private Map<String, Item> forecastProductionList;

	@FXML
	private ForeCastController foreCastController;

	@FXML
	private XmlInputController xmlInputController;

	@FXML
	private ExportController exportController;

	@FXML
	public void initialize() {
		System.out.println("Start Application");
		foreCastController.init(this);
		xmlInputController.init(this);
		exportController.init(this);
		xmlInputData = new XmlInputData();
		sellWish = new ArrayList<>();
		directSellList = new ArrayList<>();
		orderList = new ArrayList<>();
		productionList = new ArrayList<>();
		workTimeList = new ArrayList<>();
		forecastProductionList = new HashMap<>();

		changeUILanguage();
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

	public void setTranslation(ResourceBundle translation) {
		this.translation = translation;
	}

	public void changeUILanguage() {
		translation = I18N.translation(language, country);

		welcome.setText(translation.getString(WELCOME));
		forecast.setText(translation.getString(FORECAST));
		disposition.setText(translation.getString(DISPOSITION));
		exportTab.setText(translation.getString(Export_TAB));

		foreCastController.initUIComponents();
		exportController.initUIComponents();
		xmlInputController.initUIComponents();
	}

}

package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.ChildBikeController;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlExportModel.DirectSell;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.util.Dialogs.DialogMessages;
import de.ibsys.planningTool.util.I18N;
import de.ibsys.planningTool.util.JFXIntegerTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by minhnguyen on 11.07.16.
 */
public class ForeCastController extends BaseTabController {

    private Stage savedStage;

    @FXML
    JFXTextField xmlInputTextField;

    @FXML
    Label priceLabel, amountLabel, costLabel, sellingLabel, productionPlanningLabel, directSellsLabel;

    @FXML
    private JFXButton saveForeCastViewButton;

    @FXML
    private JFXIntegerTextField forecast1TextField, forecast2TextField, forecast3TextField;
    @FXML
    private JFXIntegerTextField p1nTextField, p1n1TextField, p1n2TextField, p1n3TextField;
    @FXML
    private JFXIntegerTextField p2nTextField, p2n1TextField, p2n2TextField, p2n3TextField;
    @FXML
    private JFXIntegerTextField p3nTextField, p3n1TextField, p3n2TextField, p3n3TextField;

    @FXML
    private JFXIntegerTextField directSalesPriceP1TextField, directSalesPriceP2TextField, directSalesPriceP3TextField;
    @FXML
    private JFXIntegerTextField directSalesAmountP1TextField, directSalesAmountP2TextField, directSalesAmountP3TextField;
    @FXML
    private JFXTextField directSalesPunishmentP1TextField, directSalesPunishmentP2TextField, directSalesPunishmentP3TextField;

    @FXML
    private void saveForeCastInformation() {
        if (main.getXmlInputData() != null) {
            logger.info("store data");
            if (Integer.valueOf(directSalesPunishmentP1TextField.getText()) >= 0 &&
                    Integer.valueOf(directSalesPunishmentP2TextField.getText()) >= 0 &&
                    Integer.valueOf(directSalesPunishmentP3TextField.getText()) >= 0) {
                if (!checkInputViewsContainsData()) {
                    if (main.getSellWish().size() != 0) {
                        main.deleteSellLists();
                        storeData();
                    } else {
                        storeData();
                    }
                    main.exportButton.setVisible(true);
                    main.initWorkThings();
                    main.productionTab.setDisable(false);
                } else {
                    DialogMessages.ErrorDialog(getI18NText(I18N.FORECAST_INPUT_ERROR));
                }
            } else {
                DialogMessages.ErrorDialog(getI18NText(I18N.XML_INPUT_ERROR_NO_XML));
            }
        } else {
            DialogMessages.ErrorDialog(getI18NText(I18N.DIGIT_SMALLER_ZERO));
        }
    }

    private void storeData() {
        main.setSellWish(storeSellWish());
        main.setForecastProductionList(storeProductionData());
        main.setDirectSellList(storeDirectSells());
    }

    private boolean checkInputViewsContainsData() {
        return isTextFieldEmpty(forecast1TextField)
                || isTextFieldEmpty(forecast2TextField)
                || isTextFieldEmpty(forecast3TextField)
                || isTextFieldEmpty(p1nTextField)
                || isTextFieldEmpty(p1n1TextField)
                || isTextFieldEmpty(p1n2TextField)
                || isTextFieldEmpty(p1n3TextField)
                || isTextFieldEmpty(p2nTextField)
                || isTextFieldEmpty(p2n1TextField)
                || isTextFieldEmpty(p2n2TextField)
                || isTextFieldEmpty(p2n3TextField)
                || isTextFieldEmpty(p3nTextField)
                || isTextFieldEmpty(p3n1TextField)
                || isTextFieldEmpty(p3n2TextField)
                || isTextFieldEmpty(p3n3TextField)
                || isTextFieldEmpty(directSalesAmountP1TextField)
                || isTextFieldEmpty(directSalesAmountP2TextField)
                || isTextFieldEmpty(directSalesAmountP3TextField)
                || isTextFieldEmpty(directSalesPriceP1TextField)
                || isTextFieldEmpty(directSalesPriceP2TextField)
                || isTextFieldEmpty(directSalesPriceP3TextField);
    }

    private boolean isTextFieldEmpty(JFXIntegerTextField textField) {
        return textField.getText().isEmpty();
    }

    private List<Item> storeSellWish() {
        List<Item> sellWish = new ArrayList<>();
        sellWish.add(new Item("1", getIntegerFromTextField(forecast1TextField)));
        sellWish.add(new Item("2", getIntegerFromTextField(forecast2TextField)));
        sellWish.add(new Item("3", getIntegerFromTextField(forecast3TextField)));
        return sellWish;
    }

    private List<DirectSell> storeDirectSells() {
        List<DirectSell> directSells = new ArrayList<>();
        directSells.add(new DirectSell("1", getIntegerFromTextField(directSalesAmountP1TextField),
                getIntegerFromTextField(directSalesPriceP1TextField),
                Integer.valueOf(directSalesPunishmentP1TextField.getText())));
        directSells.add(new DirectSell("2", getIntegerFromTextField(directSalesAmountP2TextField),
                getIntegerFromTextField(directSalesPriceP2TextField),
                Integer.valueOf(directSalesPunishmentP2TextField.getText())));
        directSells.add(new DirectSell("3", getIntegerFromTextField(directSalesAmountP3TextField),
                getIntegerFromTextField(directSalesPriceP3TextField),
                Integer.valueOf(directSalesPunishmentP3TextField.getText())));
        return directSells;
    }

    private Map<String, Item> storeProductionData() {
        Map<String, Item> productionData = new HashMap<>();

        productionData.put("p1n", new Item("1", getIntegerFromTextField(p1nTextField)));
        productionData.put("p1n1", new Item("1", getIntegerFromTextField(p1n1TextField)));
        productionData.put("p1n2", new Item("1", getIntegerFromTextField(p1n2TextField)));
        productionData.put("p1n3", new Item("1", getIntegerFromTextField(p1n3TextField)));

        productionData.put("p2n", new Item("2", getIntegerFromTextField(p2nTextField)));
        productionData.put("p2n1", new Item("2", getIntegerFromTextField(p2n1TextField)));
        productionData.put("p2n2", new Item("2", getIntegerFromTextField(p2n2TextField)));
        productionData.put("p2n3", new Item("2", getIntegerFromTextField(p2n3TextField)));

        productionData.put("p3n", new Item("3", getIntegerFromTextField(p3nTextField)));
        productionData.put("p3n1", new Item("3", getIntegerFromTextField(p3n1TextField)));
        productionData.put("p3n2", new Item("3", getIntegerFromTextField(p3n2TextField)));
        productionData.put("p3n3", new Item("3", getIntegerFromTextField(p3n3TextField)));

        return productionData;
    }

    private XmlInputData initXmlImport() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML Files(*.xml)", "*.xml");
        fileChooser.getExtensionFilters().addAll(filter);
        File selectedFile = fileChooser.showOpenDialog(savedStage);
        XmlInputData xmlInputData = new XmlInputData();
        if (selectedFile != null) {
            xmlInputTextField.setText(selectedFile.getName());
            try {
                if (xmlInputData.checkXMLFile(selectedFile)) {
                    if (!xmlInputData.parseXML(selectedFile)) {
                        logger.info("xml input parse failure");
                        DialogMessages.ErrorDialog(getI18NText(I18N.XML_INPUT_FAILURE_PARSING));
                    }
                } else {
                    logger.info("xml input structure is wrong");
                    DialogMessages.ErrorDialog(getI18NText(I18N.XML_INPUT_ERROR_WRONG_XML_STRUCTURE));
                }
            } catch (IOException e) {
                logger.info(e);
            } catch (ParserConfigurationException e) {
                logger.info(e);
            }
        }
        return xmlInputData;
    }

    @Override
    public void init(MainController main) {
        super.init(main);
        saveForeCastViewButton.getStyleClass().add("button-raised");
    }

    private Integer getIntegerFromTextField(JFXIntegerTextField textfield) {
        return Integer.valueOf(textfield.getText());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.savedStage = primaryStage;
    }

    public void initUIComponents() {
        priceLabel.setText(getI18NText(I18N.PRICE));
        amountLabel.setText(getI18NText(I18N.AMOUNT));
        costLabel.setText(getI18NText(I18N.PUNISHMENT));
        directSellsLabel.setText(getI18NText(I18N.DIRECT_SELLS));
        productionPlanningLabel.setText(getI18NText(I18N.PRODUCTION_PLAN));
        sellingLabel.setText(getI18NText(I18N.SELLING));
        saveForeCastViewButton.setText(getI18NText(I18N.SAVE));
        xmlInputTextField.setText(getI18NText(I18N.XML_INPUT));
    }

    @FXML
    public void importSimulationXml() {
        main.setXmlInputData(initXmlImport());
    }
}

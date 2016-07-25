package de.ibsys.planningTool.controller.tab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.model.xmlExportModel.DirectSell;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.util.JFXIntegerTextField;
import de.ibsys.planningTool.util.Dialogs.DialogMessages;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by minhnguyen on 11.07.16.
 */
public class ForeCastController extends BaseTabController{

    @FXML
    private JFXButton saveForeCastViewButton;

    @FXML
    private JFXIntegerTextField forecast1TextField;
    @FXML
    private JFXIntegerTextField forecast2TextField;
    @FXML
    private JFXIntegerTextField forecast3TextField;
    @FXML
    private JFXIntegerTextField p1nTextField;
    @FXML
    private JFXIntegerTextField p1n1TextField;
    @FXML
    private JFXIntegerTextField p1n2TextField;
    @FXML
    private JFXIntegerTextField p1n3TextField;
    @FXML
    private JFXIntegerTextField p2nTextField;
    @FXML
    private JFXIntegerTextField p2n1TextField;
    @FXML
    private JFXIntegerTextField p2n2TextField;
    @FXML
    private JFXIntegerTextField p2n3TextField;
    @FXML
    private JFXIntegerTextField p3nTextField;
    @FXML
    private JFXIntegerTextField p3n1TextField;
    @FXML
    private JFXIntegerTextField p3n2TextField;
    @FXML
    private JFXIntegerTextField p3n3TextField;

    @FXML
    private JFXIntegerTextField directSalesPriceP1TextField;
    @FXML
    private JFXIntegerTextField directSalesPriceP2TextField;
    @FXML
    private JFXIntegerTextField directSalesPriceP3TextField;
    @FXML
    private JFXIntegerTextField directSalesAmountP1TextField;
    @FXML
    private JFXIntegerTextField directSalesAmountP2TextField;
    @FXML
    private JFXIntegerTextField directSalesAmountP3TextField;
    @FXML
    private JFXTextField directSalesPunishmentP1TextField;
    @FXML
    private JFXTextField directSalesPunishmentP2TextField;
    @FXML
    private JFXTextField directSalesPunishmentP3TextField;

    @FXML
    private void saveForeCastInformation() {
        if (!checkInputViewsContainsData()) {
            System.out.println("store data");
            if (main.getSellWish().size() != 0) {
               main.deleteSellLists();
                storeData();

            } else {
                storeData();
            }

            main.getSellWish().parallelStream().forEach((Item item) -> System.out.println(item.toString()));

        } else {
            DialogMessages.ErrorDialog("Unvollständige Datensätze");
        }
    }

    private void storeData() {
        main.setSellWish(storeSellWish());
        main.setDirectSellList(storeDirectSells());
        main.setForecastProductionList(storeProductionData());
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

    private Map<String, Item> storeProductionData () {
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

    }
}

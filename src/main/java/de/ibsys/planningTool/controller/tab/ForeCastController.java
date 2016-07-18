package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.util.JFXIntegerTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by minhnguyen on 11.07.16.
 */
public class ForeCastController extends BaseTabController{

    public JFXButton saveForeCastViewButton;

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
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}

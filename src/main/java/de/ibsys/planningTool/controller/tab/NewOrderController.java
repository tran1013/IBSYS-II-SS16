package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.model.OrderResult;
import de.ibsys.planningTool.util.Dialogs.DialogMessages;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

/**
 * Created by Admin on 11.09.2016.
 */
public class NewOrderController extends BaseTabController{

    @FXML
    private Label noLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Label optionLabel;

    @FXML
    private JFXTextField quantityTF;

    @FXML
    private JFXComboBox<String> optionCombo;

    @FXML
    private JFXComboBox<String> noCombo;

    @FXML
    private JFXButton okBtn;

    @FXML
    private JFXButton cancelBtn;

    @Autowired
    OrderController orderController;

    @FXML
    public void cancelOrder() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addNewOrder() {

        if(noCombo.getSelectionModel().isEmpty() && optionCombo.getSelectionModel().isEmpty()) {
            DialogMessages.ErrorDialog("ERROR");
        }

        String itemConfigId = noCombo.getSelectionModel().getSelectedItem().toString();
        String deliveryOption = optionCombo.getSelectionModel().getSelectedItem().toString();
        String quantity = quantityTF.getText();
        System.out.println(itemConfigId+" "+deliveryOption);



    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void init(MainController main) {
        super.init(main);
    }


    protected void showNewOrderDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tab/neworder.fxml"));
            AnchorPane pane = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}

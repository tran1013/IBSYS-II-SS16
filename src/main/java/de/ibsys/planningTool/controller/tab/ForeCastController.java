package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.controller.MainController;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by minhnguyen on 11.07.16.
 */
public class ForeCastController extends BaseTabController{

    public JFXButton saveForeCastViewButton;

    @FXML
    private JFXTextField forecast1TextField;

    @FXML
    private JFXTextField forecast2TextField;

    @FXML
    private JFXTextField forecast3TextField;

    @FXML
    private void saveForeCastInformation() {
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}

package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.controller.MainController;
import javafx.fxml.FXML;

/**
 * Created by minhnguyen on 11.07.16.
 */
public class ForeCastController {

    private MainController main;

    public JFXButton saveForeCastViewButton;

    public void init(MainController mainController) {
        main = mainController;
    }

	@FXML
    private JFXTextField forecast1TextField;
    @FXML
    private JFXTextField forecast2TextField;
    @FXML
    private JFXTextField forecast3TextField;

    @FXML
    private void saveForeCastInformation() {
    }

}

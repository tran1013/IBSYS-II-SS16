package planningTool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.text.MessageFormat;

/**
 * Created by minhnguyen on 11.07.16.
 */
public class ForeCastController {

    public JFXButton saveForeCastViewButton;

    @FXML
    private JFXTextField forecast1TextField;
    @FXML
    private JFXTextField forecast2TextField;
    @FXML
    private JFXTextField forecast3TextField;

    @FXML
    public void saveForeCastView() {
        System.out.println("something");
    }
}

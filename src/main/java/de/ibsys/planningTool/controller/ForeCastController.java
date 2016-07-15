package de.ibsys.planningTool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.util.XmlExtractor;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by minhnguyen on 11.07.16.
 */
@SuppressWarnings("restriction")
public class ForeCastController extends Application{

    @Autowired
    XmlExtractor extractor;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public JFXButton saveForeCastViewButton;

	@FXML
    private JFXTextField forecast1TextField;
    @FXML
    private JFXTextField forecast2TextField;
    @FXML
    private JFXTextField forecast3TextField;

    @FXML
    public void saveForeCastView() {

        System.out.println("something" + extractor.getWareHouseArticles().get("1").toString());

    }
}

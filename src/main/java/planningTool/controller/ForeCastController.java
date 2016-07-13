package planningTool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import planningTool.model.Article;
import planningTool.util.XmlExtractor;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by minhnguyen on 11.07.16.
 */
public class ForeCastController extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        List<Article> list = new XmlExtractor().getArticleList();

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
        System.out.println("something");

        List<Article> list = new XmlExtractor().getArticleList();

        for(Article a : list) {
            System.out.println("bla bla " + a.getAmount());
        }

    }
}

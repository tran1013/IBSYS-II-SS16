package planningTool.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import planningTool.model.Article;
import planningTool.util.XmlExtractor;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnguyen on 11.07.16.
 */
public class XmlInputController extends Application {

    private Stage savedStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        savedStage = primaryStage;
    }

    private File f;

    @FXML
    private JFXTextField xmlinputpathTextField;

    @FXML
    public void loadXmlResultData () {
        System.out.println("file sollte hier geoeffnet werden");
        initXmlImport();
    }

    public void initXmlImport() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML file");

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML Files(*.xml)", "*.xml");
        fileChooser.getExtensionFilters().addAll(filter);

        File selectedFile = fileChooser.showOpenDialog(savedStage);

        if (selectedFile != null) {
            String text = selectedFile.getPath();
            String text2 = selectedFile.getName();

            xmlinputpathTextField.setText(text2);

            System.out.println("File \n" + selectedFile);

            try {
                new XmlExtractor().parseXML(selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }

    }
}

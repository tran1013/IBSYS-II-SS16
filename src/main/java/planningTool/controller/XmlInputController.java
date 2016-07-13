package planningTool.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import planningTool.model.Article;
import planningTool.util.Dialogs.DialogMessages;
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
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML Files(*.xml)", "*.xml");
        fileChooser.getExtensionFilters().addAll(filter);
        File selectedFile = fileChooser.showOpenDialog(savedStage);

        if (selectedFile != null) {
            xmlinputpathTextField.setText(selectedFile.getName());
            try {
                if (new XmlExtractor().checkXMLFile(selectedFile)) {
                    new XmlExtractor().parseXML(selectedFile);
                } else {
                    DialogMessages.ErrorDialog("Wrong XML Input");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }

    }
}

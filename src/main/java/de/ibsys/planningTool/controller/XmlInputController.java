package de.ibsys.planningTool.controller;

import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.util.Dialogs.DialogMessages;
import de.ibsys.planningTool.util.XmlExtractor;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by minhnguyen on 11.07.16.
 */
public class XmlInputController extends Application {

    private Stage savedStage;

    @Autowired
    XmlExtractor xmlExtractor;

    @Override
    public void start(Stage primaryStage) throws Exception {
        savedStage = primaryStage;
    }

    @FXML
    private JFXTextField xmlinputpathTextField;

    @FXML
    public void loadXmlResultData () {
        System.out.println("file sollte hier geoeffnet werden");
        initXmlImport();
    }

    @FXML
    public void readData () {
        int i = 0;
        try {
            i = xmlExtractor.getWareHouseArticles().size();
            if(i != 0) {
            	System.out.println("something went right");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("someting" + i);
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

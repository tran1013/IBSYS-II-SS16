package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.util.Dialogs.DialogMessages;
import de.ibsys.planningTool.model.XmlInput;
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
public class XmlInputController extends BaseTabController {


    private Stage savedStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        savedStage = primaryStage;
    }

    @FXML
    private JFXTextField xmlinputpathTextField;

    @FXML
    public void loadXmlResultData () {
        System.out.println("file sollte hier geoeffnet werden");
        main.setXmlInput(initXmlImport());
    }

    @FXML
    public void readData () {
        int i = 0;
        try {
            if(i != main.getXmlInput().getWareHouseArticles().size()) {
            	System.out.println("something went right");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private XmlInput initXmlImport() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML Files(*.xml)", "*.xml");
        fileChooser.getExtensionFilters().addAll(filter);
        File selectedFile = fileChooser.showOpenDialog(savedStage);
        XmlInput xmlInput = new XmlInput();
        if (selectedFile != null) {
            xmlinputpathTextField.setText(selectedFile.getName());
            try {
                if (xmlInput.checkXMLFile(selectedFile)) {
                	xmlInput.parseXML(selectedFile);
                } else {
                    DialogMessages.ErrorDialog("Wrong XML Input");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
        return xmlInput;
    }
}

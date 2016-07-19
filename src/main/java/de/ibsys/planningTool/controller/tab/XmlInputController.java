package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.util.Dialogs.DialogMessages;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by minhnguyen on 11.07.16.
 */
public class XmlInputController extends BaseTabController {

    @FXML
    private JFXButton loadXmlButton;

    private Stage savedStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        savedStage = primaryStage;
    }

    @Override
    public void init(MainController main) {
        super.init(main);
    }

    @FXML
    private JFXTextField xmlinputpathTextField;

    @FXML
    public void loadXmlResultData () {
        System.out.println("file sollte hier geoeffnet werden");
        main.setXmlInputData(initXmlImport());
    }

    @FXML
    public void readData () {
        int i = 0;
        try {
            if(i != main.getXmlInputData().getWareHouseArticles().size()) {
            	System.out.println("something went right");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private XmlInputData initXmlImport() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML Files(*.xml)", "*.xml");
        fileChooser.getExtensionFilters().addAll(filter);
        File selectedFile = fileChooser.showOpenDialog(savedStage);
        XmlInputData xmlInputData = new XmlInputData();
        if (selectedFile != null) {
            xmlinputpathTextField.setText(selectedFile.getName());
            try {
                if (xmlInputData.checkXMLFile(selectedFile)) {
                	xmlInputData.parseXML(selectedFile);
                } else {
                    DialogMessages.ErrorDialog("Wrong XML Input");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
        return xmlInputData;
    }
}

package planningTool.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by minhnguyen on 11.07.16.
 */
public class XmlInputController{

    private File f;

    @FXML
    private JFXTextField xmlinputpathTextField;

    @FXML
    public void loadXmlResultData () {
        System.out.println("file sollte hier geoeffnet werden");
        openFile();
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        
    }
}

package de.ibsys.planningTool.controller.tab;

import de.ibsys.planningTool.model.XmlExport;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by minhnguyen on 17.07.16.
 */
public class ExportController extends BaseTabController{
    private Stage saveStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        saveStage = primaryStage;
    }

    @FXML
    public void exportButtonTapped() {
        System.out.println("Export button Tapped");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Xml Input");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML Files", "*.xml");
        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showSaveDialog(saveStage);

        //TODO Adding the other list in here
        if (file != null) {
            new XmlExport().exportXmlInputData(main.getSellWish(), main.getDirectSellList(), file.getPath());
        }
    }
}

package de.ibsys.planningTool.controller.tab;

import java.io.File;

import com.jfoenix.controls.JFXButton;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.model.XmlExport;
import de.ibsys.planningTool.util.I18N;
import de.ibsys.planningTool.util.MockObject;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by minhnguyen on 17.07.16.
 */
public class ExportController extends BaseTabController{

    private Stage saveStage;

    @FXML
    private JFXButton changeLanguageButton;

    @FXML
    private JFXButton exportButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        saveStage = primaryStage;
    }

    @Override
    public void init(MainController main) {
        super.init(main);
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
            new XmlExport().exportXmlInputData(main.getSellWish()
                    , main.getDirectSellList()
                    , new MockObject().orderListMockData()
                    , new MockObject().productionListMockData()
                    , main.getWorkTimeList()
                    , file.getPath());
        }
    }

    @FXML
    public void changeLanguageButtonTapped() {
        if (main.getLanguage().equals("de")) {
            main.setLanguage("en");
            main.setCountry("US");
        } else {
            main.setLanguage("de");
            main.setCountry("DE");
        }
        main.changeUILanguage();
        initUIComponents();
    }

    public void initUIComponents() {
        changeLanguageButton.setText(main.getTranslation().getString(I18N.CHANGE_LANGUAGE_BUTTON));
        exportButton.setText(main.getTranslation().getString(I18N.XML_EXPORT));
    }

}

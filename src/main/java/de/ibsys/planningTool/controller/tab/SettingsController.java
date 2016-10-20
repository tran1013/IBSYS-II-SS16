package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXButton;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.util.I18N;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by minhnguyen on 17.07.16.
 */
public class SettingsController extends BaseTabController{

    @FXML
    private JFXButton changeLanguageButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @Override
    public void init(MainController main) {
        super.init(main);
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
    }

}

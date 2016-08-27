package de.ibsys.planningTool.util.Dialogs;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.util.I18N;
import javafx.scene.control.Alert;

/**
 * Created by minhnguyen on 13.07.16.
 */
public class DialogMessages {

    private static MainController main;

    public void setMainController(MainController mainController) {
        main = mainController;
    }

    public static void ErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(main.getTranslation().getString(I18N.DIALOG_MESSAGE_ERROR_TITLE));
        alert.setHeaderText(main.getTranslation().getString(I18N.DIALOG_MESSAGE_ERROR_MESSAGE));
        alert.setContentText(message);
        alert.show();
    }

    public static void InfoDialog(String message, String info_message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(main.getTranslation().getString(I18N.DIALOG_MESSAGE_INFO_TITLE));
        alert.setHeaderText(info_message);
        alert.setContentText(message);
        alert.show();
    }
}

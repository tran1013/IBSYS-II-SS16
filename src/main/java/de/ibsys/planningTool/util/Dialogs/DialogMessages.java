package de.ibsys.planningTool.util.Dialogs;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.util.I18N;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

/**
 * dialog message class which contains some dialogs for everyone
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

        ButtonType okButton = new ButtonType(main.getTranslation().getString(I18N.OK), OK_DONE);
        ButtonType cancelButton = new ButtonType(main.getTranslation().getString(I18N.CANCEL), ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(cancelButton, okButton);

        alert.show();
    }
}

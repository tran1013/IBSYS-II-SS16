package de.ibsys.planningTool.util.Dialogs;

import javafx.scene.control.Alert;

/**
 * Created by minhnguyen on 13.07.16.
 */
public class DialogMessages {
    public static void ErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error Message here");
        alert.setContentText(message);
        alert.show();
    }

    public static void InfoDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Information");
        alert.setContentText(message);
        alert.show();
    }
}

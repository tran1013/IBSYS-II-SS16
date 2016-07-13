package planningTool.util.Dialogs;

import javafx.scene.control.Alert;

/**
 * Created by minhnguyen on 13.07.16.
 */
public class DialogMessages {
    public static void ErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(message);
        alert.show();
    }
}

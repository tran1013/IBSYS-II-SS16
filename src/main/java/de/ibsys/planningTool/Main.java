package de.ibsys.planningTool;

import de.ibsys.planningTool.util.XmlExtractor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainWindow.fxml"));
        primaryStage.setTitle("IBSYS_PlanningTool");
        primaryStage.setScene(new Scene(root, 1000, 750));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

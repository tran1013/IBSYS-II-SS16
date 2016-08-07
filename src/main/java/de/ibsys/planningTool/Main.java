package de.ibsys.planningTool;

import de.ibsys.planningTool.service.CapPla;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        primaryStage.setTitle("IBSYS_PlanningTool");
        Scene scene = new Scene(root, 1100, 650);
        scene.getStylesheets().add(getClass().getResource("/stylesheet/stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);

    CapPla cap = new CapPla();
    Map<String, Integer> map = cap.getCapResult();

    System.out.println(Arrays.toString(map.entrySet().toArray()));
    System.out.println("Hope");

    }

}

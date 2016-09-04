package de.ibsys.planningTool;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.database.ItemDB;
import de.ibsys.planningTool.mock.ForeCastMockData;
import de.ibsys.planningTool.mock.MockProductionResult;
import de.ibsys.planningTool.model.ItemComponents;
import de.ibsys.planningTool.model.TermsOfSaleData;
import de.ibsys.planningTool.database.OrderDB;
import de.ibsys.planningTool.controller.tab.OrderController;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.service.OrderService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    }

}

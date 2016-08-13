package de.ibsys.planningTool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

      /*  File file = new File("/Users/Duc/Downloads/resultServlet.xml");

        XmlInputData input = new XmlInputData();
        try {
           *//* input.parseXML(file);
            Map<String, OrdersInWork> map = input.getOrdersInWorkMap();*//*

            CapPla cap = new CapPla();
            cap.getXML();
        }catch (Exception e){
             e.printStackTrace();
            }

*/

    }

}

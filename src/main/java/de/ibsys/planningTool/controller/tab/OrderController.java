package de.ibsys.planningTool.controller.tab;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.model.OrderResult;
import de.ibsys.planningTool.model.TermsOfSaleData;
import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.service.OrderService;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.Map;

/**
 * Created by Che on 18.08.2016.
 */
public class OrderController extends BaseTabController{

    @FXML
    private TableView<OrderResult> orderColumn;

    @FXML
    private TableColumn<OrderResult, ?> nrColumn;

    @FXML
    private TableColumn<?, ?> quantityColumn;

    @FXML
    private TableColumn<?, ?> optionColumn;

    @FXML
    private JFXTextField quantatityTF;

    @FXML
    private Label bestandLabel;

    @FXML
    private Label verbrauchLabel;

    @FXML
    private Label discontLabel;

    @FXML
    private Label nrLabel;

    @FXML
    private JFXCheckBox expressCB;

    @FXML
    private JFXButton newBtn;

    @FXML
    private JFXButton changeBtn;

    @FXML
    private JFXButton deleteBtn;

    OrderService orderService = new OrderService();
    MainController main = new MainController();
    XmlInputData inputData = new XmlInputData();

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public double calculateStockRange(List<Map<String, Integer>> kUsageList, TermsOfSaleData terms) {
        double stockRange = 0.0;
        String itemConfigId = terms.getItemConfigId();
        //double maxTime = calculateMaxDeliveryTime(terms.getItemConfigId());
        double avg = orderService.calculateAverage(kUsageList, terms.getItemConfigId());
        //double stock = inputData.getWareHouseArticles().get(itemConfigId).getAmount();
        //double stock = main.getXmlInputData().getWareHouseArticles().get(itemConfigId).getStockValue();

        //stockRange = Math.round(stock/avg);
        System.out.println("AVG IN ST" + avg);
        return stockRange;

    }

}

package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXButton;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.ChildBikeController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.MenBikeController;
import de.ibsys.planningTool.controller.tab.productionOrderTab.WomenBikeController;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.model.xmlInputModel.WaitingList;
import de.ibsys.planningTool.service.ProductionService;
import de.ibsys.planningTool.util.I18N;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by minhnguyen on 22.09.16.
 */
public class ProductionController extends BaseTabController {

    private MainController mainController;

    @FXML
    ChildBikeController childBikeController;

    @FXML
    MenBikeController menBikeController;

    @FXML
    WomenBikeController womenBikeController;

    @FXML
    Tab childBikeTab;

    @FXML
    Tab menBikeTab;

    @FXML
    Tab womanBikeTab;

    @FXML
    public JFXButton saveBtn;

    private String language = "de";
    private String country = "DE";

    private ResourceBundle translation;

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @Override
    public void init(MainController main) {
        super.init(main);
        mainController = main;
        childBikeController.init(this);
        menBikeController.init(this);
        womenBikeController.init(this);
        initUI();
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public ResourceBundle getTranslation() {
        return main.getTranslation();
    }

    @FXML
    public void saveBtnPressed() {
        initProductionControllerCalculation();
    }

    public void initUI() {
        translation = I18N.translation(language, country);

        childBikeTab.setText(main.getTranslation().getString(I18N.CHILD_BIKE));
        womanBikeTab.setText(main.getTranslation().getString(I18N.WOMAN_BIKE));
        menBikeTab.setText(main.getTranslation().getString(I18N.MEN_BIKE));
        saveBtn.setText(main.getTranslation().getString(I18N.CALC_N_SAVE));

        childBikeController.initUI();
        womenBikeController.initUI();
        menBikeController.initUI();
    }

    public void initProductionControllerCalculation() {
        try {
            childBikeController.storeNewReserve();
            menBikeController.storeNewReserve();
            womenBikeController.storeNewReserve();
            childBikeController.initUIComponents();
            menBikeController.initUIComponents();
            womenBikeController.initUIComponents();
            childBikeController.initUI();
            initUI();

            List<Item> result = new ArrayList<>();

            result.clear();

            result = new ProductionService().calculate( // do calculation and reduce 
                    Stream.concat(childBikeController.setList().parallelStream(), //
                            Stream.concat(menBikeController.setList().parallelStream(), //
                                    womenBikeController.setList().parallelStream() //
                            ).collect(Collectors.toList()).parallelStream()) // get all list from men and women in one list
                            .collect(Collectors.toList())) // get all list form views together
                    .parallelStream()
//                    .sorted((item1, item2) -> Integer.valueOf(item1.getArticleId()) // sorted things
//                            .compareTo(Integer.valueOf(item2.getArticleId()))) //
                    .collect(Collectors.toList()); // return a list

            getMainController().setProductionList(new ProductionService().getRightOrder(result));

            List<Item> caPlaResult = new ArrayList<>();

            getMainController().getProductionList().parallelStream().forEach(item -> {
                caPlaResult.add(new Item(item.getArticleId(), item.getQuantity() + getWaitingListPartsAmount(item.getArticleId())));
            });

            getMainController().setProdcutionListCapla(caPlaResult);

            main.initWorkThings();
            main.orderController.getData();
            setUI();
            mainController.getProductionPriorityController().initFunctions();
            logger.info("save new stuff successfull");
        } catch (NullPointerException e) {
            logger.info(e);
        }
    }

    // get Production list which are on the machines
    protected int getWaitingListPartsAmount(String code) {
//        return mainController.getXmlInputData().getStringWaitingListMissingPartsMap().entrySet().parallelStream()
//                .filter(predicate -> predicate.getValue().getMissingPartsId().equals(code))
//                .mapToInt(waitingList -> waitingList.getValue().getWaitingLists().parallelStream()
//                        .mapToInt(WaitingList::getAmount).sum())
//                .sum();

        return mainController.getXmlInputData().getStringWaitingListMissingPartsMap().entrySet().parallelStream().mapToInt(value ->
                value.getValue().getWaitingLists().parallelStream().filter(waitingList -> waitingList.getArticleId().equals(code))
                        .mapToInt(WaitingList::getAmount)
                        .sum()
        ).sum();
    }


    public void setUI() {
        main.cappla.setDisable(false);
        main.orderTab.setDisable(false);
        main.productionpriorityTab.setDisable(false);
        main.exportButton.setVisible(true);
    }
}

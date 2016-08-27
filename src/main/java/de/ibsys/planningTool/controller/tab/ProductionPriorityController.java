package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXListView;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.util.MockObject;
import de.ibsys.planningTool.util.PriorityCell;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * TODO Refactor all this cell stuff in the cell class
 * Created by minhnguyen on 26.08.16.
 */
public class ProductionPriorityController extends BaseTabController {

    Logger logger = Logger.getLogger(ProductionPriorityController.class.getSimpleName());

    @FXML
    private JFXListView listView;

    //TODO REPLACE THIS SHIT
    private List<Item> mockItems = new MockObject().productionListMockData();

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @Override
    public void init(MainController main) {
        super.init(main);

        logger.info(mockItems.toString());

        final IntegerProperty dragFromIndex = new SimpleIntegerProperty(-1);
        ObservableList<Item> productionList = FXCollections.observableArrayList(mockItems);
        listView.setItems(productionList);
        listView.setCellFactory(itemListView -> {
            PriorityCell cell = new PriorityCell(productionList);

            cell.setOnDragDetected(event -> {
                if (!cell.isEmpty()) {
                    dragFromIndex.set(cell.getIndex());
                    Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent cc = new ClipboardContent();
                    cc.putString(String.valueOf(cell.getItem()));
                    db.setContent(cc);
                    db.setDragView(cell.snapshot(null, null));
                }
            });

            cell.setOnDragOver(event -> {
                if (dragFromIndex.get() >= 0 && dragFromIndex.get() != cell.getIndex()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
            });

            cell.setOnDragEntered(event -> {
                if (dragFromIndex.get() >= 0 && dragFromIndex.get() != cell.getIndex()) {
                    cell.setOpacity(0.3);
                }
            });

            cell.setOnDragExited(event -> {
                cell.setOpacity(1);
            });

            cell.setOnDragDropped(event -> {

                int dragItemsStartIndex;
                int dragItemsEndIndex;
                int direction;
                if (cell.isEmpty()) {
                    dragItemsStartIndex = dragFromIndex.get();
                    dragItemsEndIndex = listView.getItems().size();
                    direction = -1;
                } else {
                    if (cell.getIndex() < dragFromIndex.get()) {
                        dragItemsStartIndex = cell.getIndex();
                        dragItemsEndIndex = dragFromIndex.get() + 1;
                        direction = 1;
                    } else {
                        dragItemsStartIndex = dragFromIndex.get();
                        dragItemsEndIndex = cell.getIndex() + 1;
                        direction = -1;
                    }
                }

                List<Item> rotatingItems = listView.getItems().subList(dragItemsStartIndex, dragItemsEndIndex);
                logger.info(rotatingItems);

                List<Item> rotatingItemsCopy = new ArrayList<>(rotatingItems);
                logger.info(rotatingItemsCopy);

                Collections.rotate(rotatingItemsCopy, direction);
                rotatingItems.clear();
                rotatingItems.addAll(rotatingItemsCopy);

                mockItems.clear();

                Arrays.stream(listView.getItems().toArray()).forEach(item -> {
                    mockItems.add((Item) item);
                });

                dragFromIndex.set(-1);
            });

            cell.setOnDragDone(event -> {
                dragFromIndex.set(-1);
                listView.getSelectionModel().select(event.getDragboard().getString());
            });

            return cell;
        });
    }
}

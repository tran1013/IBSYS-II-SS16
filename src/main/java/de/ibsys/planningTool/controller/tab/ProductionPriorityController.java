package de.ibsys.planningTool.controller.tab;

import com.jfoenix.controls.JFXListView;
import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import de.ibsys.planningTool.util.Dialogs.DialogMessages;
import de.ibsys.planningTool.util.MockObject;
import de.ibsys.planningTool.util.PriorityCell;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * TODO Refactor all this cell stuff in the cell class
 * Created by minhnguyen on 26.08.16.
 */
public class ProductionPriorityController extends BaseTabController {


    @FXML
    private JFXListView listView;

    /***
     * TODO REPLACE THIS WITH THE RIGHT DATA
     * DO IT IN THE INIT
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @Override
    public void init(MainController main) {
        super.init(main);
        main.setProductionList(new MockObject().productionListMockData());
        final IntegerProperty dragFromIndex = new SimpleIntegerProperty(-1);
        ObservableList<Item> productionList = FXCollections.observableArrayList(main.getProductionList());
        listView.setItems(productionList);
        listView.setCellFactory(itemListView -> dragAndDrop(dragFromIndex, productionList));
    }

    /**
     * Set up for Drag and drop for the list view
     *
     * @param dragFromIndex
     * @param productionList
     * @return cells
     */
    private Object dragAndDrop(IntegerProperty dragFromIndex, ObservableList<Item> productionList) {
        PriorityCell cell = new PriorityCell(productionList);

        /**
         * Detection if the user want to drag and drop something
         */
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

        /**
         * change index of the elments
         * and overwrite the current lists
         */
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
            List<Item> rotatingItemsCopy = new ArrayList<>(rotatingItems);
            Collections.rotate(rotatingItemsCopy, direction);
            rotatingItems.clear();
            rotatingItems.addAll(rotatingItemsCopy);

            updateProductionList();

            dragFromIndex.set(-1);
        });

        cell.setOnDragDone(event -> {
            dragFromIndex.set(-1);
            listView.getSelectionModel().select(event.getDragboard().getString());
        });

        cell.setOnMouseClicked(event -> {
            MouseButton mouseButton = event.getButton();
            if (mouseButton.equals(MouseButton.SECONDARY)) {
                logger.info("Right Click happens");

                logger.info(listView.getItems().get(cell.getIndex()));

                Item item = (Item) listView.getItems().get(cell.getIndex());

                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Split things up");
                dialog.setHeaderText("quantity = " + item.getQuantity());
                dialog.setContentText("Please put in a number");


                dialog.showAndWait().ifPresent(number -> {
                    try {
                        if (Integer.valueOf(number) > 0) {
                            int tmpResult = item.getQuantity() - Integer.valueOf(number);
                            ((Item) listView.getItems().get(cell.getIndex())).setQuantity(tmpResult);
                            Item splitItem = new Item(item.getArticleId(), Integer.valueOf(number));
                            listView.getItems().add(splitItem);
                            updateProductionList();
                        } else {
                            DialogMessages.InfoDialog("Bitte nur ganze zahlen eingeben");
                        }
                    } catch (Exception e) {
                        DialogMessages.ErrorDialog("Bitte nur Zahlen eingeben");
                        logger.error("INFO",e);
                    }
                });
            }
        });

        return cell;
    }

    private void updateProductionList() {
        List<Item> updatedProductionList = new ArrayList<>();
        main.getProductionList().clear();
        Arrays.stream(listView.getItems().toArray()).forEach(results -> {
            updatedProductionList.add((Item) results);
        });
        main.setProductionList(updatedProductionList);
    }
}

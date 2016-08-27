package de.ibsys.planningTool.util;

import com.jfoenix.controls.JFXListCell;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * custom list view for priorities
 * Created by minhnguyen on 26.08.16.
 */
public class PriorityCell extends JFXListCell<Item> implements ChangeListener<Number> {

    private Logger logger = Logger.getLogger(PriorityCell.class.getSimpleName());

    @FXML
    private Label item_id;

    @FXML
    private Label item_quantity;

    @FXML
    private GridPane gridPane_priority;

    private ObservableList<Item> items;

    private FXMLLoader mLLoader;

    private IntegerProperty dragFromIndex = new SimpleIntegerProperty(-1);


    public PriorityCell(ObservableList<Item> items) {

        this.items = items;

//        JFXListCell cell = this;
//
//        setOnDragDetected(event -> {
//            if (getItem().equals(null)) {
//                return;
//            }
//            dragFromIndex.set(cell.getIndex());
//
//            Dragboard dragboard = cell.startDragAndDrop(TransferMode.MOVE);
//
//            ClipboardContent clipboardContent = new ClipboardContent();
//            clipboardContent.putString(String.valueOf(cell.getItem()));
//
//            dragboard.setContent(clipboardContent);
//
//            dragboard.setDragView(cell.snapshot(null, null));
//            event.consume();
//        });
//
//        setOnDragOver(event -> {
//            if (event.getGestureSource() != cell &&
//                    event.getDragboard().hasString()) {
//                event.acceptTransferModes(TransferMode.MOVE);
//            }
//            event.consume();
//        });
//
//        setOnDragEntered(event -> {
//            if (event.getGestureSource() != cell &&
//                    event.getDragboard().hasString()) {
//                setOpacity(0.3);
//            }
//        });
//
//        setOnDragExited(event -> {
//            if (event.getGestureSource() != cell &&
//                    event.getDragboard().hasString()) {
//                setOpacity(1);
//            }
//        });
//
//        setOnDragDropped(event -> {
//
//            if (getItem() == null) {
//                return;
//            }
//
//            Dragboard dragboard = event.getDragboard();
//
//            boolean success = false;
//            if (dragboard.hasString()) {
//                ObservableList<Item> tmpList = getListView().getItems();
////
////                String tmpDraggedIndex = dragboard.getString();
////                int thisIndex = tmpList.indexOf(getItem());
////
////                logger.info("Dragged Index = " + tmpDraggedIndex + " This index = " + thisIndex);
////                logger.info("Dragged Index Item = " + tmpDraggedIndex + " index item = " + tmpList.get(thisIndex));
//
//                int dragItemsStartIndex;
//                int dragItemsEndIndex;
//                int direction;
//
//
//                if (cell.isEmpty()) {
//                    dragItemsStartIndex = dragFromIndex.get();
//                    dragItemsEndIndex = getListView().getItems().size();
//                    direction = -1;
//                } else {
//                    if (cell.getIndex() < dragFromIndex.get()) {
//                        dragItemsStartIndex = cell.getIndex();
//                        dragItemsEndIndex = dragFromIndex.get() + 1;
//                        direction = 1;
//                    } else {
//                        dragItemsStartIndex = dragFromIndex.get();
//                        dragItemsEndIndex = cell.getIndex() + 1;
//                        direction = -1;
//                    }
//                    logger.info("start " + dragItemsStartIndex + " stop " + dragItemsEndIndex);
//                }
//
////                List<Item> rotatingItems = getListView().getItems().subList(dragItemsStartIndex, dragItemsEndIndex);
////                List<Item> rotatingItemsCopy = new ArrayList<Item>(rotatingItems);
////                Collections.rotate(rotatingItemsCopy, direction);
////                rotatingItems.clear();
////                rotatingItems.addAll(rotatingItemsCopy);
////                dragFromIndex.set(-1);
//
//                success = true;
//            }
//
//            event.setDropCompleted(success);
//            event.consume();
//        });
//
//        setOnDragDone(DragEvent::consume);
    }

    @Override
    public void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/cells/prioritycell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            item_id.setText(item.getArticleId());
            item_quantity.setText(String.valueOf(item.getQuantity()));

            setText(null);
            setGraphic(gridPane_priority);
        }
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public void setItems(ObservableList<Item> items) {
        this.items = items;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

    }
}
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


    public Label getItem_id() {
        return item_id;
    }

    public void setItem_id(Label item_id) {
        this.item_id = item_id;
    }

    public Label getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(Label item_quantity) {
        this.item_quantity = item_quantity;
    }

    public PriorityCell(ObservableList<Item> items) {
        this.items = items;
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
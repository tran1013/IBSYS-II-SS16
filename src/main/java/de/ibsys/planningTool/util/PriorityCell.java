package de.ibsys.planningTool.util;

import com.jfoenix.controls.JFXListCell;
import de.ibsys.planningTool.model.xmlExportModel.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * custom list view for priorities
 * Created by minhnguyen on 26.08.16.
 */
public class PriorityCell extends JFXListCell<Item> {
    @FXML
    private Label item_id;

    @FXML
    private Label item_quantity;

    @FXML
    private GridPane gridPane_priority;

    private FXMLLoader mLLoader;

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
}

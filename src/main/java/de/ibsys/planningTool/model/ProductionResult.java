package de.ibsys.planningTool.model;

/**
 * Created by Che on 16.08.2016.
 */
public class ProductionResult {
    private String itemConfigId;
    private int quantity;

    public ProductionResult() {}

    public ProductionResult(String itemConfigId, int quantity) {
        this.itemConfigId = itemConfigId;
        this.quantity = quantity;
    }

    public String getItemConfigId() {
        return itemConfigId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item Components{" +
                "itemConfigId=" + itemConfigId +
                ", quantity=" + quantity +
                '}';
    }
}

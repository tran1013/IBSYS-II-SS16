package de.ibsys.planningTool.model;

/**
 * Created by Che on 16.08.2016.
 */
public class OrderResult {

    private int orderingMode;
    private int deliveryCosts;
    private int discountQuantity;
    private double deliveryTime;
    private double variance;

    public OrderResult() {}

    public OrderResult(String itemConfigId, int quantity, int orderingMode, int deliveryCosts,
                       int discountQuantity, double deliveryTime, double variance) {

        this.itemConfigId = itemConfigId;
        this.quantity = quantity;
        this.orderingMode = orderingMode;
        this.deliveryCosts = deliveryCosts;
        this.discountQuantity = discountQuantity;
        this.deliveryTime = deliveryTime;
        this.variance = variance;
    }

    private String itemConfigId;
    private int quantity;

    public String getItemConfigId() {
        return itemConfigId;
    }

    public void setItemConfigId(String itemConfigId) {
        this.itemConfigId = itemConfigId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderingMode() {
        return orderingMode;
    }

    public void setOrderingMode(int orderingMode) {
        this.orderingMode = orderingMode;
    }

    public int getDeliveryCosts() {
        return deliveryCosts;
    }

    public void setDeliveryCosts(int deliveryCosts) {
        this.deliveryCosts = deliveryCosts;
    }

    public int getDiscountQuantity() {
        return discountQuantity;
    }

    public void setDiscountQuantity(int discountQuantity) {
        this.discountQuantity = discountQuantity;
    }

    public double getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(double deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public double getVariance() {
        return variance;
    }


    public void setVariance(double variance) {
        this.variance = variance;
    }
    @Override
    public String toString() {
        return "OrderResult{" +
                "itemConfigId=" + itemConfigId +
                ", quantity=" + quantity +
                ", orderingMode=" + orderingMode +
                ", deliveryCosts=" + deliveryCosts +
                ", discountQuantity=" + discountQuantity +
                ", deliveryTime=" + deliveryTime +
                ", variance=" + variance +
                '}';
    }

}

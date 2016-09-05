package de.ibsys.planningTool.model;

import java.util.Objects;
/**
 * Created by Che on 16.08.2016.
 */
public class TermsOfSaleData {

    private String itemConfigId;
    private double deliveryTime;
    private double variance;
    private int orderingCosts;
    private int discountQuantity;

    public TermsOfSaleData() {}

    public TermsOfSaleData(String itemConfigId, double deliveryTime, double variance, int orderingCosts, int discountQuantity) {
        this.itemConfigId = itemConfigId;
        this.deliveryTime = deliveryTime;
        this.variance = variance;
        this.orderingCosts = orderingCosts;
        this.discountQuantity = discountQuantity;
    }

    public String getItemConfigId() {
        return itemConfigId;
    }

    public void setItemConfigId(String itemConfigId) {
        this.itemConfigId = itemConfigId;
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

    public int getOrderingCosts() {
        return orderingCosts;
    }

    public void setOrderingCosts(int orderingCosts) {
        this.orderingCosts = orderingCosts;
    }

    public int getDiscountQuantity() {
        return discountQuantity;
    }

    public void setDiscountQuantity(int discountQuantity) {
        this.discountQuantity = discountQuantity;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(obj == this) {
            return true;
        }

        if(!(obj instanceof TermsOfSaleData)) {
            return false;
        }

        TermsOfSaleData termsOfSaleConfig = (TermsOfSaleData) obj;
        return Objects.equals(itemConfigId, termsOfSaleConfig.getItemConfigId());
    }

    @Override
    public String toString() {
        return "Terms of Sale Data{"
                + "itemId=" + itemConfigId
                + ", deliveryTime=" + deliveryTime
                + ", variance" + variance
                + ", orderingCosts" + orderingCosts
                + ", discountQuantity" + discountQuantity +
                '}';
    }

}

package de.ibsys.planningTool.model;

import java.util.Objects;

/**
 * Created by Duc on 14.07.16.
 */
public class ProductionSteps {

    private Integer id;
    private Integer workplaceID;
    private String itemConfigID;
    private int productionTime;
    private int setupTime;
    private Integer preProductionStepConfigID;

    public ProductionSteps() {

    }

    public ProductionSteps(Integer id, Integer workplaceID, String itemConfigID, int productionTime, int setupTime, Integer preProductionStepConfigID) {
        this.id = id;
        this.workplaceID = workplaceID;
        this.itemConfigID = itemConfigID;
        this.productionTime = productionTime;
        this.setupTime = setupTime;
        this.preProductionStepConfigID = preProductionStepConfigID;
    }

    public ProductionSteps(Integer id, Integer workplaceID, String itemConfigID, int productionTime, int setupTime) {
        this.id = id;
        this.workplaceID = workplaceID;
        this.itemConfigID = itemConfigID;
        this.productionTime = productionTime;
        this.setupTime = setupTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getWorkplaceID() {
        return workplaceID;
    }

    public String getItemConfigID() {
        return itemConfigID;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public int getSetupTime() {
        return setupTime;
    }

    public Integer getPreProductionStepConfigID() {
        return preProductionStepConfigID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ProductionSteps)) {
            return false;
        }

        ProductionSteps productionSteps = (ProductionSteps) obj;
        return Objects.equals(id, productionSteps.getId()) &&
                Objects.equals(workplaceID, productionSteps.getWorkplaceID()) &&
                Objects.equals(itemConfigID, productionSteps.getItemConfigID());
    }


    @Override
    public String toString() {
        return "ProductionSteps{" +
                "id=" + id +
                ", workplaceID=" + workplaceID +
                ", itemConfigID='" + itemConfigID + '\'' +
                ", productionTime=" + productionTime +
                ", setupTime=" + setupTime +
                ", preProductionStepConfigID=" + preProductionStepConfigID +
                '}';
    }
}

package de.ibsys.planningTool.model.xmlExportModel;

/**
 * Created by minhnguyen on 18.07.16.
 */
public class Order extends Item{
    private int modus;

    public Order(String articleId, int quantity, int modus) {
        super(articleId, quantity);
        this.modus = modus;
    }

    public int getModus() {
        return modus;
    }

    public void setModus(int modus) {
        this.modus = modus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "modus=" + modus +
                '}';
    }
}

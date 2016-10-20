package de.ibsys.planningTool.model.xmlExportModel;

/**
 * Created by minhnguyen on 18.07.16.
 */
public class DirectSell extends Item {

    private int price;
    private int penalty;

    public DirectSell(String articleId, int quantity, int price, int penalty) {
        super(articleId, quantity);
        this.price = price;
        this.penalty = penalty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "DirectSell{" +
                "price=" + price +
                ", penalty=" + penalty +
                '}';
    }
}

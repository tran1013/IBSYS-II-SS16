package de.ibsys.planningTool.model.xmlInputModel;

/**
 * Created by minhnguyen on 13.07.16.
 */
public class Article {
    private int id;
    private int amount;
    private int reserve;
    private double pct;
    private double price;
    private double stockValue;

    public Article(int id, int amount, int reserve, double pct, double price, double stockValue) {
        this.id = id;
        this.amount = amount;
        this.reserve = reserve;
        this.pct = pct;
        this.price = price;
        this.stockValue = stockValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getReserve() {
        return reserve;
    }

    public void setReserve(int reserve) {
        this.reserve = reserve;
    }

    public double getPct() {
        return pct;
    }

    public void setPct(double pct) {
        this.pct = pct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStockValue() {
        return stockValue;
    }

    public void setStockValue(double stockValue) {
        this.stockValue = stockValue;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", amount=" + amount +
                ", reserve=" + reserve +
                ", pct=" + pct +
                ", price=" + price +
                ", stockValue=" + stockValue +
                '}';
    }
}

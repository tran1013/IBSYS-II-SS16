package de.ibsys.planningTool.model;

/**
 * Created by minhnguyen on 13.07.16.
 */
public class FutureInComingOrder {

    private String id;
    private String articleId;
    private int mode;
    private int orderPeriode;
    private int amount;

    public FutureInComingOrder(String id, String articleId, int mode, int orderPeriode, int amount) {
        this.id = id;
        this.articleId = articleId;
        this.mode = mode;
        this.orderPeriode = orderPeriode;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getOrderPeriode() {
        return orderPeriode;
    }

    public void setOrderPeriode(int orderPeriode) {
        this.orderPeriode = orderPeriode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FutureInComingOrder{" +
                "id='" + id + '\'' +
                ", articleId='" + articleId + '\'' +
                ", mode=" + mode +
                ", orderPeriode=" + orderPeriode +
                ", amount=" + amount +
                '}';
    }
}

package de.ibsys.planningTool.model.xmlInputModel;

/**
 * Created by minhnguyen on 13.07.16.
 */
public class OrdersInWork {
    private String id;
    private String articleId;
    private int periode;
    private int order;
    private int amount;
    private int timeNecessary;

    public OrdersInWork(String id, String articleId, int amount, int timeNecessary) {
        this.id = id;
        this.articleId = articleId;
        this.amount = amount;
        this.timeNecessary = timeNecessary;
    }

    public OrdersInWork(String id, String articleId, int periode, int order, int amount, int timeNecessary) {
        this.id = id;
        this.articleId = articleId;
        this.periode = periode;
        this.order = order;
        this.amount = amount;
        this.timeNecessary = timeNecessary;
    }

    @Override
    public String toString() {
        return "OrdersInWork{" +
                "id='" + id + '\'' +
                ", articleId='" + articleId + '\'' +
                ", periode=" + periode +
                ", order=" + order +
                ", amount=" + amount +
                ", timeNecessary=" + timeNecessary +
                '}';
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

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTimeNecessary() {
        return timeNecessary;
    }

    public void setTimeNecessary(int timeNecessary) {
        this.timeNecessary = timeNecessary;
    }
}

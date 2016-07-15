package de.ibsys.planningTool.model;

/**
 * Master Object for WaitingList --> Parts
 * Created by minhnguyen on 13.07.16.
 */
public class WaitingList {
    //PART ID
    private String articleId;
    private int amount;
    private int necessaryTime;
    private int period;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getNecessaryTime() {
        return necessaryTime;
    }

    public void setNecessaryTime(int necessaryTime) {
        this.necessaryTime = necessaryTime;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public WaitingList(String articleId, int amount, int necessaryTime) {

        this.articleId = articleId;
        this.amount = amount;
        this.necessaryTime = necessaryTime;
    }

    public WaitingList(String articleId, int amount, int necessaryTime, int period) {

        this.articleId = articleId;
        this.amount = amount;
        this.necessaryTime = necessaryTime;
        this.period = period;
    }

    @Override
    public String toString() {
        return "WaitingList{" +
                "articleId='" + articleId + '\'' +
                ", amount=" + amount +
                ", necessaryTime=" + necessaryTime +
                ", period=" + period +
                '}';
    }
}

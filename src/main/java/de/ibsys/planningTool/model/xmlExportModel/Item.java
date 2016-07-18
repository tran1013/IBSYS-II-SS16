package de.ibsys.planningTool.model.xmlExportModel;

/**
 * Created by minhnguyen on 18.07.16.
 */
public class Item {

    private String articleId;
    private int quantity;

    public Item(String articleId, int quantity) {
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "articleId='" + articleId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

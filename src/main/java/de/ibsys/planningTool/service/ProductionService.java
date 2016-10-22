package de.ibsys.planningTool.service;

import java.util.ArrayList;
import java.util.List;

import de.ibsys.planningTool.model.xmlExportModel.Item;

/**
 * Created by Duc on 28.09.16. Ansatz is komplett falsch
 */
public class ProductionService {

    private String orderList[] = {
            "7", "13", "18", "8", "14", "19", "9", "15", "20", // Parts First
            "4", "10", "49", "5", "11", "54", "6", "12", "29", // Parts Second
            "16", "17", "50", "55", "30", // Parts Third
            "26", "51", "56", "31", // Parts Fourth
            "1", "2", "3" // Last Step
    };

    public List<Item> calculate(List<Item> production) {

        int tmp16 = production.parallelStream().filter(predicate -> predicate.getArticleId().equals("16"))
                .mapToInt(Item::getQuantity).sum();
        int tmp17 = production.parallelStream().filter(predicate -> predicate.getArticleId().equals("17"))
                .mapToInt(Item::getQuantity).sum();
        int tmp26 = production.parallelStream().filter(predicate -> predicate.getArticleId().equals("26"))
                .mapToInt(Item::getQuantity).sum();

        List<Item> tmpList = new ArrayList<>();

        production.stream().filter(item -> item.getQuantity() > 0).forEach(value -> {
            String article = value.getArticleId();
            switch (article) {
                case "16":
                    break;
                case "17":
                    break;
                case "26":
                    break;
                default:
                    int tmp = value.getQuantity() > 0 ? value.getQuantity() : 0;
                    tmpList.add(new Item(article, tmp));
                    break;
            }
        });

        if (tmp16 > 0) {
            tmpList.add(new Item("16", tmp16));
        }

        if (tmp17 > 0) {
            tmpList.add(new Item("17", tmp17));
        }

        if (tmp26 > 0) {
            tmpList.add(new Item("26", tmp26));
        }

        return tmpList;
    }

    /**
     *
     * @param production
     * @return sorted production list like orderlist
     */
    public List<Item> getRightOrder(List<Item> production) {
        List<Item> sortedProductionList = new ArrayList<>();

        for (int i = 0; i < orderList.length; i++) {
            final int tmp = i;
            production.stream().filter(item -> item.getArticleId().equals(orderList[tmp])).forEach(sortedProductionList::add);
        }
        return sortedProductionList;
    }
}

package de.ibsys.planningTool.service;

import java.util.ArrayList;
import java.util.List;

import de.ibsys.planningTool.model.xmlExportModel.Item;

/**
 * Created by Duc on 28.09.16. Ansatz is komplett falsch
 */
public class ProductionService {

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

        if (tmp16 > 0 ) {
        	tmpList.add(new Item("16", tmp16 > 0 ? tmp16 : 0));
        }
        
        if (tmp17 > 0 ) {
        	tmpList.add(new Item("17", tmp17 > 0 ? tmp17 : 0));
        }
        
        if (tmp26 > 0) {
        	tmpList.add(new Item("26", tmp26 > 0 ? tmp26 : 0));
        }

        return tmpList;
    }
}

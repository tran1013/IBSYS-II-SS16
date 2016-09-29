package de.ibsys.planningTool.service;

import java.util.ArrayList;
import java.util.List;

import de.ibsys.planningTool.model.xmlExportModel.Item;

/**
 * Created by Duc on 28.09.16. Ansatz is komplett falsch
 */
public class Dispo {

	public List<Item> calculate(List<Item> production) {

		int tmp16 = production.parallelStream().filter(predicate -> predicate.getArticleId().equals("16"))
				.mapToInt(mapper -> mapper.getQuantity()).sum();
		int tmp17 = production.parallelStream().filter(predicate -> predicate.getArticleId().equals("17"))
				.mapToInt(mapper -> mapper.getQuantity()).sum();
		int tmp26 = production.parallelStream().filter(predicate -> predicate.getArticleId().equals("26"))
				.mapToInt(mapper -> mapper.getQuantity()).sum();

		List<Item> tmpList = new ArrayList<>();

		production.stream().forEach(value -> {
			String article = value.getArticleId();
			switch (article) {
			case "16":
				break;
			case "17":
				break;
			case "26":
				break;
			default:
				tmpList.add(value);
				break;
			}
		});

		tmpList.add(new Item("16", tmp16));
		tmpList.add(new Item("17", tmp17));
		tmpList.add(new Item("26", tmp26));

		return tmpList;
	}

}
